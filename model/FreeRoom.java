package model;

public class FreeRoom extends Room {
public FreeRoom(String no, RoomType kind) { super(no, 0.0, kind); }

@Override public boolean isFree() { return true; }
@Override public String toString() { return "Room Number: " + getRoomNumber() + "\nPrice: FREE\nRoom Type: " + getRoomType(); }
}
