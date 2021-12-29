import {Component, EventEmitter, Input, Output} from "@angular/core";
import {Application} from "../../../models/application";
import {ApplicationStatus} from "../../../models/application-status.enum";
import {ApplicationService} from "../../../services/application.service";

@Component({
  selector: "app-vacancy-applications-table",
  templateUrl: "./vacancy-applications-table.component.html",
  styleUrls: ["./vacancy-applications-table.component.scss"],
})
export class VacancyApplicationsTableComponent {

  @Input() applications: Application[] = [];
  @Output() updateApplications = new EventEmitter<void>();

  constructor(private applicationService: ApplicationService) {
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
          // FIXME - Show error.
          application.loading = false;
        },
      });
  }

}
