package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/buddyinfo")
public class BuddyInfoController {

    @Autowired
    private BuddyInfoRepository buddyInfoRepository;

    // Create a new BuddyInfo
    @PostMapping("/create")
    public BuddyInfo createBuddy(@RequestBody BuddyInfo buddyInfo) {
        return buddyInfoRepository.save(buddyInfo);
    }

    // Get all buddies
    @GetMapping("/all")
    public Iterable<BuddyInfo> getAllBuddies() {
        return buddyInfoRepository.findAll();
    }
}
