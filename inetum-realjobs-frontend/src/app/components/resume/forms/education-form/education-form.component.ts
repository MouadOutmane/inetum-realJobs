import {Component, OnInit} from '@angular/core';
import {FormGroup, FormBuilder, Validators} from "@angular/forms";
import {Education} from "../../../../models/education";
import {ResumeService} from "../../../../services/resume.service";
import {MessageService} from "primeng/api";
import {catchError, throwError} from "rxjs";
import {HttpErrorResponse} from "@angular/common/http";
import CustomValidators from "../../../../validators/CustomValidators";

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
      startDate: [undefined, [Validators.required, CustomValidators.dateInPastValidator()]],
      endDate: [undefined, [Validators.required]],
      description: [""],
    }, {
      validators: CustomValidators.date1AfterDate2Validator("endDate", "startDate")
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
      degree: this.degree,
      program: this.program,
      school: this.school,
      startDate: this.startDate,
      endDate: this.endDate,
      description: this.description,
    }
  }

  isInvalid(component: string): boolean {
    const fieldInvalid = this.educationForm.controls[component].invalid && this.educationForm.controls[component].dirty;
    if (component === "endDate") {
      return fieldInvalid || (this.educationForm.errors && this.educationForm.errors["date1AfterDate2"]);
    }
    return fieldInvalid;
  }

  getValidationMessage(component: string): string {
    const control = this.educationForm.controls[component];
    if (control.valid && this.educationForm.valid) return undefined;
    if (!control.valid) {
      if (control.errors["required"]) return "This field can't be empty!";
      else if (control.errors["dateInPast"]) return "This date can't be in the future";
    } else {
      if (this.educationForm.errors["date1AfterDate2"] && component === "endDate") return "The end date can't be before the start date";
    }

    return "Invalid input!";
  }

  get degree() {
    return this.educationForm.controls["degree"].value;
  }

  get program() {
    return this.educationForm.controls["program"].value;
  }

  get school() {
    return this.educationForm.controls["school"].value;
  }

  get startDate() {
    return this.educationForm.controls["startDate"].value;
  }

  get endDate() {
    return this.educationForm.controls["endDate"].value;
  }

  get description() {
    return this.educationForm.controls["description"].value;
  }

  get maxStartDate() {
    return this.today > this.endDate ? this.today : this.endDate;
  }
}
