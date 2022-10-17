import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ProductCategory} from "../models/product-category";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ProductCategoryService {

  private categoryUrl = "http://localhost:8085/api/ecommerce/categories"

  constructor(private httpClient: HttpClient) { }

  getProductCategories(): Observable<ProductCategory[]>{

    return this.httpClient.get<ProductCategory[]>(this.categoryUrl);

  }



}
