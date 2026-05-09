package service;

import model.*;

import java.util.*;

public class ReservationService {
private static ReservationService one;
private final Map<String, IRoom> roomBag = new HashMap<>();
private final Collection<Reservation> slips = new ArrayList<>();

private ReservationService() {}

public static ReservationService getInstance() { if (one == null) { one = new ReservationService(); } return one; }

public void addRoom(IRoom r) {
if (roomBag.containsKey(r.getRoomNumber())) { throw new IllegalArgumentException("Room number already exists"); }
roomBag.put(r.getRoomNumber(), r);
}

public IRoom getARoom(String no) { return roomBag.get(no); }
public Collection<IRoom> getAllRooms() { return roomBag.values(); }

public Reservation reserveRoom(Customer c, IRoom r, Date in, Date out) {
for (Reservation s : slips) { if (s.getRoom().getRoomNumber().equals(r.getRoomNumber())) { boolean clash = in.before(s.getCheckOutDate()) && out.after(s.getCheckInDate()); if (clash) { throw new IllegalArgumentException("Room already booked"); } } }
Reservation made = new Reservation(c, r, in, out); slips.add(made); return made;
}

public Collection<Reservation> getCustomerReservations(Customer c) {
Collection<Reservation> mine = new ArrayList<>(); for (Reservation s : slips) { if (s.getCustomer().equals(c)) { mine.add(s); } }
return mine;
}

public Collection<IRoom> findRooms(Date in, Date out) {
Collection<IRoom> open = new ArrayList<>();
for (IRoom r : roomBag.values()) { boolean ok = true; for (Reservation s : slips) { if (s.getRoom().getRoomNumber().equals(r.getRoomNumber())) { boolean clash = in.before(s.getCheckOutDate()) && out.after(s.getCheckInDate()); if (clash) { ok = false; } } } if (ok) { open.add(r); } }
return open;
}

public void printAllReservations() { for (Reservation s : slips) { System.out.println(s); System.out.println("----------------------"); } }
}
