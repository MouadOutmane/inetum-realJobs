import {Component, Input, Output, EventEmitter} from '@angular/core';
import {Education} from "../../../../models/education";

@Component({
  selector: 'app-education-controller',
  templateUrl: './education-controller.component.html',
  styleUrls: ['./education-controller.component.scss']
})
export class EducationControllerComponent {

  @Input() isFormOpen: boolean;
  @Input() educationList: Education[];
  @Output() educationUpdatedEvent = new EventEmitter<Education[]>();
  @Output() formChangeEvent = new EventEmitter<boolean>();

  updateEducation(educationList: Education[]) {
    this.educationUpdatedEvent.emit(educationList);
  }

  closeForm() {
    this.formChangeEvent.emit(false);
  }

  openForm() {
    this.formChangeEvent.emit(true);
  }
}
