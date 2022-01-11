import {Industry} from "./industry.enum";
import {FunctionCategory} from "./functionCategory.enum";

export interface Experience {
  id?: number;
  version?: number;
  jobTitle: string;
  functionCategory: FunctionCategory;
  company: string;
  industry: Industry;
  startDate: Date;
  endDate: Date;
  currentJob: boolean;
  description: string;
}
