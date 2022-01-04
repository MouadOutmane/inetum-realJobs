import {Component, OnInit} from '@angular/core';
import {FormGroup, FormBuilder, Validators} from "@angular/forms";
import {ResumeStatus} from "../../../../models/resumeStatus.enum";
import {ResumeService} from "../../../../services/resume.service";
import {MessageService, ConfirmationService} from "primeng/api";
import {HttpErrorResponse} from "@angular/common/http";
import {throwError, catchError} from "rxjs";

@Component({
  selector: 'app-status-form',
  templateUrl: './status-form.component.html',
  styleUrls: ['./status-form.component.scss']
})
export class StatusFormComponent implements OnInit {

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
    this.resumeService
      .getResumeStatus()
      .pipe(catchError((err) => this.onError(err)))
      .subscribe(status => {
        this.setStatus(status);
      });
    this.statusForm = this.formBuilder.group({
      status: [undefined, [Validators.required]],
    });
  }

  private setStatus(status: ResumeStatus) {
    this.prevStatus = status;
    this.statusForm.controls["status"].setValue(status);
  }

  confirm(event: Event) {
    this.confirmationService.confirm({
      target: event.target,
      message: "You are about to change your status",
      accept: () => this.submitForm(),
      reject: () => this.setStatus(this.prevStatus)
    });
  }

  submitForm() {
    if (this.statusForm.valid) {
      this.resumeService
        .setResumeStatus(this.getFormData())
        .pipe(catchError((err) => this.onError(err)))
        .subscribe(status => {
          this.setStatus(status);
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
