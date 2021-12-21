package world.inetum.realdolmen.inetumrealJobs.exceptions;

import world.inetum.realdolmen.inetumrealJobs.exceptions.messages.ExceptionMessage;

public class EndpointException extends RuntimeException {

    private final ExceptionMessage exceptionMessage;

    public EndpointException(ExceptionMessage exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public ExceptionMessage getExceptionMessage() {
        return exceptionMessage;
    }

}
