import {Component, OnInit} from "@angular/core";
import {ActivatedRoute, ParamMap} from "@angular/router";
import {catchError, Observable, throwError} from "rxjs";
import {Vacancy} from "../../models/vacancy";
import {VacancyService} from "../../services/vacancy.service";
import * as moment from "moment";

@Component({
  selector: "app-vacancy-details",
  templateUrl: "./vacancy-details.component.html",
  styleUrls: ["./vacancy-details.component.scss"],
})
export class VacancyDetailsComponent implements OnInit {

  id: number;
  vacancy$: Observable<Vacancy>;
  error: any = null;

  constructor(
    private route: ActivatedRoute,
    private vacancyService: VacancyService,
  ) {
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe((params: ParamMap) => {
      this.id = parseInt(params.get("id"));

      this.vacancy$ = this.vacancyService
        .getVacancy(this.id)
        .pipe(
          catchError(error => {
            this.error = error;
            return throwError(() => error);
          }),
        );
    });
  }

  formatDate(inputDate: string): string {
    const date: Date = new Date(inputDate);

    return moment(date).format("LL");
  }

  formatAddress(vacancy: Vacancy) {
    const number = vacancy.box ? `${vacancy.houseNumber}/${vacancy.box}` : vacancy.houseNumber;

    return `${vacancy.streetName} ${number}, ${vacancy.postalCode} ${vacancy.city}, ${vacancy.country}`;
  }

  share() {
    // TODO 22-dec-2021 Actually share link.
  }

}
