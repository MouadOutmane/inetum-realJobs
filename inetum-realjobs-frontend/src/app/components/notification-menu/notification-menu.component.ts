import {Component, OnInit, Input} from '@angular/core';

@Component({
  selector: 'app-notification-menu',
  templateUrl: './notification-menu.component.html',
  styleUrls: ['./notification-menu.component.scss']
})
export class NotificationMenuComponent implements OnInit {
  @Input() notificationTemplateRef: any;

  constructor() { }

  ngOnInit(): void {
  }

}
