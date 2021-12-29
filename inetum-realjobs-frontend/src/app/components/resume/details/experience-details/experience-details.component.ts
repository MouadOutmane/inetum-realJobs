import {Component, Input, Output, EventEmitter} from '@angular/core';
import {Experience} from "../../../../models/experience";

@Component({
  selector: 'app-experience-details',
  templateUrl: './experience-details.component.html',
  styleUrls: ['./experience-details.component.scss']
})
export class ExperienceDetailsComponent {

  @Input() experience: Experience;

  @Input() deleteButton: boolean;
  @Output() deleteEvent = new EventEmitter<number>();

  deleteItem() {
    this.deleteEvent.emit(this.experience.id);
  }

  getEndDate(): string {
    if (this.experience.currentJob) {
      return "now";
    }
    return String(this.experience.endDate);
  }
}
