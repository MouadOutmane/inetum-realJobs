import {Component, OnInit} from '@angular/core';
import {Observable, count} from "rxjs";
import {RecruiterOverviewModel} from "../../models/recruiter-overview.model";
import {RecruiterService} from "../../services/recruiter.service";
import {AuthenticationService} from "../../services/authentication.service";
import {ActivatedRoute, Router} from "@angular/router";
import {VacancyService} from "../../services/vacancy.service";
import {SortEvent} from "primeng/api";
import {Vacancy} from "../../models/vacancy";
import {Notification} from "../../models/notification.model";
import {NotificationComponent} from "../notification/notification.component";

@Component({
  selector: 'app-recruiter-overview',
  templateUrl: './recruiter-overview.component.html',
  styleUrls: ['./recruiter-overview.component.scss'],
  styles: [`.applicants {font-weight: bold;}`]
})
export class RecruiterOverviewComponent implements OnInit {
  title: string = "Overview: My vacancies";
  vacancies: RecruiterOverviewModel[] = [];
  vacancies$: Observable<RecruiterOverviewModel[]>;
  first: number = 0;
  rows: number = 5;
  username: string;
  notifications: NotificationComponent[] = [];
  notifications$: Observable<Notification[]>;
  newVacancy$: Observable<Vacancy>;
  newVacancy: Vacancy;

  constructor(private recruiterService: RecruiterService,
              private auth: AuthenticationService,
              private activatedRoute: ActivatedRoute,
              private vacancyService: VacancyService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.getAllVacancies();
    this.username = this.auth.getLoggedInUserEmail();
    this.getApplicationsUpdate();
  }

  getAllVacancies(): Observable<RecruiterOverviewModel[]> {
    this.vacancies$ = this.recruiterService.getAllVacancies();
    this.vacancies$.subscribe({
      next(x) {console.log(x)},
      error(error) {console.log(error)},
      complete() {console.log("getAllVacancies has finished")}
    });
    return this.vacancies$;
  }

  getApplicationsUpdate(): Observable<Notification[]> {
    this.notifications$ = this.recruiterService.getApplicationsUpdate();
    this.notifications$.subscribe({
      next(x) {console.log(x)},
      error(error) {console.log(error)},
      complete() {console.log("getApplicationsUpdate has finished")}
    });
    return this.notifications$;
  }

  createVacancy() {
    this.navigateToCreateVacancy();
    this.newVacancy$ = this.vacancyService.createVacancy(this.newVacancy);
    this.newVacancy$.subscribe((vacancy) => this.newVacancy = vacancy);
  }

  navigateToVacancy(id: number) {
    this.router.navigate(['vacancy', id]);
  }

  navigateToCreateVacancy() {
    this.router.navigate(['vacancy', 'create']);
  }

  navigateToApplicants(id: number) {
    this.router.navigate(['vacancy', id, 'applications']);
  }

  next(): number {
    return this.first = this.first + this.rows;
  }

  previous(): number {
    return this.first = this.first - this.rows;
  }

  reset(): void {
    this.first = 0;
  }

  isLastPage(): boolean {
    return this.vacancies ? this.first === (length - this.rows) : true;
  }

  isFirstPage(): boolean {
    return this.vacancies ? this.first === 0 : true;
  }

  customSort(event: SortEvent) {
    event.data.sort(
      (data1, data2) => {
        let value1 = data1[event.field];
        let value2 = data2[event.field];
        let result;
        if (value1 == null && value2 != null) {
          result = -1;
        } else if (value1 != null && value2 == null) {
          result = 1;
        } else if (value1 == null && value2 == null) {
          result = 0;
        } else if (typeof value1 === 'string' && typeof value2 === 'string') {
          result = value1.localeCompare(value2);
        } else {
          result = (value1 < value2) ? -1 : (value1 > value2) ? 1 : 0;
        }
        return (event.order * result);
      }
    );
  }
}
