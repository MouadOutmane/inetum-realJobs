import {Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {SkillLevel} from "../../../../models/skillLevel.enum";
import {Skill} from "../../../../models/skill";
import {ResumeService} from "../../../../services/resume.service";
import {MessageService} from "primeng/api";
import {catchError, throwError} from "rxjs";
import {HttpErrorResponse} from "@angular/common/http";
import CustomValidators from "../../../../validators/CustomValidators";

@Component({
  selector: "app-skill-form",
  templateUrl: "./skill-form.component.html",
  styleUrls: ["./skill-form.component.scss"],
})
export class SkillFormComponent implements OnInit {

  @Input() skills: Skill[];
  @Output() formCloseEvent = new EventEmitter<null>();
  @Output() skillsUpdatedEvent = new EventEmitter<Skill[]>();

  skillForm: FormGroup;
  skillLevelOptions: SkillLevel[];

  constructor(private resumeService: ResumeService,
              private messageService: MessageService,
              private formBuilder: FormBuilder) {
    this.skillLevelOptions = Object.keys(SkillLevel) as SkillLevel[];
    this.skills = [];
  }

  ngOnInit(): void {
    this.skillForm = this.formBuilder.group({
      skill: ["", [Validators.required, CustomValidators.uniqueItemValidator(this.skills.map(s => s.skill))]],
      skillLevel: [undefined, [Validators.required]],
    });
    this.skillForm.get("skill").valueChanges.subscribe(val => {
      this.skillForm
        .controls["skill"]
        .setValidators(
          Validators.compose([
            Validators.required,
            CustomValidators.uniqueItemValidator(this.skills.map(s => s.skill))
          ])
        );
    });
  }

  submitForm() {
    if (this.skillForm.valid) {
      this.resumeService
        .addSkill(this.getFormData())
        .pipe(catchError((err) => this.onError(err)))
        .subscribe(skills => {
          this.skillsUpdatedEvent.emit(skills);
          this.skillForm.reset();
        });
    }
  }

  onError(error: HttpErrorResponse) {
    this.messageService.add({key: "tl", severity: "error", summary: "Error", detail: error.message});
    return throwError(() => error.message);
  }

  closeForm() {
    this.formCloseEvent.emit();
  }

  deleteSkill(id: number) {
    this.resumeService
      .removeSkill(id)
      .pipe(catchError((err) => this.onError(err)))
      .subscribe(skills => {
        this.skillsUpdatedEvent.emit(skills);
      });
  }

  getFormData(): Skill {
    return {
      skill: this.skillForm.controls["skill"].value,
      skillLevel: this.skillForm.controls["skillLevel"].value,
    }
  }

  isInvalid(component: string): boolean {
    return this.skillForm.controls[component].invalid && this.skillForm.controls[component].dirty
  }

  getValidationMessage(component: string): string {
    const control = this.skillForm.controls[component];
    if (control.valid) return undefined;

    if (control.errors["required"]) return "This field can't be empty!";
    else if (control.errors["uniqueItem"]) return "This skill is already in the list";

    return "Invalid input!";
  }
}
