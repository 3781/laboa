package team.oha.laboa.service;

import team.oha.laboa.util.password.HashInfo;

public interface PasswordService {
    void encryptPassword(HashInfo hashInfo);
    void checkPassword(HashInfo hashInfo, String toCheck);
}
