package team.oha.laboa.shiro.model;

import org.apache.shiro.authc.SaltedAuthenticationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import team.oha.laboa.shiro.realm.UserRealm;

/**
 * <p></p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/11/26
 * @modified
 */
public class UserAuthenticationInfo implements SaltedAuthenticationInfo {

    private PrincipalCollection principals;
    private Object credentials;
    private ByteSource credentialsSalt;
    private boolean locked;
    private boolean disable;

    @Override
    public PrincipalCollection getPrincipals() {
        return principals;
    }

    public void setPrincipal(Object principal) {
        principals = new SimplePrincipalCollection(principal, UserRealm.class.getName());
    }

    public void setPrincipals(PrincipalCollection principals) {
        this.principals = principals;
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    public void setCredentials(Object credentials) {
        this.credentials = credentials;
    }

    @Override
    public ByteSource getCredentialsSalt() {
        return credentialsSalt;
    }

    public void setCredentialsSalt(String password) {
        this.credentialsSalt = ByteSource.Util.bytes(password);
    }

    public void setCredentialsSalt(ByteSource credentialsSalt) {
        this.credentialsSalt = credentialsSalt;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }
}
