package com.skyvera.texttovoice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.skyvera.texttovoice.entity.CredentialsEntity;

@Service
public interface CredentialsService {
	List<CredentialsEntity> findAllCredentials();
	Optional<CredentialsEntity> findById(Long id);
	CredentialsEntity saveCredentials(CredentialsEntity credentialsEntity);
	CredentialsEntity updateCredentials(CredentialsEntity credentialsEntity);
	void deleteCredentials(Long id);
}
