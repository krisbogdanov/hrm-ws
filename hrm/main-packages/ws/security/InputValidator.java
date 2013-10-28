package ws.security;

public interface InputValidator {
	public boolean usernameValidation(final String username);
	public boolean passwordValidation(final String password);
}
