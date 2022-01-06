import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Application} from "../models/application";
import {ApplicationStatus} from "../models/application-status.enum";

@Injectable({
  providedIn: "root",
})
export class ApplicationService {

  constructor(private httpClient: HttpClient) {
  }

  updateApplication(id: number, status: ApplicationStatus): Observable<Application> {
    return this.httpClient.put<Application>(`applications/${id}/status`, {status});
  }
}
