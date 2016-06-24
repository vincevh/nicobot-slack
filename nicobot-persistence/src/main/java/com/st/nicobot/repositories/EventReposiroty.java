package com.st.nicobot.repositories;

import com.st.nicobot.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Logs on 23-04-16.
 */
public interface EventReposiroty extends JpaRepository<Event, Long> {

    Event findFirstByOrderByEventDateDesc();
}
