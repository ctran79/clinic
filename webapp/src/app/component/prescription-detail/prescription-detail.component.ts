import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {Prescription} from "../../domain/prescription";
import {PrescriptionService} from "../../service/prescription.service";
import {MatTableDataSource} from "@angular/material/table";
import {Indication} from "../../domain/indication";
import {MatDialog} from "@angular/material/dialog";
import {IndicationDialogComponent} from "../indication-dialog/indication-dialog.component";
import {Diagnosis} from "../../domain/diagnosis";
import {Patient} from "../../domain/patient";
import {DiagnosisDialogComponent} from "../diagnosis-dialog/diagnosis-dialog.component";
import {PatientService} from "../../service/patient.service";
import {forkJoin} from "rxjs";
import {environment} from "../../../environments/environment";

@Component({
  selector: 'app-prescription-detail',
  templateUrl: './prescription-detail.component.html',
  styleUrls: ['./prescription-detail.component.css']
})
export class PrescriptionDetailComponent implements OnInit {
  indicationDisplayedColumns: string[] = ['drug', 'quantity', 'unit', 'actions'];

  form!: FormGroup;
  prescription!: Prescription;
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
      note: [''],
    });
  }

  onSubmit() {
    if (this.form.valid) {
      const prescription = this.createObject();
      this.prescriptionService.saveModel(prescription).subscribe(prescription => this.navigateToList());
    }
  }

  createObject(): Prescription {
    return {
      id: this.prescription?.id,
      patient: this.patient,
      diagnoses: this.diagnosisList,
      indications: this.indicationDataSource.data,
      note: this.getControl('note').value
    };
  }

  getData() {
    if (this.patientId) {
      forkJoin([this.patientService.getModel(this.patientId), this.prescriptionService.getByPatientId(this.patientId)])
        .subscribe(([patient, prescription]) => {
            this.patient = patient;
            this.patchFormValue(prescription);
          }
        );
    }
  }

  getControl(name: string): FormControl {
    return this.form.controls[name] as FormControl;
  }

  patchFormValue(prescription: Prescription) {
    this.prescription = prescription;
    if (prescription?.id) {
      this.form.patchValue({note: prescription.note});

      this.diagnosisList = prescription.diagnoses;
      this.diagnosisList.sort((a, b) => a.seqNo - b.seqNo);
      this.indicationDataSource = new MatTableDataSource(prescription.indications.sort((a, b) => a.id! - b.id!));
    }
  }

  async navigateToList() {
    await this.router.navigate(['/patient-list']);
  }

  addIndication(obj?: Indication) {
    const dialogRef = this.dialog.open(IndicationDialogComponent, {
      width: '666px',
      height: '350px',
      data: obj
    });
    dialogRef.afterClosed().subscribe(indication => {
      if (indication) {
        const indicationList = this.indicationDataSource.data;
        indicationList.push(indication);
        this.indicationDataSource = new MatTableDataSource([...indicationList]);
      }
    });
  }

  addDiagnosis() {
    const dialogRef = this.dialog.open(DiagnosisDialogComponent, {
      width: '666px',
      height: '150px',
    });
    dialogRef.afterClosed().subscribe(diagnosis => {
      if (diagnosis) {
        const seqNo = this.diagnosisList.length + 1;
        this.diagnosisList = [...this.diagnosisList, {
          seqNo,
          diagnosis
        }];
      }
    });
  }

  removeDiagnosis(diagnosis: Diagnosis) {
    const idx = this.diagnosisList.indexOf(diagnosis);
    this.diagnosisList.splice(idx, 1);
    this.diagnosisList.forEach((diagnosis, index) => diagnosis.seqNo = index + 1);
    this.diagnosisList = [...this.diagnosisList];
  }

  deleteDrug(indication: Indication) {
    const indicationList = this.indicationDataSource.data;
    const idx = indicationList.indexOf(indication);
    indicationList.splice(idx, 1);
    this.indicationDataSource = new MatTableDataSource(indicationList);
  }

  getPrintPrescriptionEndPoint() {
    return `${environment.server}/print/prescription?patientId=${this.patientId}`;
  }
}
