import { Injectable } from '@angular/core';
import {CartItem} from "../models/cart-item";
import {BehaviorSubject, ReplaySubject, Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CartService {

  cartItems: CartItem[]=[];

  totalPrice: Subject<number> = new BehaviorSubject<number>(0);

  totalQuantity: Subject<number> = new BehaviorSubject<number>(0);



  constructor() { }

  addToCart(cartItem: CartItem) {
    let existingCartItem: CartItem | undefined;
    if(this.cartItems.length>0){
      existingCartItem = this.cartItems.find(tempCartItem => tempCartItem.id === cartItem.id);
    }
    if(existingCartItem!=undefined){
      existingCartItem.quantity++;
    }else {
      this.cartItems.push(cartItem);
    }

  this.calculateTotals();

  }

  public calculateTotals() {

    let totalPriceValue = 0;
    let totalQuantityValue =0;

    for(let tempCartItem of this.cartItems){
      totalPriceValue+=tempCartItem.unitPrice*tempCartItem.quantity;
      totalQuantityValue+=tempCartItem.quantity;
    }
    //publish the new values for all subscribers

    this.totalPrice.next(totalPriceValue);
    this.totalQuantity.next(totalQuantityValue);

    this.logCartData(totalPriceValue, totalQuantityValue);

  }

  private logCartData(totalPriceValue: number, totalQuantityValue: number) {

    console.log("contents of the cart")
    for (let tempItem of this.cartItems) {
      const subTotalPrice = tempItem.quantity * tempItem.unitPrice;
      console.log(`name: ${tempItem.name}, quantity: ${tempItem.quantity}, unitPrice = ${tempItem.unitPrice}, subTotalPrice = ${subTotalPrice}`)

    }
    console.log(`totalPrice: ${totalPriceValue.toFixed(2)}, totalQuantity: ${totalQuantityValue}`)
    console.log("-----");
  }

  incrementQuantity(cartItem: CartItem) {
    cartItem.quantity++;
    this.calculateTotals();

  }

  decrementQuantity(cartItem: CartItem) {
    cartItem.quantity--;

    if(cartItem.quantity===0){
      this.removeItem(cartItem);
    }

    this.calculateTotals();
  }

  public removeItem(cartItem: CartItem) {

    let itemIndex = this.cartItems.findIndex(tempCartItem=> tempCartItem.id === cartItem.id);

    if(itemIndex>-1){
      this.cartItems.splice(itemIndex,1);
      this.calculateTotals();
    }

  }
}
