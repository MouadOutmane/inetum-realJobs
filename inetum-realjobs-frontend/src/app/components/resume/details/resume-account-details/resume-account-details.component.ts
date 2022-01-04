import {Component, OnInit} from '@angular/core';
import {ResumeService} from "../../../../services/resume.service";
import {AccountResume} from "../../../../models/accountResume";
import {catchError, throwError} from "rxjs";
import {HttpErrorResponse} from "@angular/common/http";
import {MessageService} from "primeng/api";

@Component({
  selector: 'app-resume-account-details',
  templateUrl: './resume-account-details.component.html',
  styleUrls: ['./resume-account-details.component.scss']
})
export class ResumeAccountDetailsComponent implements OnInit {

  data: AccountResume;
  loading: boolean = true;

  constructor(private resumeService: ResumeService,
              private messageService: MessageService) {
  }

  ngOnInit(): void {
    this.resumeService
      .getAccountResume()
      .pipe(catchError((err) => this.onError(err)))
      .subscribe(data => {
        this.data = data;
        this.loading = false;
      })
  }

  onError(error: HttpErrorResponse) {
    this.messageService.add({key: "tl", severity: "error", summary: "Error", detail: error.message});
    return throwError(() => error.message);
  }
}
