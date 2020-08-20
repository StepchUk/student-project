package loc.stalex.studentrequestsproject.validator;

import loc.stalex.studentrequestsproject.domain.wedding.AnswerWedding;
import loc.stalex.studentrequestsproject.domain.StudentOrder;

public class WeddingValidator {

    public AnswerWedding checkWedding(StudentOrder studentOrder) {
        System.out.println("Wedding running");
        return new AnswerWedding();
    }
}
