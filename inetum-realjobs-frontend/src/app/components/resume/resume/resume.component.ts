import {Component, OnInit} from '@angular/core';
import {ResumeService} from "../../../services/resume.service";
import {Resume} from "../../../models/resume";
import {catchError, throwError} from "rxjs";
import {HttpErrorResponse} from "@angular/common/http";
import {MessageService} from "primeng/api";
import {Education} from "../../../models/education";
import {Experience} from "../../../models/experience";
import {Language} from "../../../models/language";
import {Skill} from "../../../models/skill";

@Component({
  selector: 'app-resume',
  templateUrl: './resume.component.html',
  styleUrls: ['./resume.component.scss'],
  providers: [MessageService],
})
export class ResumeComponent implements OnInit {

  resume: Resume;
  isLoading: boolean = true;

  constructor(private resumeService: ResumeService,
              private messageService: MessageService) {
  }

  ngOnInit(): void {
    this.resumeService
      .getResume()
      .pipe(catchError(err => this.onError(err)))
      .subscribe(resume => {
        this.resume = resume;
        this.isLoading = false;
      });
  }

  onError(error: HttpErrorResponse) {
    this.messageService.add({key: "tl", severity: "error", summary: "Error", detail: error.error.message});
    return throwError(() => error.message);
  }

  updateSummary(summary: string) {
    this.resume.summary = summary;
  }

  updateEducation(education: Education[]) {
    this.resume.educationList = education;
  }

  updateExperience(experience: Experience[]) {
    this.resume.experienceList = experience;
  }

  updateLanguage(language: Language[]) {
    this.resume.languages = language;
  }

  updateSkills(skills: Skill[]) {
    this.resume.skills = skills;
  }
}
