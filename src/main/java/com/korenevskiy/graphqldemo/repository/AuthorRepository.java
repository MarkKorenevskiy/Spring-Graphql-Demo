package com.korenevskiy.graphqldemo.repository;

import com.korenevskiy.graphqldemo.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
