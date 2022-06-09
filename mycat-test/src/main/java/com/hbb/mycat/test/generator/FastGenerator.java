package com.hbb.mycat.test.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

/**
 * @Author hjc
 * @Date 2022-06-09-14-44
 **/
public class FastGenerator {

    @Test
    public void generator() {
        AutoGenerator generator = new AutoGenerator();
        //全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        globalConfig.setOutputDir(projectPath + "/src/main/java");
        globalConfig.setAuthor("hjc");//设置代码作者
        globalConfig.setOpen(false);
        globalConfig.setFileOverride(false);//是否覆盖
        globalConfig.setServiceName("%sService");//去掉Service的I前缀


        generator.setGlobalConfig(globalConfig);


        //配置数据源
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUrl("jdbc:mysql://101.132.97.234:8099/mycat_test?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("root@3131.");

        generator.setDataSource(dataSourceConfig);

        //配置包信息
        PackageConfig packageConfig = new PackageConfig();

        packageConfig.setParent("com.hbb.mycat.test");//设置父路径
        packageConfig.setEntity("pojo");
        packageConfig.setMapper("mapper");
        packageConfig.setService("service");
        packageConfig.setServiceImpl("service.impl");
        packageConfig.setController("controller");
        packageConfig.setXml("mapper");


        generator.setPackageInfo(packageConfig);

        //配置策略信息
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setInclude("test");//生成那个表
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);//配置表名驼峰命名
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);//表中字段驼峰命名
        strategyConfig.setEntityLombokModel(true);//为实体类开启lombok注解


        strategyConfig.setRestControllerStyle(true);


        generator.setStrategy(strategyConfig);
        //执行
        generator.execute();
    }
}
