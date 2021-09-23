package aa.mydatas.elastic.springdata.domains;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

@Data
@Document(indexName = "books")
public class BookModel implements Serializable {

    @Id
    private String _id; // ES中的document_id
    private Integer book_id; // book编号
    private String name; // 图书名称
    private String author; // 作者
    private String category; // 图书分类
    private Double price; // 图书价格
    private String sellReason; // 上架理由
    private String sellTime; // 上架时间
    private Integer status; // 状态（1：可售，0：不可售）
}