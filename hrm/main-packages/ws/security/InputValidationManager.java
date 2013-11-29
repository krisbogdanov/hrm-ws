package ws.security;

public interface InputValidationManager {
	public boolean usernameValidation(final String username);
	public boolean passwordValidation(final String password);
}
