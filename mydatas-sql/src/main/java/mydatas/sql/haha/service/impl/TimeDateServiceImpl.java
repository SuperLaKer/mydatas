package mydatas.sql.haha.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import mydatas.sql.haha.entity.TimeDate;
import mydatas.sql.haha.mapper.TimeDateMapper;
import mydatas.sql.haha.service.ITimeDateService;

/**
 * <p>
 * 服务实现类
 * </p>
 * <p>
 * extends ServiceImpl<RoleMapper, Role> implements IRoleService
 *
 * @author jobob
 * @since 2021-03-31
 */
@Service
public class TimeDateServiceImpl extends ServiceImpl<TimeDateMapper, TimeDate> implements ITimeDateService {

    public TimeDate findTimeDateById(Long id) {
        return baseMapper.selectById(id);
    }

}
