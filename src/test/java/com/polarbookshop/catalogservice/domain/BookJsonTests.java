package com.polarbookshop.catalogservice.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

@JsonTest
public class BookJsonTests {

    @Autowired
    JacksonTester<Book> jacksonTester;

    @Test
    public void testSerialize() throws IOException {
        Book book = new Book("1234567890", "title", "author", 9.90);
        var jsonBook = jacksonTester.write(book);
        assertThat(jsonBook).extractingJsonPathStringValue("@.isbn")
                .isEqualTo(book.isbn());
        assertThat(jsonBook).extractingJsonPathStringValue("@.title")
                .isEqualTo(book.title());
        assertThat(jsonBook).extractingJsonPathStringValue("@.author")
                .isEqualTo(book.author());
        assertThat(jsonBook).extractingJsonPathNumberValue("@.price")
                .isEqualTo(book.price());
    }

    @Test
    public void testDeserialize() throws IOException {
        var jsonBook = """
        {
            "isbn":"1234567890",
            "title":"title",
            "author":"author",
            "price":9.90
        }
        """;
        assertThat(jacksonTester.parse(jsonBook))
                .usingRecursiveComparison()
                .isEqualTo(new Book("1234567890", "title", "author", 9.90));
    }
}
