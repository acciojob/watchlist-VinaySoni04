package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MovieRepository {
    private Map<String,Movie> movies=new HashMap<>();
    private Map<String,Director> directors=new HashMap<>();
    private Map<String,List<String>> pairs=new HashMap<>();

    public void addMovie(Movie movie) {
        movies.put(movie.getName(),movie);
    }

    public void addDirector(Director director) {
        directors.put(director.getName(),director);
    }

    public Optional<Movie> findMovie(String movie) {
        return movies.containsKey(movie)?Optional.of(movies.get(movie)):Optional.empty();
    }

    public Optional<Director> findDirector(String director) {
        return directors.containsKey(director)?Optional.of(directors.get(director)):Optional.empty();
    }

    public List<String> getAllMoviesByDirector(String director) {
        return pairs.getOrDefault(director,new ArrayList<String>());
    }

    public void addDirectorMovie(String director, List<String> list) {
        pairs.put(director,list);
    }

    public Optional<Movie> getMovieByName(String name) {
        return movies.containsKey(name)?Optional.of(movies.get(name)):Optional.empty();
    }

    public Optional<Director> getDirectorByName(String name) {
        return directors.containsKey(name)?Optional.of(directors.get(name)):Optional.empty();
    }

    public List<String> findAllMovies() {
        List<String> moviesList=new ArrayList<>();
        for(String key:movies.keySet()){
            moviesList.add(key);
        }
        return moviesList;
    }

    public void deleteMovie(String movie) {
        movies.remove(movie);
    }

    public void deleteDirector(String name) {
        directors.remove(name);
        pairs.remove(name);
    }

    public List<String> findAllDirectors() {
        return new ArrayList<>(directors.keySet());
    }
}
