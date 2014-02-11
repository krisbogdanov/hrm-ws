package ws.security;

import ws.dao.Employee;

public interface AuthenticationManager {
	public Employee authenticate(final String username, final String password);
}
