package com.ipiecoles.java.audio.controller;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ipiecoles.java.audio.model.Album;

import com.ipiecoles.java.audio.repository.AlbumRepository;

@RestController
@RequestMapping("/albums")
public class AlbumController {
	
	@Autowired
	private AlbumRepository albumRepository;
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	 public Album ajoutAlbum(
			 @RequestBody Album album) {
					
		 return albumRepository.save(album);
		 
	 }
	
	@RequestMapping(value = "/{X}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	 public void deleteAlbum(
			 @PathVariable("X") Integer id) {
		
		Optional<Album> a = albumRepository.findById(id);
		
		if(! a.isPresent()) {
			throw new EntityNotFoundException("L'album d'identifiant " + id + " n'a pas été trouvé !");
		}
	
		  albumRepository.deleteById(id);
		 
	 }

}
