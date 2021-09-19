package mydatas.sql.haha.service;

import com.baomidou.mybatisplus.extension.service.IService;
import mydatas.sql.haha.entity.TimeDate;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author jobob
 * @since 2021-03-31
 */
public interface ITimeDateService extends IService<TimeDate> {
    TimeDate findTimeDateById(Long id);
}
