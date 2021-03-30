package com.cpirrachatbot;

import org.jibble.pircbot.PircBot;

import java.util.Random;

public class TwitchBot extends PircBot {

    private String channel;

    public TwitchBot(String nick, String channel) {
        this.channel = channel;

        setName(nick);
        setLogin(nick);
    }

    @Override
    protected void onConnect() {
        super.onConnect();
        System.out.println("Connected!");

        // Sending special capabilities.
        sendRawLine("CAP REQ :twitch.tv/membership");
        sendRawLine("CAP REQ :twitch.tv/commands");
        sendRawLine("CAP REQ :twitch.tv/tags");
    }

    @Override
    protected void handleLine(String line) {
        super.handleLine(line);
        if (line.contains("PRIVMSG")) {
            TwitchMessage message = parseLine(line);
            if (message.getMessage().charAt(0) == '!') {
//                String response = "@" + message.getUsername() + " " + handleMessage(message);
                String response = handleMessage(message);
                sendMessage(channel, response);

            }
        }
    }

    private static TwitchMessage parseLine(String line) {

        String[] arr = line.split(";");
        String username = arr[4].trim().substring(13);

        String body = line.substring(line.lastIndexOf(":") + 1);
        System.out.println(body);

        return new TwitchMessage(username, body);
    }


    private static String handleMessage(TwitchMessage message) {

        if (message.getMessage().equalsIgnoreCase("!time")) {
            String time = new java.util.Date().toString();
            return " " + time;
        }
        if (message.getMessage().equalsIgnoreCase("!ping")) {
            return "pong";
        }
        if (message.getMessage().equalsIgnoreCase("!mmr")) {
            return "mmr is just a number! Kappa";
        }
        if (message.getMessage().equalsIgnoreCase("!pc")) {
            return "i5-9300h, 1660ti, 16gb ram";
        }
        if (message.getMessage().equalsIgnoreCase("!toxic")) {
            Random rand = new Random();
            int random = rand.nextInt(101);
            if (random < 50) {
                return "@" + message.getUsername() + " is " + random + "% toxic GlitchCat" ;
            } else return "@" + message.getUsername() + " is " + random + "% toxic NotLikeThis";
        }
        if (message.getMessage().equalsIgnoreCase("!keks")) {
            return "cpirraKEKS";
        }
        if (message.getMessage().startsWith("!ban ")) {
            return message.getMessage().split(" ")[1] + " is banned";
        }
        if (message.getMessage().startsWith("!flame ")) {
            return message.getMessage().split(" ")[1] + " you a noob";
        }

        return "Unsupported operation BibleThump";
    }
}