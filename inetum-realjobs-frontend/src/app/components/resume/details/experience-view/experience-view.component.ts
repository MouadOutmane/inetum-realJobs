import {Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import {Experience} from "../../../../models/experience";

@Component({
  selector: 'app-experience-view',
  templateUrl: './experience-view.component.html',
  styleUrls: ['./experience-view.component.scss']
})
export class ExperienceViewComponent implements OnInit {

  @Input() experienceList: Experience[];
  @Output() toFormEvent = new EventEmitter<null>();

  constructor() {
  }

  ngOnInit(): void {
  }

  toForm() {
    this.toFormEvent.emit();
  }
}
