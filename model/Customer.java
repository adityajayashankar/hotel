package model;

import java.util.regex.Pattern;

public class Customer {
private final String fname, lname, mail;
private static final String MAIL_RULE = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.com$";

public Customer(String fname, String lname, String mail) {
if (!Pattern.matches(MAIL_RULE, mail)) { throw new IllegalArgumentException("Invalid email format"); }
this.fname = fname; this.lname = lname; this.mail = mail;
}

public String getFirstName() { return fname; }
public String getLastName() { return lname; }
public String getEmail() { return mail; }

@Override
public String toString() { return "First Name: " + fname + "\nLast Name: " + lname + "\nEmail: " + mail; }

@Override
public boolean equals(Object thing) { if (this == thing) { return true; } if (thing == null || getClass() != thing.getClass()) { return false; } Customer other = (Customer) thing; return mail.equals(other.mail); }
@Override
public int hashCode() { return mail.hashCode(); }
}
