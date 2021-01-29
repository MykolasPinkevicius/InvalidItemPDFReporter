package lt.mykolaspinkevicius.invaliditempdfreporter.service;

import lt.mykolaspinkevicius.invaliditempdfreporter.exception.NoItemsForThatDate;
import lt.mykolaspinkevicius.invaliditempdfreporter.pdf.PDFReportCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.Arrays;

@Service
public class InvalidItemPDFReportService {

    @Autowired
    private ItemDTOService itemDTOService;
    @Autowired
    private PDFReportCreator pdfReportCreator;

    public ByteArrayInputStream getInvalidItems(LocalDate validDate) {
        return pdfReportCreator.preparePDF(Arrays.asList(itemDTOService.getInvalidItemsFromItemStock(validDate)
                .orElseThrow(() -> new NoItemsForThatDate("No Item Found For That Date"))));
    }
}
