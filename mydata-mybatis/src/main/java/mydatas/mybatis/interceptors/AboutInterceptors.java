package mydatas.mybatis.interceptors;

import aa.slkenv.pageable.parameter.PagePlugin;
import org.springframework.context.annotation.Import;

/**
 * mybatis-plus很多功能都是基于插件实现的
 * 如分页插件
 */
@Import({PagePlugin.class})
public class AboutInterceptors {
    public static void main(String[] args) {

    }
}
