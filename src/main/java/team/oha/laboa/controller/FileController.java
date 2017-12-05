package team.oha.laboa.controller;

import org.apache.commons.io.FileUtils;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import team.oha.laboa.dto.ApiDto;
import team.oha.laboa.service.FileService;
import team.oha.laboa.vo.FileVo;

import java.io.File;

@RestController
@RequestMapping("/api/file")
public class FileController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private FileService fileService;

    @RequiresUser
    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public ApiDto upload(@RequestParam(name = "file") CommonsMultipartFile file) {
        FileVo fileVo = new FileVo();
        fileVo.setFile(file);
        return fileService.upload(fileVo);
    }

    @RequiresUser
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping
    public ApiDto update(@RequestBody FileVo fileVo) {
        return fileService.update(fileVo);
    }

    @RequiresUser
    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("/{fileId:[1-9][0-9]*}")
    public HttpEntity<byte[]> update(@PathVariable Integer fileId) throws Exception{
        File file = fileService.download(fileId);
        HttpHeaders headers = new HttpHeaders();
        //下载显示的文件名，解决中文名称乱码问题
        String downloadFielName = new String(file.getName().getBytes("UTF-8"),"iso-8859-1");
        //通知浏览器以attachment（下载方式）打开图片
        headers.setContentDispositionFormData("attachment", downloadFielName);
        return new HttpEntity<>(FileUtils.readFileToByteArray(file), headers);
    }
}
