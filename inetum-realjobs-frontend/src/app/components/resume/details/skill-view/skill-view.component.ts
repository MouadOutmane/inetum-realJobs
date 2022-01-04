import {Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import {Skill} from "../../../../models/skill";

@Component({
  selector: 'app-skill-view',
  templateUrl: './skill-view.component.html',
  styleUrls: ['./skill-view.component.scss']
})
export class SkillViewComponent implements OnInit {

  @Input() skills: Skill[];
  @Output() toFormEvent = new EventEmitter<null>();

  constructor() {
  }

  ngOnInit(): void {
  }

  toForm() {
    this.toFormEvent.emit();
  }
}
