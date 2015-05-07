package com.sqshq.service;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import javax.xml.XMLConstants;

import com.sqshq.models.XMLFile;


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

        } catch (Exception e) {
            errors.rejectValue("file", "smth_wrong");
        }
    }
}