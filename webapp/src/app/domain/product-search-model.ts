import {BaseSearchModel, SearchField} from "./base-search-model";

export class ProductSearchModel extends BaseSearchModel {

  constructor() {
    super();

    this.fields = [
      new SearchField('Tên thuốc', 'string', 'name', ''),
      new SearchField('Cách dùng', 'string', 'note', ''),
      new SearchField('Code', 'string', 'code', ''),
    ];
  }

  get name(): string {
    return 'ProductSearchModel';
  }
}
