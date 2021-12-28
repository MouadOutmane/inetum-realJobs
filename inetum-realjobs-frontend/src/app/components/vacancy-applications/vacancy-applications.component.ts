import {Component, OnInit} from "@angular/core";
import {catchError, Observable, OperatorFunction, throwError} from "rxjs";
import {ActivatedRoute, ParamMap} from "@angular/router";
import {VacancyService} from "../../services/vacancy.service";
import {Application} from "../../models/application";
import {Vacancy} from "../../models/vacancy";

@Component({
  selector: "app-vacancy-applications",
  templateUrl: "./vacancy-applications.component.html",
  styleUrls: ["./vacancy-applications.component.scss"],
})
export class VacancyApplicationsComponent implements OnInit {

  id: number;
  vacancy$: Observable<Vacancy>;
  applications$: Observable<Application[]>;
  error: any = null;

  constructor(
    private route: ActivatedRoute,
    private vacancyService: VacancyService,
  ) {
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe((params: ParamMap) => {
      this.id = parseInt(params.get("id"));

      this.vacancy$ = this.vacancyService
        .getVacancy(this.id)
        .pipe(this.onError());
      this.applications$ = this.vacancyService
        .getApplications(this.id)
        .pipe(this.onError());

      this.applications$.subscribe(value => {
        console.log("applications", value);
      });
    });
  }

  private onError<T>(): OperatorFunction<T, T> {
    return catchError(error => {
      this.error = error;
      return throwError(() => error);
    });
  }

}
