package loc.stalex.studentorder.validator;

import loc.stalex.studentorder.domain.children.AnswerChildren;
import loc.stalex.studentorder.domain.StudentOrder;

public class ChildrenValidator {

    public AnswerChildren checkChildren(StudentOrder studentOrder) {
        System.out.println("Children Check is running");
        return new AnswerChildren();
    }
}
