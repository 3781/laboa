package team.oha.laboa.vo;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * <p></p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/12/5
 * @modified
 */
public class FileVo {
    private Integer fileId;
    private Integer userId;
    private CommonsMultipartFile file;
    private String remark;

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public CommonsMultipartFile getFile() {
        return file;
    }

    public void setFile(CommonsMultipartFile file) {
        this.file = file;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "FileVo{" +
                "fileId=" + fileId +
                ", userId=" + userId +
                ", file=" + file +
                ", remark='" + remark + '\'' +
                '}';
    }
}
