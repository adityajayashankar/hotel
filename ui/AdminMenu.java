package ui;

import api.AdminResource;
import model.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class AdminMenu {
private static final AdminResource admin = new AdminResource();

public static void adminMenu() {
Scanner scan = new Scanner(System.in);
adminMenu(scan);
}

public static void adminMenu(Scanner scan) {
boolean run = true;
while (run) {
System.out.println("\nADMIN MENU"); System.out.println("1. See all customers"); System.out.println("2. See all rooms"); System.out.println("3. See all reservations"); System.out.println("4. Add a room"); System.out.println("5. Back to main menu");
String pick = scan.nextLine().trim();
switch (pick) {
case "1": for (Customer c : admin.getAllCustomers()) { System.out.println(c); } break;
case "2": for (IRoom r : admin.getAllRooms()) { System.out.println(r); } break;
case "3": admin.displayAllReservations(); break;
case "4":
try {
Collection<IRoom> bunch = new ArrayList<>();
System.out.println("Enter room number:"); String no = scan.nextLine().trim();
System.out.println("Enter room price:"); double cost = Double.parseDouble(scan.nextLine().trim());
System.out.println("Enter room type: 1 for SINGLE, 2 for DOUBLE"); int typePick = Integer.parseInt(scan.nextLine().trim());
RoomType kind;
if (typePick == 1) { kind = RoomType.SINGLE; } else if (typePick == 2) { kind = RoomType.DOUBLE; } else { throw new IllegalArgumentException("Invalid room type"); }
if (cost == 0) { bunch.add(new FreeRoom(no, kind)); } else { bunch.add(new Room(no, cost, kind)); }
admin.addRoom(bunch); System.out.println("Room added successfully");
} catch (Exception ex) { System.out.println("Invalid room data"); }
break;
case "5": run = false; break;
default: System.out.println("Invalid menu option");
}
}
}
}
