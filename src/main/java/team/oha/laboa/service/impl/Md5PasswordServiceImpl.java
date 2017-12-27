package team.oha.laboa.service.impl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import team.oha.laboa.exception.WrongPasswordException;
import team.oha.laboa.util.password.PasswordInfo;
import team.oha.laboa.util.password.SaltGenerator;
import team.oha.laboa.util.password.SaltPasswordInfo;

@Service
public class Md5PasswordServiceImpl extends AbstractHashPasswordService {
    @Override
    public void setSaltGenerator(@Autowired SaltGenerator saltGenerator) {
        super.setSaltGenerator(saltGenerator);
    }
    @Override
    public void setHashIterations(@Value("${hashIterations}") int hashIterations) {
        super.setHashIterations(hashIterations);
    }
    @Override
    public void encryptPassword(PasswordInfo passwordInfo) {
        String salt = null;
        if( passwordInfo instanceof SaltPasswordInfo){
            SaltPasswordInfo saltPasswordInfo = (SaltPasswordInfo)passwordInfo;
            saltPasswordInfo.setSalt(getSaltGenerator().buildSalt());
            salt = saltPasswordInfo.getSalt();
        }
        passwordInfo.setPassword(new Md5Hash(passwordInfo.getPassword(), salt, getHashIterations()).toHex());
    }
    @Override
    public void checkPassword(PasswordInfo passwordInfo, String toCheck) {

        String salt = null;
        if( passwordInfo instanceof SaltPasswordInfo){
            SaltPasswordInfo saltPasswordInfo = (SaltPasswordInfo)passwordInfo;
            salt = saltPasswordInfo.getSalt();
        }

        String encryptToCheck = new Md5Hash(toCheck, salt, getHashIterations()).toHex();

        if(!passwordInfo.getPassword().equals(encryptToCheck)){
            throw new WrongPasswordException((String)SecurityUtils.getSubject().getPrincipal(), toCheck);
        }
    }
}
