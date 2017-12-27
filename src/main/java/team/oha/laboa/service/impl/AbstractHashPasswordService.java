package team.oha.laboa.service.impl;

import team.oha.laboa.service.PasswordService;
import team.oha.laboa.util.password.SaltGenerator;

public abstract class AbstractHashPasswordService implements PasswordService {
    private SaltGenerator saltGenerator;
    private int hashIterations;
    public SaltGenerator getSaltGenerator() {
        return saltGenerator;
    }
    public void setSaltGenerator(SaltGenerator saltGenerator) {
        this.saltGenerator = saltGenerator;
    }
    public int getHashIterations() {
        return hashIterations;
    }
    public void setHashIterations(int hashIterations) {
        this.hashIterations = hashIterations;
    }
}
