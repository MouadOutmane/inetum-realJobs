import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from "../../services/authentication.service";
import {MenuItem} from "primeng/api";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {
  avatarItems: MenuItem[];
  notificationItems: MenuItem[];
  username: string;

  constructor(private auth: AuthenticationService) { }

  ngOnInit(): void {
    this.avatarItems = [
      {label: 'Account', icon: 'pi pi-user', routerLink: '../../users/' + this.username},
      {label: 'Log out', icon: 'pi pi-sign-out', command: () => {
          this.auth.logout()
        }, routerLink: '../../vacancy/search'
      }
    ];
    this.notificationItems = [
      {label: 'Notifications', items: []}
    ];
    this.username = this.auth.getLoggedInUserEmail();
  }

  isLoggedIn(): boolean {
    return this.auth.isLoggedIn();
  }

  logout() {
    this.auth.logout();
  }

  hasRoleRecruiter(): boolean {
    return this.auth.getCurrentRoles().includes("ROLE_RECRUITER");
  }

  isJobseeker(): boolean {
    return this.auth.getCurrentRoles().includes("ROLE_JOBSEEKER");
  }

}
