import {Component, OnInit, EventEmitter, Output, Input} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {SkillLevel} from "../../../../models/skillLevel.enum";
import {Skill} from "../../../../models/skill";

@Component({
  selector: "app-skill-form",
  templateUrl: "./skill-form.component.html",
  styleUrls: ["./skill-form.component.scss"],
})
export class SkillFormComponent implements OnInit {

  skillForm: FormGroup;
  skillLevelOptions: SkillLevel[];

  @Input() skills: Skill[];
  @Output() newSkillEvent = new EventEmitter<Skill>();
  @Output() deleteSkillEvent = new EventEmitter<number>();

  constructor(private formBuilder: FormBuilder) {
    this.skillLevelOptions = Object.keys(SkillLevel)
      .filter(key => !isNaN(Number(key)))
      .map(key => SkillLevel[key]);
  }

  ngOnInit(): void {
    this.skillForm = this.formBuilder.group({
      skill: ["", [Validators.required]],
      skillLevel: [undefined, [Validators.required]],
    })
  }

  submitForm() {
    if (this.skillForm.valid) {
      this.newSkillEvent.emit(this.getFormData());
      this.skillForm.reset();
    }
  }

  deleteSkill(index: number) {
    this.deleteSkillEvent.emit(index);
  }

  getFormData(): Skill {
    return {
      skill: this.skillForm.controls["skill"].value,
      skillLevel: this.skillForm.controls["skillLevel"].value,
    }
  }

  isSkillAlreadyAdded(): boolean {
    const newSkill = this.skillForm.controls["skill"].value;

    if (newSkill === null) {
      return false;
    }

    for (let skill of this.skills) {
      if (skill.skill.toLowerCase() === newSkill.toLowerCase()) {
        return true;
      }
    }
    return false;
  }
}
