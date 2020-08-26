package loc.stalex.studentorder.validator;

import loc.stalex.studentorder.domain.wedding.AnswerWedding;
import loc.stalex.studentorder.domain.StudentOrder;

public class WeddingValidator {

    public AnswerWedding checkWedding(StudentOrder studentOrder) {
        System.out.println("Wedding running");
        return new AnswerWedding();
    }
}
