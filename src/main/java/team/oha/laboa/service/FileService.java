package team.oha.laboa.service;

import team.oha.laboa.dto.ApiDto;
import team.oha.laboa.query.file.FileSelectQuery;
import team.oha.laboa.vo.FileVo;

import java.io.File;

/**
 * <p></p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/12/5
 * @modified
 */
public interface FileService {
    ApiDto upload(FileVo fileVo);
    File download(Integer fileId);
    ApiDto update(FileVo fileVo);
    ApiDto listFiles(FileSelectQuery fileSelectQuery);
}
