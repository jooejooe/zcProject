package com.xzx.service;

import com.alibaba.fastjson.JSONObject;
import com.xzx.dao.MapSystjxx;
import com.xzx.dao.MapUpload;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class SerUpload {
    /********************************************
     * operation:操作值
     * "ImageUpload" 图片上传
     * "ImageClear"  图片清除
     * des: 说明
     * "sjryzp"  社矫人员照片
     * "sjsfzzp" 社矫人员身份证照片
     * image: 图片字符串
     * id:对应操作ID
     * ******************************************/
    @Resource
    private MapUpload mapUpload;

    @Resource
    private MapSystjxx mapSystjxx;

    public JSONObject Operation(String json) {
        JSONObject jsStr = JSONObject.parseObject(json);
        //System.out.println("jsStr:"+jsStr);
        jsStr = JSONObject.parseObject(jsStr.getString("info"));
        JSONObject jsReturn = new JSONObject();
        String operation,des,id;
        operation = jsStr.getString("operation");
        des = jsStr.getString("des");
        id = jsStr.getString( "id");

        if (operation.equals("ImageUpload")) {
            String image, strSqlData ="";
            image = jsStr.getString( "image");


            if (des.equals("sjryzp")){
                strSqlData ="update social set SocialImage ='"+image+"' where  SocialUserId ="+id;
            }

            if (des.equals("sjsfzzp")){
                strSqlData ="update social set SocialIdCardImage ='"+image+"' where  SocialUserId ="+id;
            }

            if (des.equals("sfywfmtp")){
                strSqlData ="update news set NewsCover ='"+image+"' where  NewsId ="+id;
            }
            if (des.equals("pfxwfmtp")){
                strSqlData ="update popilarizingnews  set Cover ='"+image+"' where  PopularizingNewsId ="+id;
            }
            if (des.equals("pfspfmtp")){
                strSqlData ="update popliarizingvideo set Cover ='"+image+"' where  PopliarizingVideoId ="+id;
            }
            if (des.equals("xxtsfmtp")){
                strSqlData ="update message set messageCover ='"+image+"' where  messageId ="+id;
            }
            if (des.equals("jgxxfmtp")){
                strSqlData ="update department set DepartmentLogo ='"+image+"' where  departmentId ="+id;
            }
            if (des.equals("ryxxfmtp")){
                strSqlData ="update fairworker set Image ='"+image+"' where  FairWorkerId ="+id;
            }
            mapUpload.imageUpload(strSqlData);
        }


        if (operation.equals("PathUpdate")) {
            String datapath, strSqlData="";
            datapath  = jsStr.getString( "datapath");
            if (des.equals("popliarizingvideo")){
                strSqlData ="update popliarizingvideo set Video ='"+datapath+"' where  PopliarizingVideoId ="+id;
            }
            if (des.equals("document")){
                strSqlData ="update document set FilePath ='"+datapath+"' where  docId ="+id;
            }
            if (des.equals("message")){
                strSqlData ="update message set MessageSendAccress ='"+datapath+"' where  MessageId ="+id;
            }
            if (des.equals("template")){
                strSqlData ="update sampletables set templatePath ='"+datapath+"' where  id ="+id;
            }
            if (des.equals("example")){
                strSqlData ="update sampletables set examplePath  ='"+datapath+"' where  id ="+id;
            }
            if (des.equals("dzjz")){
                strSqlData ="update dzjz set accessurl  ='"+datapath+"' where  jzId ="+id;
            }
            if (des.equals("casebase")){
                SimpleDateFormat getdatetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                String createdate = getdatetime.format(new Date());// new Date()为获取当前系统时间
                strSqlData ="update casebase set filepath  ='"+datapath+"',createdate ='"+createdate+"'  where  caseid ="+id;
                String extensionName = datapath.substring(datapath.lastIndexOf("."));
                //System.out.println("extensionName:"+extensionName);
                String strSqlDatatemp;
                if (extensionName.equals(".mp4")){
                    strSqlDatatemp ="update casebase set type =2 where  caseid ="+id;
                    mapSystjxx.objectVoid(strSqlDatatemp);
                }
                if (extensionName.equals(".pdf")){
                    strSqlDatatemp ="update casebase set type =1 where  caseid ="+id;
                    mapSystjxx.objectVoid(strSqlDatatemp);
                }

            }
            mapUpload.PathUpdate(strSqlData);
        }
        return jsReturn;
    }
}
