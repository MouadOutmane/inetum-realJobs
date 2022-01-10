import {Component, OnInit} from "@angular/core";
import {Observable} from "rxjs";
import {VacancyFilterFields} from "../../models/vacancy-filter-fields.model";
import {VacancyService} from "../../services/vacancy.service";
import {Vacancy} from "../../models/vacancy";
import {Router} from "@angular/router";

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

  constructor(private vacancyService: VacancyService, private router: Router) {
  }

  ngOnInit(): void {
  }

  getFilteredVacancies() {
    this.vacancies$ = this.vacancyService.getFilteredVacancies(this.filter);
  }

  onSubmit() {
    this.getFilteredVacancies();
  }

  navigateToVacancy(id: number) {
    this.router.navigate(['vacancy', id]);
  }

}
