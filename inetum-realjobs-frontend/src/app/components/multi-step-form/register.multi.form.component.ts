import {Component, EventEmitter, Input, OnInit, Output} from "@angular/core";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Observable} from "rxjs";
import {Country} from "../../models/country";
import {Gender} from "../../models/gender.enum";
import {Roles} from "../../models/roles.enum";

//TODO: move this to register
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

  @Input() formStep1: any;

  activeStepIndex: number;
  masterForm: Array<FormGroup>;
  stepOneForm: FormGroup;

  constructor(
    private readonly _formBuilder: FormBuilder
  ) {
  }

  ngOnInit() {



    // this.stepItems.forEach((data, i) => {
    //   this.currentFormContent.push(this.stepItems[i]["data"]);
    //   this.formFields.push(Object.keys(this.currentFormContent[i]));
    //   this.masterForm.push(this.buildForm(this.currentFormContent[i]));
    // });
  }

  setPreviousForm(){
    this.activeStepIndex -= 1;
  }

  setNextForm(){
    this.activeStepIndex += 1;
  }

  onFormSubmit(){

  }
}
