package loc.stalex.studentorder.validator.register;

import loc.stalex.studentorder.domain.register.CityRegisterRequest;
import loc.stalex.studentorder.domain.register.CityRegisterResponse;
import loc.stalex.studentorder.domain.Person;
import loc.stalex.studentorder.exception.CityRegisterException;
import loc.stalex.studentorder.exception.TransportException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

public class RealCityRegisterChecker implements CityRegisterChecker {

    public CityRegisterResponse checkerPerson(Person person)
            throws CityRegisterException, TransportException {

        CityRegisterRequest request = new CityRegisterRequest(person);

        Client client = ClientBuilder.newClient();
        CityRegisterResponse response = client.target("http://localhost:8080/city-register-1.0/rest/check/")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(request, MediaType.APPLICATION_JSON))
                .readEntity(CityRegisterResponse.class);

        return response;
    }
}
