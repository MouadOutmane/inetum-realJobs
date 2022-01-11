import {Component, Input, Output, EventEmitter} from '@angular/core';
import {Experience} from "../../../../models/experience";

@Component({
  selector: 'app-experience-view',
  templateUrl: './experience-view.component.html',
  styleUrls: ['./experience-view.component.scss']
})
export class ExperienceViewComponent {

  @Input() experienceList: Experience[];
  @Output() toFormEvent = new EventEmitter<null>();

  toForm() {
    this.toFormEvent.emit();
  }
}
