package scut.legend.cg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HtmlController {
	@RequestMapping(value="/div",method=RequestMethod.GET)
	public String getDiv(@RequestParam(value="featureID",required=true)String featureID){
		return "/div/"+featureID;
	}
}
