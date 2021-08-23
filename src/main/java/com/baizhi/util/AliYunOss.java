package com.baizhi.util;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.PutObjectRequest;
import com.baizhi.config.AliYunConfig;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

public class AliYunOss {

    //    上传 (字节数组上传)
    public static void uploadByBytes(MultipartFile file,String fileName) {
        // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
        String endpoint = AliYunConfig.ENDPOINT;
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = AliYunConfig.ACCESS_KEY_ID;
        String accessKeySecret = AliYunConfig.ACCESS_KEY_SECRET;

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 填写字符串。
        //        String content = "Hello OSS";
        byte[] content = null;
        try {
            content = file.getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 创建PutObjectRequest对象。
        // 依次填写Bucket名称（例如examplebucket）和Object完整路径（例如exampledir/exampleobject.txt）。Object完整路径中不能包含Bucket名称。
        PutObjectRequest putObjectRequest = new PutObjectRequest("yinxa", fileName, new ByteArrayInputStream(content));
        ossClient.putObject(putObjectRequest);
        ossClient.shutdown();

    }



    //删除文件
    public static void deleteFile(String fileName){
        String endpoint = AliYunConfig.ENDPOINT;
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = AliYunConfig.ACCESS_KEY_ID;
        String accessKeySecret = AliYunConfig.ACCESS_KEY_SECRET;

        String bucketName = "yinxa";  //存储空间名
        String objectName = fileName;  //文件名

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 删除文件。如需删除文件夹，请将ObjectName设置为对应的文件夹名称。如果文件夹非空，则需要将文件夹下的所有object删除后才能删除该文件夹。
        ossClient.deleteObject(bucketName, objectName);

        // 关闭OSSClient。
        ossClient.shutdown();
    }
    public static String jqVideo(String filePath){
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
        String endpoint = AliYunConfig.ENDPOINT;
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = AliYunConfig.ACCESS_KEY_ID;
        String accessKeySecret = AliYunConfig.ACCESS_KEY_SECRET;
// 填写视频文件所在的Bucket名称。
        String bucketName = "yinxa";
// 填写视频文件的完整路径。若视频文件不在Bucket根目录，需携带文件访问路径，例如examplefolder/videotest.mp4。
        String objectName = filePath;
// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
// 使用精确时间模式截取视频50s处的内容，输出为JPG格式的图片，宽度为800，高度为600。
        String style = "video/snapshot,t_50000,f_jpg,w_800,h_600";
// 指定过期时间为10分钟。
        Date expiration = new Date(new Date().getTime() + 1000 * 60 * 10 );
        GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest(bucketName, objectName, HttpMethod.GET);
        req.setExpiration(expiration);
        req.setProcess(style);
        URL signedUrl = ossClient.generatePresignedUrl(req);
        System.out.println(signedUrl);
// 关闭OSSClient。
        ossClient.shutdown();
        return signedUrl.toString();
    }
    public static void URLuploal(String url,String fileName) {
        // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
        String endpoint = AliYunConfig.ENDPOINT;
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = AliYunConfig.ACCESS_KEY_ID;
        String accessKeySecret = AliYunConfig.ACCESS_KEY_SECRET;

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 填写网络流地址。
        InputStream  inputStream = null;
        try {
            inputStream = new URL(url).openStream();
        }catch(Exception e){
            e.printStackTrace();
        }
// 依次填写Bucket名称（例如examplebucket）和Object完整路径（例如exampledir/exampleobject.txt）。Object完整路径中不能包含Bucket名称。
        ossClient.putObject("yinxa", "video/"+fileName, inputStream);

// 关闭OSSClient。
        ossClient.shutdown();
    }
    public static void download(String fileName){
        String endpoint = AliYunConfig.ENDPOINT;
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = AliYunConfig.ACCESS_KEY_ID;
        String accessKeySecret = AliYunConfig.ACCESS_KEY_SECRET;
// 填写Bucket名称。
        String bucketName = "yinxa";
// 填写不包含Bucket名称在内的Object完整路径，例如testfolder/exampleobject.txt。
        String objectName = "http://yinxa.oss-cn-beijing.aliyuncs.com/"+fileName;
        String localFile="D:\\photo\\"+objectName;  //下载本地地址  地址加保存名字

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 下载OSS文件到本地文件。如果指定的本地文件存在会覆盖，不存在则新建。
        ossClient.getObject(new GetObjectRequest(bucketName, objectName), new File(localFile));

        // 关闭OSSClient。
        ossClient.shutdown();
    }
}
