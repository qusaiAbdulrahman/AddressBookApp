package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class AddressBookViewController {

    @Autowired
    private AddressBookRepository addressBookRepository;

    @GetMapping("/addressbook/{id}/view")
    public String viewBuddies(@PathVariable Long id, Model model) {
        Optional<AddressBook> addressBookOpt = addressBookRepository.findById(id);
        if (addressBookOpt.isPresent()) {
            AddressBook addressBook = addressBookOpt.get();
            model.addAttribute("buddies", addressBook.getBuddies());
            return "buddies";
        } else {
            return "error"; // You can create an error template if needed.
        }
    }
}
