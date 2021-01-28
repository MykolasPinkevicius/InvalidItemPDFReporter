package lt.mykolaspinkevicius.invaliditempdfreporter.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lt.mykolaspinkevicius.invaliditempdfreporter.entity.ItemDTO;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

@Component
public class PDFReportCreator {

    public static final int FONT_SIZE = 14;
    public static final int ITEM_VARIABLE_HEADER_QUANTITY = 5;
    public static final int BORDER_WIDTH = 2;

    public ByteArrayInputStream preparePDF(List<ItemDTO> invalidItems) {

        Document invalidItemsReport = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(invalidItemsReport, out);
            invalidItemsReport.open();
            invalidItemsReport.add(createParagraph(FontFactory.getFont(FontFactory.COURIER, FONT_SIZE, BaseColor.BLACK)));
            invalidItemsReport.add(Chunk.NEWLINE);
            PdfPTable table = new PdfPTable(ITEM_VARIABLE_HEADER_QUANTITY);
            getItemDTOVariableHeaders()
                    .forEach(headerTitle -> table.addCell(createHeaderCell(headerTitle)));
            invalidItems.forEach(item -> {
                table.addCell(createCell(item.getId().toString()));
                table.addCell(createCell(item.getType()));
                table.addCell(createCell(item.getQuantity().toString()));
                table.addCell(createCell(item.getCreated().toString()));
                table.addCell(createCell(item.getValidUntil().toString()));
            });
            invalidItemsReport.add(table);
            invalidItemsReport.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return new ByteArrayInputStream(out.toByteArray());
    }

    private Paragraph createParagraph(Font font) {
        Paragraph paragraph = new Paragraph("Invalid Items Report of " + LocalDate.now(), font);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        return paragraph;
    }

    private PdfPCell createHeaderCell(String headerTitle) {
        PdfPCell header = new PdfPCell();
        Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
        header.setHorizontalAlignment(Element.ALIGN_CENTER);
        header.setVerticalAlignment(Element.ALIGN_CENTER);
        header.setBorderWidth(BORDER_WIDTH);
        header.setPhrase(new Phrase(headerTitle, headFont));
        return header;
    }

    private PdfPCell createCell(String field) {
        PdfPCell idCell = new PdfPCell(new Phrase(field));
        idCell.setPaddingLeft(4);
        idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        idCell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
        return idCell;
    }

    private Stream<String> getItemDTOVariableHeaders() {
        return Stream.of("ID", "Type", "Quantity", "Created", "ValidUntil");
    }
}
