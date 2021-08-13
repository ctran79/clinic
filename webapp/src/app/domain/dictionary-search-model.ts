import {BaseSearchModel, SearchField} from "./base-search-model";

export class DictionarySearchModel extends BaseSearchModel {
  constructor() {
    super();

    this.fields = [];
  }

  get name(): string {
    return 'DictionarySearchModel';
  }
}
