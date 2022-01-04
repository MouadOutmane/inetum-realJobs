import {ResumeStatus} from "./resumeStatus.enum";
import {Skill} from "./skill";
import {Language} from "./language";
import {Education} from "./education";
import {Experience} from "./experience";

export interface Resume {
  summary: string;
  status: ResumeStatus;
  skills: Skill[];
  languages: Language[];
  educationList: Education[];
  experienceList: Experience[];
}
