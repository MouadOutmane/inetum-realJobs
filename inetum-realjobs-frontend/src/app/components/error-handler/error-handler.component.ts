import {Component, Input} from "@angular/core";

@Component({
  selector: "app-error-handler",
  templateUrl: "./error-handler.component.html",
  styleUrls: ["./error-handler.component.scss"],
})
export class ErrorHandlerComponent {

  @Input()
  error: any;

  get message(): string {
    if (this.code === 403) return "You don't have access to this page!";

    return this.error?.message || "Something went wrong.";
  }

  get code(): number {
    return this.error?.status || -1;
  }

}
