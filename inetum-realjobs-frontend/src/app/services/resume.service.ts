import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable, map} from "rxjs";
import {Skill} from "../models/skill";
import {Language} from "../models/language";
import {Education} from "../models/education";
import {Experience} from "../models/experience";
import {ResumeStatus} from "../models/resumeStatus.enum";
import {SingleResult} from "../models/singleResult";
import {AccountResume} from "../models/accountResume";
import {Resume} from "../models/resume";

const baseUrl = "resume/";

@Injectable({
  providedIn: "root",
})
export class ResumeService {

  constructor(private http: HttpClient) {
  }

  getResume(): Observable<Resume> {
    return this.http.get<Resume>(baseUrl);
  }

  addSkill(skill: Skill): Observable<Skill[]> {
    return this.http.post<Skill[]>(baseUrl + "skill", skill);
  }

  removeSkill(id: number): Observable<Skill[]> {
    return this.http.delete<Skill[]>(baseUrl + "skill/" + id);
  }

  addLanguage(language: Language): Observable<Language[]> {
    return this.http.post<Language[]>(baseUrl + "language", language);
  }

  removeLanguage(id: number): Observable<Language[]> {
    return this.http.delete<Language[]>(baseUrl + "language/" + id);
  }

  addEducation(education: Education): Observable<Education[]> {
    return this.http.post<Education[]>(baseUrl + "education", education);
  }

  removeEducation(id: number): Observable<Education[]> {
    return this.http.delete<Education[]>(baseUrl + "education/" + id);
  }

  addExperience(experience: Experience): Observable<Experience[]> {
    return this.http.post<Experience[]>(baseUrl + "experience", experience);
  }

  removeExperience(id: number): Observable<Experience[]> {
    return this.http.delete<Experience[]>(baseUrl + "experience/" + id);
  }

  setSummary(summary: string): Observable<string> {
    return this.http.post<SingleResult<string>>(baseUrl + "summary/", {value: summary}).pipe(ResumeService.getValue());
  }

  setResumeStatus(status: ResumeStatus): Observable<ResumeStatus> {
    return this.http.post<SingleResult<ResumeStatus>>(baseUrl + "status/", {value: status}).pipe(ResumeService.getValue());
  }

  getResumeStatus(): Observable<ResumeStatus> {
    return this.http.get<SingleResult<ResumeStatus>>(baseUrl + "status/").pipe(ResumeService.getValue());
  }

  getAccountResume(): Observable<AccountResume> {
    return this.http.get<AccountResume>(baseUrl + "account/");
  }

  private static getValue() {
    return map(({value}) => value);
  }
}
