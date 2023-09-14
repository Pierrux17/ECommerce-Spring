package be.epsmarche.sgbd.pierre.springPierre.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.epsmarche.sgbd.pierre.springPierre.repository.StatsRepository;

@Service
public class StatService {
	
	@Autowired
    private StatsRepository statsRepository;
	
	public StatService() {
		
	}

	public List<List<Object>> getArtCmdByCat() {
		return statsRepository.getArtCmdByCat();
	}
	

}
