import {Component, OnInit} from "@angular/core";
import {catchError, Observable, throwError} from "rxjs";
import {ActivatedRoute, ParamMap} from "@angular/router";
import {VacancyService} from "../../services/vacancy.service";
import {Application} from "../../models/application";

@Component({
  selector: "app-vacancy-applications",
  templateUrl: "./vacancy-applications.component.html",
  styleUrls: ["./vacancy-applications.component.scss"],
})
export class VacancyApplicationsComponent implements OnInit {

  id: number;
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

      this.applications$ = this.vacancyService
        .getApplications(this.id)
        .pipe(
          catchError(error => {
            this.error = error;
            return throwError(() => error);
          }),
        );

      this.applications$.subscribe(value => {
        console.log("applications", value);
      });
    });
  }

}
