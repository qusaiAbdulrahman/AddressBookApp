package org.example;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/addressbook")
public class AddressBookController {
    private final AddressBookRepository addressBookRepo;
    private final BuddyInfoRepository buddyRepo;


    public AddressBookController(AddressBookRepository addressBookRepo, BuddyInfoRepository buddyRepo) {
        this.addressBookRepo = addressBookRepo;
        this.buddyRepo = buddyRepo;
    }

    @PostMapping("/create")
    public AddressBook createBook(){
        return addressBookRepo.save(new AddressBook());
    }

    @GetMapping("/{id}")
    public AddressBook getBook(@PathVariable Long id) {
        return addressBookRepo.findById(id).orElseThrow();
    }

    @GetMapping("/all")
    public Iterable<AddressBook> listBooks() {
        return addressBookRepo.findAll();
    }

    @GetMapping("/{id}/buddies")
    public List<BuddyInfo> listBuddies(@PathVariable Long id) {
        AddressBook book = addressBookRepo.findById(id).orElseThrow();
        return book.getBuddies();
    }

    @PostMapping("/{id}/buddies")
    public BuddyInfo createBuddy(@PathVariable Long id, @RequestBody BuddyInfo buddyInfo){
        AddressBook addressBook = addressBookRepo.findById(id).orElseThrow();
        addressBook.addBuddy(buddyInfo);
        buddyRepo.save(buddyInfo);
        return buddyInfo;
    }

    @DeleteMapping("/{bookId}/buddies/{buddyId}")
    public void removeBuddy(@PathVariable Long bookId, @PathVariable Long buddyId){
        buddyRepo.deleteById(buddyId);
    }
}

