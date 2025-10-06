package org.example;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AddressBookIntegrationTests {
    @LocalServerPort int port;
    TestRestTemplate http = new TestRestTemplate();

    private String url(String p) { return "http://localhost:" + port + p; }

    @Test
    void createAndFetchBook() {
        // POST /api/addressbook/create  -> returns AddressBook
        AddressBook createdBook = http.postForObject(url("/api/addressbook/create"), null, AddressBook.class);
        assertThat(createdBook).isNotNull();
        assertThat(createdBook.getId()).isNotNull();

        // GET /api/addressbook/{id} -> same id exists
        AddressBook fetched = http.getForObject(url("/api/addressbook/" + createdBook.getId()), AddressBook.class);
        assertThat(fetched).isNotNull();
        assertThat(fetched.getId()).isEqualTo(createdBook.getId());
    }
}
