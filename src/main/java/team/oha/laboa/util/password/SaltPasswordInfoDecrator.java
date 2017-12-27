package team.oha.laboa.util.password;

public class SaltPasswordInfoDecrator implements SaltPasswordInfo {
    private SaltPasswordInfo saltPasswordInfo;

    public SaltPasswordInfoDecrator(SaltPasswordInfo saltPasswordInfo) {
        this.saltPasswordInfo = saltPasswordInfo;
    }

    @Override
    public String getPassword() {
        return saltPasswordInfo.getPassword();
    }

    @Override
    public void setPassword(String password) {
        saltPasswordInfo.setPassword(password);
    }

    @Override
    public String getSalt() {
        return saltPasswordInfo.getSalt();
    }

    @Override
    public void setSalt(String salt) {
        saltPasswordInfo.setSalt(salt);
    }
}
