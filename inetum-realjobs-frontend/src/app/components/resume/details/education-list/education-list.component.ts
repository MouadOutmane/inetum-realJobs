import {Component, Input, Output, EventEmitter} from '@angular/core';
import {Education} from "../../../../models/education";
import {MessageService, ConfirmationService} from "primeng/api";

@Component({
  selector: 'app-education-list',
  templateUrl: './education-list.component.html',
  styleUrls: ['./education-list.component.scss']
})
export class EducationListComponent {

  @Input() educationList: Education[];

  @Input() deleteButton: boolean;
  @Output() deleteEvent = new EventEmitter<number>();

  constructor(private messageService: MessageService,
              private confirmationService: ConfirmationService,) {
  }

  confirm(index: number) {
    this.confirmationService.confirm({
      header: "You are about to delete an education",
      message: "Are you sure you want to delete this education?",
      icon: "pi pi-exclamation-triangle",
      accept: () => this.deleteItem(index)
    });
  }

  deleteItem(index: number) {
    this.deleteEvent.emit(index);
  }
}
