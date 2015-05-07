package com.sqshq.controllers;

import com.sqshq.models.lists.Customers;
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


@Controller
public class ParserController {

    @Autowired
    XMLValidator validator;

    @Autowired
    XMLProcessor processor;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @RequestMapping("/")
	public String showForm(ModelMap model) {
		return "form";
	}

    @RequestMapping("/process")
    public String processForm(ModelMap model, @Validated XMLFile file, BindingResult result) {

        if (result.hasErrors()) {
            model.addAttribute("error", result.getFieldError().getCode());
            return "form";
        }

        try {
            Customers customers = processor.parse(file);
            model.addAttribute("error", result.getFieldError().getCode());
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "smth_wrong");
        }

        return "result";
    }
}