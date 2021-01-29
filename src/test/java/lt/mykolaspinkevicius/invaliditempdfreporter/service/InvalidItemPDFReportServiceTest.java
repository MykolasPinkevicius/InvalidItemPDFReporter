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
import java.util.Arrays;
import java.util.Optional;

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
    private  Optional<ItemDTO[]> invalidItems;
    private final ItemDTO[] mockedItems = new ItemDTO[1];

    @Before
    public void setup() {
        validDate = LocalDate.now();
        mockedItems[0] = new ItemDTO();
        invalidItems = Optional.of(mockedItems);
    }

    @Test
    public void shouldCallTwoMethodsOnceWhenCreatingPdfReport() {
        when(itemDTOService.getInvalidItemsFromItemStock(validDate)).thenReturn(invalidItems);
        when(pdfReportCreator.preparePDF(Arrays.asList(invalidItems.get()))).thenReturn(mock(ByteArrayInputStream.class));
        invalidItemPDFReportService.getInvalidItems(validDate);
        verify(itemDTOService, times(1)).getInvalidItemsFromItemStock(validDate);
        verify(pdfReportCreator, times(1)).preparePDF(Arrays.asList(invalidItems.get()));
    }
}
