package com.jonathanperez.perspective.service;

import java.util.List;

import com.jonathanperez.perspective.dto.AuthorDTO;
import com.jonathanperez.perspective.entities.Author;

public interface AuthorService {
	public List<Author> getAuthors();
	public Author getAuthor(long id);
	public void createAuthor(Author author);
	public Author updateAuthor(AuthorDTO authorDTO, long id);
	public void deleteAuthor(long id);
}