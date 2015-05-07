package com.sqshq.service;

import com.sqshq.models.lists.Customers;
import com.sqshq.models.XMLFile;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.*;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;

@Service
public class XMLProcessor {
    public Customers parse(XMLFile file) throws JAXBException, IOException, SAXException {

        JAXBContext jc = JAXBContext.newInstance(Customers.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();

        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        ClassPathResource xsd = new ClassPathResource("/xsd/customers.xsd");

        Schema schema = sf.newSchema(xsd.getFile());
        unmarshaller.setSchema(schema);

        File xml = new File(file.getFile().getOriginalFilename());
        file.getFile().transferTo(xml);

        Customers customers = (Customers) unmarshaller.unmarshal(xml);

        return customers;
    }
}
