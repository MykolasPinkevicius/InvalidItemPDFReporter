package lt.mykolaspinkevicius.invaliditempdfreporter.service;

import lt.mykolaspinkevicius.invaliditempdfreporter.entity.ItemDTO;
import lt.mykolaspinkevicius.invaliditempdfreporter.pdf.PDFReportCreator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class InvalidItemPDFReportServiceTest {

    @Mock
    private ItemDTOService itemDTOService;
    @Mock
    private PDFReportCreator pdfReportCreator;

    @InjectMocks
    private InvalidItemPDFReportService invalidItemPDFReportService;

    private LocalDate validDate;
    private final List<ItemDTO> invalidItems = new ArrayList<>();

    @Before
    public void setup() {
        validDate = LocalDate.now();
        invalidItems.add(new ItemDTO());
    }

    @Test
    public void shouldCallTwoMethodsOnceWhenCreatingPdfReport() {
        when(itemDTOService.invalidItems(validDate)).thenReturn(invalidItems);
        when(pdfReportCreator.preparePDF(invalidItems)).thenReturn(mock(ByteArrayInputStream.class));
        invalidItemPDFReportService.getInvalidItems(validDate);
        verify(itemDTOService, times(1)).invalidItems(validDate);
        verify(pdfReportCreator, times(1)).preparePDF(invalidItems);
    }
}
