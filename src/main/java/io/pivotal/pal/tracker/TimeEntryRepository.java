package io.pivotal.pal.tracker;

import java.util.List;
import io.pivotal.pal.tracker.TimeEntry;

public interface TimeEntryRepository {

    public TimeEntry create(TimeEntry timeEntry);
    public TimeEntry get(Long id);
    public List<TimeEntry> list();
    public TimeEntry update(Long id, TimeEntry timeEntry);
    public void delete(Long id);
}
