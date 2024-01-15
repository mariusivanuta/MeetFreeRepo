package global.exception;

import org.hibernate.validator.internal.engine.path.PathImpl;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.stream.Collectors;

@Provider
public class ConstraintViolationExceptionHandler implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException constraintViolationException){

        String errorMessages = constraintViolationException.getConstraintViolations().stream()
                .map(constraintViolation -> ((PathImpl) constraintViolation.getPropertyPath()).getLeafNode().getName() + " - " + constraintViolation.getMessage() )
                .collect(Collectors.joining());
        return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorResponse(errorMessages)).build();
    }
}
