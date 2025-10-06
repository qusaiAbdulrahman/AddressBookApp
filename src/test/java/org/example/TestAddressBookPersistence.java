package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestAddressBookPersistence implements CommandLineRunner {

    @Autowired
    private AddressBookRepository addressBookRepository;

    @Override
    public void run(String... args) throws Exception {
        // Create BuddyInfo objects
        BuddyInfo buddy1 = new BuddyInfo("Qusai", "(613)-252-9999");
        BuddyInfo buddy2 = new BuddyInfo("John", "(555)-123-4567");

        // Create AddressBook and add buddies
        AddressBook addressBook = new AddressBook();
        addressBook.addBuddy(buddy1);
        addressBook.addBuddy(buddy2);

        // Save AddressBook (will cascade and save buddies as well)
        addressBookRepository.save(addressBook);

        // Fetch and print all AddressBooks
        Iterable<AddressBook> addressBooks = addressBookRepository.findAll();
        for (AddressBook ab : addressBooks) {
            System.out.println("AddressBook ID: " + ab.getId());
            for (BuddyInfo b : ab.getBuddies()) {
                System.out.println("Buddy: " + b.getName() + " - " + b.getPhoneNumber());
            }
        }
    }
}
