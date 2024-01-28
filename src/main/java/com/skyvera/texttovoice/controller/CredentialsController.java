package com.skyvera.texttovoice.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skyvera.texttovoice.entity.CredentialsEntity;
import com.skyvera.texttovoice.service.CredentialsService;

import net.andrewcpu.elevenlabs.ElevenLabs;
import net.andrewcpu.elevenlabs.model.voice.Voice;
import net.andrewcpu.elevenlabs.model.voice.VoiceSettings;

@RestController
@CrossOrigin()
@RequestMapping("/credentials")
public class CredentialsController {
	
	private final CredentialsService credentialsService;

	public CredentialsController(CredentialsService credentialsService) {
		super();
		this.credentialsService = credentialsService;
	}
	
	@GetMapping
	public List<CredentialsEntity> findAllCredentials()
	{
		return credentialsService.findAllCredentials();
	}
	
	
	@GetMapping("/{id}")
	public Optional<CredentialsEntity> findCredentialsById(@PathVariable("id") Long id)
	{
		return credentialsService.findById(id);
	}
	
	@PostMapping
	public CredentialsEntity saveCredentials(@RequestBody CredentialsEntity credentialsEntity)	
	{
		System.out.println("Request :"+credentialsEntity.getUsername() +","+credentialsEntity.getPassword());
		return credentialsService.saveCredentials(credentialsEntity);
	}
	
	@PutMapping
	public CredentialsEntity updateCredentials(@RequestBody CredentialsEntity credentialsEntity)	
	{
		System.out.println("Request :"+credentialsEntity.getUsername() +","+credentialsEntity.getPassword());
		return credentialsService.updateCredentials(credentialsEntity);
	}
	
	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable("id") Long id)
	{
		credentialsService.deleteCredentials(id);
	}
	
	@PostMapping("/login")
	public String validateCredentials(@RequestBody CredentialsEntity credentialsEntity)
	{
		
		System.out.println(credentialsEntity.getUsername());
		List<CredentialsEntity> existingCredentials = credentialsService.findAllCredentials();
		for(int i =0 ;i<existingCredentials.size();i++)
		{
			CredentialsEntity temp = existingCredentials.get(i);
			System.out.println("Request :"+credentialsEntity.getUsername() +","+credentialsEntity.getPassword());
			System.out.println("Request :"+temp.getUsername() +","+temp.getPassword());
			if(temp.getUsername().equals(credentialsEntity.getUsername()) && temp.getPassword().equals(credentialsEntity.getPassword()))
				return ""+temp.getId();
		}
		return "Username not found.";		
	}
	
	@PostMapping("/generateVoice")
	@CrossOrigin()
	public String test(@RequestBody String requestPayload) throws ParseException
	{
		/*
	    Request Structure from UI-
	    {
	      requestId : 1,      
	      textContent : "Sample text to be converted",
	      voiceDetails:{
	        name: "Zoe",
	        voiceId: "s7cGGyzMgx8nrexdOJlS",
	        description: "Hyped Characters and Animation.",
	        gender: "Female",
	        type: "Middle-Aged American",
	        inUse: false,
	        voiceConfiguration:{
	          stability: 78
	          clarity: 67
	          style: 56
	          speakerBoost: true,
	        }
	      }      
	    }
	    */
		
		//Extract the data sent in the request payload from the UI layer
		JSONParser parser = new JSONParser();
		JSONObject dataObject = (JSONObject) parser.parse(requestPayload.toString());
		
		String textContent = (String)dataObject.get("textContent");
				
		JSONObject voiceDetails = (JSONObject) dataObject.get("voiceDetails");
		String voiceId = (String)voiceDetails.get("voiceId");
		
		JSONObject voiceConfiguration = (JSONObject) voiceDetails.get("voiceConfiguration"); 
		double stability = Integer.parseInt(""+voiceConfiguration.get("stability"))/100.0;
		double clarity = Integer.parseInt(""+voiceConfiguration.get("clarity"))/100.0;
		double style = Integer.parseInt(""+voiceConfiguration.get("style"))/100.0;
		boolean speakerBoost = Boolean.parseBoolean(""+voiceConfiguration.get("speakerBoost"));
		
		
		//Ping ElevanLabs API with the request payload data
		
		//Store the voice data retrieved from the front end
		ElevenLabs.setApiKey("d6803ae96ace3de1c57a1429ff7cd937");
		ElevenLabs.setDefaultModel("eleven_monolingual_v1");
		Voice voice = Voice.getVoice(voiceId);		
		VoiceSettings settings = new VoiceSettings(stability,clarity,style,speakerBoost);
		
		//Generate the speech for the given text.	
		File fileToStore = voice.generate(textContent);
		
		//name of the stored file
		/*
		 * Convention - Voice Name-Date(DDMMYY)-Time(ms).mp3
		 * Example: Zoe23012412345678.mp3		 * 
		 */
		
		Calendar cal = Calendar.getInstance();
		Date downloadDate = cal.getTime();
		String newFileName = voice.getName()+downloadDate.getDate()+downloadDate.getMonth()+downloadDate.getYear()+cal.getTimeInMillis()+".mp3";
				
		//as of now the destination directory is the same as the source directory of the react application.
		//Need to change this to a DB in production
		File destinationDirectory = new File("D:\\Dev\\Skyvera Hackathon\\voicecraft-master\\voicecraft-master\\public\\downloaded-audio");

	    try {
	      Path destinationPath = Paths.get(destinationDirectory.getAbsolutePath(), newFileName);
	      Files.copy(fileToStore.toPath(), destinationPath);	      
	    } catch (IOException e) {
	      e.printStackTrace();
	    }		
		
	    //Return the newFileName back to the UI layer.
		return newFileName;
	}
	
	
	
	

}
