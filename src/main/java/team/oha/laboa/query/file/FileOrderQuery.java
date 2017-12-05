package team.oha.laboa.query.file;

import team.oha.laboa.query.OrderQuery;

public class FileOrderQuery extends OrderQuery<FileOrderQuery.SortField> {
    @Override
    public SortField getField() {
        return super.getField();
    }

    @Override
    public void setField(SortField field) {
        super.setField(field);
    }

    public static enum SortField{
        fileId,
        username,
        name,
        remark,
        updateTime,
        createTime,
    }
}
