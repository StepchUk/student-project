package loc.stalex.studentorder.dao;

import loc.stalex.studentorder.domain.StudentOrder;
import loc.stalex.studentorder.exception.DaoException;

import java.util.List;

public interface StudentOrderDao {

    Long saveStudentOrder(StudentOrder studentOrder) throws DaoException;

    List<StudentOrder> getStudentOrders() throws  DaoException;
}
