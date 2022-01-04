package world.inetum.realdolmen.realjobs.exceptions.messages;

public enum ProfileExceptionMessage implements ExceptionMessage {

    PROFILE_NOT_FOUND;

    @Override
    public String getMessage() {
        return name();
    }
}
