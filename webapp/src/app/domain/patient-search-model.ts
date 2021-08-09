import {BaseSearchModel, SearchField} from "./base-search-model";

export class PatientSearchModel extends BaseSearchModel{
  constructor() {
    super();

    this.fields = [
      new SearchField('Ngày khám từ', 'date', 'createDateFrom', new Date()),
      new SearchField('Ngày khám đến', 'date', 'createDateTo', new Date())
    ];
  }

  get name(): string {
    return 'PatientSearchModel';
  }
}
