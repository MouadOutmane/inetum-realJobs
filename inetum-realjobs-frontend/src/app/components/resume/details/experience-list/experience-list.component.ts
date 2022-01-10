import {Component, Input, Output, EventEmitter, OnInit} from '@angular/core';
import {Experience} from "../../../../models/experience";

@Component({
  selector: 'app-experience-list',
  templateUrl: './experience-list.component.html',
  styleUrls: ['./experience-list.component.scss']
})
export class ExperienceListComponent implements OnInit {

  @Input() experienceList: Experience[];

  @Input() formMode: boolean;
  @Output() deleteEvent = new EventEmitter<number>();
  @Output() editEvent = new EventEmitter<Experience>();

  expanded: boolean;

  ngOnInit(): void {
    this.expanded = this.formMode;
  }

  deleteItem(index: number) {
    this.deleteEvent.emit(index);
  }

  editItem(experience: Experience) {
    this.editEvent.emit(experience);
  }

  loadMore() {
    this.expanded = true;
  }
}
