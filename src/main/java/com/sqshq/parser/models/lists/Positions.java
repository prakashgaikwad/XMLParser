
package com.sqshq.parser.models.lists;

import com.sqshq.parser.models.Position;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "position"
})
@XmlRootElement(name = "positions")
public class Positions {

    @XmlElement(required = true)
    protected List<Position> position;

    @XmlTransient
    protected BigDecimal totalPositionsAmount = new BigDecimal(0);

    public List<Position> getPosition() {
        if (position == null) {
            position = new ArrayList<Position>();
        }
        return this.position;
    }

    public void afterUnmarshal(Unmarshaller unmarshaller, Object parent) {
        for (Position item : position) {
            BigDecimal itemAmount = item.getPrice().multiply(new BigDecimal(item.getCount()));
            this.totalPositionsAmount = this.totalPositionsAmount.add(itemAmount);
        }
    }

    public BigDecimal getTotalPositionsAmount() {
        return this.totalPositionsAmount;
    }
}
