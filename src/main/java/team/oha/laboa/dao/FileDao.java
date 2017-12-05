package team.oha.laboa.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import team.oha.laboa.dto.FileDto;
import team.oha.laboa.model.FileDo;
import team.oha.laboa.query.file.FileFilterQuery;
import team.oha.laboa.query.file.FileSelectQuery;
import team.oha.laboa.vo.DeleteVo;

import java.util.List;

/**
 * <p></p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/12/5
 * @modified
 */
@Repository
@Mapper
public interface FileDao {
    FileDo get(Integer fileId);
    Integer save(FileDo fileDo);
    Integer update(FileDo fieDo);
    Integer delete(DeleteVo deleteVo);
    List<FileDto> list(FileSelectQuery userSelectQuery);
    Integer count(FileFilterQuery filterQuery);
}
