import {Component, Input, Output, EventEmitter} from '@angular/core';
import {Education} from "../../../../models/education";
import {DEGREE_OPTIONS} from "../../../../models/degree.enum";

@Component({
  selector: 'app-education-details',
  templateUrl: './education-details.component.html',
  styleUrls: ['./education-details.component.scss']
})
export class EducationDetailsComponent {

  @Input() education: Education;

  @Input() formMode: boolean;
  @Output() deleteEvent = new EventEmitter<number>();
  @Output() editEvent = new EventEmitter<Education>();

  deleteItem() {
    this.deleteEvent.emit(this.education.id);
  }

  editItem() {
    if (this.formMode) {
      this.editEvent.emit(this.education);
    }
  }

  get degree(): string {
    return DEGREE_OPTIONS.filter(i => i.value === this.education.degree)[0].name;
  }
}
