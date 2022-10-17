import { Component, OnInit } from '@angular/core';
import {ProductCategoryService} from "../../services/product-category.service";
import {ProductCategory} from "../../models/product-category";
import {UserService} from "../../services/user.service";

@Component({
  selector: 'app-product-category-menu',
  templateUrl: './product-category-menu.component.html',
  styleUrls: ['./product-category-menu.component.css']
})
export class ProductCategoryMenuComponent implements OnInit {

  categories: ProductCategory[]=[];

  constructor(private productCategoryService: ProductCategoryService, public userService: UserService) { }

  ngOnInit(): void {
    this.getProductCategories();
  }

  private getProductCategories(){
    this.productCategoryService.getProductCategories().subscribe(data=>{
      console.log("Product Categories"+ JSON.stringify(data));
      this.categories = data});

  }

}
