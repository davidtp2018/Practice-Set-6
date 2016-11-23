package base;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.PersonDomainModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person_Test {

	private static PersonDomainModel person1;
	private static PersonDomainModel person2;

	private static UUID person1UUID = UUID.randomUUID();
	private static UUID person2UUID = UUID.randomUUID();

	@BeforeClass
	public static void personInstance() throws Exception {

		Date person1Birth = null;
		Date person2Birth = null;

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		person1 = new PersonDomainModel();
		person2 = new PersonDomainModel();

		try {
			person1Birth = dateFormat.parse("1994-11-27");
			person2Birth = dateFormat.parse("1995-05-20");

		} catch (ParseException e) {
			e.printStackTrace();
		}

		person1.setPersonID(person1UUID);
		person1.setFirstName("Mingkun");
		person1.setMiddleName("a");
		person1.setLastName("Chen");
		person1.setBirthday(person1Birth);
		person1.setCity("Elkton");
		person1.setStreet("702 Stone Gate Blvd");
		person1.setPostalCode(21921);

		person2.setPersonID(person2UUID);
		person2.setFirstName("Jackie");
		person2.setMiddleName("Alan");
		person2.setLastName("Chan");
		person2.setBirthday(person2Birth);
		person2.setCity("Newark");
		person2.setStreet("304 Ranks Road");
		person2.setPostalCode(23415);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		PersonDAL.deletePerson(person1.getPersonID());
		PersonDAL.deletePerson(person2.getPersonID());
		assertNull("Empty Database", null);
	}

	@Test
	public void AddPersonTest() {

		PersonDAL.addPerson(person1);
		assertNotNull("Person1 has a record in the database", person1);

	}

	@Test
	public void AddPersonTest2() {
		PersonDAL.addPerson(person2);
		assertNotNull("Person1 has a record in the database", person2);
	}

	@Test
	public void getPerson() {
		PersonDAL.getPerson(person1UUID);
		assertTrue(person1.getPersonID() == person1UUID);
	}

	@Test
	public void getAllPersons() {
		ArrayList<PersonDomainModel> per = new ArrayList<PersonDomainModel>();
		per.add(person1);
		per.add(person2);
		assertNotNull(per == PersonDAL.getAllPersons());

	}

	@Test
	public void UpdatePersonTest() {

		person1.setLastName("Eagle");
		person1.setCity("Wilmington");
		PersonDAL.updatePerson(person1);
		assertTrue(person1.getLastName() == "Eagle");
		assertTrue(person1.getCity() == "Wilmington");
	}

	@Test
	public void UpdatePersonTest2() {

		person2.setLastName("Randy");
		person2.setStreet("352 West Hill Street");
		PersonDAL.updatePerson(person2);
		assertTrue(person2.getLastName() == "Randy");
		assertTrue(person2.getStreet() == "352 West Hill Street");
	}

	@Test
	public void DeletePersonTest() {

		PersonDAL.addPerson(person1);
		PersonDAL.deletePerson(person1UUID);
		assertNull("There is no record of person1", null);

	}

}