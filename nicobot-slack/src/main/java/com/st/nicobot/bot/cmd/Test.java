package com.st.nicobot.bot.cmd;

import com.st.nicobot.bot.NicoBot;
import com.st.nicobot.bot.utils.Option;
import com.st.nicobot.domain.Event;
import com.st.nicobot.repositories.EventReposiroty;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Logs on 24-04-16.
 */
@Service
public class Test extends NiCommand {

    private static Logger logger = LoggerFactory.getLogger(Test.class);

    private static final String COMMAND = "!test";
    private static final String FORMAT = "!test new|next 'str' YYYYMMDD";
    private static final String DESC = "test";

    @Autowired
    private NicoBot nicobot;

    @Autowired
    private EventReposiroty eventReposiroty;

    @Override
    public String getCommandName() {
        return COMMAND;
    }

    @Override
    public String getDescription() {
        return DESC;
    }

    @Override
    public String getFormat() {
        return FORMAT;
    }

    @Override
    @Transactional(readOnly = false)
    protected void doCommand(String command, String[] args, Option opts) {
        if("new".equals(args[0])) {
            String text = args[1];
            DateTime date = DateTimeFormat.forPattern("yyyyMMdd").parseDateTime(args[2]);

            Event event = new Event();
            event.setCreationDate(new DateTime());
            event.setEventDate(date);
            event.setEventName(text);

            eventReposiroty.save(event);
        } else if ("next".equals(args[0])) {
            try {
                Event event = eventReposiroty.findFirstByOrderByEventDateDesc();

                nicobot.sendMessage(opts.message, event.getEventName() + " " + event.getEventDate());
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }

    }
}
