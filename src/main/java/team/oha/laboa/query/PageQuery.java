package team.oha.laboa.query;


import java.io.Serializable;

public class PageQuery implements Serializable{
     
    private Integer offset;
    private Integer rows;

     public Integer getOffset() {
          return offset;
     }

     public void setOffset(Integer offset) {
          this.offset = offset;
     }

     public Integer getRows() {
          return rows;
     }

     public void setRows(Integer rows) {
          this.rows = rows;
     }
}
