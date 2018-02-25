package com.horizon.common.spring;

import java.util.Date;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import com.horizon.common.util.DateConvertEditor;


public class ExtendsWebBinding implements WebBindingInitializer {


    public void initBinder(WebDataBinder binder, WebRequest request) {
     
        binder.registerCustomEditor(Date.class, new DateConvertEditor());
    }

}
