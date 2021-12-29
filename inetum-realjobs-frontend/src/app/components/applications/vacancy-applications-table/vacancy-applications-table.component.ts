import {Component, EventEmitter, Input, Output} from "@angular/core";
import {Application} from "../../../models/application";
import {ApplicationStatus} from "../../../models/application-status.enum";
import {ApplicationService} from "../../../services/application.service";
import {MessageService} from "primeng/api";

@Component({
  selector: "app-vacancy-applications-table",
  templateUrl: "./vacancy-applications-table.component.html",
  styleUrls: ["./vacancy-applications-table.component.scss"],
  providers: [MessageService],
})
export class VacancyApplicationsTableComponent {

  @Input() applications: Application[] = [];
  @Output() updateApplications = new EventEmitter<void>();

  constructor(private applicationService: ApplicationService,
              private messageService: MessageService) {
  }

  get status(): typeof ApplicationStatus {
    return ApplicationStatus;
  }

  updateApplication(application: Application, status: ApplicationStatus) {
    application.loading = true;

    this.applicationService.updateApplication(application.id, status)
      .subscribe({
        next: () => {
          this.updateApplications.emit();
        },
        error: (error) => {
          let message;
          if (error?.error?.message) {
            switch (error.error.message) {
              case "ID_MISMATCH":
              case "NOT_FOUND":
                message = "Something went wrong. Please refresh and try again.";
                break;
              default:
                message = "Something went wrong.";
                break;
            }
          } else {
            message = "Something went wrong.";
          }

          this.messageService.add({key: "tl", severity: "error", summary: "Error", detail: message});
          application.loading = false;
        },
      });
  }

}
