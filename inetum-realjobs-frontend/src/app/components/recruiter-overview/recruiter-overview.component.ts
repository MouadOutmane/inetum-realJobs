import {Component, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {RecruiterOverviewModel} from "../../models/recruiter-overview.model";
import {RecruiterService} from "../../services/recruiter.service";
import {AuthenticationService} from "../../services/authentication.service";
import {ActivatedRoute, Router} from "@angular/router";
import {VacancyService} from "../../services/vacancy.service";

@Component({
  selector: 'app-recruiter-overview',
  templateUrl: './recruiter-overview.component.html',
  styleUrls: ['./recruiter-overview.component.scss']
})
export class RecruiterOverviewComponent implements OnInit {
  vacancies$: Observable<RecruiterOverviewModel[]>;
  title: string = "Overview: My vacancies";
  columns: any[];
  defaultMessage: string = "Your vacancies will appear here." +
    " Get started and post your first vacancy by clicking 'Post new vacancy'";
  first: number = 0;
  rows: number = 10;
  vacancies: RecruiterOverviewModel[] = [];
  length: number = this.getArrayLength();
  username: string;

  constructor(private recruiterService: RecruiterService,
              private auth: AuthenticationService,
              private activatedRoute: ActivatedRoute,
              private vacancyService: VacancyService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.getAllVacancies();
    this.columns = [
      {field: 'vacancyId', header: 'Vacancy ID'},
      {field: 'functionTitle', header: 'Function title'},
      {field: 'createdOn', header: 'Date posted'},
      {field: 'recruiterId', header: 'Vacancy manager'},
      {field: 'applicants', header: 'Applicants'}
    ];
    this.username = this.auth.getLoggedInUserEmail();
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

  navigateToVacancy(id: number) {
    this.router.navigate(['vacancy', id]);
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

  getArrayLength(): number {
    this.recruiterService.getAllVacancies().subscribe(nuts => this.vacancies = nuts);
    console.log('length = ' + this.vacancies.length);
    return this.vacancies.length;
  }
}
