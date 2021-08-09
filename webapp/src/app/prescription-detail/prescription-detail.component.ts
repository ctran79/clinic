import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {Prescription} from "../domain/prescription";
import {PrescriptionService} from "../service/prescription.service";
import {MatTableDataSource} from "@angular/material/table";
import {Indication} from "../domain/indication";
import {MatDialog} from "@angular/material/dialog";
import {IndicationDialogComponent} from "../indication-dialog/indication-dialog.component";
import {Diagnosis} from "../domain/diagnosis";

@Component({
  selector: 'app-prescription-detail',
  templateUrl: './prescription-detail.component.html',
  styleUrls: ['./prescription-detail.component.css']
})
export class PrescriptionDetailComponent implements OnInit {
  indicationDisplayedColumns: string[] = ['seqNo', 'drug', 'quantity', 'unit', 'actions'];
  diagnosisDisplayedColumns: string[] = ['seqNo', 'indication', 'actions'];

  form!: FormGroup;
  prescription!: Prescription;
  id: number = this.activatedRoute.snapshot.params.id;
  patientId: number = this.activatedRoute.snapshot.params.patientId;

  indicationDataSource = new MatTableDataSource<Indication>([]);
  diagnosisDataSource = new MatTableDataSource<Diagnosis>([]);

  constructor(public formBuilder: FormBuilder,
              public router: Router,
              public dialog: MatDialog,
              public activatedRoute: ActivatedRoute,
              public orderService: PrescriptionService,
  ) {
  }

  ngOnInit(): void {
    this.createFormView();
    this.getData();
  }

  createFormView() {
    this.form = this.formBuilder.group({
      client: ['', Validators.required],
      address: [''],
    });
  }

  errorHandling(control: string, error: string) {
    return this.getControl(control).hasError(error);
  }

  onSubmit() {
    if (this.form.valid) {
      const order = this.createObject();
      this.orderService.saveModel(order).subscribe(order => this.navigateToList());
    }
  }

  getControl(name: string): AbstractControl {
    return this.form.controls[name];
  }

  createObject() {
    // this.prescription = {
    //   id: this.id,
    //
    // };
    return this.prescription;
  }

  getData() {
    if (this.id) {
      this.orderService.getModel(this.id).subscribe(order => this.patchFormValue(order));
    }
  }

  patchFormValue(prescription: Prescription) {
    if (prescription.id) {
      this.id = prescription.id;
    }
    this.prescription = prescription;
    this.form.patchValue({});

    this.indicationDataSource = new MatTableDataSource(Array.from(prescription.indications));
  }

  async navigateToList() {
    await this.router.navigate(['/prescription-list']);
  }

  edit(obj?: Indication) {
    const dialogRef = this.dialog.open(IndicationDialogComponent, {
      width: '880px',
      height: '300px',
      data: obj
    });
    dialogRef.afterClosed().subscribe(newItem => {
      if (newItem) {
        const list = this.indicationDataSource.data;
        if (newItem.seqNo < 0) {
          newItem.seqNo = this.indicationDataSource.data.length + 1;
          list.push(newItem);
        } else {
          const idx = list.findIndex(orderItem => orderItem.seqNo === newItem.seqNo);
          list[idx] = newItem;
        }
        this.indicationDataSource = new MatTableDataSource(list);
      }
    });
  }
}
