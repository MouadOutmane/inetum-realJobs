import {Component, Input, Output, EventEmitter} from '@angular/core';
import {Education} from "../../../../models/education";

@Component({
  selector: 'app-education-details',
  templateUrl: './education-details.component.html',
  styleUrls: ['./education-details.component.scss']
})
export class EducationDetailsComponent {

  @Input() education: Education;

  @Input() deleteButton: boolean;
  @Output() deleteEvent = new EventEmitter<number>();

  deleteItem() {
    this.deleteEvent.emit(this.education.id);
  }
}
