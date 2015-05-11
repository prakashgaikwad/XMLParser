package com.sqshq.parser.service;

import com.sqshq.parser.models.XMLFile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.validation.Errors;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.springframework.validation.Validator;

import org.xml.sax.SAXException;

import java.io.File;


public class XMLValidator implements Validator {
    public boolean supports(Class<?> paramClass) {
        return XMLFile.class.equals(paramClass);
    }

    public void validate(Object obj, Errors errors) {

        try {

            XMLFile file = (XMLFile) obj;

            if (file.getFile().getSize() == 0) {
                errors.rejectValue("file", "empty_file");
            } else if (!file.getFile().getContentType().contains(XMLConstants.XML_NS_PREFIX)) {
                errors.rejectValue("file", "wrong_type");
            }

            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            ClassPathResource xsd = new ClassPathResource("/xsd/customers.xsd");

            Schema schema = sf.newSchema(xsd.getFile());

            javax.xml.validation.Validator validator = schema.newValidator();
            validator.validate(new StreamSource(file.getFile().getInputStream()));

        } catch (SAXException e) {
            errors.rejectValue("file", "wrong_xml");
        } catch (Exception e) {
            errors.rejectValue("file", "smth_wrong");
        }
    }
}