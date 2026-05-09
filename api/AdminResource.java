package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;

public class AdminResource {
private final CustomerService custs = CustomerService.getInstance();
private final ReservationService stays = ReservationService.getInstance();

public Collection<Customer> getAllCustomers() { return custs.getAllCustomers(); }

public void addRoom(Collection<IRoom> bunch) { for (IRoom r : bunch) { stays.addRoom(r); } }

public Collection<IRoom> getAllRooms() { return stays.getAllRooms(); }
public void displayAllReservations() { stays.printAllReservations(); }
}
