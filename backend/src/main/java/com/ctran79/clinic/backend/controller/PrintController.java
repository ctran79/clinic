package com.ctran79.clinic.backend.controller;

import com.ctran79.clinic.backend.facade.PrescriptionFacade;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/** @author ctran79 */
@Controller
@RequestMapping("/print")
public class PrintController {

  private final PrescriptionFacade prescriptionFacade;

  public PrintController(PrescriptionFacade prescriptionFacade) {
    this.prescriptionFacade = prescriptionFacade;
  }

  protected void setResponseMetadata(HttpServletResponse response, String filename) {
    response.setContentType("application/pdf");
    response.setHeader("Content-Disposition", "inline; filename=\"" + filename + "\"");
  }

  @GetMapping(path = "/prescription/{prescriptionId}")
  public void printOrder(HttpServletResponse response, @PathVariable Long prescriptionId) {
    try {
      setResponseMetadata(response, "don thuoc.pdf");
      JasperPrint jasperPrint = prescriptionFacade.getPrescriptionAsReport(prescriptionId);

      HtmlExporter exporter = new HtmlExporter();

      exporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getOutputStream()));
      exporter.setExporterInput(new SimpleExporterInput(jasperPrint));

      exporter.exportReport();
    } catch (Exception ex) {
      ex.printStackTrace();
      throw new RuntimeException(
          "Exception while generating prescription print: " + ex.getMessage());
    }
  }
}
