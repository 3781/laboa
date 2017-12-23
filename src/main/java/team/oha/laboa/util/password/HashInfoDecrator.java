package team.oha.laboa.util.password;

public class HashInfoDecrator implements HashInfo{
    private HashInfo hashInfo;

    public HashInfoDecrator(HashInfo hashInfo) {
        this.hashInfo = hashInfo;
    }

    @Override
    public String getPassword() {
        return hashInfo.getPassword();
    }

    @Override
    public void setPassword(String password) {
        hashInfo.setPassword(password);
    }

    @Override
    public String getSalt() {
        return hashInfo.getSalt();
    }

    @Override
    public void setSalt(String salt) {
        hashInfo.setSalt(salt);
    }
}
