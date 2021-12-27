import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable, tap} from "rxjs";
import {User} from "../models/user.model";
import * as moment from "moment";

const baseUrl = "authentication/";

@Injectable({
  providedIn: "root",
})
export class AuthenticationService {

  constructor(private http: HttpClient) {
  }

  login(values: any): Observable<User> {
    return this.http.post<User>(baseUrl + "login", values)
      .pipe(tap((res: any) => this.setSession(res)));
  }

  register(user: User): Observable<string> {
    return this.http.post<string>(baseUrl + "signUp", user);
  }

  logout() {
    localStorage.removeItem("id_token");
    localStorage.removeItem("expires_at");
  }

  public isLoggedIn(): boolean {
    return !this.isTokenExpired();
  }

  public isLoggedOut(): boolean {
    return !this.isLoggedIn();
  }

  isTokenExpired(): boolean {
    const expiration: number = Number(localStorage.getItem("expires_at"));
    if (expiration != null) {
      return ((1000 * expiration) - (new Date()).getTime()) < 5000;
    } else {
      return false;
    }
  }

  getJWTToken() {
    return localStorage.getItem("id_token");
  }

  private setSession(authResult: any) {
    const expiresAt = moment().add(authResult.expiresIn, "second");

    localStorage.setItem("id_token", authResult.accessToken);
    localStorage.setItem("expires_at", JSON.stringify(expiresAt.valueOf()));
  }
}
