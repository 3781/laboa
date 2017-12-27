package team.oha.laboa.util.password;

public interface SaltPasswordInfo extends PasswordInfo{
    String getSalt();
    void setSalt(String salt) ;
}
