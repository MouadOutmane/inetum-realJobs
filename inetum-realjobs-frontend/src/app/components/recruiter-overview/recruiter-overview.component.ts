import {Component, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {RecruiterOverviewModel} from "../../models/recruiter-overview.model";
import {RecruiterService} from "../../services/recruiter.service";

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

  constructor(private recruiterService: RecruiterService) {
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
  }

  getAllVacancies(): Observable<RecruiterOverviewModel[]> {
    return this.vacancies$ = this.recruiterService.getAllVacancies();
  }

}
