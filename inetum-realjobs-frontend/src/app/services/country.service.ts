import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {Country} from "../models/country";

const baseUrl = "country/";

@Injectable({
  providedIn: "root",
})
export class CountryService {

  constructor(private http: HttpClient) {
  }

  getAllCountries(): Observable<Country[]> {
    return this.http.get<Country[]>(baseUrl + "all");
  }
}
