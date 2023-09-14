package be.epsmarche.sgbd.pierre.springPierre.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.epsmarche.sgbd.pierre.springPierre.service.StatService;

@RestController
@RequestMapping(value = "/")
public class StatsController {
	
	public StatsController() {
		
	}
	
	@Autowired
	StatService ss;
	
	@GetMapping("StatCatBar")
	public List<List<Object>> getStatBar(Model model) {
		List<List<Object>> statCat = ss.getArtCmdByCat();
		statCat.add(0, Arrays.asList("Catégorie", "Commandes"));
		return statCat; 
	}
}
