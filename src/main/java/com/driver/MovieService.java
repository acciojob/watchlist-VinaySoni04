package com.driver;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;
    public void addMovie(Movie movie) {
        movieRepository.addMovie(movie);
    }

    public void addDirector(Director director) {
        movieRepository.addDirector(director);
    }

    public void addMovieDirectorPair(String movie, String director) {
        Optional<Movie> foundMovie=movieRepository.findMovie(movie);
        Optional<Director> foundDirector=movieRepository.findDirector(director);
        if(foundMovie.isEmpty() || foundDirector.isEmpty()){
            throw new NotFoundException("Entities not present");
        }
        List<String> list=movieRepository.getAllMoviesByDirector(director);
        list.add(movie);
        movieRepository.addDirectorMovie(director,list);
        Director dir=foundDirector.get();
        dir.setNumberOfMovies(dir.getNumberOfMovies()+1);
    }

    public Movie getMovieByName(String name) {
        Optional<Movie> movie=movieRepository.getMovieByName(name);
        if(movie.isEmpty())
            throw new NotFoundException("Movie not found");
        return movie.get();
    }

    public Director getDirectorByName(String name) {
        Optional<Director> director=movieRepository.getDirectorByName(name);
        if(director.isEmpty())
            throw new NotFoundException("Director not found");
        return director.get();
    }

    public List<String> getMoviesByDirectorName(String director) {
        List<String> movies=movieRepository.getAllMoviesByDirector(director);
        if(movies.isEmpty())
            throw new NotFoundException("Not found");
        return movies;
    }

    public List<String> findAllMovies() {
        return movieRepository.findAllMovies();
    }

    public void deleteDirectorByName(String name) {
        List<String> movies=getMoviesByDirectorName(name);
        for(String movie:movies){
            movieRepository.deleteMovie(movie);
        }
        movieRepository.deleteDirector(name);
    }

    public void deleteAllDirectors() {
        List<String> directors=movieRepository.findAllDirectors();
        for(String director:directors){
            this.deleteDirectorByName(director);
        }
    }
}
