package loc.stalex.studentrequestsproject.dao;

import loc.stalex.studentrequestsproject.domain.CountryArea;
import loc.stalex.studentrequestsproject.domain.PassportOffice;
import loc.stalex.studentrequestsproject.domain.RegisterOffice;
import loc.stalex.studentrequestsproject.domain.Street;
import loc.stalex.studentrequestsproject.exception.DaoException;

import java.util.List;

public interface DictionaryDao {

    List<Street> findStreet(String pattern) throws DaoException;

    List<PassportOffice> findPassportOffice(String areaId) throws DaoException;

    List<RegisterOffice> findRegisterOffice(String areaId) throws DaoException;

    List<CountryArea> findAreas(String areaId) throws DaoException;
}
