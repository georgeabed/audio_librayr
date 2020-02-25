package com.ipiecoles.java.audio.model;

import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "artist")
public class Artist {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ArtistId")
	private Integer id;
	
	@Column(name = "Name")
	private String name;
	
	@OneToMany(mappedBy = "artist", fetch = FetchType.EAGER)
	@JsonIgnoreProperties("artist")
	private Set<Album> albums;
	
	public Artist() {
		
	}

	public Artist(Integer artistId, String name) {
		super();
		this.id = artistId;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer artistId) {
		this.id = artistId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	public Set<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(Set<Album> albums) {
		this.albums = albums;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Artist other = (Artist) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Artist [artistId=" + id + ", name=" + name + "]";
	}
	
	

}
