package loc.stalex.studentorder.dao;

import loc.stalex.studentorder.domain.CountryArea;
import loc.stalex.studentorder.domain.PassportOffice;
import loc.stalex.studentorder.domain.RegisterOffice;
import loc.stalex.studentorder.domain.Street;
import loc.stalex.studentorder.exception.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DictionaryDaoImpl implements DictionaryDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(DictionaryDaoImpl.class);

    private static final String GET_STREET = "SELECT street_code, street_name " +
            "FROM jc_street WHERE UPPER(street_name) LIKE UPPER(?)";

    private static final String GET_PASSPORT_OFFICE = "SELECT * FROM jc_passport_office WHERE p_office_area_id = ?";

    private static final String GET_REGISTER_OFFICE = "SELECT * FROM jc_register_office WHERE r_office_area_id = ?";

    private static final String GET_AREA = "SELECT * FROM jc_country_struct WHERE area_id LIKE ? AND  area_id <> ?";

    public List<Street> findStreet(String pattern) throws DaoException {
        List<Street> result = new LinkedList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_STREET)) {

            statement.setString(1, "%" + pattern + "%");
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                result.add(new Street(rs.getLong("street_code"), rs.getString("street_name")));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DaoException(e);
        }

        return result;
    }

    @Override
    public List<PassportOffice> findPassportOffice(String areaId) throws DaoException {
        List<PassportOffice> result = new LinkedList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_PASSPORT_OFFICE)) {

            statement.setString(1, areaId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                PassportOffice passportOffice = new PassportOffice(
                        rs.getLong("p_office_id"),
                        rs.getString("p_office_area_id"),
                        rs.getString("p_office_name")
                );

                result.add(passportOffice);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DaoException(e);
        }

        return result;
    }

    @Override
    public List<RegisterOffice> findRegisterOffice(String areaId) throws DaoException {
        List<RegisterOffice> result = new LinkedList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_REGISTER_OFFICE)) {

            statement.setString(1, areaId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                RegisterOffice passportOffice = new RegisterOffice(
                        rs.getLong("r_office_id"),
                        rs.getString("r_office_area_id"),
                        rs.getString("r_office_name")
                );

                result.add(passportOffice);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DaoException(e);
        }

        return result;
    }

    @Override
    public List<CountryArea> findAreas(String areaId) throws DaoException {
        List<CountryArea> result = new LinkedList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_AREA)) {

            String param1 = buildParam(areaId);

            statement.setString(1, param1);
            statement.setString(2, areaId);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                CountryArea passportOffice = new CountryArea(
                        rs.getString("area_id"),
                        rs.getString("area_name")
                );

                result.add(passportOffice);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DaoException(e);
        }

        return result;
    }

    private Connection getConnection() throws SQLException {
        return ConnectionBuilder.getConnection();
    }

    private String buildParam(String areaId) throws SQLException {
        if (areaId == null || areaId.trim().isEmpty()) {
            return "__0000000000";
        } else if (areaId.endsWith("0000000000")) {
            return areaId.substring(0, 2) + "___0000000";
        } else if (areaId.endsWith("0000000")) {
            return areaId.substring(0, 5) + "___0000";
        } else if (areaId.endsWith("0000")) {
            return areaId.substring(0, 8) + "____";
        } else {
            throw new SQLException("Invalid parameter 'areaId': " + areaId);
        }
    }
}
