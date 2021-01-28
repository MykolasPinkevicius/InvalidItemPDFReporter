package lt.mykolaspinkevicius.invaliditempdfreporter.controller;

import lt.mykolaspinkevicius.invaliditempdfreporter.service.InvalidItemPDFReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;

@RestController
@RequestMapping("/report")
public class InvalidItemPDFReportController {

    public static final String ITEM_DATE_FORMAT = "yyyy-MM-dd";
    @Autowired
    private InvalidItemPDFReportService invalidItemPDFReportService;

    @GetMapping(value = "/getReport", produces = MediaType.APPLICATION_PDF_VALUE)
    public @ResponseBody
    ResponseEntity<InputStreamResource> getReport(@RequestParam @DateTimeFormat(pattern = ITEM_DATE_FORMAT) LocalDate validUntil) {
        ByteArrayInputStream invalidItems = invalidItemPDFReportService.getInvalidItems(validUntil);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=InvalidItemsReportFor" + validUntil + ".pdf");
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(invalidItems));
    }
}
