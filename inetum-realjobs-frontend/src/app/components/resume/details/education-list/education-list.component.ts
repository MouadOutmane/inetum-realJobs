import {Component, Input, Output, EventEmitter, OnInit} from '@angular/core';
import {Education} from "../../../../models/education";
import {MessageService, ConfirmationService} from "primeng/api";

@Component({
  selector: 'app-education-list',
  templateUrl: './education-list.component.html',
  styleUrls: ['./education-list.component.scss']
})
export class EducationListComponent implements OnInit {

  @Input() educationList: Education[];

  @Input() formMode: boolean;
  @Output() deleteEvent = new EventEmitter<number>();

  expanded: boolean;

  constructor(private messageService: MessageService,
              private confirmationService: ConfirmationService,) {
  }

  ngOnInit(): void {
    this.expanded = this.formMode;
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

  loadMore() {
    this.expanded = true;
  }
}
