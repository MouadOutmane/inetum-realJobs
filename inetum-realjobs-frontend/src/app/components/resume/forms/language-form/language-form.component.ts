import {Component, OnInit, EventEmitter, Output, Input} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Language} from "../../../../models/language";
import {SkillLevel} from "../../../../models/skillLevel.enum";
import {ResumeService} from "../../../../services/resume.service";
import {MessageService} from "primeng/api";
import {catchError, throwError} from "rxjs";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: "app-language-form",
  templateUrl: "./language-form.component.html",
  styleUrls: ["./language-form.component.scss"],
})
export class LanguageFormComponent implements OnInit {

  languageForm: FormGroup;
  skillLevelOptions: SkillLevel[];
  languages: Language[];

  constructor(private resumeService: ResumeService,
              private messageService: MessageService,
              private formBuilder: FormBuilder) {
    this.skillLevelOptions = Object.keys(SkillLevel) as SkillLevel[];
    this.languages = [];
  }

  ngOnInit(): void {
    this.languageForm = this.formBuilder.group({
      language: ["", [Validators.required]],
      skillLevel: [undefined, [Validators.required]],
    });
    this.resumeService
      .getLanguages()
      .pipe(catchError((err) => this.onError(err)))
      .subscribe(languages => {
        this.languages = languages;
      });
  }

  submitForm() {
    if (this.languageForm.valid) {
      this.resumeService
        .addLanguage(this.getFormData())
        .pipe(catchError((err) => this.onError(err)))
        .subscribe(languages => {
          this.languages = languages;
          this.languageForm.reset();
        });
    }
  }

  onError(error: HttpErrorResponse) {
    this.messageService.add({key: "tl", severity: "error", summary: "Error", detail: error.message});
    return throwError(() => error.message);
  }

  deleteLanguage(index: number) {
    this.resumeService
      .removeLanguage(index)
      .pipe(catchError((err) => this.onError(err)))
      .subscribe(languages => {
        this.languages = languages;
      });
  }

  getFormData(): Language {
    return {
      language: this.languageForm.controls["language"].value,
      skillLevel: this.languageForm.controls["skillLevel"].value,
    }
  }

  isInvalid(component: string): boolean {
    return this.languageForm.controls[component].invalid && this.languageForm.controls[component].dirty
  }

  isLanguageAlreadyAdded(): boolean {
    const newLang = this.languageForm.controls["language"].value;

    if (newLang === null) {
      return false;
    }

    for (let lang of this.languages) {
      if (lang.language.toLowerCase() === newLang.toLowerCase()) {
        return true;
      }
    }
    return false;
  }
}
