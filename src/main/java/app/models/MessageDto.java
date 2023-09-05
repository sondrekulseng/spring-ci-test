package app.models;

public record MessageDto (String roomId, String sender, String message, String dateTimeSent) {};
