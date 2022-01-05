import {Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import {FormGroup, FormBuilder, Validators} from "@angular/forms";
import {ResumeStatus} from "../../../../models/resumeStatus.enum";
import {ResumeService} from "../../../../services/resume.service";
import {MessageService, ConfirmationService} from "primeng/api";
import {HttpErrorResponse} from "@angular/common/http";
import {throwError, catchError} from "rxjs";
import {AccountResume} from "../../../../models/accountResume";

@Component({
  selector: 'app-status-form',
  templateUrl: './status-form.component.html',
  styleUrls: ['./status-form.component.scss']
})
export class StatusFormComponent implements OnInit {

  @Input() accountInfo: AccountResume;
  @Input() status: ResumeStatus;
  @Output() formCloseEvent = new EventEmitter<null>();
  @Output() statusUpdatedEvent = new EventEmitter<ResumeStatus>();

  statusForm: FormGroup;
  resumeStatusOptions: ResumeStatus[];
  prevStatus: ResumeStatus;

  constructor(private resumeService: ResumeService,
              private messageService: MessageService,
              private confirmationService: ConfirmationService,
              private formBuilder: FormBuilder) {
    this.resumeStatusOptions = Object.keys(ResumeStatus) as ResumeStatus[];
  }

  ngOnInit(): void {
    this.prevStatus = this.status;
    this.statusForm = this.formBuilder.group({
      status: [this.status, [Validators.required]],
    });
  }

  confirm(event: Event) {
    this.confirmationService.confirm({
      target: event.target,
      header: "You are about to change your status",
      message: (this.statusForm.controls["status"].value === ResumeStatus.NEGATIVE)
        ? "By changing your status to: Not interested in any new opportunities, your personal contact information won't be visible for recruiters on the platform."
        : "By changing your status to: Excited about new opportunities/Open to discussing new opportunities, your personal contact information will be visible for recruiters on the platform.",
      icon: "pi pi-info-circle",
      accept: () => this.submitForm(),
      reject: () => this.statusForm.controls["status"].setValue(this.prevStatus)
    });
  }

  submitForm() {
    if (this.statusForm.valid) {
      this.resumeService
        .setResumeStatus(this.getFormData())
        .pipe(catchError((err) => this.onError(err)))
        .subscribe(status => {
          this.prevStatus = status;
          this.statusUpdatedEvent.emit(status);
          this.formCloseEvent.emit();
        });
    }
  }

  onError(error: HttpErrorResponse) {
    this.messageService.add({key: "tl", severity: "error", summary: "Error", detail: error.message});
    return throwError(() => error.message);
  }

  isInvalid(component: string): boolean {
    return this.statusForm.controls[component].invalid && this.statusForm.controls[component].dirty;
  }

  private getFormData(): ResumeStatus {
    return this.statusForm.controls["status"].value;
  }
}
