package com.jonathanperez.perspective.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.jonathanperez.perspective.dto.AuthorDTO;
import com.jonathanperez.perspective.entities.Author;
import com.jonathanperez.perspective.exception.ResourceNotFoundException;
import com.jonathanperez.perspective.repository.AuthorRepository;
import com.jonathanperez.perspective.util.UserSessionUtil;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {
	
	@Autowired
	private AuthorRepository authorRepository;

	@Override
	public Author getAuthor(long id) {
		try {
			return authorRepository.getAuthor(id, UserSessionUtil.getUsername());
		}catch(EmptyResultDataAccessException ex) {
			throw new ResourceNotFoundException("Author", "Id", id);
		}
	}

	@Override
	public List<Author> getAuthors() {
		return authorRepository.getAuthors(UserSessionUtil.getUsername());
	}

	@Override
	public void createAuthor(Author author) {
		authorRepository.saveAuthor(author);
	}

	@Override
	public Author updateAuthor(AuthorDTO authorDTO, long id) {
		try {
			Author author = authorRepository.getAuthor(id, UserSessionUtil.getUsername());
			author.setName(authorDTO.name);
			authorRepository.updateAuthor(author);
			
			return author;
		}catch(EmptyResultDataAccessException ex) {
	 		throw new ResourceNotFoundException("Author", "Id", id);
	 	}
	}

	@Override
	public void deleteAuthor(long id) {
		try {
			Author author = authorRepository.getAuthor(id, UserSessionUtil.getUsername());	
			author.setDeleted(true);
			author.setDeletedBy("admin");
			author.setDeletedDate(new Date());
			authorRepository.updateAuthor(author);
	 	}catch(EmptyResultDataAccessException ex) {
	 		throw new ResourceNotFoundException("Author", "Id", id);
	 	}
	}

}