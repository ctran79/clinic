import {BaseObject} from "../domain/base-object";

export const compareFn = (x: BaseObject, y: BaseObject) => {
  return x && y ? x.id === y.id : x === y;
}
