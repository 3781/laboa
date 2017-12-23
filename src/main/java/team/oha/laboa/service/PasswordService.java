package team.oha.laboa.service;

import team.oha.laboa.model.HashInfo;

public interface PasswordService {
    void encryptPassword(HashInfo hashInfo);
    void checkPassword(HashInfo hashInfo, String toCheck);
}
