import {Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Experience} from "../../../../models/experience";
import {MessageService, ConfirmationService} from "primeng/api";
import {ResumeService} from "../../../../services/resume.service";
import {catchError, throwError} from "rxjs";
import {HttpErrorResponse} from "@angular/common/http";
import CustomValidators from "../../../../validators/CustomValidators";
import {INDUSTRY_OPTIONS} from "../../../../models/industry.enum";
import {FUNCTION_CATEGORY_OPTIONS} from "../../../../models/functionCategory.enum";

@Component({
  selector: "app-experience-form",
  templateUrl: "./experience-form.component.html",
  styleUrls: ["./experience-form.component.scss"],
})
export class ExperienceFormComponent implements OnInit {

  @Input() dob: Date;
  @Input() experienceList: Experience[];
  @Output() formCloseEvent = new EventEmitter<null>();
  @Output() experienceUpdatedEvent = new EventEmitter<Experience[]>();

  experienceForm: FormGroup;
  today: Date = new Date();
  birthDate: Date = undefined;
  industryOptions = INDUSTRY_OPTIONS;
  functionCategoryOptions = FUNCTION_CATEGORY_OPTIONS;
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
    this.experienceForm = this.formBuilder.group({
      jobTitle: ["", [Validators.required]],
      functionCategory: ["", [Validators.required]],
      company: ["", [Validators.required]],
      industry: ["", [Validators.required]],
      startDate: [undefined, [Validators.required, CustomValidators.dateInPastValidator()]],
      endDate: [undefined, [CustomValidators.dateInPastValidator()]],
      currentJob: [false],
      description: ["", [Validators.required]],
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
    if (this.dob !== undefined) {
      this.birthDate = new Date(this.dob);
    }
  }

  editItem(experience: Experience) {
    this.experienceForm.get("jobTitle").setValue(experience.jobTitle);
    this.experienceForm.get("functionCategory").setValue(experience.functionCategory);
    this.experienceForm.get("company").setValue(experience.company);
    this.experienceForm.get("industry").setValue(experience.industry);
    this.experienceForm.get("startDate").setValue(new Date(experience.startDate));
    this.experienceForm.get("endDate").setValue(new Date(experience.endDate));
    this.experienceForm.get("currentJob").setValue(experience.currentJob);
    this.experienceForm.get("description").setValue(experience.description);
    this.editingData = {
      isEditing: true,
      id: experience.id,
      version: experience.version
    }
  }

  cancelEdit() {
    this.editingData = {
      isEditing: false,
      id: undefined,
      version: undefined
    }
    this.experienceForm.reset();
  }

  submitForm() {
    if (this.experienceForm.valid) {
      if (this.editingData.isEditing) {
        this.resumeService
          .editExperience({
            id: this.editingData.id,
            version: this.editingData.version,
            ...this.getFormData()
          })
          .pipe(catchError((err) => this.onError(err)))
          .subscribe(experienceList => {
            this.experienceUpdatedEvent.emit(experienceList);
            this.experienceForm.reset();
          });
      } else {
        this.resumeService
          .addExperience(this.getFormData())
          .pipe(catchError((err) => this.onError(err)))
          .subscribe(experienceList => {
            this.experienceUpdatedEvent.emit(experienceList);
            this.experienceForm.reset();
          });
      }
    }
  }

  onError(error: HttpErrorResponse) {
    this.messageService.add({key: "tl", severity: "error", summary: "Error", detail: error.message});
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
      header: "You are about to delete an experience",
      message: "Are you sure you want to delete this experience?",
      icon: "pi pi-exclamation-triangle",
      accept: () => this.deleteExperience(index)
    });
  }

  deleteExperience(index: number) {
    this.resumeService
      .removeExperience(index)
      .pipe(catchError((err) => this.onError(err)))
      .subscribe(experienceList => {
        this.experienceUpdatedEvent.emit(experienceList);
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
    return this.today;
  }

  get minStartDate() {
    return this.birthDate;
  }

  get minEndDate() {
    return this.startDate > this.birthDate ? this.startDate : this.birthDate;
  }
}
