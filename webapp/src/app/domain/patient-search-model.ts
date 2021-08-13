import {BaseSearchModel, SearchField} from "./base-search-model";

export class PatientSearchModel extends BaseSearchModel {
  constructor() {
    super();

    this.fields = [
      new SearchField('Ngày đăng kí từ', 'date', 'createDateFrom', new Date()),
      new SearchField('Ngày đăng kí đến', 'date', 'createDateTo', new Date()),
      new SearchField('Tất cả', 'checkbox', 'allPatients', false)
    ];
  }

  get name(): string {
    return 'PatientSearchModel';
  }
}
