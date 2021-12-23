import {Component, OnInit, EventEmitter, Output, Input} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Language} from "../../../../models/language";
import {SkillLevel} from "../../../../models/skillLevel.enum";

@Component({
  selector: "app-language-form",
  templateUrl: "./language-form.component.html",
  styleUrls: ["./language-form.component.scss"],
})
export class LanguageFormComponent implements OnInit {

  languageForm: FormGroup;
  skillLevelOptions: SkillLevel[];

  @Input() languages: Language[];
  @Output() newLanguageEvent = new EventEmitter<Language>();
  @Output() deleteLanguageEvent = new EventEmitter<number>();

  constructor(private formBuilder: FormBuilder) {
    this.skillLevelOptions = Object.keys(SkillLevel)
      .filter(key => !isNaN(Number(key)))
      .map(key => SkillLevel[key]);
  }

  ngOnInit(): void {
    this.languageForm = this.formBuilder.group({
      language: ["", [Validators.required]],
      skillLevel: [undefined, [Validators.required]],
    })
  }

  submitForm() {
    if (this.languageForm.valid) {
      this.newLanguageEvent.emit(this.getFormData());
      this.languageForm.reset();
    }
  }

  deleteLanguage(index: number) {
    this.deleteLanguageEvent.emit(index);
  }

  getFormData(): Language {
    return {
      language: this.languageForm.controls["language"].value,
      skillLevel: this.languageForm.controls["skillLevel"].value,
    }
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
