import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from "../../services/authentication.service";
import {MenuItem} from "primeng/api";
import {RecruiterService} from "../../services/recruiter.service";
import {Observable} from "rxjs";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {
  avatarItems: MenuItem[];
  notificationItems: MenuItem[];
  username: string;
  notifications: string[] = ['item1', 'item2'];
  amountOfNotifications: number = 1;
  amountOfNotifications$: Observable<number>;

  constructor(private auth: AuthenticationService,
              private recruiterService: RecruiterService) {
  }

  ngOnInit(): void {
    this.username = this.auth.getLoggedInUserEmail();
    this.avatarItems = [
      {
        label: this.username,
        items: [
          {label: 'Account', icon: 'pi pi-user', routerLink: '../../users/' + this.username},
          {label: 'Log out', icon: 'pi pi-sign-out', command: () => {
              this.auth.logout()}, routerLink: '../../vacancy/search'}
        ]},
    ];
    this.notificationItems = [
      {label: 'Notifications',
        items: [
          {label: this.notifications[1]}
        ]}
    ];
  }

  getAmountOfUpdates(): Observable<number> {
    this.amountOfNotifications$ = this.recruiterService.getAmountOfNotifications();
    this.amountOfNotifications$.subscribe(x => this.amountOfNotifications = x);
    return this.amountOfNotifications$;
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
