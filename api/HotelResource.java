package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;

public class HotelResource {
private CustomerService custs = CustomerService.getInstance();
private ReservationService stays = ReservationService.getInstance();

public Customer getCustomer(String mail) { return custs.getCustomer(mail); }

public void createCustomer(String mail, String fname, String lname) { custs.addCustomer(mail, fname, lname); }

public IRoom getRoom(String no) { return stays.getARoom(no); }

public Reservation bookRoom(String mail, IRoom r, Date in, Date out) {
Customer c = custs.getCustomer(mail); return stays.reserveRoom(c, r, in, out);
}

public Collection<Reservation> getCustomerReservations(String mail) {
Customer c = custs.getCustomer(mail); return stays.getCustomerReservations(c);
}

public Collection<IRoom> findRoom(Date in, Date out) { return stays.findRooms(in, out); }
}
