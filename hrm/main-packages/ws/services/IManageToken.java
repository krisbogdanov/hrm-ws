/**
 * 
 */
package ws.services;



/**
 * @author Kristiyan
 *
 */
public interface IManageToken {
	public String getToken(final String username, final String password, boolean secure);
	public void deleteToken(final String token);
}
