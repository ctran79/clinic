import {Component, EventEmitter, Input, Output} from '@angular/core';
import {TableBase} from "../../table-base";
import {Dictionary} from "../../domain/dictionary";
import {DictionarySearchModel} from "../../domain/dict-search-model";
import {DictionaryValue} from "../../domain/dictionary-value";
import {DictValueService} from "../../service/dict-value.service";
import {MatTableDataSource} from "@angular/material/table";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-dictionary-value-list',
  templateUrl: './dictionary-value-list.component.html',
  styleUrls: ['./dictionary-value-list.component.css']
})
export class DictionaryValueListComponent extends TableBase<DictionaryValue> {
  displayedColumns: keyof Dictionary | string[] = ['name', 'code', 'actions'];

  editDictValue: boolean = false;
  form!: FormGroup;

  @Output()
  onAddOrEditDictValue = new EventEmitter<Dictionary>();

  constructor(public formBuilder: FormBuilder,
              public dictService: DictValueService) {
    super(dictService);
  }

  _dictionary!: Dictionary;

  @Input()
  set dictionary(dict: Dictionary) {
    this._dictionary = dict;
    this.editDictValue = false;
    this.dataSource = new MatTableDataSource(dict.dictionaryValues);
  }

  ngOnInit(): void {
    this.createEditDictValueForm();
  }

  initSearchModel(): DictionarySearchModel {
    return new DictionarySearchModel();
  }

  edit(dictValue: DictionaryValue) {
    this.editDictValue = true;
    this.form.patchValue(dictValue);
  }

  add() {
    this.editDictValue = true;
  }

  onSubmit() {
    if (this.form.valid) {
      const dictionaryValue = this.createObject();
      const index = this._dictionary.dictionaryValues.findIndex(value => value.id === dictionaryValue.id);
      if (index >= 0) {
        this._dictionary.dictionaryValues[index] = dictionaryValue;
      } else {
        this._dictionary.dictionaryValues.push(dictionaryValue);
      }
      this.onAddOrEditDictValue.emit(this._dictionary);
      this.editDictValue = false;
    }
  }

  getControl(name: string): FormControl {
    return this.form.controls[name] as FormControl;
  }

  onCancel() {
    this.editDictValue = false;
  }

  private createEditDictValueForm() {
    this.form = this.formBuilder.group({
      id: [null],
      value: ['', Validators.required],
      code: ['']
    });
  }

  private createObject(): DictionaryValue {
    return this.form.value;
  }
}
