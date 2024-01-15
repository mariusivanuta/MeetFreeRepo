package global.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class WrongUsageExceptionHandler implements ExceptionMapper<WrongUsageException> {
    @Override
    public Response toResponse(WrongUsageException wrongUsageException){

        ErrorResponse errorResponse = new ErrorResponse(wrongUsageException.getMessage());

        return Response.status(Response.Status.NOT_ACCEPTABLE).entity(errorResponse).build();
    }
}
