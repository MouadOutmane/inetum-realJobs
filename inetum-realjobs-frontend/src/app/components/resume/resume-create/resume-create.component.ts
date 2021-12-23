import {Component, OnInit} from '@angular/core';
import {FormGroup, FormBuilder, Validators} from "@angular/forms";
import {ResumeService} from "../../../services/resume.service";
import {Resume} from "../../../models/resume";
import {ResumeStatus} from "../../../models/resumeStatus.enum";
import {MessageService} from "primeng/api";
import {Language} from "../../../models/language";
import {Skill} from "../../../models/skill";
import {Experience} from "../../../models/experience";
import {Education} from "../../../models/education";

@Component({
  selector: "resume-create",
  templateUrl: "./resume-create.component.html",
  styleUrls: ["resume-create.component.scss"],
  providers: [MessageService],
})
export class ResumeCreateComponent implements OnInit {

  creationForm: FormGroup;
  experienceList: Experience[];
  educationList: Education[];
  skills: Skill[];
  languages: Language[];
  resumeStatusOptions: ResumeStatus[];

  constructor(private resumeService: ResumeService,
              private messageService: MessageService,
              private formBuilder: FormBuilder) {
    this.resumeStatusOptions = Object.keys(ResumeStatus)
      .filter(key => !isNaN(Number(key)))
      .map(key => ResumeStatus[key]);
    this.experienceList = [];
    this.educationList = [];
    this.skills = [];
    this.languages = [];
  }

  ngOnInit(): void {
    this.creationForm = this.formBuilder.group({
      summary: ["", [Validators.required]],
      status: [undefined, [Validators.required]],
    });
  }

  submitForm() {
    if (this.creationForm.valid) {
      this.resumeService.createResume(this.getFormData()).subscribe(() => {
        this.onSuccess();
      }, error => {
        this.onError(error.error.message);
      })
    }
  }

  private getFormData(): Resume {
    return {
      summary: this.creationForm.controls["summary"].value,
      status: this.creationForm.controls["status"].value,
      educationList: this.educationList,
      experienceList: this.experienceList,
      languages: this.languages,
      skills: this.skills,
    }
  }

  onSuccess() {
  }

  onError(error: string) {
    this.messageService.add({key: "tl", severity: "error", summary: "Error", detail: error});
  }

  addExperience(newExperience: Experience) {
    this.experienceList.push(newExperience);
  }

  removeExperience(index: number) {
    this.experienceList.splice(index, 1);
  }

  addEducation(newEducation: Education) {
    this.educationList.push(newEducation);
  }

  removeEducation(index: number) {
    this.educationList.splice(index, 1);
  }

  addSkill(newSkill: Skill) {
    this.skills.push(newSkill);
  }

  removeSkill(index: number) {
    this.skills.splice(index, 1);
  }

  addLanguage(newLanguage: Language) {
    this.languages.push(newLanguage);
  }

  removeLanguage(index: number) {
    this.languages.splice(index, 1);
  }
}
