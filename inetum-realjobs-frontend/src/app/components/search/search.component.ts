import { Component, OnInit } from '@angular/core';
import {DropdownModule} from 'primeng/dropdown';
import { ScrollPanelModule } from 'primeng/scrollpanel';
import { Observable } from 'rxjs';
import { City } from 'src/app/models/city.model';
import { ContractType } from 'src/app/models/contractTypes.model';
import {Country} from 'src/app/models/country.model'
import { Experience } from 'src/app/models/experience.model';
import { VacancyFilterFields } from 'src/app/models/vacancy-filter-fields.model';
import { CountryService } from 'src/app/services/country.service';
import { VacancySearchService } from 'src/app/services/vacancy-search.service';
import { Vacancy } from 'src/app/vacancy';


@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})


export class SearchComponent implements OnInit {

  vacancies$: Observable<Vacancy[]>

  contractType: ContractType[];
  selectedContract: ContractType;

  experiences: Experience[];
  selectedExperience: Experience;

  cities: City[];
  selectedCity: City;

  countries$: Observable<Country[]>;
  selectedCountry: Country;

  filter: VacancyFilterFields ={
    functionTitle: "",
    contractType: "",
    industry: "",
    country: 0,
    requiredYearsOfExperience: 0
  };

  selectedVacancy: Vacancy = new Vacancy();

  constructor(private vacancySearchService: VacancySearchService, private countryService: CountryService) {

    this.vacancies$ = this.vacancySearchService.getAllVacancies();

    this.countries$ = this.countryService.getAllCountries();

    this.contractType = [
      {name: 'Full-Time'},
      {name: 'Part-Time'},
      {name: 'Flexi'},
      {name: 'Student'}
    ];

    this.experiences = [
      {name: '0-3'},
      {name: '3-6'},
      {name: '6+'}
    ];

   }

  ngOnInit(): void {

    this.cities = [
      {name: 'Bruxelles'},
      {name: 'Antwerpen'},
      {name: 'Paris'},
      {name: 'Tokyo'}
    ];
  }

  vacancySelection(vacancy: Vacancy): void{
    this.selectedVacancy = vacancy;
  }

  getFilteredVacancies(){
    this.vacancies$ = this.vacancySearchService.getFilteredVacancies(this.filter);
  }

  onSubmit(){
    this.getFilteredVacancies();
  }

}
