package com.xzx.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.apache.shiro.codec.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.xzx.model.Fingerprintinfos;
import com.xzx.model.Photoinfos;
import com.xzx.model.Register;
import com.xzx.model.Social;
import com.xzx.service.IFingerprintinfosService;
import com.xzx.service.IPhotoinfosService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/equipment")
@Api(value="硬件设备操作相关接口",description="equipment")
public class EquipmentController {
	@Autowired
	IPhotoinfosService photoinfosService;

	@Autowired
	IFingerprintinfosService fingerprintinfosService;
	
    @Value("${file.uploadFolder}")
    private String uploadFolder;

	/**
	 * 高拍仪拍照数据写入
	 * @param photoInfo
	 * @return
	 */
	@PostMapping("/photoSubmit")
	@ApiOperation(value="高拍仪拍照",notes="高拍仪拍照",produces="application/json")
	public JSONObject photoSubmit(@ModelAttribute Photoinfos photoInfo) {
		JSONObject jsonObject=new JSONObject();

		try
		{	
			String imgBase64=photoInfo.getImgBase64();
			imgBase64=imgBase64.replaceAll(" ", "+");		
			photoInfo.setImgBase64(imgBase64);
			
			String outputPath;
			
			if(photoInfo.getSFZH().equals("00000000"))
			{
				String FileType="elecSignImg";
				
				
				File dir = new File(uploadFolder);
	            if (!dir.isDirectory()) {
	                dir.mkdirs();
	            }
	            
	            File dirType=new File(uploadFolder+"/"+FileType);
	            if (!dirType.isDirectory()) {
	            	dirType.mkdirs();
	            }
	            
	            String ext=imgBase64.substring(0,imgBase64.indexOf(",")).split(";")[0].substring(11);
	            
	            String fileName=Long.toString(System.currentTimeMillis())+"."+ext;
	            String fileurl=uploadFolder+"/"+FileType+"/"+fileName;
	            
	            String imgStr = imgBase64.substring(imgBase64.indexOf(",")+1);
	            
				String writeFileFlag=decryptByBase64(imgStr,fileurl);
				
				
				if(writeFileFlag.equals("1"))
					outputPath="/"+FileType+"/"+fileName;
				else
					outputPath="";
				
				if(!outputPath.equals(""))
					photoInfo.setSxqmurl(outputPath);
			}
			
			int flag=photoinfosService.insertPhoto(photoInfo);

			if(flag>0)
			{
				jsonObject.put("photoId", photoInfo.getId());
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "拍照文件读取成功");
			}
			else
			{
				jsonObject.put("Code", "11111111");
				jsonObject.put("Msg", "拍照文件读取失败");
			}
		}
		catch(Exception e)
		{
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "拍照文件读取失败");
		}
		return jsonObject;
	}
	
	public String decryptByBase64(String base64, String filePath) throws Exception{
        String flag;
		
        if (base64 == null && filePath == null) {
        	flag="0";
        }
        
        try 
        {
            Files.write(Paths.get(filePath),Base64.decode(base64), StandardOpenOption.CREATE);
            
            flag="1";
        } 
        catch (IOException e) 
        {
        	flag="0";
        	
            throw e;
        }
        return flag;
    }

	/**
	 * 删除已读取的高拍仪拍照记录
	 * @param id
	 * @return
	 */
	@PostMapping("/delPhotosById")
	@ApiOperation(value="删除已读取的高拍仪拍照记录",notes="删除已读取的高拍仪拍照记录",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="id",value="拍照记录id",dataType="int",required=true,paramType="query")
	})
	public JSONObject delPhotosById(@RequestParam int id) {
		JSONObject jsonObject=new JSONObject();

		try
		{
			int flag=photoinfosService.delPhotosInfo(id);

			if(flag>=0)
			{
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "删除已读取拍照文件成功");
			}
			else
			{
				jsonObject.put("Code", "11111111");
				jsonObject.put("Msg", "删除已读取拍照文件失败");
			}
		}
		catch(Exception e)
		{
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "删除已读取拍照文件失败");
		}
		return jsonObject;
	}

	/**
	 * 根据id获取高拍仪拍摄的图片流
	 * @param id
	 * @return
	 */
	@GetMapping("getPhotosById")
	@ApiOperation(value="根据id获取高拍仪拍摄的图片流",notes="根据id获取高拍仪拍摄的图片流",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="id",value="拍摄图片主键id",dataType="int",required=true,paramType="query")
	})
	public JSONObject getPhotosById(@RequestParam("id")int id)
	{
		JSONObject jsonObject=new JSONObject();

		try 
		{
			Photoinfos photoinfos=photoinfosService.getPhotoById(id);

			if(photoinfos==null)
			{
				jsonObject.put("Code", "11111111");
				jsonObject.put("Msg", "没有对应的图片信息！");
			}
			else 
			{
				jsonObject.put("photo", photoinfos);

				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "图片获取成功！");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "图片获取失败！");
		}
		return jsonObject;
	}


	/**
	 * 指纹写入
	 * @param fingerprintinfos
	 * @return
	 */
	@PostMapping("/fingerSubmit")
	@ApiOperation(value="指纹录取",notes="指纹录取",produces="application/json")
	public JSONObject fingerSubmit(@ModelAttribute Fingerprintinfos fingerprintinfos) {
		JSONObject jsonObject=new JSONObject();

		try
		{
			String imgBase64=fingerprintinfos.getFingerPrint();
			imgBase64=imgBase64.replaceAll(" ", "+");		
			
			Register register=new Register();
			register.setSFZH(fingerprintinfos.getSFZH());
			register.setFingers(imgBase64);

			Social social=new Social();
			social.setSocialIdCard(fingerprintinfos.getSFZH());
			social.setHandImage(imgBase64);

			fingerprintinfos.setFingerPrint(imgBase64);
			
			int flag=fingerprintinfosService.insertFingerPhoto(fingerprintinfos,register,social);

			if(flag>0)
			{
				jsonObject.put("photoId", fingerprintinfos.getId());
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "指纹读取成功");
			}
			else
			{
				jsonObject.put("Code", "11111111");
				jsonObject.put("Msg", "指纹读取失败");
			}
		}
		catch(Exception e)
		{
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "指纹读取失败");
		}
		return jsonObject;
	}

	/**
	 * 根据id获取相应指纹图片流
	 * @param id
	 * @return
	 */
	@GetMapping("getFingerById")
	@ApiOperation(value="根据id获取相应指纹图片流",notes="根据id获取相应指纹图片流",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="id",value="指纹主键id",dataType="int",required=true,paramType="query")
	})
	public JSONObject getFingerById(@RequestParam("id")int id)
	{
		JSONObject jsonObject=new JSONObject();

		try 
		{
			Fingerprintinfos fingerprintinfos=fingerprintinfosService.getFingerById(id);

			if(fingerprintinfos==null)
			{
				jsonObject.put("Code", "11111111");
				jsonObject.put("Msg", "没有对应的指纹信息！");
			}
			else 
			{
				jsonObject.put("photo", fingerprintinfos);

				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "指纹获取成功！");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "指纹获取失败！");
		}
		return jsonObject;
	}

	/**
	 * 删除已读取的指纹记录
	 * @param id
	 * @return
	 */
	@PostMapping("/delFingerById")
	@ApiOperation(value="删除已读取的指纹记录",notes="删除已读取的指纹记录",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="id",value="指纹记录id",dataType="int",required=true,paramType="query"),
		@ApiImplicitParam(name="test",value="test",dataType="String",required=true,paramType="query")
	})
	public JSONObject delFingerById(@RequestParam int id,@RequestParam String test) {
		JSONObject jsonObject=new JSONObject();

		try
		{
			int flag=fingerprintinfosService.delFingerInfo(id);

			if(flag>=0)
			{
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "删除已读取指纹成功");
			}
			else
			{
				jsonObject.put("Code", "11111111");
				jsonObject.put("Msg", "删除已读取指纹失败");
			}
		}
		catch(Exception e)
		{
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "删除已读取指纹失败");
		}
		return jsonObject;
	}
}
