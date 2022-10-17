import { Component, OnInit } from '@angular/core';
import {Product} from "../../models/product";
import {ProductService} from "../../services/product.service";
import {ActivatedRoute} from "@angular/router";
import {CartService} from "../../services/cart.service";
import {CartItem} from "../../models/cart-item";

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {


  products: Product[]=[];
  currentCategoryId: number = 1;
  previousCategoryId: number = 1;
  searchMode: boolean = false;
  previousSearchName: string = "";

  thePageNumber: number = 1;
  thePageSize: number = 5;
  theTotalElements: number = 0;

  constructor(private productService: ProductService, private route: ActivatedRoute, private cartService:CartService) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(()=>{
    this.getProducts();
    });
  }
  public getProducts() {
    this.searchMode = this.route.snapshot.paramMap.has("searchName");
    if (this.searchMode == true) {
      this.handleListProductsWithSearchName()
    } else {
this.handleListProducts();
    }
  }

  private handleListProducts(){
    //check if "id" parameter is available
    const hasCategoryId: boolean = this.route.snapshot.paramMap.has("id");
    if (hasCategoryId) {
      // get the "id" param string convert string to a number using the "+" symbol
      // this.currentCategoryId = +this.route.snapshot.paramMap.get("id")!;
      this.currentCategoryId = +this.route.snapshot.paramMap.get("id")!;
    } else {
      this.currentCategoryId = 1;
    }
    if (this.currentCategoryId != this.previousCategoryId) {
      this.thePageNumber = 1;
    }
    this.previousCategoryId = this.currentCategoryId;
    this.getProductsByCategoryIdWithPagination(this.currentCategoryId);
  }


  private getProductsByCategoryIdWithPagination(categoryId:number){
    this.productService.getProductsByCategoryIdWithPagination(categoryId,this.thePageNumber-1,this.thePageSize ).subscribe(data=>{
      console.log("Category products" + JSON.stringify(data))
      // this.products = data;
      this.products = data.content;
      this.thePageNumber=data.pageable.pageNumber+1;
      this.thePageSize = data.pageable.pageSize;
      this.theTotalElements = data.totalElements;
    });
}

  private handleListProductsWithSearchName(){
    const searchName = this.route.snapshot.paramMap.get("searchName")!;
    if(this.previousSearchName != searchName){
      this.thePageNumber=1;
    }
    this.previousSearchName = searchName;
    this.getProductsWithSearchName(searchName);
  }

  private getProductsWithSearchName(searchName: string){
    this.productService.getProductsListWithSearchName(searchName, this.thePageNumber-1, this.thePageSize).subscribe(data=>{
      console.log("Search products" + JSON.stringify(data))
      this.products = data.content;
      this.thePageNumber = data.pageable.pageNumber+1;
      this.thePageSize = data.pageable.pageSize;
      this.theTotalElements = data.totalElements;
    });
  }

  updatePageSize(pageSize: string) {
    this.thePageSize = +pageSize;
    this.thePageNumber = 1;
    this.getProducts();
  }

  addToCart(product: Product) {

    let cartItem = new CartItem(product);

    this.cartService.addToCart(cartItem);

    console.log("Product added" + product.name + product.unitPrice)

  }
}
