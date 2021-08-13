import {BaseSearchModel, SearchField} from "./base-search-model";

export class DictionaryValueSearchModel extends BaseSearchModel {
  constructor() {
    super();

    this.fields = [
      new SearchField('Tên từ điể', 'string', 'dictionary', ''),
      new SearchField('Giá trị trong từ điển', 'string', 'value', ''),
    ];
  }

  get name(): string {
    return 'DictionaryValueSearchModel';
  }
}
