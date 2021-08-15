package com.ctran79.clinic.backend.service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import java.io.InputStream;

public class Utils {
  public static JasperReport getCompiledReport(String reportName) throws JRException {
    InputStream jasperReportStream =
        new Object() {}.getClass().getResourceAsStream("/reports/prescription.jasper");
    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperReportStream);
    return jasperReport;
  }
}
