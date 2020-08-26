package loc.stalex.studentorder.dao;

import loc.stalex.studentorder.domain.CountryArea;
import loc.stalex.studentorder.domain.PassportOffice;
import loc.stalex.studentorder.domain.RegisterOffice;
import loc.stalex.studentorder.domain.Street;
import loc.stalex.studentorder.exception.DaoException;

import java.util.List;

public interface DictionaryDao {

    List<Street> findStreet(String pattern) throws DaoException;

    List<PassportOffice> findPassportOffice(String areaId) throws DaoException;

    List<RegisterOffice> findRegisterOffice(String areaId) throws DaoException;

    List<CountryArea> findAreas(String areaId) throws DaoException;
}
