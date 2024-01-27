package com.skyvera.texttovoice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.skyvera.texttovoice.entity.VoicesEntity;

@Service
public interface VoicesService {
	
	List<VoicesEntity> findAllVoices();
	VoicesEntity saveVoice(VoicesEntity newVoiceEntity);
	void deleteVoice (Long id);
}
