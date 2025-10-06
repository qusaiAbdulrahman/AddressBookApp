package org.example;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AddressBookController.class)
public class AddressBookControllerWebTest {
    @Autowired MockMvc mvc;

    @MockBean AddressBookRepository addressBookRepo;
    @MockBean BuddyInfoRepository buddyRepo;

    @Test
    void listBuddies_returns200() throws Exception {
        BuddyInfo b = new BuddyInfo();
        b.setName("Alice");
        b.setPhoneNumber("111");
        AddressBook book = new AddressBook();
        book.setId(1L);
        book.addBuddy(b);
        when(addressBookRepo.findById(1L)).thenReturn(Optional.of(book));

        mvc.perform(get("/api/addressbook/1/buddies"))
                .andExpect(status().isOk());
    }
}
