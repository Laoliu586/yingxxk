package com.baizhi.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileUtil {
    private static Properties map = new Properties();   // map集合类

    // 读文件的动作只需要在类加载时执行，执行一次
    static {
        // 将配置文件里的内容 还原到 java程序中
        InputStream is = null;
        try {
            is = FileUtil.class.getResourceAsStream("/com/baizhi/conf/conn.properties");
            map.load(is);   // 方法过后，文件的所有内容都被保存在了map里
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) try {
                is.close();
            } catch (Exception e) {
            }
        }
    }

    //删除文件
    public static void delete(String fileName){
        // yourEndpoint填写Bucket所在地域对应的Endpoint。
        // 以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
        String endpoint = map.getProperty("endpoint");
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。
        // 强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = map.getProperty("accessKeyId");
        String accessKeySecret = map.getProperty("accessKeySecret");
        // 填写Bucket名称。
        String bucketName = map.getProperty("examplebucket");
        // 填写文件完整路径。文件完整路径中不能包含Bucket名称。
        String objectName = fileName;
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 删除文件或目录。如果要删除目录，目录必须为空。
        ossClient.deleteObject(bucketName, objectName);
        // 关闭OSSClient。
        ossClient.shutdown();
    }

    //上传文件
    public static void UploadFile(MultipartFile photo, String fileName) throws IOException {
        // yourEndpoint填写Bucket所在地域对应的Endpoint。
        // 以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
        String endpoint = map.getProperty("endpoint");
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。
        // 强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = map.getProperty("accessKeyId");
        String accessKeySecret = map.getProperty("accessKeySecret");
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 创建PutObjectRequest对象。
        // 依次填写Bucket名称（例如examplebucket）和Object完整路径
        // 例如exampledir/exampleobject.txt）。Object完整路径中不能包含Bucket名称。

        PutObjectRequest putObjectRequest = new PutObjectRequest(
                map.getProperty("examplebucket"),
                fileName,
                new ByteArrayInputStream(photo.getBytes()));
        // 上传字符串。
        ossClient.putObject(putObjectRequest);
        // 关闭OSSClient。
        ossClient.shutdown();
    }
}

