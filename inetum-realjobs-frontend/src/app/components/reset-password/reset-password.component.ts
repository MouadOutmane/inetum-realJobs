import {Component, OnInit} from "@angular/core";
import {AbstractControl, FormBuilder, FormGroup, ValidationErrors, ValidatorFn, Validators} from "@angular/forms";
import {AuthenticationService} from "../../services/authentication.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: "app-reset-password",
  templateUrl: "./reset-password.component.html",
  styleUrls: ["./reset-password.component.scss"],
})
export class ResetPasswordComponent implements OnInit {

  forgotPasswordForm: FormGroup;
  message: { message: string, error: boolean } = {message: undefined, error: false};
  loading: boolean = false;
  code: string = "";

  constructor(private formBuilder: FormBuilder,
              private route: ActivatedRoute,
              private authenticationService: AuthenticationService) {
  }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => this.code = params["code"]);
    this.forgotPasswordForm = this.formBuilder.group(
      {
        password: ["", [Validators.required]],
        password_repeat: ["", [Validators.required]],
      },
      {validators: this.requireDuplicatePassword()});
  }

  resetPassword(): void {
    if (!this.forgotPasswordForm.valid) {
      return;
    }

    this.loading = true;
    this.message.error = false;
    this.message.message = undefined;

    this.authenticationService.resetPassword(this.code, this.forgotPasswordForm.controls["password"].value)
      .subscribe({
        next: () => {
          this.loading = false;
          this.message.error = false;
          this.message.message = "New password confirmed successfully. <a href='/login'>Click here to login.</a>";
        },
        error: (error) => {
          let message;
          if (error?.error?.message) {
            switch (error.error.message) {
              case "INVALID_CODE":
                message = "The used code is not valid.";
                break;
              default:
                message = "Something went wrong.";
                break;
            }
          } else {
            message = "Something went wrong.";
          }

          this.loading = false;
          this.message.error = true;
          this.message.message = message;
        },
      });
  }

  requireDuplicatePassword(): ValidatorFn {
    return (form: FormGroup): ValidationErrors | null => {
      if (!form.controls["password"]) return null;

      const passwordControl = form.controls["password"];
      if (!passwordControl.valid) return null;

      const passwordRepeatControl = form.controls["password_repeat"];
      if (!passwordRepeatControl.valid) return null;

      const password = passwordControl.value;
      const passwordRepeat = passwordRepeatControl.value;
      if (!password || !passwordRepeat) return null;

      console.log("password", {password, passwordRepeat});
      const forbidden = password !== passwordRepeat;
      return forbidden ? {duplicatePassword: true} : null;
    };
  }

  getGlobalValidationMessages(): string {
    const form: FormGroup = this.forgotPasswordForm;
    if (form.valid || !form.errors) return undefined;

    let message: string;

    if (form.errors["duplicatePassword"]) message = "Both passwords should be the same!";
    else message = "Invalid data!";

    return message;
  }

  getValidationMessage(control: AbstractControl): string {
    if (control.valid) return undefined;

    if (control.errors["required"]) return "Field can't be empty!";
    else if (control.errors["duplicatePassword"]) return "Both passwords should be the same!";

    console.log("getValidationMessage", control.errors);
    return "Invalid input!";
  }

}
