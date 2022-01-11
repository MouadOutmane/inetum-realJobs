import {Component, Input, Output, EventEmitter} from '@angular/core';
import {AccountResume} from "../../../../models/accountResume";
import {ResumeStatus} from "../../../../models/resumeStatus.enum";

@Component({
  selector: 'app-status-controller',
  templateUrl: './status-controller.component.html',
  styleUrls: ['./status-controller.component.scss']
})
export class StatusControllerComponent {

  @Input() isFormOpen: boolean;
  @Input() accountInfo: AccountResume;
  @Input() status: ResumeStatus;
  @Output() statusUpdatedEvent = new EventEmitter<ResumeStatus>();
  @Output() formChangeEvent = new EventEmitter<boolean>();

  updateStatus(status: ResumeStatus) {
    this.statusUpdatedEvent.emit(status);
  }

  closeForm() {
    this.formChangeEvent.emit(false);
  }

  openForm() {
    this.formChangeEvent.emit(true);
  }
}
