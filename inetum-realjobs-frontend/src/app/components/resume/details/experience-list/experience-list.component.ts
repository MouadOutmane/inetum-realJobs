import {Component, Input, Output, EventEmitter} from '@angular/core';
import {Experience} from "../../../../models/experience";
import {MessageService, ConfirmationService} from "primeng/api";

@Component({
  selector: 'app-experience-list',
  templateUrl: './experience-list.component.html',
  styleUrls: ['./experience-list.component.scss']
})
export class ExperienceListComponent {

  @Input() experienceList: Experience[];

  @Input() deleteButton: boolean;
  @Output() deleteEvent = new EventEmitter<number>();

  constructor(private messageService: MessageService,
              private confirmationService: ConfirmationService,) {
  }

  confirm(index: number) {
    this.confirmationService.confirm({
      header: "You are about to delete an experience",
      message: "Are you sure you want to delete this experience?",
      icon: "pi pi-exclamation-triangle",
      accept: () => this.deleteItem(index)
    });
  }

  deleteItem(index: number) {
    this.deleteEvent.emit(index);
  }
}
