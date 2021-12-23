import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {Resume} from "../models/resume";

const baseUrl = "resume/";

@Injectable({
  providedIn: "root",
})
export class ResumeService {

  constructor(private http: HttpClient) {
  }

  createResume(resume: Resume): Observable<Resume> {
    return this.http.post<Resume>(baseUrl + "create", resume);
  }
}
