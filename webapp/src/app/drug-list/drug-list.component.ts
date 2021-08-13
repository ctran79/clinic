import {Component} from '@angular/core';
import {Drug} from "../domain/drug";
import {DrugService} from "../service/drug.service";
import {TableBase} from "../table-base";
import {DrugSearchModel} from "../domain/drug-search-model";
import {Router} from "@angular/router";

@Component({
  selector: 'app-drug-list',
  templateUrl: './drug-list.component.html',
  styleUrls: ['./drug-list.component.css']
})
export class DrugListComponent extends TableBase<Drug> {
  displayedColumns: string[] = ['name', 'usage', 'actions'];

  constructor(public router: Router,
              public productService: DrugService) {
    super(productService);
  }

  ngOnInit(): void {
    super.ngOnInit();
  }

  initSearchModel(): DrugSearchModel {
    return new DrugSearchModel();
  }

  async edit(obj: Drug) {
    await this.router.navigate([`/drug-detail/${obj.id}`]);
  }

  async add() {
    await this.router.navigate(['/drug-detail']);
  }
}
