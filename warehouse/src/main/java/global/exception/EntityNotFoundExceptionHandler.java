package global.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class EntityNotFoundExceptionHandler implements ExceptionMapper<EntityNotFoundException> {

    @Override
    public Response toResponse(EntityNotFoundException entityNotFoundExceptionException){
        return Response.status(Response.Status.NOT_FOUND).entity(new ErrorResponse(entityNotFoundExceptionException.getMessage())).build();
    }
}
