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

    public static String upload(String rootPath, String fold, CommonsMultipartFile file){
        String path = MessageFormat.format("{0}/{1}",fold ,Clock.systemDefaultZone().millis() + file.getOriginalFilename());
        try{
            File parent = new File(MessageFormat.format("{0}/{1}", new File(rootPath).getParent(), fold));
            if(parent.exists() || parent.mkdir()){
                file.transferTo(new File(MessageFormat.format("{0}/{1}", new File(rootPath).getParent(), path)));
            }else{
                throw new FileUploadException(file.getOriginalFilename());
            }

        }catch (Exception e){
            logger.warn("文件[{}]保存失败", path, e);
            throw new FileUploadException(file.getOriginalFilename());
        }
        return path;
    }
}
