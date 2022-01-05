import {Component, OnInit, Output} from "@angular/core";
import {FormBuilder} from "@angular/forms";
import {Router} from "@angular/router";
import {Country} from "src/app/models/country";
import {Gender} from "src/app/models/gender.enum";
import {Roles} from "src/app/models/roles.enum";
import {User} from "src/app/models/user";
import {AuthenticationService} from "src/app/services/authentication.service";
import {MessageService} from "primeng/api";
import {CountryService} from "src/app/services/country.service";
import {Observable} from "rxjs";

import {STEP_ITEMS} from "../../constants/register-multi-step-form";


@Component({
  selector: "app-register",
  templateUrl: "./register.component.html",
  styleUrls: ["./register.component.scss"],
  providers: [MessageService],
})
export class RegisterComponent implements OnInit {
  submitted: boolean;

  error: boolean;

  formContent: any;
  formData: any;

  @Output()
  genders: Gender[];

  selectedRole: any = null;

  @Output()
  countries$: Observable<Country[]>;

  @Output()
  roles: Array<Roles>;

  user: User;

  constructor(private authService: AuthenticationService, private router: Router,
              private formBuilder: FormBuilder, private messageService: MessageService,
              private countryService: CountryService) {
  }

  ngOnInit(): void {
    this.countries$ = this.countryService.getAllCountries();
    this.genders = Object.keys(Gender).map(key =>
      Gender[key].toString());
    console.log(this.genders);
    this.roles = Object.keys(Roles).map(key => Roles[key].toString());
    this.formContent = STEP_ITEMS;
    console.log(this.genders);
  }

  register({data: formData}) {
    this.formData = formData;
    console.log(this.formData);
  }

  //
  // ngOnInit(): void {
  //   this.registerForm = this.formBuilder.group({
  //     email: ["", [Validators.required, Validators.email]],
  //     password: ["", [Validators.required, Validators.minLength(4)]],
  //     password_repeat: ["", [Validators.required, Validators.minLength(4)]],
  //     role: ["", Validators.required],
  //     gender: ["", Validators.required],
  //     firstName: ["", [Validators.required, Validators.pattern("^[A-Za-z0-9ñÑáéíóúÁÉÍÓÚ ]+$")]],
  //     lastName: ["", [Validators.required, Validators.pattern("^[A-Za-z0-9ñÑáéíóúÁÉÍÓÚ ]+$")]],
  //     dateOfBirth: ["", Validators.required],
  //     streetName: ["", [Validators.required, Validators.pattern("^[A-Za-z0-9ñÑáéíóúÁÉÍÓÚ ]+$")]],
  //     houseNumber: ["", [Validators.required, Validators.pattern("^[0-9]*$")]],
  //     box: [""],
  //     city: ["", [Validators.required, Validators.pattern("^[A-Za-z0-9ñÑáéíóúÁÉÍÓÚ ]+$")]],
  //     postalCode: ["", [Validators.required, Validators.pattern("^[0-9]*$")]],
  //     country: ["", Validators.required],
  //     mobilePhone: ["", [Validators.required]],
  //   });
  //   this.error = false;
  //   this.submitted = false;
  //   this.getCountries();
  //
  // }
  //
  // register() {
  //   if (this.registerForm.valid) {
  //     this.user = this.getUser();
  //     this.authService.register(this.user).subscribe(() => {
  //       this.onSuccess();
  //     }, error => {
  //       this.onError(error.error.message);
  //     });
  //   } else {
  //     this.error = true;
  //   }
  // }
  //
  // verify(control: AbstractControl): boolean {
  //   return control.touched || control.dirty;
  // }
  //
  // getKeyName(value: string): string {
  //   return Object.entries(Roles).find(([, val]) => val === value)?.[0];
  // }
  //
  // unmatchPassword(): boolean {
  //   let password = this.registerForm.controls["password"].value;
  //   let password_repeat = this.registerForm.controls["password_repeat"].value;
  //   return password !== password_repeat;
  // }
  //
  // isDateBeforeToday(): boolean {
  //   let pickedDate = new Date(this.registerForm.controls["dateOfBirth"].value);
  //   return new Date(pickedDate) > new Date(new Date().toDateString());
  // }
  //
  // sleep(ms) {
  //   return new Promise((resolve) => {
  //     setTimeout(resolve, ms);
  //   });
  // }
  //
  // showSuccess() {
  //   this.messageService.add({key: "tl", severity: "success", summary: "Success", detail: "Registration successful"});
  // }
  //
  // showError(errorMessage: string) {
  //   this.messageService.add({key: "tl", severity: "error", summary: "Error", detail: errorMessage});
  // }
  //
  // async onSuccess() {
  //   this.submitted = true;
  //   this.showSuccess();
  //   await this.sleep(1000);
  //   this.router.navigate(["login"]);
  //   this.error = false;
  // }
  //
  // onError(error: string) {
  //   this.error = true;
  //   this.submitted = false;
  //   this.showError(error);
  // }
  //
  // onlyPositive(event: any, controlName: string) {
  //   if (event.target.value < 1 || !this.hasNumber(event.target.value)) {
  //     this.registerForm.get(controlName).patchValue(1);
  //   }
  // }
  //
  // onlyLetters(event: any, controlName: string) {
  //   if (this.hasNumber(event.target.value)) {
  //     this.registerForm.get(controlName).patchValue("");
  //   }
  // }
  //
  // private hasNumber(myString): boolean {
  //   return /\d/.test(myString);
  // }
  //
  // private getUser(): User {
  //   let user: User = new User();
  //   user.email = this.registerForm.controls["email"].value;
  //   user.password = this.registerForm.controls["password"].value;
  //   user.role = this.getKeyName(this.registerForm.controls["role"].value);
  //   user.gender = this.registerForm.controls["gender"].value;
  //   user.firstName = this.registerForm.controls["firstName"].value;
  //   user.lastName = this.registerForm.controls["lastName"].value;
  //   user.birthDate = new Date(this.registerForm.controls["dateOfBirth"].value).toJSON();
  //   user.mobilePhone = this.registerForm.controls["mobilePhone"].value;
  //   user.address = this.getAddress();
  //   return user;
  // }
  //
  // private getAddress(): Address {
  //   let address1: Address = new Address();
  //   address1.box = this.registerForm.controls["box"].value;
  //   address1.streetName = this.registerForm.controls["streetName"].value;
  //   address1.houseNumber = this.registerForm.controls["houseNumber"].value;
  //   address1.city = this.registerForm.controls["city"].value;
  //   address1.postalCode = this.registerForm.controls["postalCode"].value;
  //   address1.country = this.registerForm.controls["country"].value;
  //   return address1;
  // }
  //
}
