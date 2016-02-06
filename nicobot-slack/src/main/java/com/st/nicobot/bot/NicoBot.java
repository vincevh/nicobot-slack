package com.st.nicobot.bot;

import com.st.nicobot.bot.utils.Emoji;
import com.ullink.slack.simpleslackapi.SlackChannel;
import com.ullink.slack.simpleslackapi.SlackMessageHandle;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.SlackUser;
import com.ullink.slack.simpleslackapi.events.SlackMessagePosted;
import com.ullink.slack.simpleslackapi.replies.SlackMessageReply;

import java.io.IOException;

/**
 * Created by Logs on 09-05-15.
 */
public interface NicoBot extends SlackSession {

    void connect() throws IOException;

    SlackMessageHandle<SlackMessageReply> sendMessage(SlackChannel channel, SlackUser sender, String message);

    SlackMessageHandle<SlackMessageReply> sendMessage(SlackChannel channel, SlackUser sender, String message, Emoji emoji);

    SlackMessageHandle<SlackMessageReply> sendMessage(SlackMessagePosted originator, String message);

    SlackMessageHandle<SlackMessageReply> sendMessage(SlackMessagePosted originator, String message, Emoji emoji, boolean placeReactionOnBotMsg);

    SlackMessageHandle<SlackMessageReply> sendPrivateMessage(SlackMessagePosted originator, String message);

    boolean isSelfMessage(SlackMessagePosted message);

    String getBotName();

}
