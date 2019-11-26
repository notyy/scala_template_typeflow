package com.github.notyy.example.aliyun

import java.io.{InputStream, OutputStream}

import com.aliyun.fc.runtime.{Context, StreamRequestHandler}
import com.aliyun.oss.OSSClientBuilder

import scala.io.Source
import scala.util.{Failure, Success, Try}

class LoadAccumulateValueHandler extends StreamRequestHandler {
  override def handleRequest(input: InputStream, output: OutputStream, context: Context): Unit = {
    val accessKey = System.getenv("ACCESS_KEY")
    val accessSecretKey = System.getenv("SECRET_KEY")
    val accountId = System.getenv("ACCOUNT_ID")
    val bucketName = System.getenv("BUCKET_NAME")
    val objectName = System.getenv("OBJECT_NAME")

    Try {
      val ossClient = new OSSClientBuilder().build("oss-cn-shanghai-internal.aliyuncs.com", accessKey, accessSecretKey)
      val ossObject = ossClient.getObject(bucketName, objectName)
      val rs = Source.fromInputStream(ossObject.getObjectContent).mkString
      ossObject.close()
      rs
    } match {
      case Success(value) => output.write(value.getBytes)
      case Failure(exception) => output.write(exception.getMessage.getBytes)
    }
  }
}
