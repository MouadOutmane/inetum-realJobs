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
}
