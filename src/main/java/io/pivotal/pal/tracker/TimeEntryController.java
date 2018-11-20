package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    public TimeEntryRepository timeEntryRepository;

    TimeEntryController(TimeEntryRepository timeEntryRepository){
        this.timeEntryRepository = timeEntryRepository;
    }



    @PostMapping
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntry) {
        timeEntryRepository.create(timeEntry);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<TimeEntry> get(@PathVariable long id) {
        TimeEntry timeEntry = timeEntryRepository.get(id);
        return ResponseEntity.ok(timeEntry);
    }

    @PutMapping("{id}")
    public ResponseEntity<TimeEntry> get(@PathVariable long id, @RequestBody TimeEntry timeEnt) {
        TimeEntry timeEntry = timeEntryRepository.update(id,timeEnt);
        return ResponseEntity.ok(timeEntry);
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> getAll() {
        List<TimeEntry> timeEntryList = new ArrayList<TimeEntry>();;
        timeEntryRepository.list().forEach(timeEntryList::add);
        return ResponseEntity.ok(timeEntryList);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable Long id) {
        timeEntryRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}