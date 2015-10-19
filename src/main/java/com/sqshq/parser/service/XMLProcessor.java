package com.sqshq.parser.service;

import com.sqshq.parser.models.Customer;
import com.sqshq.parser.models.Order;
import com.sqshq.parser.models.lists.Customers;
import com.sqshq.parser.models.XMLFile;

import org.xml.sax.SAXException;

import javax.xml.bind.*;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

public class XMLProcessor {

    private Customers customers;
    private Customer maxTotalOrdersAmountCustomer;

    private List<Customer> selectedCustomers = new ArrayList<Customer>();

    private BigDecimal totalAmount = new BigDecimal(0);
    private BigDecimal maxOrderAmount = new BigDecimal(0);
    private BigDecimal minOrderAmount = new BigDecimal(0);
    private BigDecimal averageOrderAmount = new BigDecimal(0);

    private long totalOrders;


    public XMLProcessor(XMLFile file) throws JAXBException, IOException, SAXException {

        JAXBContext jc = JAXBContext.newInstance(Customers.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();

        File xml = new File(file.getFile().getOriginalFilename());
        file.getFile().transferTo(xml);

        this.customers = (Customers) unmarshaller.unmarshal(xml);
    }

    public void calculateStatistics() {
        for (Customer customer: this.customers.getCustomer()) {

            for (Order order : customer.getOrders().getOrder()) {

                BigDecimal orderAmount = order.getOrderAmount();
                this.minOrderAmount = (this.minOrderAmount.compareTo(orderAmount) > 0 || this.minOrderAmount.compareTo(BigDecimal.ZERO) == 0) ? orderAmount : this.minOrderAmount;
                this.maxOrderAmount = (this.maxOrderAmount.compareTo(orderAmount) < 0) ? orderAmount : this.maxOrderAmount;

            }

            BigDecimal customerOrdersAmount = customer.getOrdersAmount();
            if (this.maxTotalOrdersAmountCustomer == null || this.maxTotalOrdersAmountCustomer.getOrdersAmount().compareTo(customerOrdersAmount) < 0) {
                this.maxTotalOrdersAmountCustomer = customer;
            }

            this.totalAmount = this.totalAmount.add(customerOrdersAmount);
            this.totalOrders += customer.getNumberOfOrders();

        }

        this.averageOrderAmount = totalAmount.divide(new BigDecimal(totalOrders),1, BigDecimal.ROUND_HALF_UP);
    }

    public void selectCustomers(BigDecimal minTotalAmount) {
        for (Customer customer: this.customers.getCustomer()) {
            if (customer.getOrdersAmount().compareTo(minTotalAmount) > 0) {
                selectedCustomers.add(customer);
            }
        }
    }

    public List<Customer> getSelectedCustomers() {
        return selectedCustomers;
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
