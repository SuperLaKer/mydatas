package aa.mydatas.common.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Goods2 implements Serializable {
    private Long id;
    private Integer status;
    private Integer goodsNum;
    private String createTime;
    private String goodsName;
    private String goodsDesc;
    private String price;
    private Integer version;
}
