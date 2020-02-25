package com.ipiecoles.java.audio;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.ipiecoles.java.audio.model.Album;
import com.ipiecoles.java.audio.model.Artist;
import com.ipiecoles.java.audio.repository.AlbumRepository;
import com.ipiecoles.java.audio.repository.ArtistRepository;

@Component
public class MyRunner implements CommandLineRunner {
	
	@Autowired
	private AlbumRepository albumRepository;
	
	@Autowired
	private ArtistRepository artistRepository;
	
	
	@Override
	public void run(String... strings) throws Exception {
		
		System.out.println(artistRepository.count());
		
		Optional<Artist> a = artistRepository.findById(5);
		if(a.isPresent()) {
			System.out.println(a.get().getName());
		} else {
			System.out.println("Artiste non trouvé");
		}
	/*
	    Album album = albumRepository.findByTitle("Let There Be Rock");
	    System.out.println(album.getTitle());
	    System.out.println(album.getArtistId().getName());
	                                                        */
	  System.out.println(albumRepository.findByTitle("Let There Be Rock"));
	
		Page<Artist> artists = artistRepository.findAll(PageRequest.of(0, 10, Sort.Direction.ASC, "Name"));
		
		System.out.println(artists);
		System.out.println(artists.getTotalElements());
		for(Artist artist:artists) {
			System.out.println(artist);
		}
		
		Page<Artist> artistsNames = artistRepository.findByNameIgnoreCase("aerosmith", PageRequest.of(0, 10, Sort.Direction.ASC, "Name"));
        for(Artist artist: artistsNames) {
        	System.out.println(artist);
        }
     /*   
        Artist artist = artistRepository.findById(5).get();
        Album album = albumRepository.findByArtist(artist);
        System.out.println(album);
     */   	
	}
	
	
	public static void print(Object t) {
		System.out.println(t);
	}
	

}
