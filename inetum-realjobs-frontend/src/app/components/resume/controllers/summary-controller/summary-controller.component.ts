import {Component, OnInit, Input, Output, EventEmitter} from '@angular/core';

@Component({
  selector: 'app-summary-controller',
  templateUrl: './summary-controller.component.html',
  styleUrls: ['./summary-controller.component.scss']
})
export class SummaryControllerComponent implements OnInit {

  @Input() summary: string;
  @Output() summaryUpdatedEvent = new EventEmitter<string>();

  isFormOpen: boolean = false;

  constructor() {
  }

  ngOnInit(): void {
  }

  updateSummary(summary: string) {
    this.summaryUpdatedEvent.emit(summary);
  }

  closeForm() {
    this.isFormOpen = false;
  }

  openForm() {
    this.isFormOpen = true;
  }
}
