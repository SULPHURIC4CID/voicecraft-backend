package com.skyvera.texttovoice.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.skyvera.texttovoice.entity.VoicesEntity;
import com.skyvera.texttovoice.repository.VoiceRepository;
import com.skyvera.texttovoice.service.VoicesService;

@Service
public class VoicesServiceImplementation implements VoicesService {

	
	private final VoiceRepository voiceRepository;
			
	public VoicesServiceImplementation(VoiceRepository voiceRepository) {
		super();
		this.voiceRepository = voiceRepository;
	}

	@Override
	public List<VoicesEntity> findAllVoices() {
		return voiceRepository.findAll();
	}

	@Override
	public VoicesEntity saveVoice(VoicesEntity newVoiceEntity) {
		return voiceRepository.save(newVoiceEntity);
	}

	@Override
	public void deleteVoice(Long id) {
		voiceRepository.deleteById(id);
		
	}

}
