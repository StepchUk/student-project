package loc.stalex.studentrequestsproject;

import loc.stalex.studentrequestsproject.dao.StudentOrderDaoImpl;
import loc.stalex.studentrequestsproject.domain.children.AnswerChildren;
import loc.stalex.studentrequestsproject.domain.register.AnswerCityRegister;
import loc.stalex.studentrequestsproject.domain.student.AnswerStudent;
import loc.stalex.studentrequestsproject.domain.wedding.AnswerWedding;
import loc.stalex.studentrequestsproject.exception.DaoException;
import loc.stalex.studentrequestsproject.mail.MailSender;
import loc.stalex.studentrequestsproject.domain.*;
import loc.stalex.studentrequestsproject.validator.ChildrenValidator;
import loc.stalex.studentrequestsproject.validator.CityRegisterValidator;
import loc.stalex.studentrequestsproject.validator.StudentValidator;
import loc.stalex.studentrequestsproject.validator.WeddingValidator;

import java.util.ArrayList;
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
