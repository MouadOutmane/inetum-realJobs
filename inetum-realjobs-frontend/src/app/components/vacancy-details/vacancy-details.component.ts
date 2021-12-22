import {Component, OnInit} from "@angular/core";
import {ActivatedRoute, ParamMap} from "@angular/router";
import {catchError, Observable, throwError} from "rxjs";
import {Vacancy} from "../../models/vacancy";
import {VacancyService} from "../../services/vacancy.service";

@Component({
  selector: "app-vacancy-details",
  templateUrl: "./vacancy-details.component.html",
  styleUrls: ["./vacancy-details.component.scss"],
})
export class VacancyDetailsComponent implements OnInit {

  vacancy$: Observable<Vacancy>;
  error: any = null;

  constructor(
    private route: ActivatedRoute,
    private vacancyService: VacancyService,
  ) {
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe((params: ParamMap) => {
      this.vacancy$ = this.vacancyService
        .getVacancy(parseInt(params.get("id")))
        .pipe(
          catchError(error => {
            this.error = error;
            return throwError(() => error);
          }),
        );
    });
  }

}
