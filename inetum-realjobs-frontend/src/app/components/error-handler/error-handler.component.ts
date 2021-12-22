import {Component, Input} from "@angular/core";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: "app-error-handler",
  templateUrl: "./error-handler.component.html",
  styleUrls: ["./error-handler.component.scss"],
})
export class ErrorHandlerComponent {

  @Input()
  error: any;

  get message(): string {
    if (this.error instanceof HttpErrorResponse) {

      switch (this.error.status) {
        case 404:
          return "Couldn't find what you were looking for.";
      }
    }

    if (this.error.message) {
      return this.error.message;
    }
    return "Something went wrong.";
  }

}
