package com.ipiecoles.java.audio.controller;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.ipiecoles.java.audio.exception.ArtistException;
import com.ipiecoles.java.audio.exception.ConflictException;

import com.ipiecoles.java.audio.model.Artist;

import com.ipiecoles.java.audio.repository.ArtistRepository;



@RestController
@RequestMapping("/artists")
public class ArtistController {

	@Autowired
	private ArtistRepository artistRepository;


	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Artist getArtistById(@PathVariable("id") Integer id) {

		Optional<Artist> artist  = artistRepository.findById(id);
		if(artist.isPresent())
		{
			return artist.get();
		}
		throw new EntityNotFoundException("L'artiste d'identifiant " + id + " n'existe pas!");
	}


	@RequestMapping(value = "", params = "name", method = RequestMethod.GET)
	public Page<Artist> findWithArtists(
			@RequestParam("name") String name,
			@RequestParam("page") Integer page,
			@RequestParam("size") Integer size,
			@RequestParam("sortDirection") Sort.Direction sortDirection,
			@RequestParam("sortProperty") String sortProperty
			){
		
	
		Artist artist = artistRepository.findByNameIgnoreCase(name);
		
		if(artist==null) {
			throw new EntityNotFoundException("L'artiste: " + name + " n'a pas été trouvé !");
		}
		
		if(size <=0 || size > 50) {
			throw new IllegalArgumentException("La taille des pages doit être compris entre 0 et 50");
		}
		
		Long maxPage = artistRepository.count()/size;
		if(page < 0 || page > maxPage) {
			throw new IllegalArgumentException("La page " + page + " doit être compris entre 0 et " + maxPage);
		}
		
		
		if(Arrays.stream(Artist.class.getDeclaredFields()).map(Field::getName).filter(s -> s.equals(sortProperty)).count() !=1) {
			throw new IllegalArgumentException("La propriété " + sortProperty + " n'existe pas !");
		}
		
		
/*
		if(!Arrays.asList("id", "name").contains(sortProperty)) { 
			throw new IllegalArgumentException("La propriété de tri est incorrecte");
		}
*/
		Page<Artist> artists = artistRepository.findByNameIgnoreCase(name, PageRequest.of(page, size, sortDirection, sortProperty));
		return artists;
	}


	@RequestMapping(value = "", method = RequestMethod.GET)
	public Page<Artist> findAllArtists(
			@RequestParam("page") Integer page,
			@RequestParam("size") Integer size,
			@RequestParam("sortDirection") Sort.Direction sortDirection,
			@RequestParam("sortProperty") String sortProperty
			){
		
		

		if(size <=0 || size > 50) {
			throw new IllegalArgumentException("La taille des pages doit être compris entre 0 et 50");
		}
		
		Long maxPage = artistRepository.count()/size;
		if(page < 0 || page > maxPage) {
			throw new IllegalArgumentException("La page " + page + " doit être compris entre 0 et " + maxPage);
		}	

		if(Arrays.stream(Artist.class.getDeclaredFields()).map(Field::getName).filter(s -> s.equals(sortProperty)).count() !=1) {
			throw new IllegalArgumentException("La propriété " + sortProperty + " n'existe pas !");
		}

		return artistRepository.findAll(PageRequest.of(page, size, sortDirection, sortProperty));
	}



	@RequestMapping(value = "", method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Artist createArtist(
			@RequestBody Artist artist) throws ConflictException {
			
		
		if(artistRepository.findByNameIgnoreCase(artist.getName())!=null) {

			throw new ConflictException("Un artiste " + artist.getName() + " existe déja, veuillez saisir un nouveau artiste");

		}
		return artistRepository.save(artist);

	}


	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Artist modifArtist(
			@PathVariable("id") Integer id,
			@RequestBody Artist artist) {
		
		if(!artistRepository.existsById(id)){
			throw new EntityNotFoundException("L'artiste d'identifiant " + id + " n'existe pas !");
		}
		
		 if(!id.equals(artist.getId())) {
	            throw new IllegalArgumentException("Requête invalide");
	        }

		return artistRepository.save(artist);

	}


	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteArtist(
			@PathVariable("id") Integer id) throws ArtistException {


		Optional<Artist> a = artistRepository.findById(id);
		
		if(! a.isPresent()) {
			throw new EntityNotFoundException("L'artiste d'identifiant " + id + " n'a pas été trouvé !");
		}
		
		Artist artist = a.get();

		if(!artist.getAlbums().isEmpty()) {
			throw new ArtistException("Pour supprimer un artiste, il faut que son album soit vide.");
		}

		artistRepository.deleteById(id);
	}


} 
