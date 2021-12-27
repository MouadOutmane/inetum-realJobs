import {Component, OnInit} from '@angular/core';
import {FormGroup, FormBuilder, Validators} from "@angular/forms";
import {Education} from "../../../../models/education";
import {ResumeService} from "../../../../services/resume.service";
import {MessageService} from "primeng/api";
import {catchError, throwError} from "rxjs";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-education-form',
  templateUrl: './education-form.component.html',
  styleUrls: ['./education-form.component.scss']
})
export class EducationFormComponent implements OnInit {

  educationForm: FormGroup;
  educationList: Education[];
  today: Date = new Date();

  constructor(private resumeService: ResumeService,
              private messageService: MessageService,
              private formBuilder: FormBuilder) {
    this.educationList = [];
  }

  ngOnInit(): void {
    this.educationForm = this.formBuilder.group({
      degree: ["", [Validators.required]],
      program: ["", [Validators.required]],
      school: ["", [Validators.required]],
      startDate: [undefined, [Validators.required]],
      endDate: [undefined, [Validators.required]],
      description: [""],
    });
    this.resumeService
      .getEducationList()
      .pipe(catchError((err) => this.onError(err)))
      .subscribe(educationList => {
        this.educationList = educationList;
      });
  }

  submitForm() {
    if (this.educationForm.valid) {
      this.resumeService
        .addEducation(this.getFormData())
        .pipe(catchError((err) => this.onError(err)))
        .subscribe(educationList => {
          this.educationList = educationList;
          this.educationForm.reset();
        });
    }
  }

  onError(error: HttpErrorResponse) {
    this.messageService.add({key: "tl", severity: "error", summary: "Error", detail: error.error.message});
    return throwError(() => error.message);
  }

  deleteEducation(index: number) {
    this.resumeService
      .removeEducation(index)
      .pipe(catchError((err) => this.onError(err)))
      .subscribe(educationList => {
        this.educationList = educationList;
      });
  }

  getFormData(): Education {
    return {
      degree: this.educationForm.controls["degree"].value,
      program: this.educationForm.controls["program"].value,
      school: this.educationForm.controls["school"].value,
      startDate: this.educationForm.controls["startDate"].value,
      endDate: this.educationForm.controls["endDate"].value,
      description: this.educationForm.controls["description"].value,
    }
  }

  isInvalid(component: string) {
    switch (component) {
      case "startDate":
        return !this.isDateInPast(this.educationForm.controls['startDate'].value) ||
          (this.educationForm.controls[component].invalid &&
            this.educationForm.controls[component].dirty);
      default:
        return this.educationForm.controls[component].invalid &&
          this.educationForm.controls[component].dirty;
    }
  }

  isInvalidBasic(component: string) {
    return this.educationForm.controls[component].invalid &&
      this.educationForm.controls[component].dirty;
  }

  isDateInPast(date: Date): boolean {
    return date < new Date();
  }

  isDateBefore(date1: Date, date2: Date): boolean {
    if (date1 && date2) {
      return date1 < date2;
    }
    return false;
  }

  get endDate() {
    return this.educationForm.controls["endDate"].value;
  }

  get startDate() {
    return this.educationForm.controls["startDate"].value;
  }

  get maxStartDate() {
    return this.today > this.endDate ? this.today : this.endDate;
  }
}
