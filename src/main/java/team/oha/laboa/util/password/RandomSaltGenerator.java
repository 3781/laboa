package team.oha.laboa.util.password;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;

public class RandomSaltGenerator implements SaltGenerator {
    private SecureRandomNumberGenerator generator;

    private RandomSaltGenerator(){
        generator = new SecureRandomNumberGenerator();
    }

    @Override
    public String buildSalt() {
        return generator.nextBytes().toHex();
    }

    public static final RandomSaltGenerator getInstance() {
        return RandomSaltGeneratorHolder.INSTANCE;
    }

    private static final class RandomSaltGeneratorHolder{
        private static final RandomSaltGenerator INSTANCE = new RandomSaltGenerator();
    }
}
