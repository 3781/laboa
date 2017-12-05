package team.oha.laboa.controller;

import org.apache.commons.io.FileUtils;
import org.apache.shiro.SecurityUtils;
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
import team.oha.laboa.query.file.FileFilterQuery;
import team.oha.laboa.query.file.FileSelectQuery;
import team.oha.laboa.service.FileService;
import team.oha.laboa.vo.BatchVo;
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
    public HttpEntity<byte[]> download(@PathVariable Integer fileId) throws Exception{
        File file = fileService.download(fileId);
        HttpHeaders headers = new HttpHeaders();
        //下载显示的文件名，解决中文名称乱码问题
        String downloadFielName = new String(file.getName().getBytes("UTF-8"),"iso-8859-1");
        //通知浏览器以attachment（下载方式）打开图片
        headers.setContentDispositionFormData("attachment", downloadFielName);
        return new HttpEntity<>(FileUtils.readFileToByteArray(file), headers);
    }

    /**
     * <p>分页文件列表</p>
     *
     * @author loser
     * @version 1.0
     * @data 2017/11/27
     * @modified
     */
    @RequiresUser
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ApiDto list(FileSelectQuery fileSelectQuery){
        if(fileSelectQuery == null){
            fileSelectQuery = new FileSelectQuery();
        }
        if(fileSelectQuery.getFilterQuery()==null){
            fileSelectQuery.setFilterQuery(new FileFilterQuery());
        }
        fileSelectQuery.getFilterQuery().setUsername((String)SecurityUtils.getSubject().getPrincipal());
        return fileService.listFiles(fileSelectQuery);
    }

    /**
     * <p>分页文件列表</p>
     *
     * @author loser
     * @version 1.0
     * @data 2017/11/27
     * @modified
     */
    @RequiresUser
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(params = "all=true")
    public ApiDto allList(FileSelectQuery fileSelectQuery){
        return fileService.listFiles(fileSelectQuery);
    }

    /**
     * <p>删除文件</p>
     *
     * @author loser
     * @version 1.0
     * @data 2017/11/27
     * @modified
     */
    @RequiresUser
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping
    public ApiDto deleteFile(BatchVo deleteVo){
        return fileService.delete(deleteVo);
    }
}
