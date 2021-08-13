import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {Prescription} from "../../domain/prescription";
import {PrescriptionService} from "../../service/prescription.service";
import {MatTableDataSource} from "@angular/material/table";
import {Indication} from "../../domain/indication";
import {MatDialog} from "@angular/material/dialog";
import {IndicationDialogComponent} from "../indication-dialog/indication-dialog.component";
import {Diagnosis} from "../../domain/diagnosis";
import {PatientService} from "../../service/patient.service";
import {Patient} from "../../domain/patient";

@Component({
  selector: 'app-prescription-detail',
  templateUrl: './prescription-detail.component.html',
  styleUrls: ['./prescription-detail.component.css']
})
export class PrescriptionDetailComponent implements OnInit {
  indicationDisplayedColumns: string[] = ['drug', 'quantity', 'unit', 'actions'];

  form!: FormGroup;
  prescription!: Prescription;
  id: number = this.activatedRoute.snapshot.params.id;
  patientId: number = this.activatedRoute.snapshot.queryParams.patientId;
  patient!: Patient;

  indicationDataSource = new MatTableDataSource<Indication>([]);
  diagnosisList: Diagnosis[] = [];

  constructor(public formBuilder: FormBuilder,
              public router: Router,
              public dialog: MatDialog,
              public activatedRoute: ActivatedRoute,
              public patientService: PatientService,
              public prescriptionService: PrescriptionService,
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
      const prescription = this.createObject();
      this.prescriptionService.saveModel(prescription).subscribe(prescription => this.navigateToList());
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
      this.prescriptionService.getModel(this.id).subscribe(prescription => this.patchFormValue(prescription));
    }
    if (this.patientId) {
      this.patientService.getModel(this.patientId).subscribe(patient => this.patient = patient);
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
    await this.router.navigate(['/patient-list']);
  }

  addDrug(obj?: Indication) {
    const dialogRef = this.dialog.open(IndicationDialogComponent, {
      width: '666px',
      height: '300px',
      data: obj
    });
    dialogRef.afterClosed().subscribe(newItem => {
      if (newItem) {
      }
    });
  }

  addDiagnosis() {
  }
}
