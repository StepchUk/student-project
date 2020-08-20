package loc.stalex.studentrequestsproject.validator;

import loc.stalex.studentrequestsproject.domain.Person;
import loc.stalex.studentrequestsproject.domain.register.AnswerCityRegister;
import loc.stalex.studentrequestsproject.domain.Child;
import loc.stalex.studentrequestsproject.domain.register.AnswerCityRegisterItem;
import loc.stalex.studentrequestsproject.domain.register.CityRegisterResponse;
import loc.stalex.studentrequestsproject.domain.StudentOrder;
import loc.stalex.studentrequestsproject.exception.CityRegisterException;
import loc.stalex.studentrequestsproject.exception.TransportException;
import loc.stalex.studentrequestsproject.validator.register.CityRegisterChecker;
import loc.stalex.studentrequestsproject.validator.register.FakeCityRegisterChecker;

public class CityRegisterValidator {

    private static final String IN_CODE = "NO_GRN";

    private final CityRegisterChecker personChecker;

    public CityRegisterValidator() {
        personChecker = new FakeCityRegisterChecker();
    }

    public AnswerCityRegister checkCityRegister(StudentOrder studentOrder) {
        AnswerCityRegister answerCityRegister = new AnswerCityRegister();

        answerCityRegister.addItem(checkPerson(studentOrder.getHusband()));
        answerCityRegister.addItem(checkPerson(studentOrder.getWife()));

        for (Child child : studentOrder.getChildren()) {
            answerCityRegister.addItem(checkPerson(child));
        }

        return answerCityRegister;
    }

    private AnswerCityRegisterItem checkPerson(Person person) {
        AnswerCityRegisterItem.CityStatus status = null;
        AnswerCityRegisterItem.CityError error = null;

        try {
            CityRegisterResponse tmp = personChecker.checkerPerson(person);
            status = tmp.isExisting() ?
                    AnswerCityRegisterItem.CityStatus.YES :
                    AnswerCityRegisterItem.CityStatus.NO;
        } catch (CityRegisterException e) {
            e.printStackTrace();
            status = AnswerCityRegisterItem.CityStatus.ERROR;
            error = new AnswerCityRegisterItem.CityError(e.getCode(), e.getMessage());
        } catch (TransportException e) {
            e.printStackTrace();
            status = AnswerCityRegisterItem.CityStatus.ERROR;
            error = new AnswerCityRegisterItem.CityError(IN_CODE, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            status = AnswerCityRegisterItem.CityStatus.ERROR;
            error = new AnswerCityRegisterItem.CityError(IN_CODE, e.getMessage());
        }

        AnswerCityRegisterItem answerCRI = new AnswerCityRegisterItem(person, status, error);

        return answerCRI;
    }
}
