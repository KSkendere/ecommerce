import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserAuthService {

  constructor() {
  }

  public setRoles(roles: string[]) {
    localStorage.setItem("roles", JSON.stringify(roles));
  }

  public getRoles(): [] {
    return JSON.parse(localStorage.getItem("roles"));
  }

  public setToken(accessToken: string) {
    localStorage.setItem("accessToken", accessToken);
  }

  public getToken(): string {
    return localStorage.getItem("accessToken");

  }

  public setEmail(email: string) {
    localStorage.setItem("user.email", email);
  }

  public getEmail(): string {
    return localStorage.getItem("email");
  }

  public clear() {
    localStorage.clear();
  }

  public isLoggedIn() {
    return this.getRoles() && this.getToken();
  }

  // public isAdmin() {
  //
  //   const roles: any[] = this.getRoles();
  //   return roles[0].role="Admin";
  // }
}
