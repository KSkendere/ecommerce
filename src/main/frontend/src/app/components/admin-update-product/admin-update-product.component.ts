import { Component, OnInit } from '@angular/core';
import {NgForm} from "@angular/forms";
import {ProductDto} from "../../models/product-dto";
import {ProductCategory} from "../../models/product-category";
import {ProductCategoryService} from "../../services/product-category.service";
import {ProductService} from "../../services/product.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Location} from '@angular/common';
@Component({
  selector: 'app-admin-update-product',
  templateUrl: './admin-update-product.component.html',
  styleUrls: ['./admin-update-product.component.css']
})
export class AdminUpdateProductComponent implements OnInit {
  productDto: ProductDto = new ProductDto();
  categories: ProductCategory[]=[];
  constructor(private productCategoryService: ProductCategoryService,
              private productService: ProductService,
              public route: ActivatedRoute,
              private location: Location) { }

  ngOnInit(): void {

    this.getProductCategories();
    //
    this.getProductDtoById();
    this.route.paramMap.subscribe(() => {
      // this.getProductDtoById();


    });


  }



  updateProduct(productForm: NgForm) {

    const productId: number = +this.route.snapshot.paramMap.get("id")!;

    this.productService.updateProduct(this.productDto,productId).subscribe(data=>{

      this.goBack();
      },
      error=>console.log(error));


  }

  private getProductCategories(){
    this.productCategoryService.getProductCategories().subscribe(data=>{
      console.log("Product Categories"+ JSON.stringify(data));
      this.categories = data});

  }

  getProductDtoById(){
    const productId: number = +this.route.snapshot.paramMap.get("id")!;
    this.productService.getProductDtoById(productId).subscribe(data=>{
      this.productDto = data;

    })
  }

  goBack(): void {
    this.location.back();
  }
}
