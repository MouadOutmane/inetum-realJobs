import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {SkillLevel} from "../../../../models/skillLevel.enum";
import {Skill} from "../../../../models/skill";
import {ResumeService} from "../../../../services/resume.service";
import {MessageService} from "primeng/api";
import {catchError, throwError} from "rxjs";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: "app-skill-form",
  templateUrl: "./skill-form.component.html",
  styleUrls: ["./skill-form.component.scss"],
})
export class SkillFormComponent implements OnInit {

  skillForm: FormGroup;
  skillLevelOptions: SkillLevel[];
  skills: Skill[];

  constructor(private resumeService: ResumeService,
              private messageService: MessageService,
              private formBuilder: FormBuilder) {
    this.skillLevelOptions = Object.keys(SkillLevel) as SkillLevel[];
    this.skills = [];
  }

  ngOnInit(): void {
    this.skillForm = this.formBuilder.group({
      skill: ["", [Validators.required]],
      skillLevel: [undefined, [Validators.required]],
    });
    this.resumeService
      .getSkills()
      .pipe(catchError((err) => this.onError(err)))
      .subscribe(skills => {
        this.skills = skills;
      });
  }

  submitForm() {
    if (this.skillForm.valid) {
      this.resumeService
        .addSkill(this.getFormData())
        .pipe(catchError((err) => this.onError(err)))
        .subscribe(skills => {
          this.skills = skills;
          this.skillForm.reset();
        });
    }
  }

  onError(error: HttpErrorResponse) {
    this.messageService.add({key: "tl", severity: "error", summary: "Error", detail: error.message});
    return throwError(() => error.message);
  }

  deleteSkill(index: number) {
    this.resumeService
      .removeSkill(index)
      .pipe(catchError((err) => this.onError(err)))
      .subscribe(skills => {
        this.skills = skills;
      });
  }

  getFormData(): Skill {
    return {
      skill: this.skillForm.controls["skill"].value,
      skillLevel: this.skillForm.controls["skillLevel"].value,
    }
  }

  isInvalid(component: string): boolean {
    return this.skillForm.controls[component].invalid && this.skillForm.controls[component].dirty
  }

  isSkillAlreadyAdded(): boolean {
    const newSkill = this.skillForm.controls["skill"].value;

    if (newSkill === null || newSkill.length === 0) {
      return false;
    }

    for (let skill of this.skills) {
      if (skill.skill.toLowerCase() === newSkill.toLowerCase()) {
        return true;
      }
    }
    return false;
  }
}
