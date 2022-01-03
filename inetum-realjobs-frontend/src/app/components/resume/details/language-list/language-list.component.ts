import {Component, Input, Output, EventEmitter} from '@angular/core';
import {Language} from "../../../../models/language";
import {SkillLevel} from "../../../../models/skillLevel.enum";

@Component({
  selector: 'app-language-list',
  templateUrl: './language-list.component.html',
  styleUrls: ['./language-list.component.scss']
})
export class LanguageListComponent {

  @Input() languages: Language[];
  @Input() deleteButton: boolean;
  @Output() deleteEvent = new EventEmitter<number>();

  deleteItem(index: number) {
    this.deleteEvent.emit(index);
  }

  get basicLanguages(): Language[] {
    return this.languages
      .filter(language => language.skillLevel === SkillLevel.BASIC);
  }

  get intermediateLanguages(): Language[] {
    return this.languages
      .filter(language => language.skillLevel === SkillLevel.INTERMEDIATE);
  }

  get expertLanguages(): Language[] {
    return this.languages
      .filter(language => language.skillLevel === SkillLevel.EXPERT);
  }
}
