package com.ctran79.clinic.backend.domain.prescription;

import com.ctran79.clinic.backend.domain.BaseEntity;
import com.ctran79.clinic.backend.domain.patient.Patient;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/** @author ctran79 */
@Entity
@Getter
@Setter
@Table(name = "prescriptions")
public class Prescription extends BaseEntity {
  @NotNull
  @JoinColumn(name = "patient_id")
  @OneToOne(cascade = CascadeType.MERGE)
  private Patient patient;

  @OneToMany(
      fetch = FetchType.EAGER,
      orphanRemoval = true,
      cascade = CascadeType.ALL,
      mappedBy = "prescription")
  private Set<Diagnosis> diagnoses = new HashSet<>();

  @OneToMany(
      fetch = FetchType.EAGER,
      orphanRemoval = true,
      cascade = CascadeType.ALL,
      mappedBy = "prescription")
  private Set<Indication> indications = new HashSet<>();

  private String note;

  public void addDiagnosis(Diagnosis diagnosis) {
    diagnosis.setPrescription(this);
    this.diagnoses.add(diagnosis);
  }

  public void addIndication(Indication indication) {
    indication.setPrescription(this);
    this.indications.add(indication);
  }

  public PrescriptionDto toDto() {
    return PrescriptionDto.builder()
        .id(id)
        .createDate(createDate)
        .patient(patient.toDto())
        .diagnoses(diagnoses.stream().map(Diagnosis::toDto).collect(Collectors.toSet()))
        .indications(indications.stream().map(Indication::toDto).collect(Collectors.toSet()))
        .note(note)
        .build();
  }

  public Prescription toEntity(PrescriptionDto dto) {
    note = dto.getNote();
    return this;
  }
}
