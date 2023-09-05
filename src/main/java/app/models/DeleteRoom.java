package app.models;

public final class DeleteRoom {
    private String roomId;
    private String pin;

    public String getPin() {
        return pin;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}
