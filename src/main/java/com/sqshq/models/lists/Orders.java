
package com.sqshq.models.lists;

import com.sqshq.models.Order;
import com.sqshq.models.Position;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "order"
})
@XmlRootElement(name = "orders")
public class Orders {

    @XmlElement(required = true)
    protected List<Order> order;

    @XmlTransient
    protected BigDecimal totalOrdersAmount = new BigDecimal(0);

    public List<Order> getOrder() {
        if (order == null) {
            order = new ArrayList<Order>();
        }
        return this.order;
    }

    public void afterUnmarshal(Unmarshaller unmarshaller, Object parent) {
        for (Order item : order) {
            this.totalOrdersAmount = this.totalOrdersAmount.add(item.getOrderAmount());
        }
    }

    public BigDecimal getTotalOrdersAmount() {
        return totalOrdersAmount;
    }
}
