import {Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import {Education} from "../../../../models/education";

@Component({
  selector: 'app-education-controller',
  templateUrl: './education-controller.component.html',
  styleUrls: ['./education-controller.component.scss']
})
export class EducationControllerComponent implements OnInit {

  @Input() educationList: Education[];
  @Output() educationUpdatedEvent = new EventEmitter<Education[]>();

  isFormOpen: boolean = false;

  constructor() {
  }

  ngOnInit(): void {
  }

  updateEducation(educationList: Education[]) {
    this.educationUpdatedEvent.emit(educationList);
  }

  closeForm() {
    this.isFormOpen = false;
  }

  openForm() {
    this.isFormOpen = true;
  }
}
