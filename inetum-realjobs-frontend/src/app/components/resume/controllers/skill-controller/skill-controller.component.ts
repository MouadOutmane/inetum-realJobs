import {Component, Input, Output, EventEmitter} from '@angular/core';
import {Skill} from "../../../../models/skill";

@Component({
  selector: 'app-skill-controller',
  templateUrl: './skill-controller.component.html',
  styleUrls: ['./skill-controller.component.scss']
})
export class SkillControllerComponent {

  @Input() isFormOpen: boolean;
  @Input() skills: Skill[];
  @Output() skillUpdatedEvent = new EventEmitter<Skill[]>();
  @Output() formChangeEvent = new EventEmitter<boolean>();

  updateSkill(skillList: Skill[]) {
    this.skillUpdatedEvent.emit(skillList);
  }

  closeForm() {
    this.formChangeEvent.emit(false);
  }

  openForm() {
    this.formChangeEvent.emit(true);
  }
}
