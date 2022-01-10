import {Component, Input, Output, EventEmitter, OnInit} from '@angular/core';
import {Education} from "../../../../models/education";

@Component({
  selector: 'app-education-list',
  templateUrl: './education-list.component.html',
  styleUrls: ['./education-list.component.scss']
})
export class EducationListComponent implements OnInit {

  @Input() educationList: Education[];

  @Input() formMode: boolean;
  @Output() deleteEvent = new EventEmitter<number>();
  @Output() editEvent = new EventEmitter<Education>();

  expanded: boolean;

  ngOnInit(): void {
    this.expanded = this.formMode;
  }

  deleteItem(index: number) {
    this.deleteEvent.emit(index);
  }

  editItem(education: Education) {
    this.editEvent.emit(education);
  }

  loadMore() {
    this.expanded = true;
  }
}
