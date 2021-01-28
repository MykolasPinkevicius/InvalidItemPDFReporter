package lt.mykolaspinkevicius.invaliditempdfreporter.service;

import lt.mykolaspinkevicius.invaliditempdfreporter.entity.ItemDTO;
import lt.mykolaspinkevicius.invaliditempdfreporter.exception.NoItemsForThatDate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ItemDTOService {

    private static final String GET_ITEMS_WITH_VALID_DATE_DATE_URL = "http://localhost:8080/items/getItemsWithValidDate?date=";
    private final RestTemplate restTemplate = new RestTemplate();

    public List<ItemDTO> invalidItems(LocalDate validDate) {
        Optional<ItemDTO[]> invalidItems = Optional.ofNullable(restTemplate.getForObject(GET_ITEMS_WITH_VALID_DATE_DATE_URL + validDate, ItemDTO[].class));
        return Arrays.asList(invalidItems.orElseThrow(() -> new NoItemsForThatDate("No Items was found for that date")));
    }
}
