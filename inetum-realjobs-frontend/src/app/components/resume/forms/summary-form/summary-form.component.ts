import {Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import {FormGroup, FormBuilder, Validators} from "@angular/forms";
import {ResumeService} from "../../../../services/resume.service";
import {MessageService} from "primeng/api";
import {catchError, throwError} from "rxjs";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-summary-form',
  templateUrl: './summary-form.component.html',
  styleUrls: ['./summary-form.component.scss']
})
export class SummaryFormComponent implements OnInit {

  @Input() summary: string;
  @Output() formCloseEvent = new EventEmitter<null>();
  @Output() summaryUpdatedEvent = new EventEmitter<string>();

  summaryForm: FormGroup;

  constructor(private resumeService: ResumeService,
              private messageService: MessageService,
              private formBuilder: FormBuilder) {
  }

  ngOnInit(): void {
    this.summaryForm = this.formBuilder.group({
      summary: [this.summary, [Validators.required]],
    });
  }

  submitForm() {
    if (this.summaryForm.valid) {
      this.resumeService
        .setSummary(this.getFormData())
        .pipe(catchError((err) => this.onError(err)))
        .subscribe(summary => {
          this.summaryUpdatedEvent.emit(summary);
          this.summaryForm.controls["summary"].setValue(summary);
          this.formCloseEvent.emit();
        });
    }
  }

  onError(error: HttpErrorResponse) {
    this.messageService.add({key: "tl", severity: "error", summary: "Error", detail: error.message});
    return throwError(() => error.message);
  }

  isInvalid(component: string): boolean {
    return this.summaryForm.controls[component].invalid && this.summaryForm.controls[component].dirty;
  }

  private getFormData(): string {
    return this.summaryForm.controls["summary"].value;
  }
}
