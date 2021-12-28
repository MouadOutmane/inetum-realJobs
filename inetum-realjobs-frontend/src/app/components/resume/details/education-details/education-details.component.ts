import {Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import {Education} from "../../../../models/education";

@Component({
  selector: 'app-education-details',
  templateUrl: './education-details.component.html',
  styleUrls: ['./education-details.component.scss']
})
export class EducationDetailsComponent implements OnInit {

  @Input() education: Education;

  @Input() deleteButton: boolean;
  @Output() deleteEvent = new EventEmitter<number>();

  constructor() {
  }

  ngOnInit(): void {
  }

  deleteItem() {
    this.deleteEvent.emit(this.education.id);
  }
}
