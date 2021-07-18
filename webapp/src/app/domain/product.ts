import {BaseObject} from "./base-object";

export interface Product extends BaseObject {
  name: string;
  code: string;
  note: string;
}
