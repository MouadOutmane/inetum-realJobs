import {Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import {Education} from "../../../../models/education";

@Component({
  selector: 'app-education-view',
  templateUrl: './education-view.component.html',
  styleUrls: ['./education-view.component.scss']
})
export class EducationViewComponent implements OnInit {

  @Input() educationList: Education[];
  @Output() toFormEvent = new EventEmitter<null>();

  constructor() {
  }

  ngOnInit(): void {
  }

  toForm() {
    this.toFormEvent.emit();
  }
}
