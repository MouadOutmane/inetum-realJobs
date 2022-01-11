import {Component, Input, Output, EventEmitter} from '@angular/core';
import {Experience} from "../../../../models/experience";
import {INDUSTRY_OPTIONS} from "../../../../models/industry.enum";
import {FUNCTION_CATEGORY_OPTIONS} from "../../../../models/functionCategory.enum";

@Component({
  selector: 'app-experience-details',
  templateUrl: './experience-details.component.html',
  styleUrls: ['./experience-details.component.scss']
})
export class ExperienceDetailsComponent {

  @Input() experience: Experience;

  @Input() formMode: boolean;
  @Output() deleteEvent = new EventEmitter<number>();
  @Output() editEvent = new EventEmitter<Experience>();

  deleteItem() {
    this.deleteEvent.emit(this.experience.id);
  }

  editItem() {
    if (this.formMode) {
      this.editEvent.emit(this.experience);
    }
  }

  getEndDate(): string {
    if (this.experience.currentJob) {
      return "now";
    }
    return String(this.experience.endDate);
  }

  get industry(): string {
    const industry = INDUSTRY_OPTIONS.filter(i => i.value === this.experience.industry)[0].name;
    const category = FUNCTION_CATEGORY_OPTIONS.filter(i => i.value === this.experience.functionCategory)[0].name;
    return industry + " - " + category;
  }
}
