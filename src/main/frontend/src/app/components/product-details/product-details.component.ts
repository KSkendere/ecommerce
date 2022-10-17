import { Component, OnInit } from '@angular/core';
import {Product} from "../../models/product";
import {ProductService} from "../../services/product.service";
import {ActivatedRoute} from "@angular/router";
import {CartItem} from "../../models/cart-item";
import {CartService} from "../../services/cart.service";

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {

  product!: Product;

  currentCategoryId: number = 1;

  thePageNumber: number = 1;
  thePageSize: number = 5;
  theTotalElements: number = 0;

  constructor(public productService: ProductService, public route: ActivatedRoute, private cartService: CartService) {
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(() => {

      this.getProductById();
    });
  }

  private getProductById() {
    const productId: number = +this.route.snapshot.paramMap.get("id")!;

    this.productService.getProductById(productId).subscribe(data => {
this.product = data;
      }
    );

  }

  private getDetailsForReturn (){
    const thePageNumber: number = +this.route.snapshot.paramMap.get("pageSize")!;
  }

  addToCart(product: Product) {

    let cartItem = new CartItem(product);

    this.cartService.addToCart(cartItem);

  }
}
