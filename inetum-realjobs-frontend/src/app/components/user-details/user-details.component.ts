import {Component, OnInit} from "@angular/core";
import {Gender} from "../../models/gender.enum";
import {Observable} from "rxjs";
import {Country} from "../../models/country";
import {CountryService} from "../../services/country.service";

@Component({
  selector: "app-user-details",
  templateUrl: "./user-details.component.html",
  styleUrls: ["./user-details.component.scss"],
})
export class UserDetailsComponent implements OnInit {
  genders: Array<String> = [];
  countries$: Observable<Country[]>;

  constructor(private countryService: CountryService) {
    this.genders = Object.keys(Gender).map(key =>
      Gender[key].toString());
  }

  ngOnInit(): void {
    this.getCountries();
  }

  private getCountries() {
    this.countries$ = this.countryService.getAllCountries();
  }

}
