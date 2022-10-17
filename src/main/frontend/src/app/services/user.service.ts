import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {UserAuthService} from "./user-auth.service";

@Injectable({
  providedIn: 'root'
})
export class UserService {


  private loginUrl = "http://localhost:8085/api/ecommerce/auth/login";
  requestHeader = new HttpHeaders(
    {"No-Auth": "True"}
  );

  constructor(private httpClient: HttpClient, private userAuthService: UserAuthService) {
  }

  public login(loginData) {
    return this.httpClient.post(this.loginUrl, loginData, {headers: this.requestHeader});
  }

  // @ts-ignore
  public roleMatch(allowedRoles): boolean {
    let isMatch = false;
    const userRoles: any = this.userAuthService.getRoles();

    console.log("USER ROLES{}",userRoles);



    if (userRoles != null && userRoles) {
      for (let i = 0; i < userRoles.length; i++) {
        for (let j = 0; j < allowedRoles.length; j++) {
          console.log("USERROLES{}", userRoles[i],"AllowedRoles{}",allowedRoles[j])
          if (userRoles[i] === allowedRoles[j]) {

            // console.log("AlloweROLES{}", allowedRoles[j])
            isMatch = true;
            return isMatch;
          } else {
            return isMatch;
          }
        }
      }
    }

  }
}
