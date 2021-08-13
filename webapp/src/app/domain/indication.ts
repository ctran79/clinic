import {BaseObject} from "./base-object";
import {Drug} from "./drug";
import {DictionaryValue} from "./dictionary-value";

export interface Indication extends BaseObject {
  drug: Drug;
  quantity: number;
  unit: DictionaryValue;
  usage: string;
}
