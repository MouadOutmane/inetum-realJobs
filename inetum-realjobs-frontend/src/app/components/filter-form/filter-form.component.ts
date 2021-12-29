import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { VacancyFilterFields } from '../../models/vacancy-filter-fields.model';
import { VacancySearchService } from '../../services/vacancy-search.service';
import { Vacancy } from '../../vacancy';
import { TableModule } from 'primeng/table';


@Component({
  selector: 'app-filter-form',
  templateUrl: './filter-form.component.html',
  styleUrls: ['./filter-form.component.css']
})
export class FilterFormComponent implements OnInit {
  
  filter: VacancyFilterFields ={
    functionTitle: "",
    contractType: "",
    industry: "",
    country: 0,
    requiredYearsOfExperience: 0
  };
  vacancies$: Observable<Vacancy[]>

  countries: string[] =["Belgium", "France"];
  
  constructor(private vacancySearchService: VacancySearchService) {
    this.vacancies$ = this.vacancySearchService.getAllVacancies();
   }

  ngOnInit(): void {
    
  }

  getFilteredVacancies(){
    this.vacancies$ = this.vacancySearchService.getFilteredVacancies(this.filter);
  }

  onSubmit(){
    this.getFilteredVacancies();
  }

}
