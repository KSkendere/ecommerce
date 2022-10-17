import { Component, OnInit } from '@angular/core';
import {Product} from "../../models/product";
import {NgForm} from "@angular/forms";
import {ProductService} from "../../services/product.service";
import {HttpErrorResponse} from "@angular/common/http";
import {ProductDto} from "../../models/product-dto";
import {ProductCategoryService} from "../../services/product-category.service";
import {ProductCategory} from "../../models/product-category";

@Component({
  selector: 'app-admin-add-product',
  templateUrl: './admin-add-product.component.html',
  styleUrls: ['./admin-add-product.component.css']
})
export class AdminAddProductComponent implements OnInit {

   date = new Date();

  productDto: ProductDto = new ProductDto();
  categories: ProductCategory[]=[];


  constructor(private productService: ProductService, private productCategoryService: ProductCategoryService) { }

  ngOnInit(): void {
this.getProductCategories();

  }

 public addProduct(productForm: NgForm) {

   this.productService.saveProduct(this.productDto).subscribe(
     (resp:ProductDto)=>{
       this.clearForm(productForm);
     },
     (error: HttpErrorResponse)=>{
       console.log(error);}

   )

  }
  private getProductCategories(){
    this.productCategoryService.getProductCategories().subscribe(data=>{
      console.log("Product Categories"+ JSON.stringify(data));
      this.categories = data});

  }


  public clearForm(productForm: NgForm){
    productForm.reset();
  }

}
