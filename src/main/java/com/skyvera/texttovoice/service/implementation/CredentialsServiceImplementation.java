package com.skyvera.texttovoice.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.skyvera.texttovoice.entity.CredentialsEntity;
import com.skyvera.texttovoice.repository.CredentialsRepository;
import com.skyvera.texttovoice.service.CredentialsService;

@Service
public class CredentialsServiceImplementation implements CredentialsService{
	
	private final CredentialsRepository credentialsRepository;

	public CredentialsServiceImplementation(CredentialsRepository credentialsRepository) {
		super();
		this.credentialsRepository = credentialsRepository;
	}

	@Override
	public List<CredentialsEntity> findAllCredentials() {
		return credentialsRepository.findAll();
	}

	@Override
	public Optional<CredentialsEntity> findById(Long id) {
		return credentialsRepository.findById(id);
	}

	@Override
	public CredentialsEntity saveCredentials(CredentialsEntity credentialsEntity) {
		return credentialsRepository.save(credentialsEntity);
	}

	@Override
	public CredentialsEntity updateCredentials(CredentialsEntity credentialsEntity) {
		return credentialsRepository.save(credentialsEntity);
	}

	@Override
	public void deleteCredentials(Long id) {
		credentialsRepository.deleteById(id);		
	}
}
