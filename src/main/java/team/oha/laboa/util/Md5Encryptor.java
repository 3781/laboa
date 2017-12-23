package team.oha.laboa.util;

import org.apache.shiro.crypto.hash.Md5Hash;

public class Md5Encryptor implements HashEncryptor{
    public static final int DEFAULT_ITERATIONS = 1;

    private int hashIterations;

    public Md5Encryptor() {
        hashIterations = DEFAULT_ITERATIONS;
    }

    public Md5Encryptor(int hashIterations) {
        this.hashIterations = hashIterations;
    }

    @Override
    public String hash(String source, String salt) {
        return new Md5Hash(source, salt, hashIterations).toHex();
    }
}
