package loc.stalex.studentorder.validator.register;

import loc.stalex.studentorder.domain.Person;
import loc.stalex.studentorder.domain.register.CityRegisterResponse;
import loc.stalex.studentorder.exception.CityRegisterException;

public interface CityRegisterChecker {

    CityRegisterResponse checkerPerson(Person person) throws CityRegisterException;
}
