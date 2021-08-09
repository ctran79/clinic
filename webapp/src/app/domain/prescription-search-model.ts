import {BaseSearchModel, SearchField} from "./base-search-model";

export class PrescriptionSearchModel extends BaseSearchModel {

  constructor() {
    super();

    this.fields = [
      new SearchField('Ngày khám từ', 'date', 'createDateFrom', new Date()),
      new SearchField('Ngày khám đến', 'date', 'createDateTo', new Date()),
      new SearchField('Bệnh nhân', 'string', 'client', ''),
      new SearchField('Địa chỉ', 'string', 'address', ''),
    ];
  }

  get name(): string {
    return 'OrderSearchModel';
  }
}
