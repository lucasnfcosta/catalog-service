package com.polarbookshop.catalogservice.domain;

public record Book(
        String isbn,
        String author,
        String title,
        Double price
) {
}
