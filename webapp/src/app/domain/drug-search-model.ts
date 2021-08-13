import {BaseSearchModel, SearchField} from "./base-search-model";

export class DictionarySearchModel extends BaseSearchModel {

  constructor() {
    super();

    this.fields = [
      new SearchField('Tên thuốc', 'string', 'name', ''),
      new SearchField('Cách dùng', 'string', 'usage', ''),
    ];
  }

  get name(): string {
    return 'ProductSearchModel';
  }
}
