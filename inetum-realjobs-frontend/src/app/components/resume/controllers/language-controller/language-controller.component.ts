import {Component, Input, Output, EventEmitter} from '@angular/core';
import {Language} from "../../../../models/language";

@Component({
  selector: 'app-language-controller',
  templateUrl: './language-controller.component.html',
  styleUrls: ['./language-controller.component.scss']
})
export class LanguageControllerComponent {

  @Input() isFormOpen: boolean;
  @Input() languages: Language[];
  @Output() languageUpdatedEvent = new EventEmitter<Language[]>();
  @Output() formChangeEvent = new EventEmitter<boolean>();

  updateLanguage(languageList: Language[]) {
    this.languageUpdatedEvent.emit(languageList);
  }

  closeForm() {
    this.formChangeEvent.emit(false);
  }

  openForm() {
    this.formChangeEvent.emit(true);
  }
}
