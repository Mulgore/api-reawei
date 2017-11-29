package cn.reawei.api;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement //启用事物
// Spring Boot 应用的标识
@SpringBootApplication
//配置Dao层扫描包配置
@MapperScan("cn.reawei.api.mapper")
public class ApiReaweiApplication extends SpringBootServletInitializer {


	private static final Logger logger = LoggerFactory.getLogger(ApiReaweiApplication.class);

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(this.getClass());
	}

	public static void main(String[] args) {
		logger.info("========== { api-reawei spring boot start } ==========");
		// 程序启动入口
		// 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
		SpringApplication.run(ApiReaweiApplication.class, args);
	}
}
