import {Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import {Experience} from "../../../../models/experience";

@Component({
  selector: 'app-experience-details',
  templateUrl: './experience-details.component.html',
  styleUrls: ['./experience-details.component.scss']
})
export class ExperienceDetailsComponent implements OnInit {

  @Input() experience: Experience;

  @Input() deleteButton: boolean;
  @Output() deleteEvent = new EventEmitter<number>();

  constructor() {
  }

  ngOnInit(): void {
  }

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
