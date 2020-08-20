package loc.stalex.studentrequestsproject.dao;

import loc.stalex.studentrequestsproject.domain.StudentOrder;
import loc.stalex.studentrequestsproject.exception.DaoException;

import java.util.List;

public interface StudentOrderDao {

    Long saveStudentOrder(StudentOrder studentOrder) throws DaoException;

    List<StudentOrder> getStudentOrders() throws  DaoException;
}
