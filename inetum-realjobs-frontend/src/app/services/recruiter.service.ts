import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, of, catchError} from "rxjs";
import {RecruiterOverviewModel} from "../models/recruiter-overview.model";

@Injectable({
  providedIn: 'root'
})
export class RecruiterService {
  allVacanciesUrl: string = "vacancies/all";

  constructor(private http: HttpClient) {
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    };
  }

  getAllVacancies(): Observable<RecruiterOverviewModel[]> {
    return this.http.get<RecruiterOverviewModel[]>(this.allVacanciesUrl, {observe: "body", responseType: "json"})
      .pipe(
        catchError(this.handleError<RecruiterOverviewModel[]>('getAllVacancies', []))
      );
  }
}
