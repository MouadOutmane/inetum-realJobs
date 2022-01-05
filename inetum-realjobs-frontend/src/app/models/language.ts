import {SkillLevel} from "./skillLevel.enum";

export interface Language {
  id?: number;
  language: string;
  skillLevel: SkillLevel;
}
