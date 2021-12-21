package world.inetum.realdolmen.realjobs.exceptions;

import world.inetum.realdolmen.realjobs.exceptions.messages.ExceptionMessage;

public class EndpointException extends RuntimeException {

    private final ExceptionMessage exceptionMessage;

    public EndpointException(ExceptionMessage exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public ExceptionMessage getExceptionMessage() {
        return exceptionMessage;
    }

}
