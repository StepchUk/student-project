package loc.stalex.studentorder.validator.register;

import loc.stalex.studentorder.domain.register.CityRegisterResponse;
import loc.stalex.studentorder.domain.Person;
import loc.stalex.studentorder.exception.CityRegisterException;
import loc.stalex.studentorder.exception.TransportException;

public class RealCityRegisterChecker implements CityRegisterChecker {

    public CityRegisterResponse checkerPerson(Person person)
            throws CityRegisterException, TransportException {
        return null;
    }
}
