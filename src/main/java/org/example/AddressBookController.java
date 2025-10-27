package org.example;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/addressbook")
public class AddressBookController {
    private final AddressBookRepository addressBookRepo;
    private final BuddyInfoRepository buddyRepo;


    public AddressBookController(AddressBookRepository addressBookRepo, BuddyInfoRepository buddyRepo) {
        this.addressBookRepo = addressBookRepo;
        this.buddyRepo = buddyRepo;
    }

    @PostMapping("/create")
    public String createAddressBook(@ModelAttribute AddressBook addressBook) {
        addressBookRepo.save(addressBook);
        return "redirect:/addressbook/" + addressBook.getId() + "/view";
    }

    @PostMapping("/{id}/addBuddy")
    public ResponseEntity<String> addBuddy(@PathVariable Long id, @RequestBody BuddyInfo buddyInfo) {
        Optional<AddressBook> addressBookOpt = addressBookRepo.findById(id);

        if (addressBookOpt.isPresent()) {
            AddressBook addressBook = addressBookOpt.get();

            // Save the BuddyInfo to the BuddyInfoRepository first
            buddyInfo = buddyRepo.save(buddyInfo);

            // Add BuddyInfo to the AddressBook and save the AddressBook
            addressBook.addBuddy(buddyInfo);
            addressBookRepo.save(addressBook);

            return ResponseEntity.ok("Buddy added successfully");
        } else {
            return ResponseEntity.status(404).body("AddressBook not found");
        }
    }

    @GetMapping("/{id}/buddies")
    public List<BuddyInfo> getBuddies(@PathVariable Long id) {
        return addressBookRepo.findById(id).orElseThrow().getBuddies();
    }
}

