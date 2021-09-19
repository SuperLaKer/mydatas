package mydatas.sql.haha.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import mydatas.sql.haha.entity.User;
import mydatas.sql.haha.mapper.UserMapper;
import mydatas.sql.haha.service.IUserService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2021-03-31
 */
//@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    public void saveUser() {
        //        User user = new User("0", "用户2", "123456", 10, 20,
//                30, new Timestamp(System.currentTimeMillis()));
//        userMapper.insert(user);
    }

}
