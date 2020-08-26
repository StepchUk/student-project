package loc.stalex.studentorder.dao;

import loc.stalex.studentorder.domain.*;
import loc.stalex.studentorder.exception.DaoException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class StudentOrderDaoImplTest {

    @BeforeAll
    public static void startUp() throws Exception {
        DBInit.startUp();
    }

    @Test
    public void saveStudentOrder() throws DaoException {
        StudentOrder studentOrder = buildStudentOrder(10);

        Long id = new StudentOrderDaoImpl().saveStudentOrder(studentOrder);
    }

    @Test
    public void saveStudentOrderError() throws DaoException {
        assertThrows(DaoException.class, () -> {
            StudentOrder studentOrder = buildStudentOrder(10);
            studentOrder.getHusband().setSurName(null);
            Long id = new StudentOrderDaoImpl().saveStudentOrder(studentOrder);
        });
    }

    @Test
    public void getStudentOrders() throws DaoException {
        List<StudentOrder> studentOrders = new StudentOrderDaoImpl().getStudentOrders();
    }

    public StudentOrder buildStudentOrder(long id) {
        StudentOrder studentOrder = new StudentOrder();
        studentOrder.setStudentOrderId(id);
        studentOrder.setMarriageCertificateId("" + (123456000 + id));
        studentOrder.setMarriageDate(LocalDate.of(2016, 7, 4));

        RegisterOffice registerOffice = new RegisterOffice(1L, "", "");
        studentOrder.setMarriageOffice(registerOffice);

        Street street = new Street(1L, "First Street");

        Address address = new Address("195000", street, "10", "2", "121");

        Adult husband = new Adult("Vasilev", "Pavel", "Nikolaevich", LocalDate.of(1995, 3, 18));
        husband.setPassportSeries("" + (1000 + id));
        husband.setPassportNumber("" + (100000 + id));
        husband.setIssueDate(LocalDate.of(2017, 9, 15));
        PassportOffice passportOffice = new PassportOffice(1L, "", "");
        husband.setIssueDepartment(passportOffice);
        husband.setStudentId("" + (100000 + id));
        husband.setAddress(address);
        husband.setUniversity(new University(2L, ""));
        husband.setStudentId("HH12345");

        Adult wife = new Adult("Vasileva", "Irina", "Petrovna", LocalDate.of(1997, 8, 21));
        wife.setPassportSeries("" + (2000 + id));
        wife.setPassportNumber("" + (200000 + id));
        wife.setIssueDate(LocalDate.of(2018, 4, 5));
        PassportOffice passportOffice2 = new PassportOffice(2L, "", "");
        wife.setIssueDepartment(passportOffice2);
        wife.setStudentId("" + (200000 + id));
        wife.setAddress(address);
        wife.setUniversity(new University(1L, ""));
        wife.setStudentId("WW12345");

        Child child = new Child("Vasileva", "Evgenia", "Pavlovna", LocalDate.of(2016, 1, 11));
        child.setCertificateNumber("" + (300000 + id));
        child.setIssueDate(LocalDate.of(2018, 6, 11));
        RegisterOffice registerOffice2 = new RegisterOffice(2L, "", "");
        child.setIssueDepartment(registerOffice2);
        child.setAddress(address);

        Child child2 = new Child("Vasilev", "Alexander", "Pavlovich", LocalDate.of(2018, 10, 24));
        child2.setCertificateNumber("" + (400000 + id));
        child2.setIssueDate(LocalDate.of(2018, 7, 19));
        RegisterOffice registerOffice3 = new RegisterOffice(3L, "", "");
        child2.setIssueDepartment(registerOffice3);
        child2.setAddress(address);

        studentOrder.setHusband(husband);
        studentOrder.setWife(wife);
        studentOrder.addChild(child);
        studentOrder.addChild(child2);

        return studentOrder;
    }
}