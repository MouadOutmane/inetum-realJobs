import {Component, Input, Output, EventEmitter} from '@angular/core';
import {Education} from "../../../../models/education";

@Component({
  selector: 'app-education-list',
  templateUrl: './education-list.component.html',
  styleUrls: ['./education-list.component.scss']
})
export class EducationListComponent {

  @Input() educationList: Education[];

  @Input() deleteButton: boolean;
  @Output() deleteEvent = new EventEmitter<number>();

  deleteItem(index: number) {
    this.deleteEvent.emit(index);
  }
}
