package com.ipiecoles.java.audio.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ipiecoles.java.audio.model.Artist;

public interface ArtistRepository extends PagingAndSortingRepository<Artist, Integer> {
	
	Page<Artist> findByNameIgnoreCase(String name, Pageable pageable);

	Artist findByNameIgnoreCase(String name);

}
