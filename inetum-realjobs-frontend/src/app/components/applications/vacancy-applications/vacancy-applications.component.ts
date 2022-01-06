import {Component, OnInit} from "@angular/core";
import {catchError, Observable, OperatorFunction, throwError} from "rxjs";
import {ActivatedRoute, ParamMap} from "@angular/router";
import {VacancyService} from "../../../services/vacancy.service";
import {Application} from "../../../models/application";
import {Vacancy} from "../../../models/vacancy";
import {ApplicationStatus} from "../../../models/application-status.enum";

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
  panel: number = 0;

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

      this.fetchApplications();
    });
  }

  fetchApplications() {
    this.applications$ = this.vacancyService
      .getApplications(this.id)
      .pipe(this.onError());
  }

  private onError<T>(): OperatorFunction<T, T> {
    return catchError(error => {
      this.error = error;
      return throwError(() => error);
    });
  }

  getApplicationsNotYetReviewed(applications: Application[]): Application[] {
    return this.filterApplications(applications, null);
  }

  getApplicationsAccepted(applications: Application[]): Application[] {
    return this.filterApplications(applications, ApplicationStatus.ACCEPTED);
  }

  getApplicationsDenied(applications: Application[]): Application[] {
    return this.filterApplications(applications, ApplicationStatus.DENIED);
  }

  private filterApplications(applications: Application[], status: ApplicationStatus): Application[] {
    return applications?.filter(application => application.status === status);
  }


}
