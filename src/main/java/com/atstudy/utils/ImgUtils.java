package com.atstudy.utils;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.NotImplementedException;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

//图片上传工具类
@Component                  //需要托管给spring框架
                            //未来它的上层是BrandService、SpuService
public class ImgUtils {
    //成员属性
    Boolean enableFDFS = true;     //true，就连fdfs的tracker执行图片上传
                                    //false，默认；就走本地的javaweb服务器，执行图片流的存放

    @Resource                       //使用@Resource注解，或者@Autowired注解，都是可以的
                                    //目标：让spring框架帮助实例化该成员属性
    private FastFileStorageClient fastFileStorageClient;

    /**
     * 图片上传、保存到服务器的工具方法
     * @param multipartFile 入参：文件上传的java类
     * @return  生成的文件名全名
     */
    public String upload(MultipartFile multipartFile){

        if(this.enableFDFS==false) {

            //调用UUID类，生成随机的文件名
            String filename = UUID.randomUUID().toString();
            // 拼接文件的扩展名
            filename += multipartFile.getOriginalFilename().substring(
                    multipartFile.getOriginalFilename().lastIndexOf("."));
            System.out.println("随机生成的文件路径：" + filename);

            //把图片流存到java服务器指定的目录
            try {
                // 实例化 File对象 映射 要保存的路径
                File target = new File(
                        ResourceUtils.getURL("classpath:").getPath() +
                                "static/img/" + filename
                );
                // 将临时文件 从 临时目录 迁移到 指定的目录
                multipartFile.transferTo(target);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //图片上传业务-结束
            return filename;
        }
        else {
            //要完成连fdfs-tracker，执行图片上传
            return this.uploadByFDFS(multipartFile);
            //throw new NotImplementedException();
        }
    }

    //通过fastdfs上传文件的业务方法
    public String uploadByFDFS(MultipartFile multipartFile){
        //声明一个字符串，存放上传后的文件名
        String filename = "";

        //把图片流抛给fastdfs执行上传，返回一个storePath的实例
        try {
            StorePath storePath = fastFileStorageClient.uploadFile(
                    multipartFile.getInputStream(),   // 文件的输入流
                    multipartFile.getSize(),          // 文件的尺寸
                    FilenameUtils.getExtension( multipartFile.getOriginalFilename() ),    // 文件的扩展名
                    null                              //修改这个参数，告诉fastdfs，是否要生成缩略图。。。等等其他的参数
            );

            //设置filename=storePath.getFullPath() ，从返回的实例中得到fastdfs存储的图片路径
            filename = storePath.getFullPath();
            System.out.println(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //返回
        return filename;
    }
}
