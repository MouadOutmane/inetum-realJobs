import {Component, OnInit, EventEmitter, Output, Input} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Experience} from "../../../../models/experience";
import {SkillLevel} from "../../../../models/skillLevel.enum";

@Component({
  selector: "app-experience-form",
  templateUrl: "./experience-form.component.html",
  styleUrls: ["./experience-form.component.scss"],
})
export class ExperienceFormComponent implements OnInit {

  experienceForm: FormGroup;

  @Input() experienceList: Experience[];
  @Output() newExperienceEvent = new EventEmitter<Experience>();
  @Output() deleteExperienceEvent = new EventEmitter<number>();

  constructor(private formBuilder: FormBuilder) {
  }

  ngOnInit(): void {
    this.experienceForm = this.formBuilder.group({
      jobTitle: ["", [Validators.required]],
      functionCategory: ["", [Validators.required]],
      company: ["", [Validators.required]],
      industry: ["", [Validators.required]],
      startDate: [undefined, [Validators.required]],
      endDate: [undefined],
      currentJob: [false],
      description: [""],
    });
    this.experienceForm.get("currentJob").valueChanges.subscribe(val => {
      if (val) {
        this.experienceForm.get("endDate").reset();
        this.experienceForm.get("endDate").disable();
      } else {
        this.experienceForm.get("endDate").enable();
      }
    });
  }

  submitForm() {
    if (this.experienceForm.valid) {
      this.newExperienceEvent.emit(this.getFormData());
      this.experienceForm.reset();
    }
  }

  deleteExperience(index: number) {
    this.deleteExperienceEvent.emit(index);
  }

  getFormData(): Experience {
    return {
      jobTitle: this.experienceForm.controls["jobTitle"].value,
      functionCategory: this.experienceForm.controls["functionCategory"].value,
      company: this.experienceForm.controls["company"].value,
      industry: this.experienceForm.controls["industry"].value,
      startDate: this.experienceForm.controls["startDate"].value,
      endDate: this.experienceForm.controls["endDate"].value,
      currentJob: this.experienceForm.controls["currentJob"].value,
      description: this.experienceForm.controls["description"].value,
    }
  }

  isEndDateNotInPast(): boolean {
    const endDate = this.experienceForm.controls["endDate"].value;
    const currentJob = this.experienceForm.controls["currentJob"].value;

    if (currentJob) {
      return false;
    }
    if (!endDate) {
      return false;
    }

    return !this.isDateInPast(endDate);
  }

  isEndDateBeforeStartDate(): boolean {
    const endDate = this.experienceForm.controls["endDate"].value;
    const startDate = this.experienceForm.controls["startDate"].value;
    const currentJob = this.experienceForm.controls["currentJob"].value;

    if (currentJob) {
      return false;
    }
    if (!startDate || !endDate) {
      return false;
    }

    return this.isDateBefore(endDate, startDate);
  }

  isDateInPast(date: Date): boolean {
    return this.isDateBefore(date, new Date());
  }

  isDateBefore(date1: Date, date2: Date): boolean {
    return date1 < date2;
  }
}
