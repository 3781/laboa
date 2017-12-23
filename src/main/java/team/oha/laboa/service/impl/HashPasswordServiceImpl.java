package team.oha.laboa.service.impl;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.oha.laboa.exception.WrongPasswordException;
import team.oha.laboa.util.password.HashInfo;
import team.oha.laboa.service.PasswordService;
import team.oha.laboa.util.password.HashEncryptor;
import team.oha.laboa.util.password.SaltGenerator;

@Service
public class HashPasswordServiceImpl implements PasswordService {
    @Autowired
    private SaltGenerator saltGenerator;
    @Autowired
    private HashEncryptor hashEncryptor;

    @Override
    public void encryptPassword(HashInfo hashInfo) {
        hashInfo.setSalt(saltGenerator.buildSalt());
        hashInfo.setPassword(hashEncryptor.hash(hashInfo.getPassword(), hashInfo.getSalt()));
    }

    @Override
    public void checkPassword(HashInfo hashInfo, String toCheck) {

        String encryptToCheck = hashEncryptor.hash(toCheck, hashInfo.getSalt());

        if(!hashInfo.getPassword().equals(encryptToCheck)){
            throw new WrongPasswordException((String)SecurityUtils.getSubject().getPrincipal(), toCheck);
        }
    }
}
