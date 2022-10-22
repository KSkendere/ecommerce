import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {ShopFormService} from "../../services/shop-form.service";
import {Country} from "../../models/country";
import {State} from "../../models/state";
import {CustomValidators} from "../../validators/custom-validators";
import {CartService} from "../../services/cart.service";
import {CheckoutService} from "../../services/checkout.service";
import {Router} from "@angular/router";
import {Purchase} from "../../models/purchase";
import {CartItem} from "../../models/cart-item";
import {Order} from "../../models/order";
import {OrderItem} from "../../models/order-item";
import {Address} from "../../models/address";

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

  checkoutFormGroup!: FormGroup;
  totalPrice: number = 0.00;
  totalQuantity: number = 0;

  creditCardMonth: number[] = [];
  creditCardYears: number[] = [];

  countries: Country[]=[];
  shippingAddressStates: State[]=[];
  billingAddressStates: State[]=[];

  constructor(private formBuilder: FormBuilder, private shopFormService: ShopFormService, private cartService: CartService,
  private checkoutService: CheckoutService, private router: Router ) {}

  ngOnInit(): void {

    this.reviewCartDetails();
//populate credit card years
    this.getCreditCardYears();
//populate credit card month
    const startMonth: number = new Date().getMonth() + 1;
    this.getCreditCardMonth(startMonth);

    this.getCountries();

    this.checkoutFormGroup = this.formBuilder.group({
      customer: this.formBuilder.group({
        firstName: new FormControl("",[Validators.required, Validators.minLength(2),CustomValidators.notOnlyWhiteSpace]),
        lastName: new FormControl("",[Validators.required, Validators.minLength(2),CustomValidators.notOnlyWhiteSpace]),
        email: new FormControl("",[Validators.required,
          Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")]),
      }),
      shippingAddress: this.formBuilder.group({
        street:  new FormControl("",[Validators.required, Validators.minLength(2),CustomValidators.notOnlyWhiteSpace]),
        city:  new FormControl("",[Validators.required, Validators.minLength(2),CustomValidators.notOnlyWhiteSpace]),
        state: [''],
        country: new FormControl("",Validators.required),
        zipCode: new FormControl("",[Validators.required, Validators.minLength(2),CustomValidators.notOnlyWhiteSpace])
      }),

      billingAddress: this.formBuilder.group({
        street:  new FormControl("",[Validators.required, Validators.minLength(2),CustomValidators.notOnlyWhiteSpace]),
        city:  new FormControl("",[Validators.required, Validators.minLength(2),CustomValidators.notOnlyWhiteSpace]),
        state: [''],
        country: new FormControl("",Validators.required),
        zipCode:  new FormControl("",[Validators.required, Validators.minLength(2),CustomValidators.notOnlyWhiteSpace])
      }),

      creditCard: this.formBuilder.group({
        cardType: new FormControl("", Validators.required),
        nameOnCard: new FormControl("",[Validators.required, Validators.minLength(2),CustomValidators.notOnlyWhiteSpace]),
        cardNumber: new FormControl("",[Validators.required, Validators.pattern("[0-9]{16}")]),
        securityCode: new FormControl("",[Validators.required, Validators.pattern("[0-9]{3}")]),
        expirationMonth: [''],
        expirationYear: ['']
      }),

    });
  }

  get firstName(){return this.checkoutFormGroup.get("customer.firstName");}
  get lastName(){return this.checkoutFormGroup.get("customer.lastName");}
  get email(){return this.checkoutFormGroup.get("customer.email");}
  get shippingAddressStreet(){return this.checkoutFormGroup.get("shippingAddress.street");}
  get shippingAddressCity(){return this.checkoutFormGroup.get("shippingAddress.city");}
  get shippingAddressCountry(){return this.checkoutFormGroup.get("shippingAddress.country");}
  get shippingAddressZipCode(){return this.checkoutFormGroup.get("shippingAddress.zipCode");}
  get billingAddressStreet(){return this.checkoutFormGroup.get("billingAddress.street");}
  get billingAddressCity(){return this.checkoutFormGroup.get("billingAddress.city");}
  get billingAddressCountry(){return this.checkoutFormGroup.get("billingAddress.country");}
  get billingAddressZipCode(){return this.checkoutFormGroup.get("billingAddress.zipCode");}
  get creditCardType(){return this.checkoutFormGroup.get("creditCard.cardType");}
  get creditCardNameOnCard(){return this.checkoutFormGroup.get("creditCard.nameOnCard");}
  get creditCardCardNumber(){return this.checkoutFormGroup.get("creditCard.cardNumber");}
  get creditCardSecurityCode(){return this.checkoutFormGroup.get("creditCard.securityCode");}


  onSubmit() {
    console.log("Handling the submit button")

    if(this.checkoutFormGroup.invalid){
      this.checkoutFormGroup.markAllAsTouched();
      return;
    }

    //set up order
    let order = new Order();
    order.totalPrice = this.totalPrice;
    order.totalQuantity = this.totalQuantity;

    //get cart items
    const cartItems = this.cartService.cartItems;

    // crete orderItems form cartItems

    //long way
   // let orderItems: OrderItem[]=[];
   // for(let i=0; i>cartItems.length; i++){
   //   orderItems[i]= new OrderItem(cartItems[i]);
   //  }

    //short way
    let orderItems: OrderItem[]=cartItems.map(tempCartItem=> new OrderItem(tempCartItem));
    //set up purchase
    let purchase = new Purchase();
    // populate purchase - customer
    purchase.customer = this.checkoutFormGroup.controls["customer"].value;
    // populate purchase - billing address
    purchase.billingAddress = this.checkoutFormGroup.controls["billingAddress"].value;
    const billingState: State = JSON.parse(JSON.stringify(purchase.billingAddress.state));
    const billingCountry: Country = JSON.parse(JSON.stringify(purchase.billingAddress.country));
    purchase.billingAddress.state = billingState.name;
    purchase.billingAddress.country = billingCountry.name;
    // populate purchase - shipping address
    purchase.shippingAddress = this.checkoutFormGroup.controls["shippingAddress"].value;
    const shippingState: State = JSON.parse(JSON.stringify(purchase.shippingAddress.state));
    const shippingCountry: Country = JSON.parse(JSON.stringify(purchase.shippingAddress.country));
    purchase.shippingAddress.state = shippingState.name;
    purchase.shippingAddress.country = shippingCountry.name;
    // populate purchase - order and order item
    purchase.order = order;
    purchase.orderItems=orderItems;
    //call Rest Api via Checkout Service
    this.checkoutService.placeOrder(purchase).subscribe({
      next:response=>{
          alert(`Your order has been received.\nOrder tracking number: ${response.orderTrackingNumber}`)

          //reset cart
        this.resetCart();
        },
        error: err=>{
          alert(`There was an error: ${err.message}`);

        },
      }
    );


    // @ts-ignore
    console.log(this.checkoutFormGroup.get('customer').value);

    // @ts-ignore
    console.log(this.checkoutFormGroup.get('customer').value.emailAddress);

    // @ts-ignore
    console.log(this.checkoutFormGroup.get('customer').value.firstName);

    // @ts-ignore
    console.log(this.checkoutFormGroup.get('customer').value.lastName);

    console.log(this.checkoutFormGroup.get('shippingAddress').value);

    console.log("The shipping address country is" + this.checkoutFormGroup.get('shippingAddress').value.country.name);
    console.log("The shipping address state is" + this.checkoutFormGroup.get('shippingAddress').value.state.name);
    console.log("The billing address country is" + this.checkoutFormGroup.get('billingAddress').value.country.name);
    console.log("The billing address state is" + this.checkoutFormGroup.get('billingAddress').value.state.name);


  }


  copyShippingAddressToBillingAddress(checked: boolean) {

    if (checked) {
      this.checkoutFormGroup.controls['billingAddress'].setValue(this.checkoutFormGroup.controls['shippingAddress'].value);
      //add code for states

      this.billingAddressStates = this.shippingAddressStates;


    } else {
      this.checkoutFormGroup.controls['billingAddress'].reset();
      //add code for states
      this.billingAddressStates = [];
    }

  }

  getCreditCardYears() {
    this.shopFormService.getCreditCardYears().subscribe(data => {
      console.log(`RetrievedCreditCardYears: ${JSON.stringify(data)}`)
      this.creditCardYears = data;
    });
  }

  private getCreditCardMonth(startMonth: number) {

    console.log(`Start month: ${startMonth}`)
    this.shopFormService.getCreditCardMonths(startMonth).subscribe(data => {
      console.log(`RetrievedCreditCardMonth: ${JSON.stringify(data)}`)
      this.creditCardMonth = data;
    });
  }

  handleMonthsAndYears() {
    const creditCardFormGroup = this.checkoutFormGroup.get("creditCard");
    const currentYear: number = new Date().getFullYear();
    const selectedYear: number = Number(creditCardFormGroup.value.expirationYear);

    //if the current year equals selected year, then start with the current month;
    let startMonth: number;
    if (currentYear === selectedYear) {
      startMonth = new Date().getMonth() + 1;
    } else {
      startMonth = 1;
    }
    this.getCreditCardMonth(startMonth);

  }

  private getCountries() {
    this.shopFormService.getCountries().subscribe(data=>{
      console.log(`COUNTRIES: ${JSON.stringify(data)}`)
      this.countries = data;
    });
  }

  getStates(formGroupName: string) {

    const formGroup = this.checkoutFormGroup.get(formGroupName);
    const countryCode = formGroup.value.country.code;
    const countryName = formGroup.value.country.name;

    console.log(`${formGroupName} country code: ${countryCode}`)
    console.log(`${formGroupName} country name: ${countryName}`)

    this.shopFormService.getStates(countryCode).subscribe(data=>{

      console.log(`STATES ${JSON.stringify(data)}`)
       if(formGroupName==='shippingAddress'){
         this.shippingAddressStates=data;
       }else{
         this.billingAddressStates = data;
       }
      //select first item by default
      formGroup.get('state').setValue(data[0]);
    });

  }

  private reviewCartDetails() {

    this.cartService.totalQuantity.subscribe(data=>{
      console.log("QUANTITY"+JSON.stringify(data))
      this.totalQuantity=data;
      console.log("QUANTITY"+this.totalQuantity)
    });

    this.cartService.totalPrice.subscribe(data=>{
      console.log("PRICE"+JSON.stringify(data))
      this.totalPrice=data;
      console.log("PRICE"+this.totalPrice)
    });
  }

  private resetCart() {
    //reset cart data
    this.cartService.cartItems = [];
    this.cartService.totalPrice.next(0);
    this.cartService.totalQuantity.next(0);
    //reset the form
    this.checkoutFormGroup.reset();
    //navigate back to the products page
    this.router.navigateByUrl("/products");


  }
}
