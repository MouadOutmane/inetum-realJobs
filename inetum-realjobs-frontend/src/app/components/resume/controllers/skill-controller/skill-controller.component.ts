import {Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import {Skill} from "../../../../models/skill";

@Component({
  selector: 'app-skill-controller',
  templateUrl: './skill-controller.component.html',
  styleUrls: ['./skill-controller.component.scss']
})
export class SkillControllerComponent implements OnInit {

  @Input() skills: Skill[];
  @Output() skillUpdatedEvent = new EventEmitter<Skill[]>();

  isFormOpen: boolean = false;

  constructor() {
  }

  ngOnInit(): void {
  }

  updateSkill(skillList: Skill[]) {
    this.skillUpdatedEvent.emit(skillList);
  }

  closeForm() {
    this.isFormOpen = false;
  }

  openForm() {
    this.isFormOpen = true;
  }
}
