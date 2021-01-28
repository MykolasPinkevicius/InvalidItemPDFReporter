package lt.mykolaspinkevicius.invaliditempdfreporter.service;

import lt.mykolaspinkevicius.invaliditempdfreporter.pdf.PDFReportCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;

@Service
public class InvalidItemPDFReportService {

    @Autowired
    private ItemDTOService itemDTOService;
    @Autowired
    private PDFReportCreator pdfReportCreator;

    private final RestTemplate restTemplate = new RestTemplate();

    public ByteArrayInputStream getInvalidItems(LocalDate validDate) {
        return pdfReportCreator.preparePDF(itemDTOService.invalidItems(validDate));
    }
}
