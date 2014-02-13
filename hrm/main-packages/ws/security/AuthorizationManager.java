/**
 * 
 */
package ws.security;

/**
 * @author Kristiyan
 *
 */
public interface AuthorizationManager {
	public boolean isAuthorizedTo(String token, String permission);
	public boolean isTheOwnerOf(String token, int targetId);
	public int getEmployeeIdFromToken(String token);
}
