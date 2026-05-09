package model;

public class Room implements IRoom {
private final String no;
private final Double price;
private final RoomType kind;

public Room(String no, Double price, RoomType kind) {
if (no == null || no.trim().isEmpty()) { throw new IllegalArgumentException("Invalid room number"); }
if (price < 0) { throw new IllegalArgumentException("Room price cannot be negative"); }
this.no = no; this.price = price; this.kind = kind;
}

@Override public String getRoomNumber() { return no; }
@Override public Double getRoomPrice() { return price; }
@Override public RoomType getRoomType() { return kind; }
@Override public boolean isFree() { return false; }
@Override public String toString() { return "Room Number: " + no + "\nPrice: $" + price + "\nRoom Type: " + kind; }
}
