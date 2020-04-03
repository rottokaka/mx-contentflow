package org.mxframework.contentflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author mx
 */
@SpringBootApplication
@EnableSwagger2
public class MxContentFlowApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(MxContentFlowApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MxContentFlowApplication.class);
    }
}
