import { Component, OnInit } from '@angular/core';
import {UserAuthService} from "../../services/user-auth.service";
import {Router} from "@angular/router";
import {UserService} from "../../services/user.service";
import {AuthGuard} from "../../auth/auth.guard";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private userAuthService: UserAuthService, private  router: Router, public userService: UserService, private authGuard: AuthGuard) { }

  ngOnInit(): void {

  }

  public isLoggedIn(){
    return this.userAuthService.isLoggedIn();
  }

  public logout(){
    this.userAuthService.clear();
    this.router.navigate(["/products"])
  }



}
