import {Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import {AccountResume} from "../../../../models/accountResume";
import {ResumeStatus} from "../../../../models/resumeStatus.enum";

@Component({
  selector: 'app-status-controller',
  templateUrl: './status-controller.component.html',
  styleUrls: ['./status-controller.component.scss']
})
export class StatusControllerComponent implements OnInit {

  @Input() accountInfo: AccountResume;
  @Input() status: ResumeStatus;
  @Output() statusUpdatedEvent = new EventEmitter<ResumeStatus>();

  isFormOpen: boolean = false;

  constructor() {
  }

  ngOnInit(): void {
  }

  updateStatus(status: ResumeStatus) {
    this.statusUpdatedEvent.emit(status);
  }

  closeForm() {
    this.isFormOpen = false;
  }

  openForm() {
    this.isFormOpen = true;
  }
}
