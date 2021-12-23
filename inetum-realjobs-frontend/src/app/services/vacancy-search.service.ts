import {Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {VacancyFilterFields} from "../models/vacancy-filter-fields.model";
import {Vacancy} from "../models/vacancy";

@Injectable({
  providedIn: "root",
})
export class VacancySearchService {

  vacancies: Vacancy[] = [];

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
}
