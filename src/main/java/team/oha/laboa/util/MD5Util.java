package team.oha.laboa.util;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;

/**
 * <p>MD5工具加密</p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/11/27
 * @modified
 */
public class MD5Util {
    private static final SecureRandomNumberGenerator secureRandomNumberGenerator = new SecureRandomNumberGenerator();

    public static ByteSource generateSalt(){
        return secureRandomNumberGenerator.nextBytes();
    }

    public static String encryptPassword(String password, String salt){
        return new Md5Hash(password, salt, 2).toHex();
    }
}
