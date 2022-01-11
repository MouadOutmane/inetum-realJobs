import {Component, Input, Output, EventEmitter} from '@angular/core';
import {Language} from "../../../../models/language";
import {LanguageLevel} from "../../../../models/languageLevel.enum";
import {MessageService, ConfirmationService} from "primeng/api";

@Component({
  selector: 'app-language-list',
  templateUrl: './language-list.component.html',
  styleUrls: ['./language-list.component.scss']
})
export class LanguageListComponent {

  @Input() languages: Language[];
  @Input() deleteButton: boolean;
  @Output() deleteEvent = new EventEmitter<number>();

  constructor(private messageService: MessageService,
              private confirmationService: ConfirmationService,) {
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

  confirm(index: number) {
    this.confirmationService.confirm({
      header: "You are about to delete a language",
      message: "Are you sure you want to delete this language?",
      icon: "pi pi-exclamation-triangle",
      accept: () => this.deleteItem(index)
    });
  }

  deleteItem(index: number) {
    this.deleteEvent.emit(index);
  }
}
