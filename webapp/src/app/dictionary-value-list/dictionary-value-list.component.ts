import {Component, Input} from '@angular/core';
import {TableBase} from "../table-base";
import {Dictionary} from "../domain/dictionary";
import {DictionarySearchModel} from "../domain/dict-search-model";
import {DictionaryValue} from "../domain/dictionary-value";
import {DictValueService} from "../service/dict-value.service";
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

  constructor(public formBuilder: FormBuilder,
              public dictService: DictValueService) {
    super(dictService);
  }

  @Input()
  set dictionary(dict: Dictionary) {
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
    this.form.patchValue({
      value: dictValue.value,
      code: dictValue.code
    });
  }

  add() {
  }

  onSubmit() {
    this.editDictValue = false;
  }

  getControl(name: string): FormControl {
    return this.form.controls[name] as FormControl;
  }

  private createEditDictValueForm() {
    this.form = this.formBuilder.group({
      value: ['', Validators.required],
      code: ['']
    });
  }
}
