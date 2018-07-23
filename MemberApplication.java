package com.copart.g2.member;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by addamaraju on 1/14/2016.
 */
@SpringBootApplication(exclude = {JpaRepositoriesAutoConfiguration.class, FreeMarkerAutoConfiguration.class,
        ThymeleafAutoConfiguration.class})
@MapperScan("com.copart.g2.member.dao.db")
@EnableAspectJAutoProxy
@EnableScheduling
@EnableAsync
public class MemberApplication extends SpringBootServletInitializer
{
	private static final Logger log = LogManager.getLogger(MemberApplication.class);

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder)
    {
        return applicationBuilder.sources(MemberApplication.class);
    }

    public static void main(String[] args)
    {
    	log.info("******************************************************  STARTING G2 MEMBER ....****************************************************************************");
        System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(MemberApplication.class, args);
        log.info("******************************************************  G2 MEMBER  STARTED SUCCESSFULLY  ***************************************************************************");
    }

    /**
     * Performs Application health check
     */
    @RestController
    public static class ApplicationHealthCheck
    {

        static final Map<String, String> healthCheckMap = new HashMap<>();
        static
        {
            healthCheckMap.put("STATUS", "OK");
        }
        
        @RequestMapping(value = {"/healthCheck"}, produces = "application/json")
        public Map<String, String> applicationHealthCheckBasic()
        {
            return healthCheckMap;
        }

    }

}
