package mydatas.mybatis.interceptors.tenants;

import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.sql.SQLException;


/**
 * 方法级别控制
 * 有些接口需要跨方法调用《有关联关系的生产商和销售商之间可能需要通信》
 */
public class TenantLineInnerInterceptorEnhance extends TenantLineInnerInterceptor {

    public TenantLineInnerInterceptorEnhance(TenantLineHandler tenantLineHandler) {
        super(tenantLineHandler);
    }

    @Override
    public void beforeQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) throws SQLException {
//        List<String> ignoreList = new ArrayList<>();
//        ignoreList.add("mydatas.mybatis.mapper.LearnMybatisPlusMapper.selectAll");
//        if (ignoreList.contains(ms.getId())) {
//            return;
//        }
        super.beforeQuery(executor, ms, parameter, rowBounds, resultHandler, boundSql);
    }
}
