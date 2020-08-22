package loc.stalex.city.dao;

import loc.stalex.city.domain.PersonRequest;
import loc.stalex.city.domain.PersonResponse;
import loc.stalex.city.exception.PersonCheckException;

import java.sql.*;

public class PersonCheckDao {

    private static final String SQL_REQUEST = "SELECT temporal FROM cr_address_person ap" +
            "    INNER JOIN cr_person p ON p.person_id = ap.person_id" +
            "    INNER JOIN cr_address a ON a.address_id = ap.address_id " +
            "WHERE " +
            "  CURRENT_DATE >= ap.start_date AND (CURRENT_DATE <= ap.end_date or ap.end_date IS NULL) " +
            "  AND upper(p.sur_name) = upper(?) " +
            "  AND upper(p.given_name) = upper(?) " +
            "  AND upper(p.patronymic) = upper(?) " +
            "  AND p.date_of_birth = ? " +
            "  AND a.street_code = ? " +
            "  AND upper(a.building) = upper(?) ";

    public PersonCheckDao() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public PersonResponse checkPerson(PersonRequest request) throws PersonCheckException {
        PersonResponse response = new PersonResponse();

        String sql = SQL_REQUEST;

        if (request.getExtension() != null) {
            sql += "AND upper(a.extension) = upper(?) ";
        } else {
            sql += "AND a.extension is null ";
        }

        if (request.getApartment() != null) {
            sql += "AND upper(a.apartment) = upper(?) ";
        } else {
            sql += "AND a.apartment is null ";
        }

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            int count = 1;
            preparedStatement.setString(count++, request.getSurName());
            preparedStatement.setString(count++, request.getGivenName());
            preparedStatement.setString(count++, request.getPatronymic());
            preparedStatement.setDate(count++, java.sql.Date.valueOf(request.getDateOfBirth()));
            preparedStatement.setInt(count++, request.getStreetCode());
            preparedStatement.setString(count++, request.getBuilding());
            if (request.getExtension() != null) {
                preparedStatement.setString(count++, request.getExtension());
            }

            if (request.getApartment() != null) {
                preparedStatement.setString(count, request.getApartment());
            }

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                response.setRegistered(true);
                response.setTemporal(resultSet.getBoolean("temporal"));
            }
        } catch (SQLException e) {
            throw new PersonCheckException(e);
        }

        return response;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost/city_register",
                "postgres", "postgres");
    }
}
