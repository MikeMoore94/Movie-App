package com.example.springbootbackend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springbootbackend.model.Movie;

@Repository
public interface MoviesRepo extends JpaRepository<Movie, Long>{
    
}