package loc.stalex.studentorder.dao;

import loc.stalex.studentorder.domain.CountryArea;
import loc.stalex.studentorder.domain.PassportOffice;
import loc.stalex.studentorder.domain.RegisterOffice;
import loc.stalex.studentorder.domain.Street;
import loc.stalex.studentorder.exception.DaoException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DictionaryDaoImplTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(DictionaryDaoImplTest.class);

    @BeforeAll
    public static void startUp() throws Exception {
        DBInit.startUp();
    }

    @Test
    public void testStreet() throws DaoException {
        LocalDateTime dt = LocalDateTime.now();
        LocalDateTime dt2 = LocalDateTime.now();
        LOGGER.info("TEST {} {}", dt, dt2);
        List<Street> streets = new DictionaryDaoImpl().findStreet("про");

        assertTrue(streets.size() == 2);
    }

    @Test
    public void testRegisterOffice() throws DaoException {
        List<PassportOffice> passportOffices = new DictionaryDaoImpl().findPassportOffice("010020000000");

        assertTrue(passportOffices.size() == 2);
    }

    @Test
    public void testExample3() throws DaoException {
        List<RegisterOffice> registerOffices = new DictionaryDaoImpl().findRegisterOffice("010010000000");

        assertTrue(registerOffices.size() == 2);
    }

    @Test
    public void testArea() throws DaoException {
        List<CountryArea> countryAreas = new DictionaryDaoImpl().findAreas("");
        assertTrue(countryAreas.size() == 2);

        List<CountryArea> countryAreas2 = new DictionaryDaoImpl().findAreas("020000000000");
        assertTrue(countryAreas2.size() == 2);

        List<CountryArea> countryAreas3 = new DictionaryDaoImpl().findAreas("020010000000");
        assertTrue(countryAreas3.size() == 2);

        List<CountryArea> countryAreas4 = new DictionaryDaoImpl().findAreas("020010010000");
        assertTrue(countryAreas4.size() == 2);
    }
}