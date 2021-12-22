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
  postingDate: string; // TODO 22-dec-2021 Use an actual Date type?

  streetName: string;
  houseNumber: string;
  box: string;
  city: string;
  postalCode: string;
  country: string;

  companyName?: string;
  companyIndustry: string;
  companyLogo?: string;
}
