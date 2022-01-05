import {Component, EventEmitter, Input, OnInit, Output} from "@angular/core";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Observable} from "rxjs";
import {Country} from "../../models/country";
import {Gender} from "../../models/gender.enum";
import {Roles} from "../../models/roles.enum";

@Component({
  selector: "app-multi-step-form",
  templateUrl: "register.multi.form.component.html",
  styleUrls: ["register.multi.form.component.scss"],
})
export class RegisterMultiFormComponent implements OnInit {
  @Input() formContent: any;

  @Output() readonly formSubmit: EventEmitter<any> = new EventEmitter<any>();

  @Input() countries$: Observable<Country[]>;

  @Input() genders: Gender[];

  @Input() roles: Array<Roles>;

  createResume: string;

  activeStepIndex: number;
  currentFormContent: Array<any>;
  formFields: Array<Array<string>>;
  stepItems: Array<any>;
  masterForm: Array<FormGroup>;
  formData: any;
  masterFormFields: Array<string>;

  constructor(
    private readonly _formBuilder: FormBuilder
  )
  {}

  ngOnInit() {

    this.activeStepIndex = 0;
    this.stepItems = this.formContent;
    this.currentFormContent = [];
    this.formFields = [];
    this.masterForm = [];

    this.stepItems.forEach((data, i) => {
      this.currentFormContent.push(this.stepItems[i]["data"]);
      this.formFields.push(Object.keys(this.currentFormContent[i]));
      this.masterForm.push(this.buildForm(this.currentFormContent[i]));
    });
  }

  buildForm(currentFormContent: any): FormGroup{
    const formDetails = Object.keys(currentFormContent).reduce((obj, key) => {
      obj[key] = ["", this.getValidators(currentFormContent[key])];

      return obj;
    }, {});

    return this._formBuilder.group(formDetails);
  }

  getValidators(formField: any): Validators {
    const fieldValidators = Object.keys(formField.validations).map(
      validator => {
        if (validator === "required") {
          return Validators[validator];
        } else {
          return Validators[validator](formField.validations[validator]);
        }
      }
    );

    return fieldValidators;
  }

  getValidationMessage(formIndex: number, formFieldName: string): string {
    const formErrors = this.masterForm[formIndex].get(formFieldName).errors;
    const errorMessages = this.currentFormContent[formIndex][formFieldName]
      .errors;
    const validationError = errorMessages[Object.keys(formErrors)[0]];

    return validationError;
  }

  goToStep(step: string): void {
    this.activeStepIndex =
      step === "prev" ? this.activeStepIndex - 1 : this.activeStepIndex + 1;

    this.setFormPreview();
  }

  setFormPreview(): void {
    this.formData = this.masterForm.reduce(
      (masterForm, currentForm) => ({ ...masterForm, ...currentForm.value }),
      {}
    );

    this.masterFormFields = Object.keys(this.formData);
  }

  onFormSubmit(): void {
    this.setFormPreview();
    this.formSubmit.emit({data: this.formData, newResume: this.masterForm[2].controls["resume"].value});
  }

  trackByFn(index: number): number {
    return index;
  }

}
