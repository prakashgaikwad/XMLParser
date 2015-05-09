
package com.sqshq.models;

import com.sqshq.models.lists.Orders;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id",
    "name",
    "orders"
})
@XmlRootElement(name = "customer")
public class Customer {

    @XmlElement(required = true)
    protected BigInteger id;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected Orders orders;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger value) {
        this.id = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders value) {
        this.orders = value;
    }

    public BigDecimal getOrdersAmount() {
        return this.orders.getTotalOrdersAmount();
    }

    public int getNumberOfOrders() {
        return this.orders.getOrder().size();
    }

}
