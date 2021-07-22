import {Component, OnInit} from "@angular/core";
import {CrudService} from "./service/crud.service";
import {BaseObject} from "./domain/base-object";
import {CollectionViewer, DataSource} from "@angular/cdk/collections";
import {BehaviorSubject, Observable, of} from "rxjs";
import {BaseSearchModel} from "./domain/base-search-model";
import {catchError, finalize} from "rxjs/operators";

@Component({
  template: ''
})
export abstract class TableBase<T extends BaseObject> implements OnInit {

  searchModel!: BaseSearchModel;
  dataSource!: BaseDataSource<T>;

  protected constructor(public service: CrudService<T>) {
  }

  ngOnInit(): void {
    this.dataSource = new BaseDataSource<T>(this.service);

    this.dataSource.loadData(this.searchModel);
  }
}

export class BaseDataSource<T extends BaseObject> implements DataSource<T> {
  private objListSubject = new BehaviorSubject<T[]>([]);
  private loadingSubject = new BehaviorSubject<boolean>(false);

  public loading$ = this.loadingSubject.asObservable();

  constructor(public service: CrudService<T>) {
  }

  connect(collectionViewer: CollectionViewer): Observable<T[]> {
    return this.objListSubject.asObservable();
  }

  disconnect(collectionViewer: CollectionViewer): void {
    this.objListSubject.complete();
    this.loadingSubject.complete();
  }

  loadData(searchModel: BaseSearchModel) {
    this.loadingSubject.next(true);
    this.service.search(searchModel).pipe(
      catchError(() => of([])),
      finalize(() => this.loadingSubject.next(false))
    )
      .subscribe(lessons => this.objListSubject.next(lessons));
  }
}
