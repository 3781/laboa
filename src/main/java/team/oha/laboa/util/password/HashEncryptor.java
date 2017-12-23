package team.oha.laboa.util.password;

public interface HashEncryptor {
    String hash(String source, String salt);
}
