package com.skyvera.texttovoice.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "voices")
public class VoicesEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name  = "id")
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "voice_id")
	private String voiceId;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "type")
	private String type;

	//no argument constructor
	public VoicesEntity() {
		super();
	}

	//parameterized constructor
	public VoicesEntity(Long id, String name, String voiceId, String description, String gender, String type) {
		super();
		this.id = id;
		this.name = name;
		this.voiceId = voiceId;
		this.description = description;
		this.gender = gender;
		this.type = type;
	}

	//Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVoiceId() {
		return voiceId;
	}

	public void setVoiceId(String voiceId) {
		this.voiceId = voiceId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
