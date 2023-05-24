package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @PostMapping("/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie){
        movieService.addMovie(movie);
        return new ResponseEntity<>("Movie added successfully", HttpStatus.CREATED);
    }

    @PostMapping("/add-director")
    public ResponseEntity<String> addDirector(@RequestBody Director director){
        movieService.addDirector(director);
        return new ResponseEntity<>("Director added successfully", HttpStatus.CREATED);
    }

    @PutMapping("/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam String movie, @RequestParam String director) throws NotFoundException{
        try{
            movieService.addMovieDirectorPair(movie,director);
            return new ResponseEntity<>("Pair added successfully",HttpStatus.CREATED);
        } catch (NotFoundException ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable String name) throws NotFoundException{
        try{
            Movie movie=movieService.getMovieByName(name);
            return new ResponseEntity<>(movie,HttpStatus.OK);
        } catch (NotFoundException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable String name) throws NotFoundException{
        try{
            Director director=movieService.getDirectorByName(name);
            return new ResponseEntity<>(director,HttpStatus.OK);
        } catch (NotFoundException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable String director) throws NotFoundException{
        try{
            List<String> movies=movieService.getMoviesByDirectorName(director);
            return new ResponseEntity<>(movies,HttpStatus.OK);
        } catch (NotFoundException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-all-movies")
    public ResponseEntity<List<String>> findAllMovies(){
        List<String> allMovies=movieService.findAllMovies();
        if(allMovies.isEmpty())
            throw new NotFoundException("No movies are present");
        return new ResponseEntity<>(allMovies,HttpStatus.OK);
    }

    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam String name){
        movieService.deleteDirectorByName(name);
        return new ResponseEntity<>("Deleted successfully",HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors(){
        movieService.deleteAllDirectors();
        return new ResponseEntity<>("Deleted successfully",HttpStatus.ACCEPTED);
    }
}
