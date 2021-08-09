import {Indication} from "./indication";
import {BaseObject} from "./base-object";
import {Patient} from "./patient";
import {Diagnosis} from "./diagnosis";

export interface Prescription extends BaseObject {
  createDate: Date;
  patient: Patient;
  diagnoses: Set<Diagnosis>;
  indications: Set<Indication>;
}
