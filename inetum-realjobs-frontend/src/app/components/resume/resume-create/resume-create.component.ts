import {Component, OnInit} from '@angular/core';
import {FormGroup, FormBuilder, Validators} from "@angular/forms";
import {ResumeService} from "../../../services/resume.service";
import {Resume} from "../../../models/resume";
import {ResumeStatus} from "../../../models/resumeStatus.enum";
import {MessageService} from "primeng/api";

@Component({
  selector: "resume-create",
  templateUrl: "./resume-create.component.html",
  styleUrls: ["resume-create.component.scss"],
  providers: [MessageService],
})
export class ResumeCreateComponent implements OnInit {

  creationForm: FormGroup;
  resumeStatusOptions: ResumeStatus[];

  constructor(private resumeService: ResumeService,
              private messageService: MessageService,
              private formBuilder: FormBuilder) {
    this.resumeStatusOptions = Object.keys(ResumeStatus)
      .filter(key => !isNaN(Number(key)))
      .map(key => ResumeStatus[key]);
  }

  ngOnInit(): void {
    this.creationForm = this.formBuilder.group({
      summary: ["", [Validators.required]],
      status: [undefined, [Validators.required]],
    });
  }

  submitForm() {

  }

  private getFormData(): Resume {
    return {
      summary: this.creationForm.controls["summary"].value,
      status: this.creationForm.controls["status"].value,
      educationList: [],
      experienceList: [],
      languages: [],
      skills: [],
    }
  }

  onSuccess() {
  }

  onError(error: string) {
    this.messageService.add({key: "tl", severity: "error", summary: "Error", detail: error});
  }
}
