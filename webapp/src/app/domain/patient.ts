import {BaseObject} from "./base-object";
import {DictionaryValue} from "./dictionary-value";

export interface Patient extends BaseObject {
  createDate?: Date;
  name: string;
  birthday: Date;
  gender: DictionaryValue;
  telephone: string;
  address: string;
  weight: string;
  height: string;
  isExamined: boolean;
}
