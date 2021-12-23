import {Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import {FormGroup, FormBuilder, Validators} from "@angular/forms";
import {Education} from "../../../../models/education";

@Component({
  selector: 'app-education-form',
  templateUrl: './education-form.component.html',
  styleUrls: ['./education-form.component.scss']
})
export class EducationFormComponent implements OnInit {

  educationForm: FormGroup;

  @Input() educationList: Education[];
  @Output() newEducationEvent = new EventEmitter<Education>();
  @Output() deleteEducationEvent = new EventEmitter<number>();

  constructor(private formBuilder: FormBuilder) {
  }

  ngOnInit(): void {
    this.educationForm = this.formBuilder.group({
      degree: ["", [Validators.required]],
      program: ["", [Validators.required]],
      school: ["", [Validators.required]],
      startDate: [undefined, [Validators.required]],
      endDate: [undefined, [Validators.required]],
      description: [""],
    });
  }

  submitForm() {
    if (this.educationForm.valid) {
      this.newEducationEvent.emit(this.getFormData());
      this.educationForm.reset();
    }
  }

  deleteEducation(index: number) {
    this.deleteEducationEvent.emit(index);
  }

  getFormData(): Education {
    return {
      degree: this.educationForm.controls["degree"].value,
      program: this.educationForm.controls["program"].value,
      school: this.educationForm.controls["school"].value,
      startDate: this.educationForm.controls["startDate"].value,
      endDate: this.educationForm.controls["endDate"].value,
      description: this.educationForm.controls["description"].value,
    }
  }

  isDateInPast(date: Date): boolean {
    return this.isDateBefore(date, new Date());
  }

  isDateBefore(date1: Date, date2: Date): boolean {
    return date1 < date2;
  }
}
