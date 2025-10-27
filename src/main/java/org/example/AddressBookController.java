package org.example;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
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
        // Fetch the AddressBook from the repository
        AddressBook book = addressBookRepo.findById(id).orElseThrow();

        // Rely on cascade by saving the PARENT; do NOT pre-save the child
        book.addBuddy(buddyInfo);
        addressBookRepo.save(book);

        // Return updated list for immediate UI refresh
        return ResponseEntity.ok("Buddy added successfully");
    }

    @GetMapping("/{id}/buddies")
    public List<BuddyInfo> getBuddies(@PathVariable Long id) {
        return addressBookRepo.findById(id).orElseThrow().getBuddies();
    }
}

