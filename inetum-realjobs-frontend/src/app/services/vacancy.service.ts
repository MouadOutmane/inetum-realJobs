import {Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable, of, catchError} from "rxjs";
import {VacancyFilterFields} from "../models/vacancy-filter-fields.model";
import {Vacancy} from "../models/vacancy";
import {Application} from "../models/application";

@Injectable({
  providedIn: "root",
})
export class VacancyService {

  vacancies: Vacancy[] = [];
  allVacanciesUrl: string = "vacancies/all";
  newVacancyUrl: string = "vacancies/create";


  constructor(private httpClient: HttpClient) {
  }

  getFilteredVacancies(filter: VacancyFilterFields): Observable<Vacancy[]> {
    let params = new HttpParams();
    params = params.append("functionTitle", filter.functionTitle);
    params = params.append("contractType", filter.contractType);
    params = params.append("industry", filter.industry);
    params = params.append("country", filter.country);
    params = params.append("requiredYearsOfExperience", filter.requiredYearsOfExperience);

    return this.httpClient
      .get<Vacancy[]>("vacancies/",
        {observe: "body", responseType: "json", params: params});
  }

  getVacancy(id: number): Observable<Vacancy> {
    return this.httpClient.get<Vacancy>(
      `vacancies/${id}`,
      {observe: "body", responseType: "json"},
    );
  }

  getApplications(vacancyId: number): Observable<Application[]> {
    return this.httpClient
      .get<Application[]>(`vacancies/${vacancyId}/applications`,
        {observe: "body", responseType: "json"});
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    };
  }

  getAllVacancies(): Observable<Vacancy[]> {
    return this.httpClient.get<Vacancy[]>(this.allVacanciesUrl, {observe: "body", responseType: "json"})
      .pipe(
        catchError(this.handleError<Vacancy[]>('getAllVacancies', []))
      );
  }

  createVacancy(vacancy: Vacancy): Observable<Vacancy> {
    return this.httpClient.post<Vacancy>(this.newVacancyUrl, vacancy)
      .pipe(
        catchError(this.handleError<Vacancy>("createVacancy", vacancy))
      );
  }

}
