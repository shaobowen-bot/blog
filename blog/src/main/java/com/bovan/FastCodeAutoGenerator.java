package com.bovan;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * @BelongsProject: blog-boot
 * @BelongsPackage: com.bovan.blog
 * @Author: bovan
 * @Date: 2022/10/13 08:05
 * @Description:
 */

public class FastCodeAutoGenerator {
    public static void main(String[] args) {
        //获取当前模块所在的路径
        String propath = System.getProperty("user.dir");
        System.out.println(propath);

        FastAutoGenerator.create("jdbc:mysql://localhost:3306/db_blogs?useSSL=true&useUnicode=true&serverTimezone=UCT", "root", "root").globalConfig(builder -> {
                    builder.author("bovan") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(propath + "/blog/src/main/java"); // 指定输出目录
                })

                .packageConfig(builder -> {
                    builder.parent("com.bovan") // 设置父包名
//                            .moduleName("plus") // 设置父包模块名
//                            指定xml生成的路径
                            .pathInfo(Collections.singletonMap(OutputFile.xml, propath + "\\blog\\src\\main\\resources\\mapper\\"));
                }).strategyConfig(builder -> {
                    builder.addInclude("t_user_roles") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_")
                            //自定义配置继承父包 HmBaseMapper  此类为我们公司扩展类
                            .mapperBuilder().build()
                            //实体类配置
                            .entityBuilder().enableLombok().enableChainModel().enableTableFieldAnnotation().disableSerialVersionUID().build()
                            //service配置
                            .serviceBuilder().build();
                    ;
                }).templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
