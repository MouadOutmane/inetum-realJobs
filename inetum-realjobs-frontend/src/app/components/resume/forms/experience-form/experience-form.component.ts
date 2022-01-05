import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Experience} from "../../../../models/experience";
import {MessageService} from "primeng/api";
import {ResumeService} from "../../../../services/resume.service";
import {catchError, throwError} from "rxjs";
import {HttpErrorResponse} from "@angular/common/http";
import CustomValidators from "../../../../validators/CustomValidators";

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
      startDate: [undefined, [Validators.required, CustomValidators.dateInPastValidator()]],
      endDate: [undefined, [CustomValidators.dateInPastValidator()]],
      currentJob: [false],
      description: [""],
    }, {
      validators: CustomValidators.date1AfterDate2Validator("endDate", "startDate")
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
      jobTitle: this.jobTitle,
      functionCategory: this.functionCategory,
      company: this.company,
      industry: this.industry,
      startDate: this.startDate,
      endDate: this.endDate,
      currentJob: this.currentJob,
      description: this.description,
    }
  }

  isInvalid(component: string): boolean {
    const fieldInvalid = this.experienceForm.controls[component].invalid && this.experienceForm.controls[component].dirty;
    if (component === "endDate") {
      return fieldInvalid || (this.experienceForm.errors && this.experienceForm.errors["date1AfterDate2"]);
    }
    return fieldInvalid;
  }

  getValidationMessage(component: string): string {
    const control = this.experienceForm.controls[component];
    if (control.valid && this.experienceForm.valid) return undefined;
    if (!control.valid) {
      if (control.errors["required"]) return "This field can't be empty!";
      else if (control.errors["dateInPast"]) return "This date can't be in the future";
    } else {
      if (this.experienceForm.errors["date1AfterDate2"] && component === "endDate") return "The end date can't be before the start date";
    }

    return "Invalid input!";
  }

  isDateInPast(date: Date): boolean {
    return this.isDateBefore(date, new Date());
  }

  isDateBefore(date1: Date, date2: Date): boolean {
    return date1 < date2;
  }

  get jobTitle() {
    return this.experienceForm.controls["jobTitle"].value;
  }

  get functionCategory() {
    return this.experienceForm.controls["functionCategory"].value;
  }

  get company() {
    return this.experienceForm.controls["company"].value;
  }

  get industry() {
    return this.experienceForm.controls["industry"].value;
  }

  get startDate() {
    return this.experienceForm.controls["startDate"].value;
  }

  get endDate() {
    return this.experienceForm.controls["endDate"].value;
  }

  get currentJob() {
    return this.experienceForm.controls["currentJob"].value;
  }

  get description() {
    return this.experienceForm.controls["description"].value;
  }

  get maxStartDate() {
    return this.today > this.endDate ? this.today : this.endDate;
  }
}
