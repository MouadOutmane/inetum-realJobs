package world.inetum.realdolmen.realjobs.exceptions;

import org.springframework.http.HttpStatus;
import world.inetum.realdolmen.realjobs.exceptions.messages.ExceptionMessage;

public class EndpointException extends RuntimeException {

    private final HttpStatus status;
    private final ExceptionMessage exceptionMessage;

    public EndpointException(HttpStatus status, ExceptionMessage exceptionMessage) {
        this.status = status;
        this.exceptionMessage = exceptionMessage;
    }

    public EndpointException(ExceptionMessage exceptionMessage) {
        this(HttpStatus.BAD_REQUEST, exceptionMessage);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public ExceptionMessage getExceptionMessage() {
        return exceptionMessage;
    }

}
