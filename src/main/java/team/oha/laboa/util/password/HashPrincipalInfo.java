package team.oha.laboa.util.password;

public class HashPrincipalInfo extends HashInfoDecrator{
    private String username;

    public HashPrincipalInfo(HashInfo hashInfo) {
        super(hashInfo);
    }

    public HashPrincipalInfo(HashInfo hashInfo, String username) {
        super(hashInfo);
        this.username = username;
    }

    @Override
    public String getSalt() {
        return username+super.getSalt();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
