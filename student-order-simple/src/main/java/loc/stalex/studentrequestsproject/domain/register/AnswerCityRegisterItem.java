package loc.stalex.studentrequestsproject.domain.register;

import loc.stalex.studentrequestsproject.domain.Person;

public class AnswerCityRegisterItem {

    private Person person;

    private CityStatus status;

    private CityError error;

    public AnswerCityRegisterItem(Person person, CityStatus status) {
        this.person = person;
        this.status = status;
    }

    public AnswerCityRegisterItem(Person person, CityStatus status, CityError error) {
        this.person = person;
        this.status = status;
        this.error = error;
    }

    public Person getPerson() {
        return person;
    }

    public CityStatus getStatus() {
        return status;
    }

    public CityError getError() {
        return error;
    }

    public static class CityError {
        private final String code;
        private final String text;

        public CityError(String code, String text) {
            this.code = code;
            this.text = text;
        }

        public String getCode() {
            return code;
        }

        public String getText() {
            return text;
        }
    }

    public enum CityStatus {
        YES, NO, ERROR;
    }
}
