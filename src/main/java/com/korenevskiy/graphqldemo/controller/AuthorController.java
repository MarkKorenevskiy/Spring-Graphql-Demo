package com.korenevskiy.graphqldemo.controller;

import com.korenevskiy.graphqldemo.model.Author;
import com.korenevskiy.graphqldemo.model.Book;
import com.korenevskiy.graphqldemo.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorRepository authorRepository;

    @SchemaMapping
    public Author author(Book book) {
        return authorRepository.findById(book.getAuthor().getId())
                .orElse(null);
    }

    @QueryMapping
    public Author authorById(@Argument Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    @QueryMapping
    public List<Author> allAuthors(@Argument int count) {
        return authorRepository.findAll()
                .stream()
                .limit(count)
                .toList();
    }


    @MutationMapping
    public Author addNewAuthor(@Argument String firstName, @Argument String lastName) {
        Author author = Author.builder()
                .firstName(firstName)
                .lastName(lastName)
                .build();
        return authorRepository.save(author);
    }

}
