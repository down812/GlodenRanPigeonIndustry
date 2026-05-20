package com.sky;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableTransactionManagement //开启注解方式的事务管理
@Slf4j
@EnableOpenApi
@EnableCaching
@EnableScheduling //任务调度器
public class GeGeziApplication {
    public static void main(String[] args) {
        // 检查并设置目录权限
//        checkOutputDirPermissions();
        SpringApplication.run(GeGeziApplication.class, args);
        log.info("server started");
    }


//    private static void checkOutputDirPermissions() {
//        String outputDir = "D:\\liveMedia\\hls";
//        try {
//            Runtime.getRuntime().exec(new String[]{
//                    "icacls", outputDir,
//                    "/grant", "Everyone:(OI)(CI)F"
//            });
//        } catch (IOException e) {
//            System.err.println("自动设置目录权限失败，请手动执行：");
//            System.err.println("icacls \"D:\\liveMedia\\hls\" /grant Everyone:(OI)(CI)F");
//        }
//    }
}
