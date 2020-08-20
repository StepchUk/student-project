package loc.stalex.city.dao;

import loc.stalex.city.domain.PersonRequest;
import loc.stalex.city.domain.PersonResponse;
import loc.stalex.city.exception.PersonCheckException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PersonCheckDaoTest {

    @Test
    void checkPerson() throws PersonCheckException {
        PersonRequest pr = new PersonRequest();
        pr.setSurName("Vasilev");
        pr.setGivenName("Pavel");
        pr.setPatronymic("Nikolaevich");
        pr.setDateOfBirth(LocalDate.of(1995, 3, 18));
        pr.setStreetCode(1);
        pr.setBuilding("10");
        pr.setExtension("2");
        pr.setApartment("121");

        PersonCheckDao dao = new PersonCheckDao();
        PersonResponse ps = dao.checkPerson(pr);

        assertTrue(ps.isRegistered());
        assertFalse(ps.isTemporal());
    }

    @Test
    void checkPersonWife() throws PersonCheckException {
        PersonRequest pr = new PersonRequest();
        pr.setSurName("Vasileva");
        pr.setGivenName("Irina");
        pr.setPatronymic("Petrovna");
        pr.setDateOfBirth(LocalDate.of(1997, 8, 21));
        pr.setStreetCode(1);
        pr.setBuilding("271");
        pr.setApartment("4");

        PersonCheckDao dao = new PersonCheckDao();
        PersonResponse ps = dao.checkPerson(pr);

        assertTrue(ps.isRegistered());
        assertFalse(ps.isTemporal());
    }
}