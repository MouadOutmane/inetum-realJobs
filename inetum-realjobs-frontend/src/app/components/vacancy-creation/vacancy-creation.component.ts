import { Component, OnInit } from '@angular/core';
import { Vacancy } from 'src/app/vacancy';
import { Country } from 'src/app/models/country.model';
import { FormGroup, FormControl, AsyncValidatorFn, AbstractControl, ValidationErrors } from '@angular/forms';
import { VacancySearchService } from 'src/app/services/vacancy-search.service';
import { Validators } from '@angular/forms';
import { delay, map, Observable, of } from 'rxjs';
import { textValidation, numberValidation } from 'src/app/validators/vacancy.validator';
import { ContractType } from 'src/app/models/contractTypes.model';


@Component({
  selector: 'app-vacancy-creation',
  templateUrl: './vacancy-creation.component.html',
  styleUrls: ['./vacancy-creation.component.css']
})



export class VacancyCreationComponent {

  vacancy: Vacancy;

  selectedSlider: number;

  vacancyForm = new FormGroup({
    functionTitle: new FormControl('', [textValidation]),
    contractType: new FormControl(''),
    offer: new FormControl('', [textValidation]),
    postalCode: new FormControl('', [numberValidation]),
    requiredYearsOfExperience: new FormControl(''),
    city: new FormControl('',[textValidation]),
    country: new FormControl(''),
    nr: new FormControl('',[numberValidation]),
    streetName: new FormControl('',[textValidation]),
    requiredExperienceSkillsEducation: new FormControl(''),
    functionDescription: new FormControl('',[textValidation]),
    companyName: new FormControl('',[textValidation]),
    industry: new FormControl(''),
    box: new FormControl(''),

  });
  
  countries: Country[] =[
    {id: 1, name: 'Belgium'},
    {id: 2, name: 'France'},
    {id: 3, name: 'Germany'},
    {id: 4, name: 'Italy'},
    {id: 5, name: 'Spain'}
  ];

  contractTypes: ContractType[] =[
    {name: 'Fixed-term'},
    {name: 'Permanent'},
  ];

  constructor(private vacancySearchService: VacancySearchService) {
   }

  onSubmit(){
    if(!this.vacancyForm.invalid){
      console.log('valid');
      this.vacancy = {...this.vacancyForm.value}

      this.vacancySearchService.submitVacancy(this.vacancy).subscribe(data => this.vacancy);
    }
    else{
      console.log('invalid');
    }
    

    
  };

  

}
