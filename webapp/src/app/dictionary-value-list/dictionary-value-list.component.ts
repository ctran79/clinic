import {Component, Input} from '@angular/core';
import {TableBase} from "../table-base";
import {Dictionary} from "../domain/dictionary";
import {DictService} from "../service/dict.service";
import {DictionarySearchModel} from "../domain/dict-search-model";
import {DictionaryValue} from "../domain/dictionary-value";
import {DictValueService} from "../service/dict-value.service";

@Component({
  selector: 'app-dictionary-value-list',
  templateUrl: './dictionary-value-list.component.html',
  styleUrls: ['./dictionary-value-list.component.css']
})
export class DictionaryValueListComponent extends TableBase<DictionaryValue> {
  displayedColumns: keyof Dictionary | string[] = ['name', 'code', 'actions'];

  @Input()
  dictionary!: Dictionary;

  constructor(public dictService: DictValueService) {
    super(dictService)
  }

  ngOnInit(): void {
  }

  initSearchModel(): DictionarySearchModel {
    return new DictionarySearchModel();
  }

  edit(obj: DictionaryValue) {

  }

  add() {

  }
}
