
package com.sqshq.models.lists;

import com.sqshq.models.Order;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "order"
})
@XmlRootElement(name = "orders")
public class Orders {

    @XmlElement(required = true)
    protected List<Order> order;

    public List<Order> getOrder() {
        if (order == null) {
            order = new ArrayList<Order>();
        }
        return this.order;
    }

}
