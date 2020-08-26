package loc.stalex.studentorder.validator;

import loc.stalex.studentorder.domain.Child;
import loc.stalex.studentorder.domain.Person;
import loc.stalex.studentorder.domain.StudentOrder;
import loc.stalex.studentorder.domain.register.AnswerCityRegister;
import loc.stalex.studentorder.domain.register.AnswerCityRegisterItem;
import loc.stalex.studentorder.domain.register.CityRegisterResponse;
import loc.stalex.studentorder.exception.CityRegisterException;
import loc.stalex.studentorder.exception.TransportException;
import loc.stalex.studentorder.validator.register.CityRegisterChecker;
import loc.stalex.studentorder.validator.register.RealCityRegisterChecker;

public class CityRegisterValidator {

    private static final String IN_CODE = "NO_GRN";

    private final CityRegisterChecker personChecker;

    public CityRegisterValidator() {
        personChecker = new RealCityRegisterChecker();
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
            status = tmp.isRegistered() ?
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
