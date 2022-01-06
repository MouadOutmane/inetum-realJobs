import {Injectable} from '@angular/core';
import {CanActivate, Router} from '@angular/router';
import {AuthenticationService} from "../services/authentication.service";

@Injectable({
  providedIn: 'root'
})
export class RecruiterGuard implements CanActivate {

  constructor(private auth: AuthenticationService, private router: Router) {
  }

  canActivate(): boolean {
    if (this.auth.getCurrentRoles() && this.auth.getCurrentRoles().includes("ROLE_RECRUITER")) {
      return true;
    }
    this.router.navigate(["not-found"]);
    return false;
  }
}
