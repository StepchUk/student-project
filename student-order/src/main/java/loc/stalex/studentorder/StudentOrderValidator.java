package loc.stalex.studentorder;

import loc.stalex.studentorder.dao.StudentOrderDaoImpl;
import loc.stalex.studentorder.domain.children.AnswerChildren;
import loc.stalex.studentorder.domain.register.AnswerCityRegister;
import loc.stalex.studentorder.domain.student.AnswerStudent;
import loc.stalex.studentorder.domain.wedding.AnswerWedding;
import loc.stalex.studentorder.exception.DaoException;
import loc.stalex.studentorder.mail.MailSender;
import loc.stalex.studentorder.domain.*;
import loc.stalex.studentorder.validator.ChildrenValidator;
import loc.stalex.studentorder.validator.CityRegisterValidator;
import loc.stalex.studentorder.validator.StudentValidator;
import loc.stalex.studentorder.validator.WeddingValidator;

import java.util.List;

public class StudentOrderValidator {

    private CityRegisterValidator cityRegisterVal;
    private WeddingValidator weddingVal;
    private ChildrenValidator childrenVal;
    private StudentValidator studentVal;
    private MailSender mailSender;

    public StudentOrderValidator() {
        cityRegisterVal = new CityRegisterValidator();
        weddingVal = new WeddingValidator();
        childrenVal = new ChildrenValidator();
        studentVal = new StudentValidator();
        mailSender = new MailSender();
    }

    public static void main(String[] args) {
        StudentOrderValidator sov = new StudentOrderValidator();
        sov.checkAll();
    }

    public void checkAll() {
        try {
            List<StudentOrder> studentOrders = readStudentOrders();

            for (StudentOrder studentOrder : studentOrders) {
                checkOneOrder(studentOrder);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkOneOrder(StudentOrder studentOrder) {
        AnswerCityRegister cityAnswer = checkCityRegister(studentOrder);
//        AnswerWedding answerWedding = checkWedding(studentOrder);
//        AnswerChildren answerChildren = checkChildren(studentOrder);
//        AnswerStudent answerStudent = checkStudent(studentOrder);

//        sendMail(studentOrder);
    }

    public List<StudentOrder> readStudentOrders() throws DaoException {
        return new StudentOrderDaoImpl().getStudentOrders();
    }

    public AnswerCityRegister checkCityRegister(StudentOrder studentOrder) {
        return cityRegisterVal.checkCityRegister(studentOrder);
    }

    public AnswerWedding checkWedding(StudentOrder studentOrder) {
        return weddingVal.checkWedding(studentOrder);
    }

    public AnswerChildren checkChildren(StudentOrder studentOrder) {
        return childrenVal.checkChildren(studentOrder);
    }

    public AnswerStudent checkStudent(StudentOrder studentOrder) {
        return studentVal.checkStudent(studentOrder);
    }

    public void sendMail(StudentOrder studentOrder) {
        mailSender.sendMail(studentOrder);
    }
}
