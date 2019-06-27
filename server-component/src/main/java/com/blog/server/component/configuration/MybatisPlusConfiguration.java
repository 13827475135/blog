package com.blog.server.component.configuration;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.autoconfigure.SpringBootVFS;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.blog.configuration.AutoFillMetaObjectHandler;
import com.blog.constant.EnvironmentConstants;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
@MapperScan({
        "com.blog.dao.*"
})
public class MybatisPlusConfiguration implements EnvironmentAware {

    private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    /**
     * 自动填充组件
     */
    @Bean
    public AutoFillMetaObjectHandler autoFillMetaObjectHandler() {
        return new AutoFillMetaObjectHandler();
    }


    /**
     * 支持乐观锁所必须的插件配置
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    /**
     * 性能分析插件，仅对非生产环境开放，用于记录每条SQL语句以及其执行时间
     */
    @Bean
    @Profile({"dev", "test"})
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor();
    }

    /**
     * 逻辑删除
     */
    @Bean
    public LogicSqlInjector logicSqlInjector() {
        return new LogicSqlInjector();
    }

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    @Bean
    public MybatisPlusProperties mybatisPlusProperties() {
        MybatisPlusProperties mybatisPlusProperties = new MybatisPlusProperties();
        // 自动填充与逻辑删除bean
        mybatisPlusProperties.getGlobalConfig().setMetaObjectHandler(autoFillMetaObjectHandler());
        mybatisPlusProperties.getGlobalConfig().setSqlInjector(logicSqlInjector());
        return mybatisPlusProperties;
    }

    @Bean
    public MybatisSqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);

        MybatisPlusProperties mybatisPlusProperties = mybatisPlusProperties();
        // test log sql
        if (this.environment.acceptsProfiles(EnvironmentConstants.DEV, EnvironmentConstants.TEST)) {
            mybatisPlusProperties.getConfiguration().setLogImpl(StdOutImpl.class);
        }
        mybatisPlusProperties.getConfiguration().setVfsImpl(SpringBootVFS.class);

        sqlSessionFactoryBean.setTypeHandlersPackage(mybatisPlusProperties.getTypeHandlersPackage());
        sqlSessionFactoryBean.setTypeEnumsPackage(mybatisPlusProperties.getTypeEnumsPackage());
        sqlSessionFactoryBean.setGlobalConfig(mybatisPlusProperties.getGlobalConfig());
        sqlSessionFactoryBean.setConfiguration(mybatisPlusProperties.getConfiguration());

        // 配置乐观锁、分页插件
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{optimisticLockerInterceptor(), paginationInterceptor()});

        return sqlSessionFactoryBean;
    }
}
