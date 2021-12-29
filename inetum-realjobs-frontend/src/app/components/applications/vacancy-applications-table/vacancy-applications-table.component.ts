import {Component, Input} from "@angular/core";
import {Application} from "../../../models/application";

@Component({
  selector: "app-vacancy-applications-table",
  templateUrl: "./vacancy-applications-table.component.html",
  styleUrls: ["./vacancy-applications-table.component.scss"],
})
export class VacancyApplicationsTableComponent {

  @Input() applications: Application[] = [];

}
