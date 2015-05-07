
package com.sqshq.models;

import com.sqshq.models.lists.Positions;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

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

}
