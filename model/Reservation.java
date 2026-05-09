package model;

import java.util.Date;

public class Reservation {
private final Customer guest;
private final IRoom bed;
private final Date inDay, outDay;

public Reservation(Customer guest, IRoom bed, Date inDay, Date outDay) {
if (!outDay.after(inDay)) { throw new IllegalArgumentException("Checkout date must be after checkin"); }
this.guest = guest; this.bed = bed; this.inDay = inDay; this.outDay = outDay;
}

public Customer getCustomer() { return guest; }
public IRoom getRoom() { return bed; }
public Date getCheckInDate() { return inDay; }
public Date getCheckOutDate() { return outDay; }
@Override public String toString() { return "Reservation Details\nCustomer Email: " + guest.getEmail() + "\nRoom Number: " + bed.getRoomNumber() + "\nCheck In Date: " + inDay + "\nCheck Out Date: " + outDay; }
}
