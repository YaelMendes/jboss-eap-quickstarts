package rs.exception;

import javax.persistence.PersistenceException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BadIdExceptionHandler implements ExceptionMapper<PersistenceException> {
    public Response toResponse(PersistenceException ex) {
        return Response.status(Response.Status.NOT_ACCEPTABLE).entity("From HandlerEx:"+ex.getMessage()).type("text/plain").build();
    }
}