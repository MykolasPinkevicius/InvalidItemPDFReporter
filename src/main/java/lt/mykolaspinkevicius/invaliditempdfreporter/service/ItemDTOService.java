package lt.mykolaspinkevicius.invaliditempdfreporter.service;

import lt.mykolaspinkevicius.invaliditempdfreporter.entity.ItemDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@Service
public class ItemDTOService {

    private static final String GET_ITEMS_WITH_VALID_DATE_DATE_URL = "http://localhost:8080/items/getItemsWithValidDate?date=";
    private final RestTemplate restTemplate = new RestTemplate();

    public List<ItemDTO> invalidItems(LocalDate validDate) {
        return restTemplate.getForObject(GET_ITEMS_WITH_VALID_DATE_DATE_URL + validDate, List.class);
    }
}
