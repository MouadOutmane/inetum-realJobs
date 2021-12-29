import { Injectable } from '@angular/core';
import { HttpClient, HttpClientModule, HttpParams, HttpResponse } from '@angular/common/http'
import { Observable } from 'rxjs';
import { VacancyFilterFields } from '../models/vacancy-filter-fields.model';
import { Vacancy } from '../vacancy';
import { FormGroup } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class VacancySearchService {

  private baseUrl = "http://localhost::8080/api/vacancies"

  vacancies: Vacancy[] = [];

  constructor(private httpClient: HttpClient) { }

  getFilteredVacancies(filter: VacancyFilterFields): Observable<Vacancy[]>{
    let params = new HttpParams();
    params = params.append('functionTitle', filter.functionTitle);
    params = params.append('contractType', filter.contractType);
    params = params.append('industry', filter.industry);
    params = params.append('country', filter.country);
    params = params.append('requiredYearsOfExperience', filter.requiredYearsOfExperience);


    let result = this.httpClient.get<Vacancy[]>('http://localhost:8080/api/vacancies/', 
      {observe:'body',  responseType :'json' , params: params})

    return result;
    
  };

  submitVacancy(vacancyForm: Vacancy): Observable<Vacancy>{
    return this.httpClient.post<Vacancy>('http://localhost:8080/api/vacancies/create', vacancyForm);

  };

  getAllVacancies(): Observable<Vacancy[]>{
    return this.httpClient.get<Vacancy[]>('http://localhost:8080/api/vacancies/all', {});
  }
}
