package com.polarbookshop.catalogservice.demo;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.domain.BookRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Profile("testdata")
public class BookDataLoader {
    private final BookRepository bookRepository;

    public BookDataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void createTestData() {
        Book book1 = new Book("1234567890", "Ficciones", "Jorge Luis Borges", 19.90);
        Book book2 = new Book("1234567891", "Metamorphoses", "Ovid", 29.90);

        bookRepository.save(book1);
        bookRepository.save(book2);
    }

}
