package com.xzx.generate;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

public class MpGenerator {
	public static void main(String[] args) {

//      assert (false) : "代码生成属于危险操作，请确定配置后取消断言执行代码生成！";
      AutoGenerator mpg = new AutoGenerator();
      // 选择 freemarker 引擎，默认 Velocity
      mpg.setTemplateEngine(new FreemarkerTemplateEngine());

      // 全局配置
      GlobalConfig gc = new GlobalConfig();
      gc.setAuthor("Helen");
      gc.setOutputDir(" E://sj20200907/project/zcProject/src/main/java");
      gc.setFileOverride(false);// 是否覆盖同名文件，默认是false
      gc.setActiveRecord(true);// 不需要ActiveRecord特性的请改为false
      gc.setEnableCache(false);// XML 二级缓存
      gc.setBaseResultMap(true);// XML ResultMap
      gc.setBaseColumnList(false);// XML columList

      mpg.setGlobalConfig(gc);

      // 数据源配置
      DataSourceConfig dsc = new DataSourceConfig();
      dsc.setDbType(DbType.MYSQL);
      dsc.setTypeConvert(new MySqlTypeConvert() {
          // 自定义数据库表字段类型转换【可选】
          @Override
          public DbColumnType processTypeConvert(String fieldType) {
              System.out.println("转换类型：" + fieldType);
              // 注意！！processTypeConvert 存在默认类型转换，如果不是你要的效果请自定义返回、非如下直接返回。
              return super.processTypeConvert(fieldType);
          }
      });
      dsc.setDriverName("com.mysql.cj.jdbc.Driver");
      //dsc.setUsername("wyb");
      dsc.setUsername("root");
      //dsc.setPassword("619728");
      dsc.setPassword("123456");
      //dsc.setUrl("jdbc:mysql://192.168.0.28:3306/judicial?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC");
      dsc.setUrl("jdbc:mysql://192.168.0.180:3306/zc?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC");
      mpg.setDataSource(dsc);

      // 策略配置
      StrategyConfig strategy = new StrategyConfig();
      // strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
      strategy.setTablePrefix(new String[] { "t_" });// 此处可以修改为您的表前缀
      strategy.setNaming(NamingStrategy.nochange);// 表名生成策略
      strategy.setInclude(new String[] { "zcdw"}); // 需要生成的表

      mpg.setStrategy(strategy);

      // 包配置
      PackageConfig pc = new PackageConfig();
      pc.setParent("com.xzx").setController("controller").setEntity("model").setMapper("dao").setXml("dao");
      mpg.setPackageInfo(pc);

      // 执行生成
      mpg.execute();
	}
}
