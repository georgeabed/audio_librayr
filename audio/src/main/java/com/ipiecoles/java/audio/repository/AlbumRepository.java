package com.ipiecoles.java.audio.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ipiecoles.java.audio.model.Album;
import com.ipiecoles.java.audio.model.Artist;

public interface AlbumRepository extends PagingAndSortingRepository<Album, Integer> {
	
	Album findByTitle(String title);
	
	Album findByArtist(Artist artist);

}
