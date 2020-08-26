package loc.stalex.studentorder.validator;

import loc.stalex.studentorder.domain.student.AnswerStudent;
import loc.stalex.studentorder.domain.StudentOrder;

public class StudentValidator {

    public AnswerStudent checkStudent(StudentOrder studentOrder) {
        System.out.println("CheckStudent is running");
        return new AnswerStudent();
    }
}
