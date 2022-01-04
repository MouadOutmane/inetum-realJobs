export enum ContractType {
  FULL_TIME = "FULL_TIME",
  PART_TIME = "PART_TIME",
  FLEXI = "FLEXI",
  STUDENT = "STUDENT",
}

export interface Vacancy {
  id: number;
  functionTitle: string;
  contractType: ContractType;
  functionDescription: string;
  requiredYearsOfExperience: number;
  requirements: string;
  offer: string;
  postedOn: string;

  streetName: string;
  houseNumber: string;
  box: string;
  city: string;
  postalCode: string;
  country: string;

  companyName?: string;
  companyIndustry: string;
  companyCountry: string;
  companyCity: string;
  companyLogo?: string;
}
