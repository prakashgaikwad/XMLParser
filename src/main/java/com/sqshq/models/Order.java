
package com.sqshq.models;

import com.sqshq.models.lists.Positions;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id",
    "positions"
})
@XmlRootElement(name = "order")
public class Order {

    @XmlElement(required = true)
    protected BigInteger id;

    @XmlElement(required = true)
    protected Positions positions;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger value) {
        this.id = value;
    }

    public Positions getPositions() {
        return positions;
    }

    public void setPositions(Positions value) {
        this.positions = value;
    }

    public BigDecimal getOrderAmount() {
        return this.positions.getTotalPositionsAmount();
    }
}
