package team.oha.laboa.util;

public interface HashEncryptor {
    String hash(String source, String salt);
}
