import {Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import {Language} from "../../../../models/language";
import {SkillLevel} from "../../../../models/skillLevel.enum";

@Component({
  selector: 'app-language-list',
  templateUrl: './language-list.component.html',
  styleUrls: ['./language-list.component.scss']
})
export class LanguageListComponent implements OnInit {

  @Input() languages: Language[];
  @Input() deleteButton: boolean;
  @Output() deleteEvent = new EventEmitter<number>();

  constructor() {
  }

  ngOnInit(): void {
  }

  deleteItem(index: number) {
    this.deleteEvent.emit(index);
  }

  get basicLanguages(): { language: Language, index: number }[] {
    return this.languages
      .map((language, index) => ({language, index}))
      .filter(({language}) => language.skillLevel === SkillLevel.BASIC);
  }

  get intermediateLanguages(): { language: Language, index: number }[] {
    return this.languages
      .map((language, index) => ({language, index}))
      .filter(({language}) => language.skillLevel === SkillLevel.INTERMEDIATE);
  }

  get expertLanguages(): { language: Language, index: number }[] {
    return this.languages
      .map((language, index) => ({language, index}))
      .filter(({language}) => language.skillLevel === SkillLevel.EXPERT);
  }
}
