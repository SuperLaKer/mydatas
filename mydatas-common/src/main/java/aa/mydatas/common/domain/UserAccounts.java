package aa.mydatas.common.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserAccounts {
    private Long uid;
    private String username;
    private String accountName;
    private String money;
}
