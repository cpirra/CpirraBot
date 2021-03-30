package com.cpirrachatbot;

import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.NickAlreadyInUseException;

import java.io.IOException;

public class Main {

    private static final String OAUTH = "oauth:tvf14cg3i8a3lr6rmk2fddbvnw6a6n";
    private static final String ADDRESS = "irc.twitch.tv.";
    private static final int PORT = 6667;
    private static final String Nick = "cpirra_bot";
    private static final String Channel = "#cpirra";

    public static void main(String[] args) throws NickAlreadyInUseException, IOException, IrcException {

        TwitchBot bot = new TwitchBot(Nick,Channel);
        bot.setVerbose(true);

        bot.connect(ADDRESS, PORT, OAUTH);
        bot.joinChannel(Channel);

    }
}