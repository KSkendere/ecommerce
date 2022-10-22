import { Component, OnInit } from '@angular/core';
import {ProductService} from "../../services/product.service";
import {CartItem} from "../../models/cart-item";
import {Product} from "../../models/product";
import {ProductDtoForAdmin} from "../../models/product-dto-for-admin";
import {Router} from "@angular/router";

@Component({
  selector: 'app-admin-product-details',
  templateUrl: './admin-product-details.component.html',
  styleUrls: ['./admin-product-details.component.css']
})
export class AdminProductDetailsComponent implements OnInit {
  productsDtoForAdmin: ProductDtoForAdmin[]=[];

  thePageNumber: number = 1;
  thePageSize: number = 5;
  theTotalElements: number = 0;


  constructor(private productService: ProductService, private router: Router) { }


  ngOnInit(): void {
    this.getProductsWithPagination();

  }


  public getProductsWithPagination(){
    this.productService.getProductsListWithPaginationForAdmin(this.thePageNumber-1,this.thePageSize ).subscribe(data=>{
      console.log("Category products" + JSON.stringify(data))
      // this.products = data;
      this.productsDtoForAdmin = data.content;
      this.thePageNumber=data.pageable.pageNumber+1;
      this.thePageSize = data.pageable.pageSize;
      this.theTotalElements = data.totalElements;
    });
  }

  updatePageSize(pageSize: string) {
    this.thePageSize = +pageSize;
    this.thePageNumber = 1;
    this.getProductsWithPagination();
  }




  deleteProduct(id: number) {
    this.productService.deleteProduct(id).subscribe(
      response => {
        alert(`"${response.name}" has been deleted successfully.`)
        console.log(response);

        this.getProductsWithPagination()


      });
  }
}
