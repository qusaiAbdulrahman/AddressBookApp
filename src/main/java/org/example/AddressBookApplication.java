package org.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AddressBookApplication {

    public static void main(String[] args) {
        SpringApplication.run(AddressBookApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(AddressBookRepository addressBookRepository, BuddyInfoRepository buddyInfoRepository) {
        return (args) -> {
            // Create a new AddressBook
            AddressBook addressBook = new AddressBook();

            // Create new BuddyInfo instances and add them to AddressBook
            BuddyInfo buddy1 = new BuddyInfo("Qusai ALi mohandma", "(613)-252-9999");
            BuddyInfo buddy2 = new BuddyInfo("John ahdi", "(555)-123-4567");
            addressBook.addBuddy(buddy1);
            addressBook.addBuddy(buddy2);

            AddressBook addressBook2 = new AddressBook();
            BuddyInfo buddy3 = new BuddyInfo("emme akde", "(613)-752-9329");
            addressBook2.addBuddy(buddy3);
            // Save the AddressBook, which will also save the BuddyInfo objects due to CascadeType.ALL
            addressBookRepository.save(addressBook);
            addressBookRepository.save(addressBook2);

            // Fetch all AddressBook entries
            System.out.println("AddressBooks found with findAll():");
            for (AddressBook ab : addressBookRepository.findAll()) {
                System.out.println("AddressBook ID: " + ab.getId());
                for (BuddyInfo b : ab.getBuddies()) {
                    System.out.println("Buddy: " + b.getName() + " - " + b.getPhoneNumber());
                }
            }

            // Fetch all BuddyInfo entries
            System.out.println("BuddyInfos found with findAll():");
            for (BuddyInfo b : buddyInfoRepository.findAll()) {
                System.out.println("Buddy: " + b.getName() + " - " + b.getPhoneNumber());
            }
        };
    }
}
