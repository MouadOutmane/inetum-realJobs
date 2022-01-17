import { Component, OnInit } from '@angular/core';
import {Notification} from "../../models/notification.model";

@Component({
  selector: 'app-notification',
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.scss']
})
export class NotificationComponent implements OnInit {
  notifications: Notification[];
  showNotification: boolean;


  constructor() { }

  ngOnInit(): void {
      this.showNotification = false;
  }

  openNotifications(state: boolean): boolean {
    console.log('openNotfications has been called');
    console.log(this.showNotification);
    return this.showNotification = state;
  }

}
