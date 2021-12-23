import {Component, OnInit} from "@angular/core";
import {Gender} from "../../models/gender.enum";
import {catchError, Observable, throwError} from "rxjs";
import {Country} from "../../models/country";
import {CountryService} from "../../services/country.service";
import {ProfileService} from "../../services/profile-service";
import {Profile} from "../../models/profile.model";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ConfirmationService} from "primeng/api";


@Component({
  selector: "app-user-details",
  templateUrl: "./user-details.component.html",
  styleUrls: ["./user-details.component.scss"],
})
export class UserDetailsComponent implements OnInit {
  genders: Array<String> = [];
  countries$: Observable<Country[]>;
  profile$: Profile;
  profileForm: FormGroup;
  profileFormLoading: boolean = false;
  deleteLoading: boolean = false;
  loading: boolean = true;
   error: boolean;
   errorMessage: string;

  constructor(private countryService: CountryService,
              private profileService: ProfileService,
              private formBuilder: FormBuilder,
              private confirmationService: ConfirmationService) {

    this.profileForm = this.formBuilder.group({
      firstName: ["", [Validators.required, Validators.pattern("^[A-Za-z0-9ñÑáéíóúÁÉÍÓÚ ]+$")]],
      lastName: ["", [Validators.required, Validators.pattern("^[A-Za-z0-9ñÑáéíóúÁÉÍÓÚ ]+$")]],
      gender: ["", Validators.required],
      dateOfBirth: ["", Validators.required],
      streetName: ["", [Validators.required, Validators.pattern("^[A-Za-z0-9ñÑáéíóúÁÉÍÓÚ ]+$")]],
      houseNumber: ["", [Validators.required, Validators.pattern("^[0-9]*$")]],
      box: [""],
      city: ["", [Validators.required, Validators.pattern("^[A-Za-z0-9ñÑáéíóúÁÉÍÓÚ ]+$")]],
      postalCode: ["", [Validators.required, Validators.pattern("^[0-9]*$")]],
      country: ["", Validators.required],
      mobilePhone: ["", [Validators.required]],
    });


  }

  ngOnInit(): void {
    this.genders = Object.keys(Gender).map(key => Gender[key].toString());
    this.getCountries();
    this.profileService.getUserProfile()
      .pipe(
        catchError((err) => {
          this.handleError(err);
          return throwError(err);
        })
      )
      .subscribe(data => {
        this.profile$ = data
        this.patchProfileFormValues();
        this.loading = false;
      })
  }


  patchProfileFormValues() {
    this.profileForm.patchValue({
      firstName: this.profile$.firstName,
      lastName: this.profile$.lastName,
      gender: this.profile$.gender,
      streetName: this.profile$.address.streetName,
      houseNumber: this.profile$.address.houseNumber,
      box: this.profile$.address.box,
      city: this.profile$.address.city,
      postalCode: this.profile$.address.postalCode,
      country: this.profile$.address.country,
      mobilePhone: this.profile$.mobilePhone,
      dateOfBirth: new Date(this.profile$.dateOfBirth)
    })
  }

  private getCountries() {
    this.countries$ = this.countryService.getAllCountries();
  }

  save() {
    if (this.profileForm.dirty && this.profileForm.valid) {
      this.profileFormLoading = true;

    }
  }

  deleteAccount() {
    this.confirmationService.confirm({
      header: 'Delete Confirmation',
      message: 'Are you sure that you want to delete your account?',
      icon: 'pi pi-trash',
      accept: () => {
        this.deleteLoading = true;
      }
    })
  }

  handleError(err: any) {
    this.error = true;
    this.loading = false;
    this.errorMessage = "Error has occured, please try again..";

  }
}
