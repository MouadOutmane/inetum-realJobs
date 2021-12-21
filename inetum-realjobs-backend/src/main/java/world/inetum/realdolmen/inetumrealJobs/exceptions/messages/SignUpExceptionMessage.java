package world.inetum.realdolmen.inetumrealJobs.exceptions.messages;

public enum SignUpExceptionMessage implements ExceptionMessage {

    EMAIL_ALREADY_USED;

    @Override
    public String getMessage() {
        return name();
    }
}
