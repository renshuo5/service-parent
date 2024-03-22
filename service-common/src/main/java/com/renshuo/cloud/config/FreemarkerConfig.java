package com.renshuo.cloud.config;

import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.StringTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.TemplateException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactory;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Lenovo on 2023/11/2.
 */
@Configuration
public class FreemarkerConfig {

    @Bean(name = "freeMarkerConfigurer")
    public FreeMarkerConfigurer freemarkerConfig() throws IOException, TemplateException {

        FreeMarkerConfigurationFactory factory = new FreeMarkerConfigurationFactory();
        factory.setTemplateLoaderPaths("classpath:/templates");
        factory.setDefaultEncoding("UTF-8");
        factory.setPreferFileSystemAccess(false);


        Properties properties = new Properties();
        properties.setProperty("number_format", "#");
        properties.setProperty("classic_compatible", "true");

        factory.setFreemarkerSettings(properties);
        FreeMarkerConfigurer result = new FreeMarkerConfigurer();
        result.setConfiguration(factory.createConfiguration());


        MultiTemplateLoader mtl = new MultiTemplateLoader(new TemplateLoader[]{
                new StringTemplateLoader(), result.getConfiguration().getTemplateLoader()});
        result.getConfiguration().setTemplateLoader(mtl);

        return result;
    }

}
