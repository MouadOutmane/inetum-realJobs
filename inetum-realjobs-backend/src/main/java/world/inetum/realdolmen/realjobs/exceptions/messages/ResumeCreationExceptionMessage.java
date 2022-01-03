package world.inetum.realdolmen.realjobs.exceptions.messages;

public enum ResumeCreationExceptionMessage implements ExceptionMessage {

    INVALID_USER;

    @Override
    public String getMessage() {
        return name();
    }
}
