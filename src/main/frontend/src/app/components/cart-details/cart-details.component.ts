import { Component, OnInit } from '@angular/core';
import {CartService} from "../../services/cart.service";
import {CartItem} from "../../models/cart-item";

@Component({
  selector: 'app-cart-details',
  templateUrl: './cart-details.component.html',
  styleUrls: ['./cart-details.component.css']
})
export class CartDetailsComponent implements OnInit {

  constructor(private cartService: CartService) { }
  cartItems: CartItem[]=[];
  totalPrice: number = 0;
  totalQuantity:number = 0;

  ngOnInit(): void {
    this.getCartItems();
  }

  private getCartItems() {
   this.cartItems= this.cartService.cartItems;
   this.cartService.totalPrice.subscribe(data=>{this.totalPrice = data});
   this.cartService.totalQuantity.subscribe(data=>{this.totalQuantity=data});
   this.cartService.calculateTotals();

  }

  incrementQuantity(cartItem: CartItem) {

    this.cartService.incrementQuantity(cartItem);

  }

  decrementQuantity(cartItem: CartItem) {

    this.cartService.decrementQuantity(cartItem);

  }

  remove(cartItem: CartItem) {

    this.cartService.removeItem(cartItem);

  }


}
