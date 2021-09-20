import {BaseObject} from "../domain/base-object";

export const compareFn = (x: BaseObject, y: BaseObject) => {
  return x && y ? x.id === y.id : x === y;
}

export const toDateUtc = (model: any): Date => {
  const _model = {
    ...model
  };

  for (let [key, value] of Object.entries(model)) {
    if (value instanceof Date) {
      _model[key] = new Date(
        Date.UTC(value.getFullYear(),
          value.getMonth(),
          value.getDate(),
          value.getHours(),
          value.getMinutes(),
          0
        )
      );
    }
  }
  return _model;
}
