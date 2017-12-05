package team.oha.laboa.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import team.oha.laboa.exception.FileUploadException;

import java.io.File;
import java.text.MessageFormat;
import java.time.Clock;

public class FileUtil {

    static private Logger logger = LoggerFactory.getLogger(FileUtil.class);

    public static String upload(String rootPath, CommonsMultipartFile file){
        String address = Clock.systemDefaultZone().millis() + file.getOriginalFilename();
        try{
            File rootFile = new File(rootPath);
            if(rootFile.exists() || rootFile.mkdir()){
                file.transferTo(new File(MessageFormat.format("{0}/{1}", rootPath, address)));
            }else{
                throw new FileUploadException(file.getOriginalFilename());
            }

        }catch (Exception e){
            logger.warn("文件[{}]保存失败", address, e);
            throw new FileUploadException(file.getOriginalFilename());
        }
        return address;
    }
}
