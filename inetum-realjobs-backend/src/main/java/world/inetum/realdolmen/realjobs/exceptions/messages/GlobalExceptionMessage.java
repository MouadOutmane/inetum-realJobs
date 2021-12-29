package world.inetum.realdolmen.realjobs.exceptions.messages;

public enum GlobalExceptionMessage implements ExceptionMessage {

    NOT_FOUND,
    ID_MISMATCH;

    @Override
    public String getMessage() {
        return name();
    }

}
