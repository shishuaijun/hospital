package com.follow;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wangchunjun
 * @date 2020/8/5
 */
@SpringBootApplication
@MapperScan("com.follow.mapper")
public class FollowUpApplication {

    public static void main(String[] args) {
        SpringApplication.run(FollowUpApplication.class, args);
    }

}
