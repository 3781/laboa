package team.oha.laboa.util.password;

public interface HashInfo{

    String getPassword();
    void setPassword(String password) ;
    String getSalt();
    void setSalt(String salt) ;
}
