import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import {ProductsComponent} from "./components/products/products.component";
import {ProductDetailsComponent} from "./components/product-details/product-details.component";
import {CartDetailsComponent} from "./components/cart-details/cart-details.component";
import {CheckoutComponent} from "./components/checkout/checkout.component";
import {ForbiddenComponent} from "./components/forbidden/forbidden.component";
import {UserPageComponent} from "./components/user-page/user-page.component";
import {LoginPageComponent} from "./components/login-page/login-page.component";
import {AuthGuard} from "./auth/auth.guard";
import {AdminAddProductComponent} from "./components/admin-add-product/admin-add-product.component";
import {AdminProductDetailsComponent} from "./components/admin-product-details/admin-product-details.component";
import {AdminUpdateProductComponent} from "./components/admin-update-product/admin-update-product.component";

const routes: Routes = [



  {path: "admin/update-product/:id", component: AdminUpdateProductComponent,canActivate:[AuthGuard], data:{roles:['admin']}},


  {path: "admin/add-product", component: AdminAddProductComponent,canActivate:[AuthGuard], data:{roles:['admin']}},
  {path: "admin/product-details", component: AdminProductDetailsComponent,canActivate:[AuthGuard], data:{roles:['admin']}},
  {path: "login-page", component: LoginPageComponent},
  {path: "user", component : UserPageComponent,canActivate:[AuthGuard], data:{roles:['user']}},

  {path: "forbidden", component : ForbiddenComponent},
  {path: "checkout", component: CheckoutComponent},
  {path: "cart-details", component: CartDetailsComponent},
  {path: "product/:id", component: ProductDetailsComponent},
  {path:"search/:searchName", component: ProductsComponent},
  {path: "category/:id", component: ProductsComponent},
  {path: "products",component: ProductsComponent},
  {path: "", redirectTo: "products", pathMatch: "full" },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule{}
