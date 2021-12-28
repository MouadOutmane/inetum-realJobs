package world.inetum.realdolmen.realjobs.exceptions.messages;

public enum GlobalExceptionMessage implements ExceptionMessage {

    NOT_FOUND;

    @Override
    public String getMessage() {
        return name();
    }

}
