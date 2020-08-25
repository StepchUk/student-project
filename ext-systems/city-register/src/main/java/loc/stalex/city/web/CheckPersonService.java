package loc.stalex.city.web;

import loc.stalex.city.dao.PersonCheckDao;
import loc.stalex.city.dao.PoolConnectionBuilder;
import loc.stalex.city.domain.PersonRequest;
import loc.stalex.city.domain.PersonResponse;
import loc.stalex.city.exception.PersonCheckException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/check")
@Singleton
public class CheckPersonService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CheckPersonServlet.class);

    private PersonCheckDao dao;

    @PostConstruct
    public void init() {
        LOGGER.info("SERVICE is created");
        dao = new PersonCheckDao(new PoolConnectionBuilder());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PersonResponse checkPerson(PersonRequest request) throws PersonCheckException {
        LOGGER.info(request.toString());

        return dao.checkPerson(request);
    }
}
