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


@Controller
public class ParserController {

    @Autowired
    XMLValidator validator;

    @InitBinder("file")
    public void initBinder(WebDataBinder binder) {
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
            XMLProcessor processor = new XMLProcessor(file);
            processor.parse();

            model.put("result", processor);

        } catch (Exception e) {
            model.addAttribute("error", "smth_wrong");
        }

        return "result";
    }
}