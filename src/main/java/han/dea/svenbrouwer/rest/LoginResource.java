package han.dea.svenbrouwer.rest;

import han.dea.svenbrouwer.persistence.DatabaseProperties;
import han.dea.svenbrouwer.persistence.LoginDAO;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginResource {
    private LoginDAO loginDAO = new LoginDAO(new DatabaseProperties());

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserTokenFromLogin(LoginDTO loginDTO) {
        LoginDTO userToken = loginDAO.getUserToken(loginDTO.getUser(), loginDTO.getPassword());
        return Response.ok(userToken).build();
    }
}
