import {BaseObject} from "./base-object";
import {DictionaryValue} from "./dictionary-value";

export interface Diagnosis extends BaseObject {
  seqNo: number;
  diagnosis: DictionaryValue;
}
