package ws.security;

public interface Authenticator {
	public boolean authenticate(final String username, final String password);
}
