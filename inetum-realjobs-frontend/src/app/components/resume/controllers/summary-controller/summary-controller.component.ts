import {Component, Input, Output, EventEmitter} from '@angular/core';

@Component({
  selector: 'app-summary-controller',
  templateUrl: './summary-controller.component.html',
  styleUrls: ['./summary-controller.component.scss']
})
export class SummaryControllerComponent {

  @Input() isFormOpen: boolean;
  @Input() summary: string;
  @Output() summaryUpdatedEvent = new EventEmitter<string>();
  @Output() formChangeEvent = new EventEmitter<boolean>();

  updateSummary(summary: string) {
    this.summaryUpdatedEvent.emit(summary);
  }

  closeForm() {
    this.formChangeEvent.emit(false);
  }

  openForm() {
    this.formChangeEvent.emit(true);
  }
}
