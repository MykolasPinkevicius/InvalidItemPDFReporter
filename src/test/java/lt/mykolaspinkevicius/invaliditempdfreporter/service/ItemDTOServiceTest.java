package lt.mykolaspinkevicius.invaliditempdfreporter.service;

import lt.mykolaspinkevicius.invaliditempdfreporter.entity.ItemDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ItemDTOServiceTest {

    private static final String GET_ITEMS_WITH_VALID_DATE_DATE_URL = "http://localhost:8080/items/getItemsWithValidDate?date=";
    private final ItemDTO[] invalidItemNotListed = new ItemDTO[1];

    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private ItemDTOService itemDTOService;

    private LocalDate validDate;

    @Before
    public void setup() {
        validDate = LocalDate.now();
        invalidItemNotListed[0] = new ItemDTO();
        itemDTOService = new ItemDTOService();
    }

    @Test
    public void shouldCallMethodWhenCallingAPIForInvalidList() {
        Mockito.when(restTemplate.getForEntity(GET_ITEMS_WITH_VALID_DATE_DATE_URL + validDate, ItemDTO[].class
        )).thenReturn(new ResponseEntity<ItemDTO[]>(invalidItemNotListed, HttpStatus.OK));
        restTemplate.getForEntity(GET_ITEMS_WITH_VALID_DATE_DATE_URL + validDate, ItemDTO[].class);
        verify(restTemplate, times(1)).getForEntity(GET_ITEMS_WITH_VALID_DATE_DATE_URL + validDate, ItemDTO[].class);
    }
}
