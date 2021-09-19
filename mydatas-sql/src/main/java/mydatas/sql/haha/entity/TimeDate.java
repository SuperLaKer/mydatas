package mydatas.sql.haha.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class TimeDate implements Serializable {

    private Long id;
    private java.sql.Date theDate;
    private java.sql.Timestamp theDatetime;
    private java.sql.Time theTime;
    private java.sql.Timestamp theTimestamp;
    private String tempAString;
    private String tempBString;
    private String status;
}
