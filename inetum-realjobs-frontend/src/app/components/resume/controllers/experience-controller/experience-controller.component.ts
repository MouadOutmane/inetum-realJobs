import {Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import {Experience} from "../../../../models/experience";

@Component({
  selector: 'app-experience-controller',
  templateUrl: './experience-controller.component.html',
  styleUrls: ['./experience-controller.component.scss']
})
export class ExperienceControllerComponent implements OnInit {

  @Input() experienceList: Experience[];
  @Output() experienceUpdatedEvent = new EventEmitter<Experience[]>();

  isFormOpen: boolean = false;

  constructor() {
  }

  ngOnInit(): void {
  }

  updateExperience(experienceList: Experience[]) {
    this.experienceUpdatedEvent.emit(experienceList);
  }

  closeForm() {
    this.isFormOpen = false;
  }

  openForm() {
    this.isFormOpen = true;
  }
}
