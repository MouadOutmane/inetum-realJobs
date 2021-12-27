import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Experience} from "../../../../models/experience";
import {MessageService} from "primeng/api";
import {ResumeService} from "../../../../services/resume.service";
import {catchError, throwError} from "rxjs";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: "app-experience-form",
  templateUrl: "./experience-form.component.html",
  styleUrls: ["./experience-form.component.scss"],
})
export class ExperienceFormComponent implements OnInit {

  experienceForm: FormGroup;
  experienceList: Experience[] = [];
  today: Date = new Date();

  constructor(private resumeService: ResumeService,
              private messageService: MessageService,
              private formBuilder: FormBuilder) {
  }

  ngOnInit(): void {
    this.experienceForm = this.formBuilder.group({
      jobTitle: ["", [Validators.required]],
      functionCategory: ["", [Validators.required]],
      company: ["", [Validators.required]],
      industry: ["", [Validators.required]],
      startDate: [undefined, [Validators.required]],
      endDate: [undefined],
      currentJob: [false],
      description: [""],
    });
    this.experienceForm.get("currentJob").valueChanges.subscribe(val => {
      if (val) {
        this.experienceForm.get("endDate").reset();
        this.experienceForm.get("endDate").disable();
      } else {
        this.experienceForm.get("endDate").enable();
      }
    });
    this.resumeService
      .getExperienceList()
      .pipe(catchError((err) => this.onError(err)))
      .subscribe(experienceList => {
        this.experienceList = experienceList;
      });
  }

  submitForm() {
    if (this.experienceForm.valid) {
      this.resumeService
        .addExperience(this.getFormData())
        .pipe(catchError((err) => this.onError(err)))
        .subscribe(experienceList => {
          this.experienceList = experienceList;
          this.experienceForm.reset();
        });
    }
  }

  onError(error: HttpErrorResponse) {
    this.messageService.add({key: "tl", severity: "error", summary: "Error", detail: error.message});
    return throwError(() => error.message);
  }

  deleteExperience(index: number) {
    this.resumeService
      .removeExperience(index)
      .pipe(catchError((err) => this.onError(err)))
      .subscribe(experienceList => {
        this.experienceList = experienceList;
      });
  }

  getFormData(): Experience {
    return {
      jobTitle: this.experienceForm.controls["jobTitle"].value,
      functionCategory: this.experienceForm.controls["functionCategory"].value,
      company: this.experienceForm.controls["company"].value,
      industry: this.experienceForm.controls["industry"].value,
      startDate: this.experienceForm.controls["startDate"].value,
      endDate: this.experienceForm.controls["endDate"].value,
      currentJob: this.experienceForm.controls["currentJob"].value,
      description: this.experienceForm.controls["description"].value,
    }
  }

  isInvalid(component: string) {
    switch (component) {
      case "startDate":
        return (this.experienceForm.controls[component].invalid &&
            this.experienceForm.controls[component].dirty) ||
          !this.isDateInPast(this.experienceForm.controls[component].value);
      case "endDate":
        return (this.experienceForm.controls[component].invalid &&
            this.experienceForm.controls[component].dirty) ||
          this.isEndDateNotInPast() ||
          this.isEndDateBeforeStartDate();
      default:
        return this.experienceForm.controls[component].invalid &&
          this.experienceForm.controls[component].dirty;
    }
  }

  isInvalidBasic(component: string) {
    return this.experienceForm.controls[component].invalid &&
      this.experienceForm.controls[component].dirty;
  }

  isEndDateNotInPast(): boolean {
    const endDate = this.experienceForm.controls["endDate"].value;
    const currentJob = this.experienceForm.controls["currentJob"].value;

    if (currentJob) {
      return false;
    }
    if (!endDate) {
      return false;
    }

    return !this.isDateInPast(endDate);
  }

  isEndDateBeforeStartDate(): boolean {
    const endDate = this.experienceForm.controls["endDate"].value;
    const startDate = this.experienceForm.controls["startDate"].value;
    const currentJob = this.experienceForm.controls["currentJob"].value;

    if (currentJob) {
      return false;
    }
    if (!startDate || !endDate) {
      return false;
    }

    return this.isDateBefore(endDate, startDate);
  }

  isDateInPast(date: Date): boolean {
    return this.isDateBefore(date, new Date());
  }

  isDateBefore(date1: Date, date2: Date): boolean {
    return date1 < date2;
  }

  get endDate() {
    return this.experienceForm.controls["endDate"].value;
  }

  get startDate() {
    return this.experienceForm.controls["startDate"].value;
  }

  get maxStartDate() {
    return this.today > this.endDate ? this.today : this.endDate;
  }
}
