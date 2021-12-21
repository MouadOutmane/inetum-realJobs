import {Component, OnInit} from "@angular/core";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {AuthenticationService} from "src/app/services/authentication.service";

@Component({
  selector: "app-add-user",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.scss"],
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;

  badCredentials: boolean = false;

  constructor(private userService: AuthenticationService,
              private router: Router, private formBuilder: FormBuilder) {
  }

  get form() {
    return this.loginForm.controls;
  }

  get email() {
    return this.loginForm.get("email");
  }

  get password() {
    return this.loginForm.get("password");
  }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      email: ["", [Validators.required, Validators.email]],
      password: ["", Validators.required],
    });
    this.loginForm.valueChanges.subscribe(() => {
      this.badCredentials = false;
    });
  }

  login() {
    if (!this.loginForm.valid) return;

    this.userService.login(this.loginForm.getRawValue())
      .subscribe({
        complete: () => {
          // noinspection JSIgnoredPromiseFromCall
          this.router.navigate([""]);
          this.badCredentials = false;
        },
        error: () => this.badCredentials = true,
      });
  }

  logout() {
    this.userService.logout();
  }

  isLoggedIn(): boolean {
    return this.userService.isLoggedIn();
  }
}
