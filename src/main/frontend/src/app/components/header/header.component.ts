import { Component, OnInit } from '@angular/core';
import {CartService} from "../../services/cart.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
public totalItem : number =0;
  constructor(private cartService: CartService, private router: Router) { }

  ngOnInit(): void {
    // this.cartService.getProducts()
    //   .subscribe(data=>{
    //     this.totalItem = data.length;
    //   })
  }

  doSearch(searchName: string) {

    console.log (`searchName = ${searchName}`);

    this.router.navigateByUrl(`search/${searchName}`);

  }
}
