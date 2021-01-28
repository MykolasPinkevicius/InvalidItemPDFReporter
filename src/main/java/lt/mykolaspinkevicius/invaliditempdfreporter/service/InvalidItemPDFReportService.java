package lt.mykolaspinkevicius.invaliditempdfreporter.service;

import lt.mykolaspinkevicius.invaliditempdfreporter.pdf.PDFReportCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;

@Service
public class InvalidItemPDFReportService {

    @Autowired
    private ItemDTOService itemDTOService;
    @Autowired
    private PDFReportCreator pdfReportCreator;

    public ByteArrayInputStream getInvalidItems(LocalDate validDate) {
        return pdfReportCreator.preparePDF(itemDTOService.invalidItems(validDate));
    }
}
