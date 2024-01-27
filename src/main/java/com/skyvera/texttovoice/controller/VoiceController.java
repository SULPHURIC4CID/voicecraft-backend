package com.skyvera.texttovoice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skyvera.texttovoice.entity.VoicesEntity;
import com.skyvera.texttovoice.service.VoicesService;

@RestController
@CrossOrigin()
@RequestMapping("/voice")
public class VoiceController {
	
	private final VoicesService voicesService;

	public VoiceController(VoicesService voicesService) {
		super();
		this.voicesService = voicesService;
	}
	
	
	
	@GetMapping()
	public List<VoicesEntity> findAllVoices()
	{
		return voicesService.findAllVoices();
	}
	
	@PostMapping()
	public VoicesEntity saveVoice(@RequestBody VoicesEntity voicesEntity)
	{
		return voicesService.saveVoice(voicesEntity);
	}
	
	@DeleteMapping("/{id}")
	public void deleteVoice(@PathVariable("id") Long id)
	{
		voicesService.deleteVoice(id);
	}

}
