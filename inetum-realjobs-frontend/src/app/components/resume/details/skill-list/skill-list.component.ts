import {Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import {Skill} from "../../../../models/skill";
import {SkillLevel} from "../../../../models/skillLevel.enum";

@Component({
  selector: 'app-skill-list',
  templateUrl: './skill-list.component.html',
  styleUrls: ['./skill-list.component.scss']
})
export class SkillListComponent implements OnInit {

  @Input() skills: Skill[];
  @Input() deleteButton: boolean;
  @Output() deleteEvent = new EventEmitter<number>();

  constructor() {
  }

  ngOnInit(): void {
  }

  deleteItem(id: number) {
    this.deleteEvent.emit(id);
  }

  get basicSkills(): Skill[] {
    return this.skills
      .filter(skill => skill.skillLevel === SkillLevel.BASIC);
  }

  get intermediateSkills(): Skill[] {
    return this.skills
      .filter(skill => skill.skillLevel === SkillLevel.INTERMEDIATE);
  }

  get expertSkills(): Skill[] {
    return this.skills
      .filter(skill => skill.skillLevel === SkillLevel.EXPERT);
  }
}
