package loc.stalex.studentrequestsproject.validator.register;

import loc.stalex.studentrequestsproject.domain.register.CityRegisterResponse;
import loc.stalex.studentrequestsproject.domain.Person;
import loc.stalex.studentrequestsproject.exception.CityRegisterException;
import loc.stalex.studentrequestsproject.exception.TransportException;

public interface CityRegisterChecker {

    CityRegisterResponse checkerPerson(Person person)
            throws CityRegisterException, TransportException;
}
