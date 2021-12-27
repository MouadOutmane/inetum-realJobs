package world.inetum.realdolmen.realjobs.exceptions.messages;

public enum ResetPasswordExceptionMessage implements ExceptionMessage {

    EMAIL_NOT_CORRECT,
    UNKNOWN_ERROR;

    @Override
    public String getMessage() {
        return name();
    }

}
