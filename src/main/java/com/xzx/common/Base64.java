package com.xzx.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Component;

import sun.misc.BASE64Decoder;

@Component
public class Base64 {  

	public String base64(String imageFile,String FileType,String serverPath) {
		String type = imageFile.split(",")[0].split("/")[1].split(";")[0];
		imageFile = imageFile.split(",")[1];
		BASE64Decoder decoder = new BASE64Decoder();
		// Base64解码
		byte[] imageByte = null;
		try {
			imageByte = decoder.decodeBuffer(imageFile);
			for (int i = 0; i < imageByte.length; ++i){
				if (imageByte[i] < 0) {// 调整异常数据
					imageByte[i] += 256;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Bytes2File(imageByte,type,FileType,serverPath);
	}

	public static String Bytes2File(byte[] imageByte , String type,String FileType,String serverPath)
	{
		String path = null;
		try {
			int length = imageByte.length;
			//追加文件夹
			File dir = new File(serverPath);
			if (!dir.isDirectory()) {
				dir.mkdirs();
			}

			File dirType=new File(serverPath+"/"+FileType);
			if (!dirType.isDirectory()) {
				dirType.mkdirs();
			}

			String filenames=UUID.randomUUID().toString().replace("-", "")+ "." + type;
			path = serverPath+"/"+FileType+"/"+filenames;
			FileOutputStream fos = new FileOutputStream(path);//isAppend如果为true，为追加写入，否则为覆盖写入
			fos.write(imageByte,0,length);
			fos.close();
			path = "/"+FileType+"/"+filenames;
		}catch (Exception e){
			e.printStackTrace();
		}
		return path;
	}

	public static String fileToBase64(String path,String serverPath) throws IOException {
		File file=new File(serverPath+"/"+path);
		FileInputStream inputStream = new FileInputStream(file);

		byte[] buffer = new byte[(int)file.length()];
		inputStream.read(buffer);
		inputStream.close();

		// 转换为base64编码格式
		String base64 = org.apache.commons.codec.binary.Base64.encodeBase64String(buffer).replaceAll("[\\s*\t\n\r]", "");
				//new sun.misc.BASE64Encoder().encode(buffer);

		return base64;
	}
}
