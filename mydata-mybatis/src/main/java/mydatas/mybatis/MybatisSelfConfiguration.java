package mydatas.mybatis;


import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import mydatas.mybatis.interceptors.tenants.TenantLineInnerInterceptorEnhance;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import org.springframework.context.annotation.Bean;

/**
 * 版本一定要对应：mybatis-plus官网 since 3.4.0
 */

public class MybatisSelfConfiguration {

    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return configuration -> configuration.setUseDeprecatedExecutor(false);
    }

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 多租户metaObject.getValue("delegate.mappedStatement.id")
        // todo 方法级别租户隔离控制
        interceptor.addInnerInterceptor(new TenantLineInnerInterceptorEnhance(new MyTenantLineHandler()));
        // 分页插件
        // interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        // 乐观锁：更新数据的时候使用
        // interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        return interceptor;
    }
}

class MyTenantLineHandler implements TenantLineHandler {

    @Override
    public Expression getTenantId() {
        // todo 登陆之后把tenantId设置到这里，threadLocal
        return new LongValue(5);
    }

    @Override
    public String getTenantIdColumn() {
        // 这个是表中的字段名
        return "tenant_id";
    }

    // false表示：所有的表都需要租户隔离
    @Override
    public boolean ignoreTable(String tableName) {
        // true忽略
        // return !"user".equalsIgnoreCase(tableName);
        return false;
    }
}
