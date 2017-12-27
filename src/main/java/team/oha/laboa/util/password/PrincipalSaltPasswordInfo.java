package team.oha.laboa.util.password;

public class PrincipalSaltPasswordInfo extends SaltPasswordInfoDecrator {
    private String username;

    public PrincipalSaltPasswordInfo(SaltPasswordInfo saltPasswordInfo) {
        super(saltPasswordInfo);
    }

    public PrincipalSaltPasswordInfo(SaltPasswordInfo saltPasswordInfo, String username) {
        super(saltPasswordInfo);
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
