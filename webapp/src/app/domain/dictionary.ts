import {BaseObject} from "./base-object";
import {DictionaryValue} from "./dictionary-value";

export interface Dictionary extends BaseObject {
  code: string;
  name: string;
  dictionaryValues: DictionaryValue[];
}
