package it.bismark.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import it.bismark.models.ResultObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WelcomeContoller {
	
	private static final DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	@RequestMapping(value = "/fields/values/{name}", method = RequestMethod.GET)
	public @ResponseBody ResultObject getShopInJSON(@PathVariable String name) {

		ResultObject shop = new ResultObject();
		shop.setName("Name");
		shop.setValue(name);

		return shop;
	}
	
	@RequestMapping(value="/increase/p/{val}",method=RequestMethod.GET)
	public @ResponseBody String getNewValue(@PathVariable String val){
		Integer res = Integer.valueOf(val);
		res++;
		return String.valueOf(res);
	}
	
	@RequestMapping("/today")
	public @ResponseBody String getToday(){
		return String.valueOf(df.format(new Date()));
	}

}
