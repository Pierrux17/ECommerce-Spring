package be.epsmarche.sgbd.pierre.springPierre.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping(value = "/springPierre/")
@RequestMapping(value = "/")
public class HomeController {

	@GetMapping("home")
	public String showHome(Model model) {
		return "home";
	}
	
	@GetMapping("catBar")
	public String showCatBar() {
		return "stats/categorieBar";
	}
	
	@GetMapping("catPie")
	public String showCatPie() {
		return "stats/categoriePie";
	}
}
