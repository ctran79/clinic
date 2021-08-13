import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {Drug} from "../../domain/drug";
import {ActivatedRoute, Router} from "@angular/router";
import {DrugService} from "../../service/drug.service";
import {Observable} from "rxjs";
import {debounceTime, distinctUntilChanged, map, startWith, switchMap} from "rxjs/operators";
import {DrugSearchModel} from "../../domain/drug-search-model";

@Component({
  selector: 'app-drug-detail',
  templateUrl: './drug-detail.component.html',
  styleUrls: ['./drug-detail.component.css']
})
export class DrugDetailComponent implements OnInit {

  form!: FormGroup;
  drug!: Drug;
  id: number = this.activatedRoute.snapshot.params.id;
  filteredDrugs!: Observable<Drug[]>

  constructor(public formBuilder: FormBuilder,
              public router: Router,
              public activatedRoute: ActivatedRoute,
              public drugService: DrugService) {
  }

  ngOnInit(): void {
    this.createFormView();
    this.getData();
    this.filteredDrugs = this.getControl('name').valueChanges
      .pipe(
        startWith(''),
        debounceTime(400),
        distinctUntilChanged(),
        switchMap(val => {
          return this.filter(val || '')
        })
      );
  }

  filter(val: string): Observable<Drug[]> {
    let drugSearchModel = new DrugSearchModel();

    return this.drugService.search(drugSearchModel)
      .pipe(
        map(response => response.content.filter(product => product.name.toLowerCase().indexOf(val.toLowerCase()) >= 0))
      );
  }


  createFormView() {
    this.form = this.formBuilder.group({
      name: ['', Validators.required],
      usage: ['']
    });
  }

  errorHandling(control: string, error: string) {
    return this.getControl(control).hasError(error);
  }

  onSubmit() {
    if (this.form.valid) {
      const product = this.createObject();
      this.drugService.saveModel(product).subscribe(product => this.navigateToList());
    }
  }

  getControl(name: string): FormControl {
    return this.form.controls[name] as FormControl;
  }

  createObject() {
    this.drug = {
      id: this.id,
      name: this.getControl('name').value,
      usage: this.getControl('usage').value,
    };
    return this.drug;
  }

  getData() {
    if (this.id) {
      this.drugService.getModel(this.id).subscribe(drug => this.patchFormValue(drug));
    }
  }

  patchFormValue(drug: Drug) {
    this.drug = drug;
    this.form.patchValue({
      name: drug.name,
      usage: drug.usage
    });
  }

  async navigateToList() {
    await this.router.navigate(['/drug-list']);
  }
}
