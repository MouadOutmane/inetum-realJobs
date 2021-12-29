import { Injectable } from "@angular/core";
import { CanActivate, Router } from "@angular/router";
import { AuthenticationService } from "../services/authentication.service";

@Injectable({
    providedIn: 'root'}
)
export class AuthGuardService implements CanActivate {

    constructor(private auth: AuthenticationService, private router: Router) { }

    canActivate():boolean {
        if (this.auth.isTokenExpired()) {
            this.router.navigate(['login']);
        } 
        return true;
    }
}