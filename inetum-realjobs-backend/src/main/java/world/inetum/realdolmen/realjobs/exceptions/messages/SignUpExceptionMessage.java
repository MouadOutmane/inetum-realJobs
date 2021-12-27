package world.inetum.realdolmen.realjobs.exceptions.messages;

public enum SignUpExceptionMessage implements ExceptionMessage {

    EMAIL_ALREADY_USED,
    INCORRECT_DATA;

    @Override
    public String getMessage() {
        return name();
    }

}
