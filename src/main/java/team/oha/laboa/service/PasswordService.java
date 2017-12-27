package team.oha.laboa.service;

import team.oha.laboa.util.password.PasswordInfo;

public interface PasswordService {
    void encryptPassword(PasswordInfo passwordInfo);
    void checkPassword(PasswordInfo passwordInfo, String toCheck);
}
