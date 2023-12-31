package com.dannyj182.salesmanager.model.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemPK implements Serializable {

    private Long saleId;
    private Long productId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemPK that = (ItemPK) o;

        return Objects.equals(saleId, that.saleId) &&
                Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(saleId, productId);
    }
}
