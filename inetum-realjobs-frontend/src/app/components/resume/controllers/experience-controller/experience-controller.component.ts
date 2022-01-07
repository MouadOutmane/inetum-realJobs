import {Component, Input, Output, EventEmitter} from '@angular/core';
import {Experience} from "../../../../models/experience";

@Component({
  selector: 'app-experience-controller',
  templateUrl: './experience-controller.component.html',
  styleUrls: ['./experience-controller.component.scss']
})
export class ExperienceControllerComponent {

  @Input() isFormOpen: boolean;
  @Input() experienceList: Experience[];
  @Output() experienceUpdatedEvent = new EventEmitter<Experience[]>();
  @Output() formChangeEvent = new EventEmitter<boolean>();

  updateExperience(experienceList: Experience[]) {
    this.experienceUpdatedEvent.emit(experienceList);
  }

  closeForm() {
    this.formChangeEvent.emit(false);
  }

  openForm() {
    this.formChangeEvent.emit(true);
  }
}
