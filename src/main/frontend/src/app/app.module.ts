import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';

import {AppComponent} from './app.component';
import { ProductsComponent } from './components/products/products.component';
import {AppRoutingModule} from "./app-routing.module";
import { HeaderComponent } from './components/header/header.component';
import { ProductCategoryMenuComponent } from './components/product-category-menu/product-category-menu.component';
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import { ProductDetailsComponent } from './components/product-details/product-details.component';
import { CartStatusComponent } from './components/cart-status/cart-status.component';
import { CartDetailsComponent } from './components/cart-details/cart-details.component';
import { CheckoutComponent } from './components/checkout/checkout.component';
import { LoginComponent } from './components/login/login.component';
import { ForbiddenComponent } from './components/forbidden/forbidden.component';
import { UserPageComponent } from './components/user-page/user-page.component';
import { LoginPageComponent } from './components/login-page/login-page.component';
import {RouterModule} from "@angular/router";
import {AuthGuard} from "./auth/auth.guard";
import { AuthInterceptor } from './auth/auth.interceptor';
import {UserService} from "./services/user.service";
import { AdminAddProductComponent } from './components/admin-add-product/admin-add-product.component';
import { AdminProductDetailsComponent } from './components/admin-product-details/admin-product-details.component';
import { AdminUpdateProductComponent } from './components/admin-update-product/admin-update-product.component';




@NgModule({
  declarations: [
    AppComponent,
    ProductsComponent,
    HeaderComponent,
    ProductCategoryMenuComponent,
    ProductDetailsComponent,
    CartStatusComponent,
    CartDetailsComponent,
    CheckoutComponent,
    LoginComponent,
    ForbiddenComponent,
    UserPageComponent,
    LoginPageComponent,
    AdminAddProductComponent,
    AdminProductDetailsComponent,
    AdminUpdateProductComponent,



  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    NgbModule,
    ReactiveFormsModule,
    RouterModule
  ],
  providers: [
  AuthGuard,
    {provide: HTTP_INTERCEPTORS,
    useClass:AuthInterceptor,
    multi:true},
  UserService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
