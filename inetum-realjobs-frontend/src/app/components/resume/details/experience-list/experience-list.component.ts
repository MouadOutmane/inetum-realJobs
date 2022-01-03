import {Component, Input, Output, EventEmitter} from '@angular/core';
import {Experience} from "../../../../models/experience";

@Component({
  selector: 'app-experience-list',
  templateUrl: './experience-list.component.html',
  styleUrls: ['./experience-list.component.scss']
})
export class ExperienceListComponent {

  @Input() experienceList: Experience[];

  @Input() deleteButton: boolean;
  @Output() deleteEvent = new EventEmitter<number>();

  deleteItem(index: number) {
    this.deleteEvent.emit(index);
  }
}
