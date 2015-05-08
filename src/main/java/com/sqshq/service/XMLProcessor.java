package com.sqshq.service;

import com.sqshq.models.Customer;
import com.sqshq.models.Order;
import com.sqshq.models.Position;
import com.sqshq.models.lists.Customers;
import com.sqshq.models.XMLFile;

import org.springframework.core.io.ClassPathResource;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.*;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class XMLProcessor {

    private Customers customers;
    private Customer maxTotalOrdersAmountCustomer;

    private BigDecimal maxCustomerTotalAmount   = new BigDecimal(0);
    private BigDecimal totalAmount              = new BigDecimal(0);
    private BigDecimal maxOrderAmount           = new BigDecimal(0);
    private BigDecimal minOrderAmount           = new BigDecimal(0);
    private BigDecimal averageOrderAmount       = new BigDecimal(0);

    private long totalOrders;


    public XMLProcessor(XMLFile file) throws JAXBException, IOException, SAXException {

        JAXBContext jc = JAXBContext.newInstance(Customers.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();

        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        ClassPathResource xsd = new ClassPathResource("/xsd/customers.xsd");

        Schema schema = sf.newSchema(xsd.getFile());
        unmarshaller.setSchema(schema);

        File xml = new File(file.getFile().getOriginalFilename());
        file.getFile().transferTo(xml);
        System.out.println("start:");
        System.out.println(new Timestamp(new java.util.Date().getTime()));
        this.customers = (Customers) unmarshaller.unmarshal(xml);
        System.out.println("finish:");
        System.out.println(new Timestamp(new java.util.Date().getTime()));
    }

    public void parse() {
        for (Customer customer: customers.getCustomer()) {

            BigDecimal customerTotalAmount = new BigDecimal(0);

            for (Order order : customer.getOrders().getOrder()) {

                BigDecimal orderAmount = new BigDecimal(0);

                for (Position position : order.getPositions().getPosition()) {
                    orderAmount = orderAmount.add( position.getPrice().multiply(new BigDecimal(position.getCount())) );
                }

                this.minOrderAmount = (this.minOrderAmount.compareTo(orderAmount) > 0 || this.minOrderAmount.compareTo(BigDecimal.ZERO) == 0) ? orderAmount : this.minOrderAmount;
                this.maxOrderAmount = (this.maxOrderAmount.compareTo(orderAmount) < 0) ? orderAmount : this.maxOrderAmount;

                customerTotalAmount = customerTotalAmount.add(orderAmount);
                this.totalOrders++;
            }

            this.totalAmount = this.totalAmount.add(customerTotalAmount);

            if (this.maxCustomerTotalAmount.compareTo(customerTotalAmount) < 0) {
                this.maxCustomerTotalAmount = customerTotalAmount;
                this.maxTotalOrdersAmountCustomer = customer;
            }
        }

        this.averageOrderAmount = totalAmount.divide(new BigDecimal(totalOrders),1, BigDecimal.ROUND_HALF_UP);
        System.out.println("done:");
        System.out.println(new Timestamp(new java.util.Date().getTime()));
    }

    public Customer getmaxTotalOrdersAmountCustomer() {
        return maxTotalOrdersAmountCustomer;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public BigDecimal getMaxOrderAmount() {
        return maxOrderAmount;
    }

    public BigDecimal getMinOrderAmount() {
        return minOrderAmount;
    }

    public BigDecimal getAverageOrderAmount() {
        return averageOrderAmount;
    }

    public long getTotalOrders() {
        return totalOrders;
    }
}
