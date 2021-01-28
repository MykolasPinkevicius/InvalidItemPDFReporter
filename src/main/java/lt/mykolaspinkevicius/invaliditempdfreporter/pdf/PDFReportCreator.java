package lt.mykolaspinkevicius.invaliditempdfreporter.pdf;

import lt.mykolaspinkevicius.invaliditempdfreporter.entity.ItemDTO;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.util.List;

@Component
public class PDFReportCreator {

    public ByteArrayInputStream preparePDF(List<ItemDTO> invalidItems) {
        return null;
    }
}
