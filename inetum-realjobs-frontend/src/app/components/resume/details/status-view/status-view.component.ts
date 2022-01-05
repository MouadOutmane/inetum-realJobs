import {Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import {AccountResume} from "../../../../models/accountResume";
import {ResumeStatus} from "../../../../models/resumeStatus.enum";

@Component({
  selector: 'app-status-view',
  templateUrl: './status-view.component.html',
  styleUrls: ['./status-view.component.scss']
})
export class StatusViewComponent implements OnInit {

  @Input() accountInfo: AccountResume;
  @Input() status: ResumeStatus;
  @Output() toFormEvent = new EventEmitter<null>();

  constructor() {
  }

  ngOnInit(): void {
  }

  toForm() {
    this.toFormEvent.emit();
  }

  getClass(): string {
    switch (this.status) {
      case ResumeStatus.NEGATIVE:
        return "pi pi-times-circle";
      case ResumeStatus.NEUTRAL:
        return "pi pi-circle-off";
      case ResumeStatus.POSITIVE:
        return "pi pi-check-circle";
    }
  }

  getStatusText(): string {
    switch (this.status) {
      case ResumeStatus.NEGATIVE:
        return "Not interested in any new opportunities";
      case ResumeStatus.NEUTRAL:
        return "Open to discussing new opportunities";
      case ResumeStatus.POSITIVE:
        return "Excited about new opportunities ";
    }
  }
}
