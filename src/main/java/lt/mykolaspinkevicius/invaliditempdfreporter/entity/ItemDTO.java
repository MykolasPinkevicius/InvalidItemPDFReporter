package lt.mykolaspinkevicius.invaliditempdfreporter.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class ItemDTO implements Serializable {
    private Long id;
    private String type;
    private Long quantity;
    private LocalDate created;
    private LocalDate validUntil;

    public ItemDTO(Long id, String type, Long quantity, LocalDate created, LocalDate validUntil) {
        this.id = id;
        this.type = type;
        this.quantity = quantity;
        this.created = created;
        this.validUntil = validUntil;
    }

    public ItemDTO() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public LocalDate getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(LocalDate validUntil) {
        this.validUntil = validUntil;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemDTO item = (ItemDTO) o;
        return Objects.equals(id, item.id) && Objects.equals(type, item.type) && Objects.equals(created, item.created) && Objects.equals(validUntil, item.validUntil);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, created, validUntil);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", quantity=" + quantity +
                ", created=" + created +
                ", validUntil=" + validUntil +
                '}';
    }
}
