package ui;

import api.HotelResource;
import model.IRoom;
import model.Reservation;

import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
private static final HotelResource hotel = new HotelResource();
private static final Scanner scan = new Scanner(System.in);

public static void main(String[] args) {
boolean run = true;
while (run) {
System.out.println("\nHOTEL RESERVATION APPLICATION"); System.out.println("1. Find and reserve a room"); System.out.println("2. See my reservations"); System.out.println("3. Create an account"); System.out.println("4. Admin"); System.out.println("5. Exit");
String pick = scan.nextLine().trim();
switch (pick) {
case "1": findAndReserveRoom(); break;
case "2": seeMyReservations(); break;
case "3": createAccount(); break;
case "4": AdminMenu.adminMenu(scan); break;
case "5": run = false; System.out.println("Thank you for using the app"); break;
default: System.out.println("Invalid menu option");
}
}
}

private static void createAccount() {
try {
System.out.println("Enter email:"); String mail = scan.nextLine().trim();
System.out.println("Enter first name:"); String fname = scan.nextLine().trim();
System.out.println("Enter last name:"); String lname = scan.nextLine().trim();
hotel.createCustomer(mail, fname, lname); System.out.println("Account created successfully");
} catch (Exception ex) { System.out.println("Invalid account details"); }
}

private static void seeMyReservations() {
try {
System.out.println("Enter email:"); String mail = scan.nextLine().trim();
Collection<Reservation> mine = hotel.getCustomerReservations(mail);
if (mine == null || mine.isEmpty()) { System.out.println("No reservations found"); } else { for (Reservation s : mine) { System.out.println(s); } }
} catch (Exception ex) { System.out.println("Error retrieving reservations"); }
}

private static void findAndReserveRoom() {
try {
SimpleDateFormat fmt = new SimpleDateFormat("MM/dd/yyyy"); fmt.setLenient(false);
System.out.println("Enter check-in date (MM/dd/yyyy):"); Date in = fmt.parse(scan.nextLine().trim());
System.out.println("Enter check-out date (MM/dd/yyyy):"); Date out = fmt.parse(scan.nextLine().trim());
Calendar today = Calendar.getInstance(); today.set(Calendar.HOUR_OF_DAY, 0); today.set(Calendar.MINUTE, 0); today.set(Calendar.SECOND, 0); today.set(Calendar.MILLISECOND, 0);
if (in.before(today.getTime())) { System.out.println("Check-in date cannot be in the past"); return; }
if (!out.after(in)) { System.out.println("Check-out date must be after check-in date"); return; }

Collection<IRoom> open = hotel.findRoom(in, out);
if (open.isEmpty()) {
System.out.println("No rooms available");
System.out.println("How many days later should I search?"); int zzz = Integer.parseInt(scan.nextLine().trim()); if (zzz <= 0) { throw new IllegalArgumentException("Bad days"); }
Calendar cal = Calendar.getInstance(); cal.setTime(in); cal.add(Calendar.DATE, zzz); Date tryIn = cal.getTime(); cal.setTime(out); cal.add(Calendar.DATE, zzz); Date tryOut = cal.getTime();
Collection<IRoom> maybe = hotel.findRoom(tryIn, tryOut);
if (!maybe.isEmpty()) { System.out.println("\nRecommended Rooms:"); System.out.println("Recommended Dates: " + fmt.format(tryIn) + " to " + fmt.format(tryOut)); for (IRoom r : maybe) { System.out.println(r); } }
else { System.out.println("No recommended rooms available"); }
return;
}

System.out.println("\nAvailable Rooms:");
for (IRoom r : open) { System.out.println(r); System.out.println("----------------"); }
System.out.println("Would you like to book a room? (y/n)"); String ans = scan.nextLine().trim();
if (ans.equalsIgnoreCase("y")) {
System.out.println("Enter your email:"); String mail = scan.nextLine().trim();
System.out.println("Enter room number:"); String no = scan.nextLine().trim();
IRoom r = hotel.getRoom(no); if (r == null) { System.out.println("Invalid room number"); return; }
hotel.bookRoom(mail, r, in, out); System.out.println("Reservation successful");
}
} catch (Exception ex) { System.out.println("Invalid reservation details"); }
}
}
