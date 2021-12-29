import {Component, OnInit} from "@angular/core";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthenticationService} from "../../services/authentication.service";

@Component({
  selector: "app-forgot-password",
  templateUrl: "./forgot-password.component.html",
  styleUrls: ["./forgot-password.component.scss"],
})
export class ForgotPasswordComponent implements OnInit {

  forgotPasswordForm: FormGroup;
  message: { message: string, error: boolean } = {message: undefined, error: false};
  loading: boolean = false;
  disabled: boolean = false;

  constructor(private formBuilder: FormBuilder,
              private authenticationService: AuthenticationService) {
  }

  ngOnInit(): void {
    this.forgotPasswordForm = this.formBuilder.group({
      email: ["", [Validators.required, Validators.email]],
    });
  }

  resetPassword(): void {
    if (!this.forgotPasswordForm.valid) {
      return;
    }

    this.loading = true;
    this.message.error = false;
    this.message.message = undefined;

    this.authenticationService
      .requestReset(this.forgotPasswordForm.getRawValue())
      .subscribe({
        next: () => {
          this.loading = false;
          this.message.error = false;
          this.message.message = "An email has been sent to your email account";
        },
        error: (error) => {
          let message;
          if (error?.error?.message) {
            switch (error.error.message) {
              case "EMAIL_NOT_CORRECT":
                message = "The email is incorrect, enter a valid email address.";
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

}
