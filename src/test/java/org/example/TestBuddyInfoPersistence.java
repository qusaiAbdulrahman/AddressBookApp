package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestBuddyInfoPersistence implements CommandLineRunner {

    @Autowired
    private BuddyInfoRepository buddyInfoRepository;

    @Override
    public void run(String... args) throws Exception {
        // Create a new BuddyInfo instance
        BuddyInfo buddy = new BuddyInfo("Qusai", "(613)-252-9999");

        // Save the BuddyInfo object
        buddyInfoRepository.save(buddy);

        // Fetch and print all BuddyInfo objects
        Iterable<BuddyInfo> buddies = buddyInfoRepository.findAll();
        System.out.println("List of BuddyInfo objects in the database:");
        for (BuddyInfo b : buddies) {
            System.out.println(b.getName() + " - " + b.getPhoneNumber());
        }
    }
}
