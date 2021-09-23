package aa.mydatas.elastic.web.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class BookModel implements Serializable {
    private String id; // ES中的document_id
    private String name; // 图书名称
    private String author; // 作者
    private String category; // 图书分类
    private Double price; // 图书价格
    private String sellReason; // 上架理由
    private String sellTime; // 上架时间
    private Integer status; // 状态（1：可售，0：不可售）
}