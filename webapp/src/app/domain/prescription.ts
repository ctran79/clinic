import {Indication} from "./indication";
import {BaseObject} from "./base-object";
import {Patient} from "./patient";
import {Diagnosis} from "./diagnosis";

export interface Prescription extends BaseObject {
  patient: Patient;
  diagnoses: Diagnosis[];
  indications: Indication[];
  note: string;
}
