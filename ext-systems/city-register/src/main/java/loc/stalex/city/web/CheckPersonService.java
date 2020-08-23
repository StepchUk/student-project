package loc.stalex.city.web;

import loc.stalex.city.domain.PersonRequest;
import loc.stalex.city.domain.PersonResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/check")
public class CheckPersonService {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PersonResponse checkPerson(PersonRequest request) {
        System.out.println(request);

        return new PersonResponse();
    }
}
