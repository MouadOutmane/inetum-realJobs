import {Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import {Language} from "../../../../models/language";

@Component({
  selector: 'app-language-controller',
  templateUrl: './language-controller.component.html',
  styleUrls: ['./language-controller.component.scss']
})
export class LanguageControllerComponent implements OnInit {

  @Input() languages: Language[];
  @Output() languageUpdatedEvent = new EventEmitter<Language[]>();

  isFormOpen: boolean = false;

  constructor() {
  }

  ngOnInit(): void {
  }

  updateLanguage(languageList: Language[]) {
    this.languageUpdatedEvent.emit(languageList);
  }

  closeForm() {
    this.isFormOpen = false;
  }

  openForm() {
    this.isFormOpen = true;
  }
}
