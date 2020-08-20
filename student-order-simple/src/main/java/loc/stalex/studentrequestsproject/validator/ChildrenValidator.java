package loc.stalex.studentrequestsproject.validator;

import loc.stalex.studentrequestsproject.domain.children.AnswerChildren;
import loc.stalex.studentrequestsproject.domain.StudentOrder;

public class ChildrenValidator {

    public AnswerChildren checkChildren(StudentOrder studentOrder) {
        System.out.println("Children Check is running");
        return new AnswerChildren();
    }
}
