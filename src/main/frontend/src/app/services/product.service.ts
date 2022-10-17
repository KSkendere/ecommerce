import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, Subject} from "rxjs";
import {Product} from "../models/product";
import {ProductDto} from "../models/product-dto";
import {ProductDtoForAdmin} from "../models/product-dto-for-admin";


@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private baseURL = "http://localhost:8085/api/ecommerce/products";
  // private ordersUrl = "http://localhost:8080/api/ecommerce/";
  //
  // private productOrder: ProductOrder | undefined;
  //
  // // @ts-ignore
  // private orders: ProductOrders = new ProductOrders();
  //
  // private productOrderSubject = new Subject();
  // private ordersSubject = new Subject();
  // private totalSubject = new Subject();
  //
  // private total: number | undefined;


  constructor(private httpClient: HttpClient) {
  }

  getProductsList(): Observable<Product[]> {
    return this.httpClient.get<Product[]>(this.baseURL);

  }

  getProductsListWithPaginationForAdmin(pageNo: number, pageSize: number): Observable<GetResponseProductsForAdmin> {

    const productsWithPaginationUrl = `${this.baseURL}?pageNo=${pageNo}&pageSize=${pageSize}`;
    return this.httpClient.get<GetResponseProductsForAdmin>(productsWithPaginationUrl);
  }


  getProductsListWithPagination(pageNo: number, pageSize: number): Observable<GetResponseProducts> {

    const productsWithPaginationUrl = `${this.baseURL}?pageNo=${pageNo}&pageSize=${pageSize}`;
    return this.httpClient.get<GetResponseProducts>(productsWithPaginationUrl);
  }

  getProductsListWithSearchName(searchName: string, pageNo: number, pageSize: number): Observable<GetResponseProducts> {

    const productsWithSearchUrl = `${this.baseURL}/searchname?pageNo=${pageNo}&pageSize=${pageSize}&searchName=${searchName}`;
    return this.httpClient.get<GetResponseProducts>(productsWithSearchUrl);
  }

  getProductById(id: number): Observable<Product>{
    return this.httpClient.get<Product>(`${this.baseURL}/${id}`);
  }

  getProductDtoById(id: number): Observable<ProductDto> {
    return this.httpClient.get<ProductDto>(`${this.baseURL}/${id}`);

  }


// /products/categoryId/{id}

  getProductsByCategoryId(productId:number): Observable<Product[]> {

    const productByCategoryUrl = `${this.baseURL}/categoryId/${productId}`;
    return this.httpClient.get<Product[]>(productByCategoryUrl)
  }

  getProductsByCategoryIdWithPagination(productId:number, pageNo: number, pageSize: number): Observable<GetResponseProducts> {
    const productByCategoryWithPaginationUrl = `${this.baseURL}/categoryId/${productId}?pageNo=${pageNo}&pageSize=${pageSize}`;
    return this.httpClient.get<GetResponseProducts>(productByCategoryWithPaginationUrl);
  }

  saveProduct(productDto:ProductDto){
    return this.httpClient.post<ProductDto>(this.baseURL, productDto);
  }

  updateProduct(productDto:ProductDto, productId: number){
    return this.httpClient.put<ProductDto>(`${this.baseURL}/${productId}`, productDto);
  }

  deleteProduct(id: number) {

    return this.httpClient.delete(`${this.baseURL}/${id}`)

  }

  // saveOrder(order: ProductOrders) {
  //   return this.httpClient.post(this.ordersUrl, order);
  // }
  //
  // set ProductOrders(value: ProductOrders) {
  //   this.orders = value;
  //   // @ts-ignore
  //   this.ordersSubject.next();
  // }
  //
  // get ProductOrders() {
  //   return this.orders;
  // }
  //
  // set SelectedProductOrder(value: ProductOrder) {
  //   this.productOrder = value;
  //   // @ts-ignore
  //   this.productOrderSubject.next();
  // }
  //
  // get SelectedProductOrder() {
  //   // @ts-ignore
  //   return this.productOrder;
  // }
  //
  // get Total() {
  //   // @ts-ignore
  //   return this.total;
  // }
  //
  // set Total(value: number) {
  //   this.total = value;
  //   // @ts-ignore
  //   this.totalSubject.next();
  // }


}

interface GetResponseProducts {
  content:Product[];

  pageable:{
    pageSize: number,


    pageNumber: number

  },

    totalElements: number,
    totalPages: number,


}

interface GetResponseProductsForAdmin {
  content:ProductDtoForAdmin[];

  pageable:{
    pageSize: number,


    pageNumber: number

  },

  totalElements: number,
  totalPages: number,


}
