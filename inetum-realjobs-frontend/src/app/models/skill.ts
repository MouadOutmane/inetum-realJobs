import {SkillLevel} from "./skillLevel.enum";

export interface Skill {
  id?: number
  skill: string;
  skillLevel: SkillLevel;
}
