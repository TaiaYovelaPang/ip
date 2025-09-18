package companio;

/**
 * Signals that the program should terminate.
 */
public class CompanioExitException extends CompanioException {
    public CompanioExitException(String message) {
        super(message);
    }
}
