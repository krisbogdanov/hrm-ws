package ws.security;

public interface AuthenticationManager {
	public boolean authenticate(final String username, final String password);
}
