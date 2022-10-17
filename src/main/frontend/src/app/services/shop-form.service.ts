import {Injectable} from '@angular/core';
import {Observable, of} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {Country} from "../models/country";
import {State} from "../models/state";

@Injectable({
  providedIn: 'root'
})
export class ShopFormService {

  private countriesUrl = "http://localhost:8085/api/ecommerce/countries";
  private statesURL = "http://localhost:8085/api/ecommerce/states";


  constructor(private httpClient: HttpClient) {
  }

  getCreditCardMonths(startMonth: number): Observable<number[]> {
    let monthArray: number[] = [];
    //build an array for "Month" dropdown list
    // start at current month and loop until month nr 12
    for (let theMonth = startMonth; theMonth <= 12; theMonth++) {
      monthArray.push(theMonth);
    }
    return of(monthArray);
  }

  getCreditCardYears(): Observable<number[]> {
    let yearsArray: number[] = [];
    //build an array for year dropdown list
    // start at current year and loop for next 10 years
    const startYear: number = new Date().getFullYear();
    const endYear: number = startYear + 10;
    for (let theYear = startYear; theYear <= endYear; theYear++) {
      yearsArray.push(theYear);
    }
    return of(yearsArray);
  }

  getCountries(): Observable<Country[]> {
    return this.httpClient.get<Country[]>(this.countriesUrl);
  }

  getStates(theCountryCode: string): Observable<State[]> {
    //searchUrl
    const searchStatesUrl = `${this.statesURL}/countryCode?countryCode=${theCountryCode}`
    return this.httpClient.get<State[]>(searchStatesUrl);
  }
}
