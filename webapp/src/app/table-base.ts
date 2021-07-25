import {AfterViewInit, Component, OnInit, ViewChild} from "@angular/core";
import {CrudService} from "./service/crud.service";
import {BaseObject} from "./domain/base-object";
import {CollectionViewer, DataSource} from "@angular/cdk/collections";
import {BehaviorSubject, Observable} from "rxjs";
import {BaseSearchModel} from "./domain/base-search-model";
import {PageEvent} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {MatTableDataSource} from "@angular/material/table";
import {Sort} from "@angular/material/sort/sort";

@Component({
  template: ''
})
export abstract class TableBase<T extends BaseObject> implements OnInit {

  pageSizeOptions = [20, 60, 100];
  totalElements = 0;
  searchModel = this.initSearchModel();
  dataSource: MatTableDataSource<T>;

  protected constructor(public service: CrudService<T>) {
    this.dataSource = new MatTableDataSource<T>([]);
  }

  ngOnInit(): void {
    this.search();
  }

  search() {
    this.loadData(this.searchModel);
  }

  pageChange($event: PageEvent) {
    this.searchModel.pageNum = $event.pageIndex;
    this.searchModel.pageSize = $event.pageSize;
    this.search();
  }

  sortData($event: Sort) {
    this.searchModel.sort = $event.active;
    this.searchModel.dir = $event.direction;
    this.search();
  }

  loadData(searchModel: BaseSearchModel) {
    this.service.search(searchModel).subscribe(page => {
      this.dataSource.data = page.content;
      this.totalElements = page.totalElements;
    });
  }

  onFilterChanged() {
    this.searchModel.pageNum = 0;
    this.search();
  }

  abstract initSearchModel(): BaseSearchModel ;
}
