package loc.stalex.studentorder.validator.register;

import loc.stalex.studentorder.domain.Adult;
import loc.stalex.studentorder.domain.Child;
import loc.stalex.studentorder.domain.Person;
import loc.stalex.studentorder.domain.register.CityRegisterResponse;
import loc.stalex.studentorder.exception.CityRegisterException;

public class FakeCityRegisterChecker implements CityRegisterChecker {

    private static final String HUSBAND_ID = "1000";
    private static final String WIFE_ID = "2000";
    private static final String BAD_HUSBAND_ID = "1001";
    private static final String BAD_WIFE_ID = "2001";
    private static final String ERROR_1 = "1002";
    private static final String ERROR_2 = "2002";

    public CityRegisterResponse checkerPerson(Person person) throws CityRegisterException {
        CityRegisterResponse res = new CityRegisterResponse();

        if (person instanceof Adult) {
            Adult t = (Adult) person;
            String passportSeries = t.getPassportSeries();
            if (HUSBAND_ID.equals(passportSeries) || WIFE_ID.equals(passportSeries)) {
                res.setRegistered(true);
                res.setTemporal(false);
            }

            if (BAD_HUSBAND_ID.equals(passportSeries) || BAD_WIFE_ID.equals(passportSeries)) {
                res.setRegistered(false);
            }

            if (ERROR_1.equals(passportSeries) || ERROR_2.equals(passportSeries)) {
                throw new CityRegisterException("1", "GRN Error " + passportSeries);
            }
        }

        if (person instanceof Child) {
            res.setRegistered(true);
            res.setTemporal(true);
        }

        System.out.println(res);

        return res;
    }
}
