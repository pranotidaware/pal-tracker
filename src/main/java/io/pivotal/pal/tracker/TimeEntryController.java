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

    public JdbcTimeEntryRepository jdbcTimeEntryRepository;

    public TimeEntryController(JdbcTimeEntryRepository jdbcTimeEntryRepository){
        this.jdbcTimeEntryRepository = jdbcTimeEntryRepository;
    }



    @PostMapping
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntry) {
        TimeEntry timeEntryRes = jdbcTimeEntryRepository.create(timeEntry);
        return new ResponseEntity<>(timeEntryRes, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<TimeEntry> get(@PathVariable long id) {
        Optional<TimeEntry> timeEntry = Optional.ofNullable(jdbcTimeEntryRepository.find(id));
        return (timeEntry.isPresent()) ?
                ResponseEntity.ok(timeEntry.get()) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable long id, @RequestBody TimeEntry timeEnt) {
        Optional<TimeEntry> timeEntryRes = Optional.ofNullable(jdbcTimeEntryRepository.find(id));
        if(timeEntryRes.isPresent()){
            TimeEntry timeEntry = jdbcTimeEntryRepository.update(id,timeEnt);
            return ResponseEntity.ok(timeEntry);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> getAll() {
        List<TimeEntry> timeEntryList = new ArrayList<TimeEntry>();;
        jdbcTimeEntryRepository.list().forEach(timeEntryList::add);
        return ResponseEntity.ok(timeEntryList);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable Long id) {
        Optional<TimeEntry> timeEntryRes = Optional.ofNullable(jdbcTimeEntryRepository.find(id));
        if(timeEntryRes.isPresent()){
            jdbcTimeEntryRepository.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}