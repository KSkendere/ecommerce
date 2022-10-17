import { Component, OnInit } from '@angular/core';
import {NgForm} from "@angular/forms";
import {UserService} from "../../services/user.service";
import {UserAuthService} from "../../services/user-auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {

  constructor(private userService: UserService, private userAuthService: UserAuthService, private router: Router) { }

  ngOnInit(): void {
  }

  login(loginForm:NgForm) {

    this.userService.login(loginForm.value).subscribe(
      (response:any)=>{console.log(response);
        console.log(response.accessToken)
        console.log(response.user.email)
        let roles = response.user.roles;
        const  roleNames: string []=[];
        for(let role of roles){
          roleNames.push(role.name);
        }
        console.log("Roles{}", roleNames);
        this.userAuthService.setRoles(roleNames);
        this.userAuthService.setToken(response.accessToken);
        this.userAuthService.setEmail(response.user.email);

        const role = roleNames[0];


        // this.router.navigate(['/products'])

//         if(role==="admin"){
// this.router.navigate(['/admin'])
//
//         }else{
//           this.router.navigate(['/user'])
//         }

  },
      (error)=>{
        console.log(error);
      }
    );
  }

  public isLoggedIn() {

    return this.userAuthService.isLoggedIn();

  }
}
