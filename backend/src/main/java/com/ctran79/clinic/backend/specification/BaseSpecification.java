package com.ctran79.clinic.backend.specification;

import java.time.format.DateTimeFormatter;

/**
 * @author ctran79
 */

public class BaseSpecification<T> {
    public static final DateTimeFormatter DATE_TIME_FORMATTER =  DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
}
