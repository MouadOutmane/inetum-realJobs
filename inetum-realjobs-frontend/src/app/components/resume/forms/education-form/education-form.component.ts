import {Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import {FormGroup, FormBuilder, Validators} from "@angular/forms";
import {Education} from "../../../../models/education";
import {ResumeService} from "../../../../services/resume.service";
import {MessageService, ConfirmationService} from "primeng/api";
import {catchError, throwError} from "rxjs";
import {HttpErrorResponse} from "@angular/common/http";
import CustomValidators from "../../../../validators/CustomValidators";
import {DEGREE_OPTIONS} from "../../../../models/degree.enum";

@Component({
  selector: 'app-education-form',
  templateUrl: './education-form.component.html',
  styleUrls: ['./education-form.component.scss']
})
export class EducationFormComponent implements OnInit {

  @Input() dob: Date;
  @Input() educationList: Education[];
  @Output() formCloseEvent = new EventEmitter<null>();
  @Output() educationUpdatedEvent = new EventEmitter<Education[]>();

  degreeOptions = DEGREE_OPTIONS;
  educationForm: FormGroup;
  today: Date = new Date();
  birthDate: Date = undefined;
  editingData: { isEditing: boolean, id: number, version: number } = {
    isEditing: false,
    id: undefined,
    version: undefined
  };
  openFormMessage = false;

  constructor(private resumeService: ResumeService,
              private messageService: MessageService,
              private confirmationService: ConfirmationService,
              private formBuilder: FormBuilder) {
  }

  ngOnInit(): void {
    this.educationForm = this.formBuilder.group({
      degree: [undefined, [Validators.required]],
      program: ["", [Validators.required]],
      school: ["", [Validators.required]],
      startDate: [undefined, [Validators.required, CustomValidators.dateInPastValidator()]],
      endDate: [undefined, [Validators.required]],
      description: [""],
    }, {
      validators: CustomValidators.date1AfterDate2Validator("endDate", "startDate")
    });
    if (this.dob !== undefined) {
      this.birthDate = new Date(this.dob);
    }
  }

  editItem(education: Education) {
    this.educationForm.get("degree").setValue(education.degree);
    this.educationForm.get("program").setValue(education.program);
    this.educationForm.get("school").setValue(education.school);
    this.educationForm.get("startDate").setValue(new Date(education.startDate));
    this.educationForm.get("endDate").setValue(new Date(education.endDate));
    this.educationForm.get("description").setValue(education.description);
    this.editingData = {
      isEditing: true,
      id: education.id,
      version: education.version
    }
  }

  cancelEdit() {
    this.editingData = {
      isEditing: false,
      id: undefined,
      version: undefined
    }
    this.educationForm.reset();
  }

  submitForm() {
    if (this.educationForm.valid) {
      if (this.editingData.isEditing) {
        this.resumeService
          .editEducation({
            id: this.editingData.id,
            version: this.editingData.version,
            ...this.getFormData()
          })
          .pipe(catchError((err) => this.onError(err)))
          .subscribe(educationList => {
            this.educationUpdatedEvent.emit(educationList);
            this.educationForm.reset();
          });
      } else {
        this.resumeService
          .addEducation(this.getFormData())
          .pipe(catchError((err) => this.onError(err)))
          .subscribe(educationList => {
            this.educationUpdatedEvent.emit(educationList);
            this.educationForm.reset();
          });
      }
    }
  }

  onError(error: HttpErrorResponse) {
    this.messageService.add({key: "tl", severity: "error", summary: "Error", detail: error.error.message});
    return throwError(() => error.message);
  }

  closeForm() {
    this.formCloseEvent.emit();
  }

  showDialog() {
    this.openFormMessage = true;
  }

  confirm(index: number) {
    if (this.editingData.isEditing && this.editingData.id === index) {
      this.showDialog();
      return;
    }
    this.confirmationService.confirm({
      header: "You are about to delete an education",
      message: "Are you sure you want to delete this education?",
      icon: "pi pi-exclamation-triangle",
      accept: () => this.deleteEducation(index)
    });
  }

  deleteEducation(index: number) {
    this.resumeService
      .removeEducation(index)
      .pipe(catchError((err) => this.onError(err)))
      .subscribe(educationList => {
        this.educationUpdatedEvent.emit(educationList);
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
    return this.today;
  }

  get minStartDate() {
    return this.birthDate;
  }

  get minEndDate() {
    return this.startDate > this.birthDate ? this.startDate : this.birthDate;
  }
}
