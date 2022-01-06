import {Component, OnInit, Output, EventEmitter, Input} from '@angular/core';
import {Language} from "../../../../models/language";
import {SkillLevel} from "../../../../models/skillLevel.enum";

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
    switch (language.skillLevel) {
      case SkillLevel.BASIC:
        return 33;
      case SkillLevel.INTERMEDIATE:
        return 66;
      case SkillLevel.EXPERT:
        return 100;
    }
  }

  ngOnInit(): void {
  }

  toForm() {
    this.toFormEvent.emit();
  }
}
