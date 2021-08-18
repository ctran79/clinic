package com.ctran79.clinic.backend.controller;

import com.ctran79.clinic.backend.facade.PrescriptionFacade;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

/** @author ctran79 */
@Controller
@RequestMapping("/print")
public class PrintController {

  private final PrescriptionFacade prescriptionFacade;

  public PrintController(PrescriptionFacade prescriptionFacade) {
    this.prescriptionFacade = prescriptionFacade;
  }

  protected void setResponseMetadata(HttpServletResponse response) {
    response.setContentType("application/pdf");
  }

  @GetMapping(path = "/prescription")
  public void printOrder(HttpServletResponse response, @RequestParam Long patientId) {
    try {
      setResponseMetadata(response);
      JasperPrint jasperPrint = prescriptionFacade.getPrescriptionAsReport(patientId);
      JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
    } catch (Exception ex) {
      ex.printStackTrace();
      throw new RuntimeException(
          "Exception while generating prescription print: " + ex.getMessage());
    }
  }
}
