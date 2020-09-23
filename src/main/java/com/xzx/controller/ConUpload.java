package com.xzx.controller;

import com.alibaba.fastjson.JSONObject;
import com.xzx.service.SerUpload;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import com.xzx.common.WordToPdf;

@RestController
//跨域处理
@CrossOrigin
@Component
public class ConUpload {
    @Resource
    private SerUpload serUpload;

    @Value("${file.uploadFolder}")
    public String uploadFolder;

    @RequestMapping(value="/FileUpload", method = RequestMethod.POST)
    public String FileUpload(MultipartFile uploadFile, HttpServletRequest request) {

        String folder = request.getParameter("folder")+"/";
        String path = uploadFolder + folder;

        //java生成ID
        UUID uuid = UUID.randomUUID();
        String id  =uuid.toString().replace("-", "");
        File dir = new File(path);
        if (!dir.isDirectory()) {//文件目录不存在，就创建一个
            boolean mkdirs = dir.mkdirs();
            if (!mkdirs){
                return "上传失败";
            }
        }
        try {
            String originalFileName = uploadFile.getOriginalFilename();
            if (originalFileName==null){
                return "上传失败";
            }
            String extensionName = originalFileName.substring(originalFileName.lastIndexOf("."));
            extensionName = extensionName.toLowerCase();
            String filename = id+extensionName;

            //服务端保存的文件对象
            File fileServer = new File(dir, filename);
            //2，实现上传
            uploadFile.transferTo(fileServer);
            //4，根据表名将文件路径写进数据库表
            String dataid = request.getParameter("dataid");
            if (!dataid.equals("dataid")) {
                String tablename = request.getParameter("tablename");
                String datapath = "/"+folder+filename;
                PathUpdate(datapath, tablename, dataid);
            }
            return  folder+filename;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }

    @RequestMapping(value="/PdfUpload", method = RequestMethod.POST)
    public String PdfUpload(MultipartFile uploadFile, HttpServletRequest request) throws IOException {
        String folder = request.getParameter("folder")+"/";
        String path = uploadFolder + folder;
        String filetype= request.getParameter("filetype");
        UUID uuid = UUID.randomUUID();
        String id  =uuid.toString().replace("-", "");
        File dir = new File(path);

        if (!dir.isDirectory()) {//文件目录不存在，就创建一个
            boolean mkdirs = dir.mkdirs();
            if (!mkdirs){
                return "上传失败";
            }
        }
        try {
            String originalFileName = uploadFile.getOriginalFilename();
            if (originalFileName==null){
                return "上传失败";
            }
            String extensionName = originalFileName.substring(originalFileName.lastIndexOf("."));
            extensionName = extensionName.toLowerCase();
            String filename = id+extensionName;

            //服务端保存的文件对象
            File fileServer = new File(dir, filename);
            //2，实现上传
            uploadFile.transferTo(fileServer);

            if (filetype.equals("msword")){
                WordToPdf d = new WordToPdf();
                String worfile = path+filename;
                String pdffile = path+id+".pdf";
                d.wordToPDF(worfile, pdffile);
                filename =id+".pdf";
            }

            String dataid = request.getParameter("dataid");
            if (!dataid.equals("dataid")) {
                String tablename = request.getParameter("tablename");
                String datapath = "/"+folder+filename;
                PathUpdate(datapath, tablename, dataid);
            }
            return  folder+filename;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }

    //上传图片字节流存入数据表中接口
    @RequestMapping(value = "/ImageUpload", method = RequestMethod.POST)
    public JSONObject ImageUpload(@RequestBody String json) {

        return serUpload.Operation(json);
    }
    public void PathUpdate(String path,String tablename,String id ) {
        String json = "{info:{operation:'PathUpdate',"+"datapath:'"+path + "',des:'"+tablename+ "',id:"+id+"}}";
        serUpload.Operation(json);
    }

}

