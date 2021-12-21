import {Component, OnInit} from "@angular/core";
import {Observable} from "rxjs";
import {VacancyFilterFields} from "../../models/vacancy-filter-fields.model";
import {VacancySearchService} from "../../services/vacancy-search.service";
import {Vacancy} from "../../models/vacancy";

@Component({
  selector: "app-filter-form",
  templateUrl: "./filter-form.component.html",
  styleUrls: ["./filter-form.component.scss"],
})
export class FilterFormComponent implements OnInit {

  filter: VacancyFilterFields = {
    functionTitle: "",
    contractType: "",
    industry: "",
    country: "",
    requiredYearsOfExperience: 0,
  };
  vacancies$: Observable<Vacancy[]>;

  constructor(private vacancySearchService: VacancySearchService) {
  }

  ngOnInit(): void {
  }

  getFilteredVacancies() {
    this.vacancies$ = this.vacancySearchService.getFilteredVacancies(this.filter);
  }

  onSubmit() {
    this.getFilteredVacancies();
  }

}
