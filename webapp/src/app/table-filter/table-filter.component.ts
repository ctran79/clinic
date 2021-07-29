import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {BaseSearchModel} from "../domain/base-search-model";

@Component({
  selector: 'app-table-filter',
  templateUrl: './table-filter.component.html',
  styleUrls: ['./table-filter.component.css']
})
export class TableFilterComponent implements OnInit {

  @Input()
  filter!: BaseSearchModel;

  @Output()
  public change = new EventEmitter<BaseSearchModel>();

  constructor() {
  }

  ngOnInit(): void {
  }

  onDateChange() {
    this.change.emit(this.filter);
  }
}
