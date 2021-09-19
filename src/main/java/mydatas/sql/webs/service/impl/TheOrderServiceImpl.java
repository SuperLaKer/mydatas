package mydatas.sql.webs.service.impl;

import mydatas.sql.webs.entity.TheOrder;
import mydatas.sql.webs.mapper.TheOrderMapper;
import mydatas.sql.webs.service.ITheOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lla
 * @since 2021-09-18
 */
@Service
public class TheOrderServiceImpl extends ServiceImpl<TheOrderMapper, TheOrder> implements ITheOrderService {

}
