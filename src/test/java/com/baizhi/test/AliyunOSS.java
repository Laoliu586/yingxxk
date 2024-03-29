package com.baizhi.test;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.PutObjectRequest;
import org.junit.jupiter.api.Test;

import java.io.File;

public class AliyunOSS {
    @Test
    public void testCrecreateBucket() {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "LTAI5tDVrg6iDrtZCKdwj6au";
        String accessKeySecret = "o8Cs1N1Xg8DOL4giBPYCmGJODevRao";
        String bucketName = "yinxa";  //存储空间名

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 创建CreateBucketRequest对象。
        CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);

        // 如果创建存储空间的同时需要指定存储类型以及数据容灾类型, 可以参考以下代码。
        // 此处以设置存储空间的存储类型为标准存储为例。
        // createBucketRequest.setStorageClass(StorageClass.Standard);
        // 默认情况下，数据容灾类型为本地冗余存储，即DataRedundancyType.LRS。如果需要设置数据容灾类型为同城冗余存储，请替换为DataRedundancyType.ZRS。
        // createBucketRequest.setDataRedundancyType(DataRedundancyType.ZRS)

        // 创建存储空间。
        ossClient.createBucket(createBucketRequest);

        // 关闭OSSClient。
        ossClient.shutdown();
    }
    @Test
    public void uploadAliyun() {
        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "LTAI5tDVrg6iDrtZCKdwj6au";
        String accessKeySecret = "o8Cs1N1Xg8DOL4giBPYCmGJODevRao";
        String bucketName = "yinxa";  //存储空间名
        String objectName = "yinxa/aaa.jpg";  //文件名  可以指定文件目录
        String localFile="C:\\Users\\86130\\Desktop\\1.jpg";  //本地视频路径

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 创建PutObjectRequest对象。 参数：Bucket名字，指定文件名，文件本地路径
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, new File(localFile));

        // 如果需要上传时设置存储类型与访问权限，请参考以下示例代码。
        // ObjectMetadata metadata = new ObjectMetadata();
        // metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
        // metadata.setObjectAcl(CannedAccessControlList.Private);
        // putObjectRequest.setMetadata(metadata);

        // 上传文件。
        ossClient.putObject(putObjectRequest);

        // 关闭OSSClient。
        ossClient.shutdown();
    }

}