import {Component, OnInit, Output, EventEmitter, Input} from '@angular/core';
import {Language} from "../../../../models/language";
import {LanguageLevel} from "../../../../models/languageLevel.enum";

@Component({
  selector: 'app-language-view',
  templateUrl: './language-view.component.html',
  styleUrls: ['./language-view.component.scss']
})
export class LanguageViewComponent implements OnInit {

  @Input() languages: Language[];
  @Output() toFormEvent = new EventEmitter<null>();

  constructor() {
  }

  getProgress(language: Language): number {
    switch (language.languageLevel) {
      case LanguageLevel.BASIC:
        return 20;
      case LanguageLevel.MEDIUM:
        return 40;
      case LanguageLevel.GOOD:
        return 60;
      case LanguageLevel.EXPERT:
        return 80;
      case LanguageLevel.MOTHER_TONGUE:
        return 100;
    }
  }

  ngOnInit(): void {
  }

  toForm() {
    this.toFormEvent.emit();
  }
}
