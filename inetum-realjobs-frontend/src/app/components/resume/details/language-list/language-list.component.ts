import {Component, Input, Output, EventEmitter} from '@angular/core';
import {Language} from "../../../../models/language";
import {LanguageLevel} from "../../../../models/languageLevel.enum";

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
      .filter(language => language.languageLevel === LanguageLevel.BASIC);
  }

  get intermediateLanguages(): Language[] {
    return this.languages
      .filter(language => language.languageLevel === LanguageLevel.MEDIUM);
  }

  get expertLanguages(): Language[] {
    return this.languages
      .filter(language => language.languageLevel === LanguageLevel.GOOD);
  }
}
