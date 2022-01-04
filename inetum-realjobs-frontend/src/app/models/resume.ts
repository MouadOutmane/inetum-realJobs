import {ResumeStatus} from "./resumeStatus.enum";
import {Skill} from "./skill";
import {Language} from "./language";
import {Education} from "./education";
import {Experience} from "./experience";
import {AccountResume} from "./accountResume";

export interface Resume {
  accountInfo: AccountResume;
  summary: string;
  status: ResumeStatus;
  skills: Skill[];
  languages: Language[];
  educationList: Education[];
  experienceList: Experience[];
}
