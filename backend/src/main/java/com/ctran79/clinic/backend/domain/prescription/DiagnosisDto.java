package com.ctran79.clinic.backend.domain.prescription;

import com.ctran79.clinic.backend.domain.dictionary.DictionaryValueDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author ctran79
 */
@Getter
@Setter
@Builder
public class DiagnosisDto  {
    private Long id;
    private Integer seqNo;
    private DictionaryValueDto diagnosis;
}
