import {Component, OnInit} from '@angular/core';
import {VacancyService} from "../../services/vacancy.service";
import {Vacancy} from "../../models/vacancy";
import {Observable} from "rxjs";

@Component({
  selector: 'app-recruiter-overview',
  templateUrl: './recruiter-overview.component.html',
  styleUrls: ['./recruiter-overview.component.scss']
})
export class RecruiterOverviewComponent implements OnInit {
  vacancies$: Observable<Vacancy[]>;
  title: string = "Overview: My vacancies";
  columns: any[];
  defaultMessage: string = "Your vacancies will appear here." +
    " Get started and post your first vacancy by clicking 'Post new vacancy'";

  constructor(private vacancyService: VacancyService) {
  }

  ngOnInit(): void {
    this.getAllVacancies();
    this.columns = [
      {field: 'functionTitle', header: 'Function title'},
      {field: 'createdOn', header: 'Date posted'},
      {field: 'recruiterId', header: 'Vacancy manager'},
      {field: 'applicants', header: 'Applicants'}
    ];
  }

  getAllVacancies(): Observable<Vacancy[]>{
    return this.vacancies$ = this.vacancyService.getAllVacancies();
  }

}
