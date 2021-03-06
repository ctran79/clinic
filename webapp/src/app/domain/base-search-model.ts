import {ListItem} from "./list-item";

export class SearchField {
  constructor(public label: string,
              public type: string,
              public name: string,
              public value: any,
              public options?: ListItem[]) {
  }
}

export abstract class BaseSearchModel {
  pageNum = 0;
  pageSize = 20;
  sort = 'id';
  dir = 'desc';
  fields: SearchField[] = [];

  abstract get name(): string;
}
