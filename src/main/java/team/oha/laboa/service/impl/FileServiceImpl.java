package team.oha.laboa.service.impl;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import team.oha.laboa.dao.FileDao;
import team.oha.laboa.dao.UserDao;
import team.oha.laboa.dto.ApiDto;
import team.oha.laboa.dto.FileDto;
import team.oha.laboa.dto.PageDto;
import team.oha.laboa.model.FileDo;
import team.oha.laboa.query.file.FileSelectQuery;
import team.oha.laboa.service.FileService;
import team.oha.laboa.util.FileUtil;
import team.oha.laboa.vo.DeleteVo;
import team.oha.laboa.vo.FileVo;

import java.io.File;
import java.text.MessageFormat;
import java.time.LocalDateTime;

/**
 * <p></p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/12/5
 * @modified
 */
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileDao fileDao;

    @Autowired
    private UserDao userDao;

    public String getRootPath(){
        String appPath = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("").replace("\\", "/");
        return MessageFormat.format("{0}/{1}", new File(appPath).getParent(), "files/");
    }

    @Override
    public ApiDto upload(FileVo fileVo) {
        FileDo fileDo = new FileDo();
        fileDo.setUserId(userDao.getByUsername((String)SecurityUtils.getSubject().getPrincipal()).getUserId());
        fileDo.setCreateTime(LocalDateTime.now());
        fileDo.setName(fileVo.getFile().getOriginalFilename());
        fileDo.setAddress(FileUtil.upload(getRootPath(), fileVo.getFile()));

        ApiDto apiDto = new ApiDto();
        apiDto.setSuccess(true);
        fileDao.save(fileDo);
        apiDto.setInfo(fileDo.getFileId());
        return apiDto;
    }

    @Override
    public File download(Integer fileId) {
        FileDo fileDo = fileDao.get(fileId);
        return new File(MessageFormat.format("{0}/{1}", getRootPath(), fileDo.getAddress()));
    }

    @Override
    public ApiDto update(FileVo fileVo) {
        FileDo fileDo = new FileDo();

        fileDo.setFileId(fileVo.getFileId());
        fileDo.setRemark(fileVo.getRemark());
        fileDo.setUpdateTime(LocalDateTime.now());
        if(fileVo.getFile()!=null){
            fileDo.setName(fileVo.getFile().getOriginalFilename());
            fileDo.setAddress(FileUtil.upload(getRootPath(), fileVo.getFile()));
        }

        ApiDto apiDto = new ApiDto();
        apiDto.setSuccess(true);
        apiDto.setInfo(fileDao.update(fileDo));
        return apiDto;
    }

    @Override
    public ApiDto delete(DeleteVo deleteVo) {
        ApiDto apiDto = new ApiDto();
        apiDto.setSuccess(true);
        apiDto.setInfo(fileDao.delete(deleteVo));
        return apiDto;
    }

    @Override
    public ApiDto listFiles(FileSelectQuery fileSelectQuery) {
        PageDto<FileDto> pageDto = new PageDto<>();
        pageDto.setTotalSize(fileDao.count(fileSelectQuery.getFilterQuery()));
        if(pageDto.getTotalSize() != 0){
            pageDto.setData(fileDao.list(fileSelectQuery));
        }

        ApiDto apiDto = new ApiDto();
        apiDto.setSuccess(true);
        apiDto.setInfo(pageDto);
        return apiDto;
    }
}
