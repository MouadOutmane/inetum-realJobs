import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable, tap} from "rxjs";
import {User} from "../models/user.model";
import * as moment from "moment";
import {ValidationErrors, Validators} from "@angular/forms";

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

  requestReset(body: any): Observable<string> {
    return this.http.post<string>(baseUrl + "forgotPassword", body);
  }

  get passwordValidators(): (ValidationErrors | null)[] {
    return [Validators.required, Validators.minLength(4)];
  }

  logout() {
    localStorage.removeItem("id_token");
    localStorage.removeItem("expires_at");
  }

  resetPassword(code: string, password: string): Observable<string> {
    return this.http.post<string>(baseUrl + "resetPassword", {code, password});
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

  parseJwt() {
    if (!this.getJWTToken()) {
      return null;
    }
    const base64Token = this.getJWTToken().split('.')[1];
    const base64 = base64Token.replace(/-/g, '+').replace(/_/g, '/');
    return JSON.parse(window.atob(base64));
  }

  getCurrentRoles(): string[] {
    const parsedJwt = this.parseJwt();
    if (parsedJwt) {
      return parsedJwt["roles"];
    }
    return null;
  }

  private setSession(authResult: any) {
    const expiresAt = moment().add(authResult.expiresIn, "second");

    localStorage.setItem("id_token", authResult.accessToken);
    localStorage.setItem("expires_at", JSON.stringify(expiresAt.valueOf()));
  }

  getLoggedInUserEmail() {
    const parsedJwt = this.parseJwt();
    if (parsedJwt) {
      return this.parseJwt()["sub"];
    }
  }

  parseJwt() {
    if (!this.getJWTToken()) {
      return null;
    }
    const base64Token = this.getJWTToken().split('.')[1];
    const base64 = base64Token.replace(/-/g, '+').replace(/_/g, '/');
    return JSON.parse(window.atob(base64));
  }
}
