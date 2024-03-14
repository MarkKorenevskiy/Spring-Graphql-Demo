package com.korenevskiy.graphqldemo.controller;

import com.korenevskiy.graphqldemo.repository.AuthorRepository;
import com.korenevskiy.graphqldemo.repository.BookRepository;
import com.korenevskiy.graphqldemo.model.Author;
import com.korenevskiy.graphqldemo.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @SchemaMapping
    public List<Book> books(Author author) {
        return bookRepository.findAllByAuthorId(author.getId());
    }

    @QueryMapping
    public Book bookById(@Argument Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @MutationMapping
    public Book addNewBook(@Argument String name, @Argument int pageCount, @Argument Long authorId) {

        Author authorRef = authorRepository.getReferenceById(authorId);
        Book book = Book.builder()
                .name(name)
                .pageCount(pageCount)
                .author(authorRef)
                .build();

        return bookRepository.save(book);
    }

    @QueryMapping
    public List<Book> allBooks(@Argument int count) {
        return bookRepository.findAll().stream()
                .limit(count)
                .toList();
    }

}
