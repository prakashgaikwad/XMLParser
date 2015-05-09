package com.sqshq.controllers;

import com.sqshq.service.XMLProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import com.sqshq.models.XMLFile;
import com.sqshq.service.XMLValidator;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
public class ParserController {

    @Autowired
    XMLValidator validator;

    @InitBinder("XMLFile")
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @RequestMapping("/")
	public String showForm(ModelMap model) {
		return "form";
	}

    @RequestMapping(value = "/process", method = RequestMethod.POST)
    public String processForm(ModelMap model, @RequestParam("action") String action, @RequestParam("amount") String amount,
                              @Validated XMLFile file, BindingResult result) {

        if (result.hasErrors()) {
            model.addAttribute("error", result.getFieldError().getCode());
            return "form";
        }

        try {
            XMLProcessor processor = new XMLProcessor(file);

            if (action.equals("customers")) {
                processor.selectCustomers(BigDecimal.valueOf(Double.valueOf(amount)));
            } else {
                processor.calculateStatistics();
            }

            model.addAttribute("action", action);
            model.put("result", processor);

        } catch (Exception e) {
            model.addAttribute("error", "smth_wrong");
        }

        return "result";
    }
}