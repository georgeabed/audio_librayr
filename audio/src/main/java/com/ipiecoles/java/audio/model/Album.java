package com.ipiecoles.java.audio.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "album")
public class Album {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AlbumId")
	private Integer id;
	
	@Column(name = "Title")
	private String title;
	
	@ManyToOne
	@JoinColumn(name = "ArtistId")
	private Artist artist;
	
	public Album() {
		
	}

	

	public Album(Integer albumId, String title, Artist artistId) {
		super();
		this.id = albumId;
		this.title = title;
		this.artist = artistId;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer albumId) {
		this.id = albumId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artistId) {
		this.artist = artistId;
	}



	@Override
	public int hashCode() {
		return Objects.hash(id, artist, title);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Album other = (Album) obj;
		return Objects.equals(id, other.id) && Objects.equals(artist, other.artist)
				&& Objects.equals(title, other.title);
	}



	@Override
	public String toString() {
		return "Album [albumId=" + id + ", title=" + title + ", artistId=" + artist + "]";
	}
	
	

}

