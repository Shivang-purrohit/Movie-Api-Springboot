package com.springboot.moviesrestapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.moviesrestapi.exception.MovieDoesNotExistsException;
import com.springboot.moviesrestapi.exception.MovieExistsException;
import com.springboot.moviesrestapi.model.Movie;
import com.springboot.moviesrestapi.repository.MoviesRepository;

@RestController
public class MoviesController {
	
	@Autowired
	private MoviesRepository repository;

	@GetMapping("/Movies")
	public List<Movie> getAllMovies(){
		return repository.getAllMovies();
	}
	
	@GetMapping("/Movies/{id}")
	public Movie getMovieById(@PathVariable int id) throws MovieDoesNotExistsException {
		return repository.getMovieById(id);
	}
	
	@PostMapping("/Movies")
	public ResponseEntity<?> addMovie(@RequestBody Movie newMovie) throws MovieExistsException {
		Movie movie = repository.addMovies(newMovie);
		return new ResponseEntity<>(movie,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/Movies/{id}")
	public ResponseEntity<?> deleteMovie(@PathVariable int id) throws MovieDoesNotExistsException{
		repository.deleteMovie(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/Movies/{id}")
	public ResponseEntity<?> editMovie(@RequestBody Movie movie,@PathVariable int id) throws MovieDoesNotExistsException{
		Movie newMovie = repository.editMovie(movie, id);
		return new ResponseEntity<>(newMovie, HttpStatus.OK);
	}
	
}
