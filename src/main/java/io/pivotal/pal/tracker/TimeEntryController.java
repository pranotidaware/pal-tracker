package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.Optional;
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
        TimeEntry timeEntryRes = timeEntryRepository.create(timeEntry);
        return new ResponseEntity<>(timeEntryRes, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<TimeEntry> get(@PathVariable long id) {
        Optional<TimeEntry> timeEntry = Optional.ofNullable(timeEntryRepository.get(id));
        return (timeEntry.isPresent()) ?
                ResponseEntity.ok(timeEntry.get()) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable long id, @RequestBody TimeEntry timeEnt) {
        Optional<TimeEntry> timeEntryRes = Optional.ofNullable(timeEntryRepository.get(id));
        if(timeEntryRes.isPresent()){
            TimeEntry timeEntry = timeEntryRepository.update(id,timeEnt);
            return ResponseEntity.ok(timeEntry);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> getAll() {
        List<TimeEntry> timeEntryList = new ArrayList<TimeEntry>();;
        timeEntryRepository.list().forEach(timeEntryList::add);
        return ResponseEntity.ok(timeEntryList);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable Long id) {
        Optional<TimeEntry> doomed = Optional.ofNullable(timeEntryRepository.delete(id));
        return new ResponseEntity<>((doomed.isPresent()) ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND);
    }


}