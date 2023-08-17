package com.springboot.moviesrestapi.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.springboot.moviesrestapi.exception.MovieDoesNotExistsException;
import com.springboot.moviesrestapi.exception.MovieExistsException;
import com.springboot.moviesrestapi.model.Movie;

@Repository
public class MoviesRepository {
	
	private List<Movie> moviesRepo=new ArrayList<>();
	
	public MoviesRepository() {
		moviesRepo.add(new Movie(01, "DDLJ", "4.0"));
		moviesRepo.add(new Movie(02, "Pathan", "3.5"));
		moviesRepo.add(new Movie(03, "Jack ryan", "4.3"));
		moviesRepo.add(new Movie(04, "Mission impossible", "4.7"));
		moviesRepo.add(new Movie(05, "John wick", "4.5"));
	}
	
	public List<Movie> getAllMovies(){
		return new ArrayList<Movie>(moviesRepo);
	}
	
	public Movie getMovieById(int id) throws MovieDoesNotExistsException {
	    Optional<Movie> movie = moviesRepo.stream()
	        .filter(m -> m.getId() == id)
	        .findFirst();

	    if (movie.isPresent()) {
	        return movie.get();
	    } else {
	        throw new MovieDoesNotExistsException("No movie found");
	    }
	}

	
	public Movie addMovies(Movie newMovie) throws MovieExistsException {
		if(moviesRepo.contains(newMovie)) {
			throw new MovieExistsException("Movie Already exists");
		}
		moviesRepo.add(newMovie);
		return newMovie;
	}
	
	public void deleteMovie(int id) throws MovieDoesNotExistsException {
	    Optional<Movie> movie = moviesRepo.stream()
	        .filter(m -> m.getId() == id)
	        .findFirst();

	    if (movie.isPresent()) {
	        moviesRepo.remove(movie.get());
	    } else {
	        throw new MovieDoesNotExistsException("No movie found by this id");
	    }
	}

	public Movie editMovie(Movie movie, int id) throws MovieDoesNotExistsException {
	    Optional<Movie> existingMovie = moviesRepo.stream()
	        .filter(m -> m.getId() == id)
	        .findFirst();

	    if (existingMovie.isPresent()) {
	        int index = moviesRepo.indexOf(existingMovie.get());
	        moviesRepo.set(index, movie);
	        return existingMovie.get();
	    } else {
	        throw new MovieDoesNotExistsException("No movie found");
	    }
	}

	
	
	
	
}
