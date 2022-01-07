import {LanguageLevel} from "./languageLevel.enum";

export interface Language {
  id?: number;
  language: string;
  languageLevel: LanguageLevel;
}
