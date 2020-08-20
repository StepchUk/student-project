package loc.stalex.studentrequestsproject.validator;

import loc.stalex.studentrequestsproject.domain.student.AnswerStudent;
import loc.stalex.studentrequestsproject.domain.StudentOrder;

public class StudentValidator {

    public AnswerStudent checkStudent(StudentOrder studentOrder) {
        System.out.println("CheckStudent is running");
        return new AnswerStudent();
    }
}
