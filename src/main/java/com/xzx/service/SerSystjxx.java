package com.xzx.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xzx.dao.MapSystjxx;
import com.xzx.model.*;
import io.swagger.models.auth.In;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;
import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SerSystjxx {

    /********************************************
     //    例子
     //    String strSqlData ="select FairWorkerId,WorkerName,Phone,RegionId,RoleId,DepartmentId " +
     //            "from  fairworker where Phone='"+Phone+"' and LoginPass ='"+LoginPass+"'";
     //    List<Map<String, Object>> mapList= mapSystjxx.findobject(strSqlData);
     //    String  fairWorkerId,departmentId;
     //    String workerName="",roleid="";
     //            for(Map<String, Object> maptemp :mapList){
     //        //map 遍历取值
     //        for (String key : maptemp.keySet()){
     //            System.out.println("key:"+key+",value:"+maptemp.get(key) );
     //        }
     //        //map 直接取值
     //        System.out.println("key:FairWorkerId,value:"+maptemp.get("FairWorkerId") );
     //        System.out.println("key:Phone,value:"+maptemp.get("Phone") );
     //        System.out.println("key:DepartmentId,value:"+maptemp.get("DepartmentId") );
     //        System.out.println("key:WorkerName,value:"+maptemp.get("WorkerName") );
     //    }
     * ******************************************/

    @Value("${file.uploadFolder}")
    public String FileUploadPath;

    @Value("${file.DataBaseName}")
    private String DataBaseName;
    @Resource
    private MapSystjxx mapSystjxx;

    @Autowired
    IZcdwService zcdwService;
    @Autowired
    IAuthreginfoService authreginfoService;
    @Autowired
    IRegisterService registerService;
    @Autowired
    IAuthenticService authenticService;
    @Autowired
    IAuthworkerinfoService authworkerinfoService;

    public JSONObject insertZc(String json){
        System.out.println(json);
        JSONObject jsStr = JSONObject.parseObject(json);
        jsStr = JSONObject.parseObject(jsStr.getString("info"));
        JSONObject jsReturn = new JSONObject();
        String AuthDepartId = jsStr.getString("AuthDepartId");
        String SummaryContext = jsStr.getString("SummaryContext");//
        String Context = jsStr.getString("Context");
        String RegionId = jsStr.getString("RegionId");
        String assistanceId = jsStr.getString("assistanceId");
        String assistanceSecondId = jsStr.getString("assistanceSecondId");
        String FairworkerId = jsStr.getString("FairworkerId");
        String UserContext = jsStr.getString("UserContext");
        String sqfay = jsStr.getString("sqfay");
        String OtherContext = jsStr.getString("OtherContext");
        String bsqfay = jsStr.getString("bsqfay");
        String YYSJ = jsStr.getString( "YYSJ");
        String zprId = jsStr.getString( "zprId");
        String zpsj = jsStr.getString( "zpsj");
        String SPSJ = jsStr.getString( "SPSJ");
        String ajje = jsStr.getString( "ajje");
        String slf = jsStr.getString( "slf");
        String cgclf = jsStr.getString( "cgclf");
        String tsclf = jsStr.getString( "tsclf");
        String isonline = jsStr.getString( "isonline");
        SimpleDateFormat getdatetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String createdate = getdatetime.format(new Date());// new Date()为获取当前系统时间
//        String strSqlNextid = "SELECT auto_increment FROM information_schema.tables  " +
//                "WHERE table_name='authentic' and table_schema ='"+DataBaseName+"'";
//        String modelid = mapSystjxx.objectStr(strSqlNextid);//仲裁业务表下一条数据的id
        String jffjxx = jsStr.get("jffjxx")==null?null:jsStr.get("jffjxx").toString();//申请方附件信息
        String yffjxx = jsStr.get("yffjxx")==null?null:jsStr.get("yffjxx").toString();//被申请方附件信息
        System.out.println("//----------------------------------------1. 获取双方单位信息 -------------------------------------------------");
        //----------------------------------------1. 获取双方单位信息 -------------------------------------------------
        String sqfisdw = jsStr.getString("sqfisdw");//判断申请方是否是单位: 0:是单位;  1:是个人
        String bsqfisdw = jsStr.getString("bsqfisdw");//判断被申请方是否是单位: 0:是单位;  1:是个人
        Integer sqfdwid = null;//申请方单位id
        Integer bsqfdwid = null;//被申请方单位id
        Integer sqfinfoid = null;//申请方信息id
        Integer bsqfinfoid = null;//被申请方信息id
        String sqfids = "";//申请方当事人id字符串
        String bsqfids = "";//被申请方当事人id字符串
        if("0".equals(sqfisdw)){//如果是单位, 获取单位信息
            System.out.println("----申请方是单位-----");
            String sqfdwmc = jsStr.getString("sqfdwmc");//申请方单位名称
            String sqffddbr = jsStr.getString("sqffddbr");//申请方法定代表人
            String sqfzw = jsStr.getString("sqfzw");//申请方职务
            String sqfdzxx = jsStr.getString("sqfdzxx");//申请方单位地址信息
            String sqfzcqq = jsStr.getString("sqfzcqq");//申请方仲裁请求
            Zcdw sqfzcdw = new Zcdw();
            sqfzcdw.setDwmc(sqfdwmc);
            sqfzcdw.setFddbr(sqffddbr);
            sqfzcdw.setZw(sqfzw);
            sqfzcdw.setDzxx(sqfdzxx);
            sqfzcdw.setZcqq(sqfzcqq);
            //将申请方单位信息存入数据库  zcdw
            zcdwService.insertZcdwInfo(sqfzcdw);
            sqfdwid = sqfzcdw.getZcdwId();
            System.out.println("申请方单位入库成功sqfdwid--->"+sqfdwid);
        }
        if("0".equals(bsqfisdw)){//如果是单位, 获取单位信息
            System.out.println("----被申请方是单位-----");
            String bsqfdwmc = jsStr.getString("bsqfdwmc");//申请方单位名称
            String bsqffddbr = jsStr.getString("bsqffddbr");//申请方法定代表人
            String bsqfzw = jsStr.getString("bsqfzw");//申请方职务
            String bsqfdzxx = jsStr.getString("bsqfdzxx");//申请方单位地址信息
            String bsqfzcqq = jsStr.getString("bsqfzcqq");//申请方仲裁请求
            Zcdw bsqfzcdw = new Zcdw();
            bsqfzcdw.setDwmc(bsqfdwmc);
            bsqfzcdw.setFddbr(bsqffddbr);
            bsqfzcdw.setZw(bsqfzw);
            bsqfzcdw.setDzxx(bsqfdzxx);
            bsqfzcdw.setZcqq(bsqfzcqq);
            //将被申请方单位信息存入数据库  zcdw
            zcdwService.insertZcdwInfo(bsqfzcdw);
            bsqfdwid = bsqfzcdw.getZcdwId();
            System.out.println("被申请方单位入库成功bsqfdwid--->"+bsqfdwid);
        }
        System.out.println("//----------------------------------------2. 获取双方当事人信息列表 -------------------------------------------------");
        //----------------------------------------2. 获取双方当事人信息列表 -------------------------------------------------
        //申请方
        String jfxmdb = jsStr.getString("jfxmdb");
        String jfsfzhdb = jsStr.getString("jfsfzhdb");
        String jfsjhdb = jsStr.getString("jfsjhdb");
        String jfzzdb = jsStr.getString("jfzzdb");
        String jfczdb = jsStr.getString("jfczdb");
        String jfzpdb = jsStr.getString("jfzpdb");
        String jfzwdb = jsStr.getString("jfzwdb");
        String jfsexdb = jsStr.getString("jfsexdb");
        String jfmzdb = jsStr.getString("jfmzdb");
        String jfcsrqdb = jsStr.getString("jfcsrqdb");
        if (jfxmdb == null) { jfxmdb ="";}
        if (jfsfzhdb == null){ jfsfzhdb ="";}
        if (jfsjhdb == null){ jfsjhdb ="";}
        if (jfczdb == null){ jfczdb ="";}
        if (jfzpdb == null){ jfzpdb ="";}
        if (jfzwdb == null){ jfzwdb ="";}
        String jfiddb ="";
        if (jfsfzhdb != null && !jfsfzhdb.equals("")){//判断申请方身份证是不是空
            String strSql ="select UserId from  register where sfzh ='"+jfsfzhdb+"'";
            List<Map<String, Object>> mapList= mapSystjxx.objectList(strSql);
            if (mapList.size() > 0){
                for(Map<String, Object> maptemp :mapList){
                    System.out.println("修改申请方信息");
                    jfiddb = maptemp.get("UserId").toString();
                    strSql="update register set realname ='"+jfxmdb+"',telephone='" +jfsjhdb+"'";
                    if(jfzzdb!=null&&!jfzzdb.equals("")) {
                        strSql+=",address = '"+jfzzdb+"'";
                    }
                    if(jfczdb!=null&&!jfczdb.equals("")) {
                        strSql+=",nowszd = '"+jfczdb+"'";
                    }
                    if(jfzpdb!=null&&!jfzpdb.equals("")) {
                        strSql+=",image = '"+jfzpdb+"'";
                    }
                    if(jfzwdb!=null&&!jfzwdb.equals("")) {
                        strSql+=",fingers ='"+jfzwdb+"'";
                    }
                    strSql+=" where UserId ='"+jfiddb+"'";
                    mapSystjxx.objectVoid(strSql);
                    sqfids = sqfids + jfiddb +",";
                    System.out.println("修改申请方信息成功jfiddb--->"+jfiddb);
                }
            } else{
                System.out.println("新增register申请方信息");
                Register registersqf = new Register();
                registersqf.setRealName(jfxmdb);
                registersqf.setSFZH(jfsfzhdb);
                registersqf.setTelephone(jfsjhdb);
                registersqf.setAddress(jfzzdb);
                registersqf.setNowSZD(jfczdb);
                registersqf.setImage(jfzpdb);
                registersqf.setFingers(jfzwdb);
                registersqf.setSex(jfsexdb);
                registersqf.setMZ(jfmzdb);
                registersqf.setBirthday(jfcsrqdb);
                registersqf.setLoginState(0);
                registersqf.setReGisterDate(createdate);
                registersqf.setLastLoginTime(createdate);
                registersqf.setIsdb(0);
                registerService.insertInfo(registersqf);
                int sqfstrSqlNextid = registersqf.getUserId();
//                jfiddb = mapSystjxx.objectInt(sqfstrSqlNextid)+"";
                jfiddb = sqfstrSqlNextid+"";
//                mapSystjxx.objectVoid(strSql);
                sqfids = sqfids + jfiddb + ",";
                System.out.println("申请方信息新增成功jfiddb---->"+jfiddb);
            }
        }
        JSONArray sqfjsonArray = jsStr.getJSONArray("sqfinfoList");//获取数组
        String sqfjsonStr = JSONObject.toJSONString(sqfjsonArray);
        List sqflist = JSONObject.parseArray(sqfjsonStr);//申请方当事人list
        System.out.println("申请方当事人list---sqflist:---->"+sqflist);
        if(sqflist != null && sqflist.size()>0){
            for( int i=0; i<sqflist.size(); i++){
                Map sqfmap = (Map) sqflist.get(i);
                String jfxm = sqfmap.get("jfxm")==null?null:sqfmap.get("jfxm").toString();//姓名
                String jfsfzh = sqfmap.get("jfsfzh")==null?null:sqfmap.get("jfsfzh").toString();//身份证
                String jfsjh = sqfmap.get("jfsjh")==null?null:sqfmap.get("jfsjh").toString();//手机号
                String jfzz = sqfmap.get("jfzz")==null?null:sqfmap.get("jfzz").toString();//住址
                String jfcz = sqfmap.get("jfcz")==null?null:sqfmap.get("jfcz").toString();//常住
                String jfsex = sqfmap.get("jfsex")==null?null:sqfmap.get("jfsex").toString();//性别
                String jfmz = sqfmap.get("jfmz")==null?null:sqfmap.get("jfmz").toString();//民族
                String jfcsrq = sqfmap.get("jfcsrq")==null?null:sqfmap.get("jfcsrq").toString();//出生日期
                if (jfxm == null) { jfxm ="";}
                if (jfsjh == null){ jfsjh ="";}
                if (jfzz == null){ jfzz ="";}
                if (jfcz == null){ jfcz ="";}
                String jfid ="";
                if (jfsfzh != null && !jfsfzh.equals("")){//判断申请方身份证是不是空
                    String strSql ="select UserId from  register where sfzh ='"+jfsfzh+"'";
                    List<Map<String, Object>> mapList= mapSystjxx.objectList(strSql);
                    if (mapList.size() > 0){
                        for(Map<String, Object> maptemp :mapList){
                            jfid = maptemp.get("UserId").toString();
                            strSql="update register set realname ='"+jfxm+"',telephone='" +jfsjh+"'";
                            if(jfzz!=null&&!jfzz.equals("")) {
                                strSql+=",address = '"+jfzz+"'";
                            }
                            if(jfcz!=null&&!jfcz.equals("")) {
                                strSql+=",nowszd = '"+jfcz+"'";
                            }
                            strSql+=" where UserId ='"+jfid+"'";
                            mapSystjxx.objectVoid(strSql);
                            sqfids = sqfids + jfid +",";
                        }
                    } else{
                        Register registersqf = new Register();
                        registersqf.setRealName(jfxm);
                        registersqf.setSFZH(jfsfzh);
                        registersqf.setTelephone(jfsjh);
                        registersqf.setAddress(jfzz);
                        registersqf.setNowSZD(jfcz);
                        registersqf.setSex(jfsex);
                        registersqf.setMZ(jfmz);
                        registersqf.setBirthday(jfcsrq);
                        registersqf.setLoginState(0);
                        registersqf.setReGisterDate(createdate);
                        registersqf.setLastLoginTime(createdate);
                        registersqf.setIsdb(1);
                        registerService.insertInfoOther(registersqf);
                        int sqfstrSqlNextid = registersqf.getUserId();
                                jfid = sqfstrSqlNextid+"";
                        sqfids = sqfids + jfid + ",";
                    }
                }
            }
        }
        if(!"".equals(sqfids) && sqfids.endsWith(",")){
            sqfids = sqfids.substring(0,sqfids.length()-1);
        }
        System.out.println("sqfids------>"+sqfids);
        //被申请方
        String yfxmdb = jsStr.getString("yfxmdb");
        String yfsfzhdb = jsStr.getString("yfsfzhdb");
        String yfsjhdb = jsStr.getString("yfsjhdb");
        String yfzzdb = jsStr.getString("yfzzdb");
        String yfczdb = jsStr.getString("yfczdb");
        String yfzpdb = jsStr.getString("yfzpdb");
        String yfzwdb = jsStr.getString("yfzwdb");
        String yfsexdb = jsStr.getString("yfsexdb");
        String yfmzdb = jsStr.getString("yfmzdb");
        String yfcsrqdb = jsStr.getString("yfcsrqdb");
        String yfiddb = "";
        if (yfxmdb == null){ yfxmdb ="";}
        if (yfsjhdb == null){ yfsjhdb ="";}
        if (yfzzdb == null){ yfzzdb ="";}
        if (yfczdb == null){ yfczdb ="";}
        if (yfzpdb == null){ yfzpdb ="";}
        if (yfzwdb == null){ yfzwdb ="";}
        if (yfsfzhdb != null && !yfsfzhdb.equals("")){
            String strSql ="select UserId from  register where sfzh ='"+yfsfzhdb+"' ";
            List<Map<String, Object>> mapList= mapSystjxx.objectList(strSql);
            if (mapList.size() > 0){
                System.out.println("修改被申请方信息");
                for(Map<String, Object> maptemp :mapList){
                    yfiddb = maptemp.get("UserId").toString();
                    strSql ="update register set realname ='"+yfxmdb+"',telephone='" +
                            yfsjhdb+"',address ='"+yfzzdb+"',nowszd = '"+yfczdb+"',image ='"+yfzpdb+"',fingers ='"+yfzwdb+"'  where UserId ="+yfiddb;
                    //System.out.println("strSql:"+strSql);
                    mapSystjxx.objectVoid(strSql);
                    bsqfids = bsqfids + yfiddb +",";
                    System.out.println("被申请方信息修改成功yfiddb--->"+yfiddb);
                }
            } else{
                System.out.println("新增一条被申请方信息");
                Register registersqf = new Register();
                registersqf.setRealName(yfxmdb);
                registersqf.setSFZH(yfsfzhdb);
                registersqf.setTelephone(yfsjhdb);
                registersqf.setAddress(yfzzdb);
                registersqf.setNowSZD(yfczdb);
                registersqf.setImage(yfzpdb);
                registersqf.setFingers(yfzwdb);
                registersqf.setSex(yfsexdb);
                registersqf.setMZ(yfmzdb);
                registersqf.setBirthday(yfcsrqdb);
                registersqf.setLoginState(0);
                registersqf.setReGisterDate(createdate);
                registersqf.setLastLoginTime(createdate);
                registersqf.setIsdb(0);
                registerService.insertInfo(registersqf);
                int sqfstrSqlNextid = registersqf.getUserId();
                yfiddb = sqfstrSqlNextid+"";
                bsqfids = bsqfids + yfiddb +",";
                System.out.println("新增register被申请方成功yfiddb--->"+yfiddb);
            }
        }
        JSONArray bsqfjsonArray = jsStr.getJSONArray("bsqfinfoList");//获取数组
        String bsqfjsonStr = JSONObject.toJSONString(bsqfjsonArray);
        List bsqflist = JSONObject.parseArray(bsqfjsonStr);//被申请方当事人list
        System.out.println("bsqflist----->"+bsqflist);
        if(bsqflist != null && bsqflist.size()>0){
            for( int i=0; i<bsqflist.size(); i++){
                Map bsqfmap = (Map) bsqflist.get(i);
                String yfxm = bsqfmap.get("yfxm")==null?null:bsqfmap.get("yfxm").toString();//姓名
                String yfsfzh = bsqfmap.get("yfsfzh")==null?null:bsqfmap.get("yfsfzh").toString();//身份证
                String yfsjh = bsqfmap.get("yfsjh")==null?null:bsqfmap.get("yfsjh").toString();//手机号
                String yfzz = bsqfmap.get("yfzz")==null?null:bsqfmap.get("yfzz").toString();//住址
                String yfcz = bsqfmap.get("yfcz")==null?null:bsqfmap.get("yfcz").toString();//常住
                String yfsex = bsqfmap.get("yfsex")==null?null:bsqfmap.get("yfsex").toString();//性别
                String yfmz = bsqfmap.get("yfmz")==null?null:bsqfmap.get("yfmz").toString();//民族
                String yfcsrq = bsqfmap.get("yfcsrq")==null?null:bsqfmap.get("yfcsrq").toString();//出生日期
                String yfid = "";
                if (yfxm == null){ yfxm ="";}
                if (yfsjh == null){ yfsjh ="";}
                if (yfzz == null){ yfzz ="";}
                if (yfcz == null){ yfcz ="";}
                if (yfsfzh != null && !yfsfzh.equals("")){
                    String strSql ="select UserId from  register where sfzh ='"+yfsfzh+"' ";
                    List<Map<String, Object>> mapList= mapSystjxx.objectList(strSql);
                    if (mapList.size() > 0){
                        for(Map<String, Object> maptemp :mapList){
                            yfid = maptemp.get("UserId").toString();
                            strSql ="update register set realname ='"+yfxm+"',telephone='" +
                                    yfsjh+"',address ='"+yfzz+"',nowszd = '"+yfcz+"'  where UserId ="+yfid;
                            //System.out.println("strSql:"+strSql);
                            mapSystjxx.objectVoid(strSql);
                            bsqfids = bsqfids + yfid +",";
                        }
                    } else{
                        Register registersqf = new Register();
                        registersqf.setRealName(yfxm);
                        registersqf.setSFZH(yfsfzh);
                        registersqf.setTelephone(yfsjh);
                        registersqf.setAddress(yfzz);
                        registersqf.setNowSZD(yfcz);
                        registersqf.setSex(yfsex);
                        registersqf.setMZ(yfmz);
                        registersqf.setBirthday(yfcsrq);
                        registersqf.setLoginState(0);
                        registersqf.setReGisterDate(createdate);
                        registersqf.setLastLoginTime(createdate);
                        registersqf.setIsdb(1);
                        registerService.insertInfoOther(registersqf);
                        int bsqfstrSqlNextid = registersqf.getUserId();
                        yfid = bsqfstrSqlNextid+"";
                        bsqfids = bsqfids + yfid +",";
                    }
                }
            }
        }
        if(!"".equals(bsqfids) && bsqfids.endsWith(",")){
            bsqfids = bsqfids.substring(0,bsqfids.length()-1);
        }
        System.out.println("//----------------------------------------3. 将双方信息存入中间表(authreginfo) -------------------------------------------------");
        //----------------------------------------3. 将双方信息存入中间表(authreginfo) -------------------------------------------------
        if(jfiddb!=null && !"".equals(jfiddb)){
            Authreginfo sqfinfo = new Authreginfo();
            sqfinfo.setDwId(sqfdwid);//单位id
            if(sqfisdw!= null && !"".equals(sqfisdw)){
                sqfinfo.setIsDw(Integer.parseInt(sqfisdw));//是否是单位
            }else {
                sqfinfo.setIsDw(1);
            }
//        sqfinfo.setSfid(modelid);//业务id
            sqfinfo.setRegisterId(sqfids);//用户ids
            sqfinfo.setDbId(Integer.parseInt(jfiddb));//代表id
            authreginfoService.insertinfo(sqfinfo);//新增的申请方id
            sqfinfoid = sqfinfo.getAuthregId();
            System.out.println("新增申请单中间表成功-->"+sqfinfo);
        }
        if(yfiddb != null && !"".equals(yfiddb)){
            Authreginfo bsqfinfo = new Authreginfo();
            bsqfinfo.setDwId(bsqfdwid);//单位id
            if(bsqfisdw != null && !"".equals(bsqfisdw)){
                bsqfinfo.setIsDw(Integer.parseInt(bsqfisdw));//是否是单位
            }else{
                bsqfinfo.setIsDw(1);
            }
//        bsqfinfo.setSfid(modelid);//业务id
            bsqfinfo.setRegisterId(bsqfids);//用户ids
            bsqfinfo.setDbId(Integer.parseInt(yfiddb));//代表id
            authreginfoService.insertinfo(bsqfinfo);//新增的被申请方id
            bsqfinfoid = bsqfinfo.getAuthregId();
            System.out.println("新增被申请单中间表成功-->"+bsqfinfo);
        }
        //----------------------------------------4. 将双方信息存入相关表(业务:Authentic; 申办进度:speed; 进度历史:speedhistory  -------------------------------------------------
        System.out.println("4.存入业务表");
        String strtemp1;//存Authentic业务表信息(字段)
        Authentic authentic = new Authentic();
        String strtemp2;//存Authentic业务表信息(值)
        if (isonline != null && !isonline.equals("")){
            authentic.setIsOnline(Integer.parseInt(isonline));
//            strtemp1="insert into Authentic ( IsOnline,createdate,";
//            strtemp2 ="values("+isonline+",'"+createdate+"',";
        } else{
            authentic.setIsOnline(1);
//            strtemp1="insert Authentic ( IsOnline,createdate,";
//            strtemp2 ="values(1,'"+createdate+"',";
        }
        authentic.setCreateDate(createdate);
        String strtemp3="insert speed (CreateDate,ModelType,State,";//存speed申办进度表信息(字段)
        String strtemp4 ="values('"+createdate+"',1,0,";//存speed申办进度表信息(值)
        String strtemp5="insert speedhistory (CreateDate,ModelType,State,";//存speedhistory申办进度历史表信息(字段)
        String strtemp6 ="values('"+createdate+"',1,0,";//存speedhistory申办进度历史表信息(值)
        if (AuthDepartId !=null && !AuthDepartId.equals("")){
//            strtemp1 += "AuthDepartId,";
//            strtemp2 += AuthDepartId+",";
            authentic.setAuthDepartId(Integer.parseInt(AuthDepartId));
        }
        if (sqfinfoid !=null && !sqfinfoid.equals("") ){
//            strtemp1 += "sqfinfoId,";
//            strtemp2 += sqfinfoid+",";
            authentic.setSqfinfoId(sqfinfoid);
            strtemp3 += "sqfinfoId,";
            strtemp4 += sqfinfoid+",";
            strtemp5 += "sqfinfoId,";
            strtemp6 += sqfinfoid+",";
        }
        if (bsqfids !=null && !bsqfids.equals("")){
//            strtemp1 += "bsqfinfoId,";
//            strtemp2 += bsqfinfoid+",";
            authentic.setBsqfinfoId(bsqfinfoid);
            strtemp3 += "bsqfinfoId,";
            strtemp4 += bsqfinfoid+",";
            strtemp5 += "bsqfinfoId,";
            strtemp6 += bsqfinfoid+",";
        }
        if (SummaryContext !=null && !SummaryContext.equals("")){
//            strtemp1 += "SummaryContext,";
//            strtemp2 += "'"+SummaryContext+"',";
            authentic.setSummaryContext(SummaryContext);
        }
        if (Context !=null && !Context.equals("")){
//            strtemp1 += "Context,";
//            strtemp2 += "'"+Context+"',";
            authentic.setContext(Context);
        }
        if (RegionId !=null && !RegionId.equals("")){
//            strtemp1 += "RegionId,";
//            strtemp2 += RegionId+",";
            authentic.setRegionId(Integer.parseInt(RegionId));
        }
        if (assistanceId !=null && !assistanceId.equals("")){
//            strtemp1 += "assistanceId,";
//            strtemp2 += assistanceId+",";
            authentic.setAssistanceId(Integer.parseInt(assistanceId));
        }
        if (assistanceSecondId !=null && !assistanceSecondId.equals("")){
//            strtemp1 += "assistanceSecondId,";
//            strtemp2 += assistanceSecondId+",";
            authentic.setAssistanceSecondId(Integer.parseInt(assistanceSecondId));
        }
        if (FairworkerId !=null && !FairworkerId.equals("")){
            Authworkerinfo authworkerinfo = new Authworkerinfo();
            authworkerinfo.setWorkerids(FairworkerId);
            authworkerinfoService.insertAuthWorkerInfo(authworkerinfo);
            int workerId = authworkerinfo.getId();
//            strtemp1 += "FairworkerId,";
//            strtemp2 += FairworkerId+",";
            authentic.setFairworkerId(String.valueOf(workerId));
            strtemp3 += "fairworkerId,";
            strtemp4 += workerId+",";
            strtemp5 += "fairworkerId,";
            strtemp6 += workerId+",";
        }
        if(jfiddb != null && !"".equals(jfiddb)){
            authentic.setUserId(Integer.parseInt(jfiddb));
            strtemp3 += "registerId,";
            strtemp4 += jfiddb+",";
            strtemp5 += "registerId,";
            strtemp6 += jfiddb+",";
        }
        if (yfiddb !=null && !yfiddb.equals("")){
            authentic.setOtherPartyId(Integer.parseInt(yfiddb));
            strtemp3 += "partyId,";
            strtemp4 += yfiddb+",";
            strtemp5 += "partyId,";
            strtemp6 += yfiddb+",";
        }
        if (UserContext !=null){
//            strtemp1 += "UserContext,";
//            strtemp2 += "'"+UserContext+"',";
            authentic.setUserContext(UserContext);
        }
        if (OtherContext !=null){
//            strtemp1 += "OtherContext,";
//            strtemp2 += "'"+OtherContext+"',";
            authentic.setOtherContext(UserContext);
        }
        if (sqfay !=null){
//            strtemp1 += "sqfay,";
//            strtemp2 += "'"+sqfay+"',";
            authentic.setSqfay(sqfay);
        }
        if (bsqfay !=null){
//            strtemp1 += "bsqfay,";
//            strtemp2 += "'"+bsqfay+"',";
            authentic.setBsqfay(bsqfay);
        }
        if (YYSJ !=null && !YYSJ.equals("")){
//            strtemp1 += "YYSJ,";
//            strtemp2 += "'"+YYSJ+"',";
            authentic.setYYSJ(YYSJ);
        }
        if (zprId !=null  && !zprId.equals("")){
//            strtemp1 += "zprId,";
//            strtemp2 += zprId+",";
            authentic.setZprId(zprId);
        }
        if (zpsj !=null && !zpsj.equals("")){
//            strtemp1 += "zpsj,";
//            strtemp2 += "'"+zpsj+"',";
            authentic.setZpsj(zpsj);
        }

        if (slf !=null && !slf.equals("")){
//            strtemp1 += "zpsj,";
//            strtemp2 += "'"+zpsj+"',";
            authentic.setSlf(slf);
        }

        if (cgclf !=null && !cgclf.equals("")){
//            strtemp1 += "zpsj,";
//            strtemp2 += "'"+zpsj+"',";
            authentic.setCgclf(cgclf);
        }

        if (tsclf !=null && !tsclf.equals("")){
//            strtemp1 += "zpsj,";
//            strtemp2 += "'"+zpsj+"',";
            authentic.setTsclf(tsclf);
        }

        if (ajje !=null && !ajje.equals("")){
//            strtemp1 += "zpsj,";
//            strtemp2 += "'"+zpsj+"',";
            authentic.setAjje(ajje);
        }
        if (SPSJ !=null && !SPSJ.equals("")){
//            strtemp1 += "SPSJ,";
//            strtemp2 += "'"+SPSJ+"',";
            authentic.setSPSJ(SPSJ);
        }
        System.out.println( strtemp3+strtemp4);
        System.out.println( strtemp5+strtemp6);
        System.out.println( authentic);
        authenticService.insertAuthInfo(authentic);
        int modelid = authentic.getAuthenticId();
        System.out.println("modelid----------------->"+modelid);
        strtemp3 += "ModelId,";
        strtemp4 += modelid+",";
        strtemp5 += "ModelId,";
        strtemp6 += modelid+",";
//        strtemp1 =  strtemp1.substring(0, strtemp1.length()-1) +" )  ";
//        strtemp2 =  strtemp2.substring(0, strtemp2.length()-1)+" )";
        strtemp3 =  strtemp3.substring(0, strtemp3.length()-1) +" )  ";
        strtemp4 =  strtemp4.substring(0, strtemp4.length()-1)+" )";
        strtemp5 =  strtemp5.substring(0, strtemp5.length()-1) +" )  ";
        strtemp6 =  strtemp6.substring(0, strtemp6.length()-1)+" )";
        System.out.println("//----------------------------------------4. 将双方附件信息存入附件表(access) -------------------------------------------------");
        //----------------------------------------4. 将双方附件信息存入附件表(access) -------------------------------------------------
        if(jffjxx!=null && !"".equals(jffjxx)){
            JSONArray jarr1 = JSONArray.parseArray(jffjxx);
            for (Object o : jarr1) {
                JSONObject fj = (JSONObject) o;
                String fjmc = fj.getString("fjmc");
                String fjtp = fj.getString("fjtp");
                //1 人民仲裁
                String strSqlData = "insert into access (accessinfo,authreginfoId,modeltype,modelid,accessname,accesstype) " +
                        "values ('" + fjtp + "'," + sqfinfoid + ",1," + modelid + ",'" + fjmc + "',0)";
                mapSystjxx.objectVoid(strSqlData);
            }
        }
        if (yffjxx !=null && !"".equals(yffjxx)){
            JSONArray jarr2 = JSONArray.parseArray(yffjxx);
            for (Object o : jarr2) {
                JSONObject fj = (JSONObject) o;
                String fjmc = fj.getString("fjmc");
                String fjtp = fj.getString("fjtp");
                //1 人民仲裁
                String strSqlData = "insert into access (accessinfo,authreginfoId,modeltype,modelid,accessname,accesstype) " +
                        "values ('" + fjtp + "'," + bsqfinfoid + ",1," + modelid + ",'" + fjmc + "',0)";
                //System.out.println("strSqlData :"+strSqlData);
                mapSystjxx.objectVoid(strSqlData);
            }
        }
        System.out.println("新增附件成功");
        String strtemp7 ="insert into dzjz (sczt,jztyle,ajId,type) values(0,0,"+modelid+",1)";
        String strtemp8 ="insert into dzjz (sczt,jztyle,ajId,type) values(0,1,"+modelid+",1)";
        String strtemp9 ="insert into dzjz (sczt,jztyle,ajId,type) values(0,2,"+modelid+",1)";
        jsReturn.put("info", "1");
//        mapSystjxx.objectVoid( strtemp1+strtemp2);
        mapSystjxx.objectVoid( strtemp3+strtemp4);
        mapSystjxx.objectVoid( strtemp5+strtemp6);
        mapSystjxx.objectVoid( strtemp7);
        System.out.println("111成功");
        mapSystjxx.objectVoid( strtemp8);
        System.out.println("222成功");
        mapSystjxx.objectVoid( strtemp9);
        System.out.println("333成功");



        jsReturn.put("info", "0");
        jsReturn.put("AuthenticId", modelid);
        System.out.println("modelid----->"+modelid);
        return jsReturn;
    }

    public JSONObject findidAuthentic(String json){
        System.out.println(json);
        JSONObject jsStr = JSONObject.parseObject(json);
        jsStr = JSONObject.parseObject(jsStr.getString("info"));
        JSONObject jsReturn = new JSONObject();
        String AuthenticId = jsStr.getString("AuthenticId");
        //属性包括读卡（读取身份证）、申请方姓名（文本，可编辑）、申请方身份证号（文本，可编辑）、申请方手机号（文本，做手机验证码校验）、
        //申请方指纹、申请方照片、被申请方姓名（文本，可编辑）、被申请方身份证号（文本、可编辑）、被申请方手机号（文本，做手机验证码校验）、
        //被申请方指纹、被申请方照片、案件类别、双方案情简介（语音转文字）、案件要件（根据案件类别显示应该上传的要件名称及个数）、预约时间。
        String strSqlpublic = "from Authentic a  " +
                "left join (select reg.*, authreg.* from register reg, authreginfo authreg where reg.UserId = authreg.dbId) c on a.sqfinfoId=c.authregId  " +
                "left join (select reg.*, authreg.* from register reg, authreginfo authreg where reg.UserId = authreg.dbId) d on a.bsqfinfoId=d.authregId  " +
                "left join zcdw e on c.dwId=e.zcdwId  " +
                "left join zcdw f on d.dwId=f.zcdwId ";
        String strSqlData="select a.AuthenticId, a.assistanceId,a.AuthDepartId ,a.assistanceSecondId,c.authregId sqfId,d.authregId bsqfId, c.UserId jfiddb, d.UserId yfiddb,  " +
                "       c.RealName as jfxm,c.sfzh as jfsfzh,c.address as jfzz,c.nowszd as jfcz,c.Telephone as jfsjh,CONVERT (c.Image USING utf8) as jfzp,CONVERT (c.fingers USING utf8) as jfzw,a.UserContext,a.sqfay,  " +
                "       d.RealName as yfxm,d.sfzh as yfsfzh,d.address as yfzz,d.nowszd as yfcz,d.Telephone as yfsjh,CONVERT (d.Image USING utf8) as yfzp,CONVERT (d.fingers USING utf8) as yfzw,a.OtherContext,a.bsqfay,  " +
                "       date_format(a.yysj, '%Y-%m-%d %T')  as yysj,a.UserId,a.OtherPartyId ,c.authregId sqfids,d.authregId bsqfids,e.dwmc sqfdwmc,e.dzxx sqfdzxx,e.fddbr sqffddbr, e.zcqq sqfzcqq,f.dwmc bsqfdwmc,f.dzxx bsqfdzxx,f.fddbr bsqffddbr, f.zcqq bsqfzcqq  ";
        String StrCondition=" and a.AuthenticId ="+AuthenticId;
        strSqlData = strSqlData + strSqlpublic + StrCondition ;
        Map<String, Object> mapOne = mapSystjxx.objectOne(strSqlData);
        String jfid = mapOne.get("sqfId").toString();
        String yfid = mapOne.get("bsqfId").toString();
        String jfids = mapOne.get("sqfids").toString();
        String yfids = mapOne.get("bsqfids").toString();
        String strSqlData1 = "select   CONVERT (AccessInfo USING utf8) as fjtp,accessname as fjmc from access where authreginfoId ="+jfid+" and  modelid ="+AuthenticId;
        String strSqlData3 = "select   CONVERT (AccessInfo USING utf8) as fjtp,accessname as fjmc from access where authreginfoId ="+yfid+" and  modelid ="+AuthenticId;
        List<Map<String, Object>> mapListjf = mapSystjxx.objectList(strSqlData1);
        List<Map<String, Object>> mapListyf = mapSystjxx.objectList(strSqlData3);
        List<Map<String, Object>>  jflists = new ArrayList<>();
        List<Map<String, Object>>  yflists = new ArrayList<>();
        if(jfids!= null && !"".equals(jfids)){
            if(jfids.contains(jfid+",")){
                jfids = jfids.replace(jfid+",","");
            }
            if(jfids.contains(jfid)){
                jfids = jfids.replace(jfid,"");
            }
        }
        if(jfids!= null && !"".equals(jfids)){
            String jfsql = "select * from register where UserId in ("+jfids+")";
            jflists = mapSystjxx.objectList(jfsql);
        }
        if(yfids!= null && !"".equals(yfids)){
            if(yfids.contains(yfid+",")){
                yfids = yfids.replace(yfid+",","");
            }
            if(yfids.contains(yfid)){
                yfids = yfids.replace(yfid,"");
            }
        }
        if(yfids!= null && !"".equals(yfids)){
            String yfsql = "select * from register where UserId in ("+yfids+")";
            yflists = mapSystjxx.objectList(yfsql);
        }

        String dzjz = jsStr.getString("dzjz");
        //0 仲裁书  1 司法确认书 2 卷宗
        if (dzjz != null){
            String strSqlData2="select a.jzId,a.bh,date_format(a.cjsj, '%Y-%m-%d %T') as cjsj,b.WorkerName as cjr, a.accessurl, a.jztyle," +
                    "case when a.sczt = 0 then '否' when a.sczt = 1 then '是' when a.sczt = 2 then '无' end  as  sczt, " +
                    "case when a.jztyle = 0 then '仲裁书' when a.jztyle = 1 then '司法确认书' when a.jztyle = 2 then '卷宗' end  as  jztylemc " +
                    "from dzjz a left join fairworker b  on a.cjr = b.FairWorkerId " +
                    "where a.ajId ="+AuthenticId+" and  a.type = 1 ";
            //System.out.println(strSqlData2);
            List<Map<String, Object>>  mapList1 = mapSystjxx.objectList(strSqlData2);
            jsReturn.put("dzjz", mapList1);
        }

        jsReturn.put("info", mapOne);
        jsReturn.put("jffjxx", mapListjf);
        jsReturn.put("yffjxx", mapListyf);
        jsReturn.put("jflists", jflists);
        jsReturn.put("yflists", yflists);
        return jsReturn;
    }

    public JSONObject updateAuthentic(String json){
        System.out.println(json);
        JSONObject jsStr = JSONObject.parseObject(json);
        jsStr = JSONObject.parseObject(jsStr.getString("info"));
        JSONObject jsReturn = new JSONObject();
        String AuthenticId = jsStr.getString("AuthenticId");//业务主键id
        String AuthDepartId = jsStr.getString("AuthDepartId");
        String SummaryContext = jsStr.getString("SummaryContext");//
        String Context = jsStr.getString("Context");
        String RegionId = jsStr.getString("RegionId");
        String assistanceId = jsStr.getString("assistanceId");
        String assistanceSecondId = jsStr.getString("assistanceSecondId");
        String FairworkerId = jsStr.getString("FairworkerId");
        String UserContext = jsStr.getString("UserContext");
        String sqfay = jsStr.getString("sqfay");
        String OtherContext = jsStr.getString("OtherContext");
        String bsqfay = jsStr.getString("bsqfay");
        String YYSJ = jsStr.getString( "YYSJ");
        String zprId = jsStr.getString( "zprId");
        String zpsj = jsStr.getString( "zpsj");
        String SPSJ = jsStr.getString( "SPSJ");
        String isonline = jsStr.getString( "isonline");
        SimpleDateFormat getdatetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String createdate = getdatetime.format(new Date());// new Date()为获取当前系统时间
        String jffjxx = jsStr.get("jffjxx")==null?null:jsStr.get("jffjxx").toString();//申请方附件信息
        String yffjxx = jsStr.get("yffjxx")==null?null:jsStr.get("yffjxx").toString();//被申请方附件信息
        Integer sqfdwid = null;//申请方单位id
        Integer bsqfdwid = null;//被申请方单位id
        Integer sqfinfoid = null;//申请方信息id
        Integer bsqfinfoid = null;//被申请方信息id
        String sqfids = "";//申请方当事人id字符串
        String bsqfids = "";//被申请方当事人id字符串
        Integer jfiddb = null;//申请方代表idregister
        Integer yfiddb  = null;//被申请方代表idregister
        Authentic authentic = authenticService.selectById(AuthenticId);
        if(authentic != null){
            sqfinfoid = authentic.getSqfinfoId()==null?null:authentic.getSqfinfoId();
            bsqfinfoid = authentic.getBsqfinfoId()==null?null:authentic.getBsqfinfoId();
        }
        Authreginfo sqfauthreginfo = new Authreginfo();
        Authreginfo bsqfauthreginfo = new Authreginfo();
        if(sqfinfoid!= null){
            sqfauthreginfo = authreginfoService.selectById(sqfinfoid);
        }
        if(bsqfinfoid!=null){
            bsqfauthreginfo = authreginfoService.selectById(bsqfinfoid);
        }
        if(sqfauthreginfo!= null){
            sqfdwid = sqfauthreginfo.getDwId()==null?null:sqfauthreginfo.getDwId();
        }
        if(bsqfauthreginfo!=null){
            bsqfdwid = sqfauthreginfo.getDwId()==null?null:sqfauthreginfo.getDwId();
        }
        System.out.println("//----------------------------------------1. 获取双方单位信息 -------------------------------------------------");
        //----------------------------------------1. 获取双方单位信息 -------------------------------------------------
        String sqfisdw = jsStr.getString("sqfisdw");//判断申请方是否是单位: 0:是单位;  1:是个人
        String bsqfisdw = jsStr.getString("bsqfisdw");//判断被申请方是否是单位: 0:是单位;  1:是个人
        if("0".equals(sqfisdw) && sqfdwid!=null){//如果是单位, 获取单位信息
            System.out.println("----申请方是单位-----");
            String sqfdwmc = jsStr.getString("sqfdwmc");//申请方单位名称
            String sqffddbr = jsStr.getString("sqffddbr");//申请方法定代表人
            String sqfzw = jsStr.getString("sqfzw");//申请方职务
            String sqfdzxx = jsStr.getString("sqfdzxx");//申请方单位地址信息
            String sqfzcqq = jsStr.getString("sqfzcqq");//申请方仲裁请求
            Zcdw sqfzcdw = new Zcdw();
            sqfzcdw.setZcdwId(sqfdwid);
            sqfzcdw.setDwmc(sqfdwmc);
            sqfzcdw.setFddbr(sqffddbr);
            sqfzcdw.setZw(sqfzw);
            sqfzcdw.setDzxx(sqfdzxx);
            sqfzcdw.setZcqq(sqfzcqq);
            //将申请方单位信息存入数据库  zcdw
            zcdwService.updateById(sqfzcdw);
            System.out.println("申请方单位入库成功sqfdwid--->"+sqfdwid);
        }else{
            if(sqfdwid!= null){
                zcdwService.deleteById(sqfdwid);
            }
        }
        if("0".equals(bsqfisdw)){//如果是单位, 获取单位信息
            System.out.println("----被申请方是单位-----");
            String bsqfdwmc = jsStr.getString("bsqfdwmc");//申请方单位名称
            String bsqffddbr = jsStr.getString("bsqffddbr");//申请方法定代表人
            String bsqfzw = jsStr.getString("bsqfzw");//申请方职务
            String bsqfdzxx = jsStr.getString("bsqfdzxx");//申请方单位地址信息
            String bsqfzcqq = jsStr.getString("bsqfzcqq");//申请方仲裁请求
            Zcdw bsqfzcdw = new Zcdw();
            bsqfzcdw.setZcdwId(bsqfdwid);
            bsqfzcdw.setDwmc(bsqfdwmc);
            bsqfzcdw.setFddbr(bsqffddbr);
            bsqfzcdw.setZw(bsqfzw);
            bsqfzcdw.setDzxx(bsqfdzxx);
            bsqfzcdw.setZcqq(bsqfzcqq);
            //将被申请方单位信息存入数据库  zcdw
            zcdwService.updateById(bsqfzcdw);
            System.out.println("被申请方单位入库成功bsqfdwid--->"+bsqfdwid);
        }else{
            if(bsqfdwid!= null){
                zcdwService.deleteById(bsqfdwid);
            }
        }
        System.out.println("//----------------------------------------2. 获取双方当事人信息列表 -------------------------------------------------");
        //----------------------------------------2. 获取双方当事人信息列表 -------------------------------------------------
        if(sqfauthreginfo !=null){
            jfiddb = sqfauthreginfo.getDbId()==null?null:sqfauthreginfo.getDbId();
            String jfxmdb = jsStr.getString("jfxmdb");
            String jfsfzhdb = jsStr.getString("jfsfzhdb");
            String jfsjhdb = jsStr.getString("jfsjhdb");
            String jfzzdb = jsStr.getString("jfzzdb");
            String jfczdb = jsStr.getString("jfczdb");
            String jfzpdb = jsStr.getString("jfzpdb");
            String jfzwdb = jsStr.getString("jfzwdb");
            String jfsexdb = jsStr.getString("jfsexdb");
            String jfmzdb = jsStr.getString("jfmzdb");
            String jfcsrqdb = jsStr.getString("jfcsrqdb");
            if (jfxmdb == null) { jfxmdb ="";}
            if (jfsfzhdb == null){ jfsfzhdb ="";}
            if (jfsjhdb == null){ jfsjhdb ="";}
            if (jfczdb == null){ jfczdb ="";}
            if (jfzpdb == null){ jfzpdb ="";}
            if (jfzwdb == null){ jfzwdb ="";}
            System.out.println("新增register申请方信息");
            Register registersqf = new Register();
            registersqf.setRealName(jfxmdb);
            registersqf.setSFZH(jfsfzhdb);
            registersqf.setTelephone(jfsjhdb);
            registersqf.setAddress(jfzzdb);
            registersqf.setNowSZD(jfczdb);
            registersqf.setImage(jfzpdb);
            registersqf.setFingers(jfzwdb);
            registersqf.setSex(jfsexdb);
            registersqf.setMZ(jfmzdb);
            registersqf.setBirthday(jfcsrqdb);
            registersqf.setLoginState(0);
            registersqf.setReGisterDate(createdate);
            registersqf.setLastLoginTime(createdate);
            registersqf.setIsdb(0);
            if(jfiddb != null){
                //申请方
                registersqf.setUserId(jfiddb);
                registerService.updateById(registersqf);
            }else{
                //申请方
                System.out.println("新增register申请方信息");
                registerService.insertInfo(registersqf);
                jfiddb = registersqf.getUserId();
            }
            sqfids = sqfids + jfiddb + ",";
            System.out.println("申请方信息新增成功jfiddb---->"+jfiddb);
        }
        JSONArray sqfjsonArray = jsStr.getJSONArray("sqfinfoList");//获取数组
        String sqfjsonStr = JSONObject.toJSONString(sqfjsonArray);
        List sqflist = JSONObject.parseArray(sqfjsonStr);//申请方当事人list
        System.out.println("申请方当事人list---sqflist:---->"+sqflist);
        if(sqflist != null && sqflist.size()>0){
            for( int i=0; i<sqflist.size(); i++){
                Map sqfmap = (Map) sqflist.get(i);
                String jfid = sqfmap.get("jfid")==null?null:sqfmap.get("jfid").toString();//姓名
                String jfxm = sqfmap.get("jfxm")==null?null:sqfmap.get("jfxm").toString();//姓名
                String jfsfzh = sqfmap.get("jfsfzh")==null?null:sqfmap.get("jfsfzh").toString();//身份证
                String jfsjh = sqfmap.get("jfsjh")==null?null:sqfmap.get("jfsjh").toString();//手机号
                String jfzz = sqfmap.get("jfzz")==null?null:sqfmap.get("jfzz").toString();//住址
                String jfcz = sqfmap.get("jfcz")==null?null:sqfmap.get("jfcz").toString();//常住
                String jfsex = sqfmap.get("jfsex")==null?null:sqfmap.get("jfsex").toString();//性别
                String jfmz = sqfmap.get("jfmz")==null?null:sqfmap.get("jfmz").toString();//民族
                String jfcsrq = sqfmap.get("jfcsrq")==null?null:sqfmap.get("jfcsrq").toString();//出生日期
                if (jfxm == null) { jfxm ="";}
                if (jfsjh == null){ jfsjh ="";}
                if (jfzz == null){ jfzz ="";}
                if (jfcz == null){ jfcz ="";}
                Register registersqf = new Register();
                registersqf.setRealName(jfxm);
                registersqf.setSFZH(jfsfzh);
                registersqf.setTelephone(jfsjh);
                registersqf.setAddress(jfzz);
                registersqf.setNowSZD(jfcz);
                registersqf.setSex(jfsex);
                registersqf.setMZ(jfmz);
                registersqf.setBirthday(jfcsrq);
                registersqf.setLoginState(0);
                registersqf.setReGisterDate(createdate);
                registersqf.setLastLoginTime(createdate);
                registersqf.setIsdb(1);
                if(jfid!=null && !"".equals(jfid)){
                    registersqf.setUserId(Integer.parseInt(jfid));
                    registerService.updateById(registersqf);
                }else {
                    registerService.insertInfoOther(registersqf);
                    int sqfstrSqlNextid = registersqf.getUserId();
                    jfid = sqfstrSqlNextid + "";
                }
                sqfids = sqfids + jfid + ",";
            }
        }
        if(!"".equals(sqfids) && sqfids.endsWith(",")){
            sqfids = sqfids.substring(0,sqfids.length()-1);
        }
        System.out.println("sqfids------>"+sqfids);
        //被申请方
        if(bsqfauthreginfo !=null){
            yfiddb = bsqfauthreginfo.getDbId()==null?null:bsqfauthreginfo.getDbId();
            String yfxmdb = jsStr.getString("yfxmdb");
            String yfsfzhdb = jsStr.getString("yfsfzhdb");
            String yfsjhdb = jsStr.getString("yfsjhdb");
            String yfzzdb = jsStr.getString("yfzzdb");
            String yfczdb = jsStr.getString("yfczdb");
            String yfzpdb = jsStr.getString("yfzpdb");
            String yfzwdb = jsStr.getString("yfzwdb");
            String yfsexdb = jsStr.getString("yfsexdb");
            String yfmzdb = jsStr.getString("yfmzdb");
            String yfcsrqdb = jsStr.getString("yfcsrqdb");
            if (yfxmdb == null){ yfxmdb ="";}
            if (yfsjhdb == null){ yfsjhdb ="";}
            if (yfzzdb == null){ yfzzdb ="";}
            if (yfczdb == null){ yfczdb ="";}
            if (yfzpdb == null){ yfzpdb ="";}
            if (yfzwdb == null){ yfzwdb ="";}
            Register registersqf = new Register();
            registersqf.setRealName(yfxmdb);
            registersqf.setSFZH(yfsfzhdb);
            registersqf.setTelephone(yfsjhdb);
            registersqf.setAddress(yfzzdb);
            registersqf.setNowSZD(yfczdb);
            registersqf.setImage(yfzpdb);
            registersqf.setFingers(yfzwdb);
            registersqf.setSex(yfsexdb);
            registersqf.setMZ(yfmzdb);
            registersqf.setBirthday(yfcsrqdb);
            registersqf.setLoginState(0);
            registersqf.setReGisterDate(createdate);
            registersqf.setLastLoginTime(createdate);
            registersqf.setIsdb(0);
            if(yfiddb != null){
                //申请方
                registersqf.setUserId(yfiddb);
                registerService.updateById(registersqf);
            }else{
                registerService.insertInfo(registersqf);
                yfiddb = registersqf.getUserId();
            }
            bsqfids = bsqfids + yfiddb +",";
            System.out.println("被申请方代表信息新增成功yfiddb---->"+jfiddb);
        }
        JSONArray bsqfjsonArray = jsStr.getJSONArray("bsqfinfoList");//获取数组
        String bsqfjsonStr = JSONObject.toJSONString(bsqfjsonArray);
        List bsqflist = JSONObject.parseArray(bsqfjsonStr);//被申请方当事人list
        System.out.println("bsqflist----->"+bsqflist);
        if(bsqflist != null && bsqflist.size()>0){
            for( int i=0; i<bsqflist.size(); i++){
                Map bsqfmap = (Map) bsqflist.get(i);
                String yfid = bsqfmap.get("yfid")==null?null:bsqfmap.get("yfid").toString();//id
                String yfxm = bsqfmap.get("yfxm")==null?null:bsqfmap.get("yfxm").toString();//姓名
                String yfsfzh = bsqfmap.get("yfsfzh")==null?null:bsqfmap.get("yfsfzh").toString();//身份证
                String yfsjh = bsqfmap.get("yfsjh")==null?null:bsqfmap.get("yfsjh").toString();//手机号
                String yfzz = bsqfmap.get("yfzz")==null?null:bsqfmap.get("yfzz").toString();//住址
                String yfcz = bsqfmap.get("yfcz")==null?null:bsqfmap.get("yfcz").toString();//常住
                String yfsex = bsqfmap.get("yfsex")==null?null:bsqfmap.get("yfsex").toString();//性别
                String yfmz = bsqfmap.get("yfmz")==null?null:bsqfmap.get("yfmz").toString();//民族
                String yfcsrq = bsqfmap.get("yfcsrq")==null?null:bsqfmap.get("yfcsrq").toString();//出生日期
                if (yfxm == null){ yfxm ="";}
                if (yfsjh == null){ yfsjh ="";}
                if (yfzz == null){ yfzz ="";}
                if (yfcz == null){ yfcz ="";}
                Register registersqf = new Register();
                registersqf.setRealName(yfxm);
                registersqf.setSFZH(yfsfzh);
                registersqf.setTelephone(yfsjh);
                registersqf.setAddress(yfzz);
                registersqf.setNowSZD(yfcz);
                registersqf.setSex(yfsex);
                registersqf.setMZ(yfmz);
                registersqf.setBirthday(yfcsrq);
                registersqf.setLoginState(0);
                registersqf.setReGisterDate(createdate);
                registersqf.setLastLoginTime(createdate);
                registersqf.setIsdb(1);
                if(yfid!=null && !"".equals(yfid)){
                    registersqf.setUserId(Integer.parseInt(yfid));
                    registerService.updateById(registersqf);
                }else {
                    registerService.insertInfoOther(registersqf);
                    int sqfstrSqlNextid = registersqf.getUserId();
                    yfid = sqfstrSqlNextid + "";
                }
                bsqfids = bsqfids + yfid +",";
            }
        }
        if(!"".equals(bsqfids) && bsqfids.endsWith(",")){
            bsqfids = bsqfids.substring(0,bsqfids.length()-1);
        }
        System.out.println("//----------------------------------------3. 将双方信息存入中间表(authreginfo) -------------------------------------------------");
        //----------------------------------------3. 将双方信息存入中间表(authreginfo) -------------------------------------------------
        Authreginfo sqfinfo = new Authreginfo();
        sqfinfo.setDwId(sqfdwid);//单位id
        sqfinfo.setIsDw(Integer.parseInt(sqfisdw));//是否是单位
//        sqfinfo.setSfid(Integer.parseInt(modelid));//业务id
        sqfinfo.setRegisterId(sqfids);//用户ids
        sqfinfo.setDbId(jfiddb);//代表id
        authreginfoService.insertinfo(sqfinfo);//新增的申请方id
        sqfinfoid = sqfinfo.getAuthregId();
        System.out.println("新增申请单中间表成功-->"+sqfinfo);
        Authreginfo bsqfinfo = new Authreginfo();
        bsqfinfo.setDwId(bsqfdwid);//单位id
        bsqfinfo.setIsDw(Integer.parseInt(bsqfisdw));//是否是单位
//        bsqfinfo.setSfid(Integer.parseInt(modelid));//业务id
        bsqfinfo.setRegisterId(bsqfids);//用户ids
        bsqfinfo.setDbId(jfiddb);//代表id
        authreginfoService.insertinfo(bsqfinfo);//新增的被申请方id
        bsqfinfoid = bsqfinfo.getAuthregId();
        System.out.println("新增被申请单中间表成功-->"+bsqfinfo);
        System.out.println("//----------------------------------------4. 将双方附件信息存入附件表(access) -------------------------------------------------");
        //----------------------------------------4. 将双方附件信息存入附件表(access) -------------------------------------------------
        if (jffjxx !=null){
            String strSqlData;
            strSqlData = "delete from  access where userid ="+sqfinfoid+" and modelid =" +AuthenticId;
            //System.out.println("strSqlData :"+strSqlData);
            mapSystjxx.objectVoid(strSqlData);

            JSONArray jarr = JSONArray.parseArray(jffjxx);
            for (Object o : jarr) {
                JSONObject fj = (JSONObject) o;
                String fjmc = fj.getString("fjmc");
                String fjtp = fj.getString("fjtp");
                //1 人民仲裁
                strSqlData = "insert into access (accessinfo,userid,modeltype,modelid,accessname,accesstype) " +
                        "values ('" + fjtp + "'," + sqfinfoid + ",1," + AuthenticId + ",'" + fjmc + "',0)";
                //System.out.println("strSqlData :"+strSqlData);
                mapSystjxx.objectVoid(strSqlData);
            }

        }

        if (yffjxx !=null){
            String strSqlData;
            strSqlData = "delete from  access where userid ="+bsqfinfoid+" and modelid =" +AuthenticId;
            //System.out.println("strSqlData :"+strSqlData);
            mapSystjxx.objectVoid(strSqlData);

            JSONArray jarr = JSONArray.parseArray(yffjxx);
            for (Object o : jarr) {
                JSONObject fj = (JSONObject) o;
                String fjmc = fj.getString("fjmc");
                String fjtp = fj.getString("fjtp");
                //1 人民仲裁
                strSqlData = "insert into access (accessinfo,userid,modeltype,modelid,accessname,accesstype) " +
                        "values ('" + fjtp + "'," + bsqfinfoid + ",1," + AuthenticId + ",'" + fjmc + "',0)";
                //System.out.println("strSqlData :"+strSqlData);
                mapSystjxx.objectVoid(strSqlData);
            }

        }
        System.out.println("修改附件成功");
        //----------------------------------------4. 将双方信息存入相关表(业务:Authentic; 申办进度:speed; 进度历史:speedhistory  -------------------------------------------------
        String strtemp1="update Authentic set ";
        if (sqfinfoid !=null && !sqfinfoid.equals("") ){
            strtemp1 += "sqfinfoId ="+sqfinfoid+",";
        }
        if (bsqfinfoid !=null && !bsqfinfoid.equals("")){
            strtemp1 += "bsqfinfoId="+bsqfinfoid+",";
        }
        if (RegionId !=null && !RegionId.equals("")){
            strtemp1 += "RegionId ="+RegionId+",";
        }
        if (assistanceId !=null && !assistanceId.equals("")){
            strtemp1 += "assistanceId ="+assistanceId+",";
        }
        if (FairworkerId !=null && !FairworkerId.equals("")){
            strtemp1 += "FairworkerId ="+FairworkerId+",";
        }
        if (UserContext !=null){
            strtemp1 += "UserContext ='"+UserContext+"',";
        }
        if (OtherContext !=null){
            strtemp1 += "OtherContext ='"+OtherContext+"',";
        }
        if (YYSJ !=null && !YYSJ.equals("")){
            strtemp1 += "YYSJ ='"+YYSJ+"',";
        }
        if (zprId !=null  && !zprId.equals("")){
            strtemp1 += "zprId="+zprId+",";
        }
        if (assistanceSecondId !=null && !assistanceSecondId.equals("")){
            strtemp1 += "assistanceSecondId="+assistanceSecondId+",";
        }
        if (sqfay !=null){
            strtemp1 += "sqfay="+sqfay+",";
        }
        if (bsqfay !=null){
            strtemp1 += "bsqfay="+bsqfay+",";
        }
        strtemp1 =  strtemp1.substring(0, strtemp1.length()-1) +"  where AuthenticId="+AuthenticId;
        jsReturn.put("info", "1");
        //System.out.println("strSqlData :"+strtemp1);
        mapSystjxx.objectVoid( strtemp1);
        jsReturn.put("info", "0");
        return jsReturn;
    }

    public JSONObject Operation(String json) {
        //System.out.println(json);
        JSONObject jsStr = JSONObject.parseObject(json);

        //System.out.println(json);
        jsStr = JSONObject.parseObject(jsStr.getString("info"));
        JSONObject jsReturn = new JSONObject();
        String operation;
        operation = jsStr.getString("operation");

        if (operation.equals("userlogin")) {
            String Phone = jsStr.getString("phone");
            String LoginPass = jsStr.getString("password");
            LoginPass = encryptMD5(LoginPass);
            //System.out.println("LoginPass:"+LoginPass);

            String strSqlData ="select FairWorkerId,WorkerName,Phone,RegionId,RoleId,DepartmentId,rytoken  " +
                    "from  fairworker where Phone='"+Phone+"' and LoginPass ='"+LoginPass+"'";
            //System.out.println(strSqlData);
            List<Map<String, Object>> mapList= mapSystjxx.objectList(strSqlData);
            String  fairWorkerId ="",departmentId="",phone="";
            String workerName="",roleid="",regionid="",rytoken="";

            for(Map<String, Object> maptemp :mapList){
                if (maptemp.get("FairWorkerId") !=null){
                    fairWorkerId = maptemp.get("FairWorkerId").toString();
                }
                if (maptemp.get("Phone") !=null){
                    phone = maptemp.get("Phone").toString();
                }
                if (maptemp.get("DepartmentId") !=null){
                    departmentId = maptemp.get("DepartmentId").toString();
                }
                if (maptemp.get("WorkerName") !=null){
                    workerName = maptemp.get("WorkerName").toString();
                }
                if (maptemp.get("RoleId") !=null){
                    roleid = maptemp.get("RoleId").toString();
                }

                if (maptemp.get("RegionId") !=null){
                    regionid = maptemp.get("RegionId").toString();
                }
                if (maptemp.get("RegionId") !=null){
                    regionid = maptemp.get("RegionId").toString();
                }
                if (maptemp.get("rytoken") !=null){
                    rytoken = maptemp.get("rytoken").toString();
                }

            }

            if (fairWorkerId.equals("")) {
                jsReturn.put("info", "1");
                return jsReturn;
            } else{
                if (roleid != null && !roleid.trim().equals("")) {

                    strSqlData = "select Num,Service,Webname,ActionState " +
                            "FROM sysrole c ,sysaction a  left join syspower b on a.Actionid = b.ActionId and b.RoleId=" + roleid + "  " +
                            " where c.roleid = " + roleid + "  and a.system ='2' order by a.num ";
                } else{
                    strSqlData = "select Num,Service,Webname,0 as ActionState FROM sysaction where system ='2'  order by num";

                }
                //System.out.println(strSqlData);
                List<Map<String, Object>> SysactionList = mapSystjxx.objectList(strSqlData);
                jsReturn.put("info", "0");
                jsReturn.put("operation", operation);
                jsReturn.put("fairWorkerId", fairWorkerId);
                jsReturn.put("phone", phone);
                jsReturn.put("departmentId", departmentId);
                jsReturn.put("workerName", workerName);
                jsReturn.put("roleid", roleid);
                jsReturn.put("regionid", regionid);
                jsReturn.put("rytoken", rytoken);

                jsReturn.put("actionList", SysactionList);

                String wdSql="select roomId,roompass from wdmeeting where fairworkerId="+fairWorkerId+" limit 1";
                
                Map<String, Object> wdMap=mapSystjxx.objectOne(wdSql);
                
                if(wdMap!=null&&!wdMap.isEmpty())
                {
                	jsReturn.put("roomId", wdMap.get("roomId"));
                    jsReturn.put("roompass", wdMap.get("roompass"));
                }
            }

        }

        if (operation.equals("findpageSeek")) {

            String sfzh = jsStr.getString("sfzh");
            String ajlb = jsStr.getString("ajlb");
            String zxjg = jsStr.getString("zxjg");
            String jdlb = jsStr.getString("jdlb");
            int pagesize = jsStr.getInteger("limit");
            int pagenum = jsStr.getInteger("page");
            int beginrow;
            beginrow = (pagenum - 1) * pagesize;

            String strSqlpublic =  "from seek a,assistance b,fairworker c " +
                    "where a.assistanceId = b.assistanceId " +
                    "and a.czrId = c.FairWorkerId ";
            String strSqlData="select a.seekId, a.dsrxm,a.dsrsfzh,b.title as ajlb, " +
                    "case when zxjg = 0 then '已解决' when zxjg = 1 then  '未解决' " +
                    "when  zxjg = 2 then '未解决，转其他部门'  else '无' end as zxjg ," +
                    "c.WorkerName as cjr,date_format(a.czsj, '%Y-%m-%d %T')  as czsj ";
            String StrCondition="";
            String strSqlCount = " select count(*) as rowcount ";
            String StrLimit = " order by czsj desc  limit " + beginrow + "," + pagesize;

            if (sfzh != null && !sfzh.trim().equals("")){
                StrCondition += " and a.dsrsfzh = '"+sfzh+"' ";
            }

            if (ajlb != null && !ajlb.trim().equals("")){
                StrCondition += " and a.assistanceId ="+ajlb;
            }

            if (zxjg != null && !zxjg.trim().equals("")){
                StrCondition += " and a.zxjg ="+zxjg;
            }

            if (jdlb != null && !jdlb.trim().equals("")){
                StrCondition += " and a.jdtype ="+jdlb;
            }
            strSqlData = strSqlData + strSqlpublic + StrCondition + StrLimit;
            strSqlCount = strSqlCount + strSqlpublic + StrCondition;
            // System.out.println("strSqlData:"+strSqlData);
            //System.out.println("strSqlCount:"+strSqlCount);
            List<Map<String, Object>> mapList = mapSystjxx.objectList(strSqlData);
            int rowcount = mapSystjxx.objectInt(strSqlCount);
            int pagecount = rowcount/pagesize ;
            int temp = rowcount%pagesize;
            if (temp > 0 ){
                pagecount +=1;
            }
            jsReturn.put("limit", pagesize);
            jsReturn.put("page",  pagenum);
            jsReturn.put("pagecount",  pagecount);
            jsReturn.put("rowcount", rowcount);
            jsReturn.put("info", mapList);
        }

        if (operation.equals("findidSeek")) {

            String seekId = jsStr.getString("seekId");
            String strSqlpublic =  "from seek a,fairworker c " +
                    "where  a.czrId = c.FairWorkerId ";
            String strSqlData="select a.seekId, a.dsrxm,a.dsrsfzh, a.assistanceId as ajlb, " +
                    " a.zxjg ,a.zxsx ,a.zxjg,a.zxjlwb,a.departmentId, a.otherzxjg, a.czrId," +
                    " a.zwxx,CONVERT (a.dzqm USING utf8)  as dzqm, a.lxdh,a.type as fylb, a.jdtype as jdlb,a.dsrimage," +
                    " c.WorkerName as cjr,date_format(a.czsj, '%Y-%m-%d %T')  as czsj ";
            String StrCondition=" and a.seekId ="+seekId;

            strSqlData = strSqlData + strSqlpublic + StrCondition ;

            //System.out.println("strSqlData:"+strSqlData);

            Map<String, Object> mapOne = mapSystjxx.objectOne(strSqlData);

            jsReturn.put("info", mapOne);
        }

        if (operation.equals("insertSeek")) {
            String dsrimage ="";
            String dsrxm = jsStr.getString("dsrxm");
            String dsrsfzh = jsStr.getString("dsrsfzh");
            String zxsx = jsStr.getString("zxsx");
            String zxjg = jsStr.getString("zxjg");
            String zxjlwb = jsStr.getString("zxjlwb");
            String departmentId = jsStr.getString("departmentId");
            String otherzxjg = jsStr.getString("otherzxjg");
            String czrId = jsStr.getString("czrId");
            String image = jsStr.getString( "image");
            String jdlb = jsStr.getString("jdlb");
            String zwxx = jsStr.getString("zwxx");
            String dzqm = jsStr.getString("dzqm");
            String lxdh = jsStr.getString("lxdh");
            String ajlb = jsStr.getString("ajlb");
            String fwlb = jsStr.getString("fwlb");

            if (image != null && !image.trim().equals("")){
                //System.out.println(image);
                dsrimage = GenerateImage(image);
            }
            SimpleDateFormat getdatetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String createdate = getdatetime.format(new Date());// new Date()为获取当前系统时间

            String strtemp1="insert seek ( czsj,dsrimage,";
            String strtemp2 ="values('"+createdate+"','"+dsrimage+"',";
            if (dsrxm !=null){
                strtemp1 += "dsrxm,";
                strtemp2 += "'"+dsrxm+"',";
            }
            if (dsrsfzh !=null){
                strtemp1 += "dsrsfzh,";
                strtemp2 += "'"+dsrsfzh+"',";
            }
            if (zxsx !=null){
                strtemp1 += "zxsx,";
                strtemp2 += "'"+zxsx+"',";
            }
            if (zxjg !=null && !zxjg.equals("") ){
                strtemp1 += "zxjg,";
                strtemp2 += zxjg+",";
            }
            if (zxjlwb !=null){
                strtemp1 += "zxjlwb,";
                strtemp2 += "'"+zxjlwb+"',";
            }
            if (departmentId !=null && !departmentId.equals("")){
                strtemp1 += "departmentId,";
                strtemp2 += departmentId+",";
            }
            if (otherzxjg !=null  && !otherzxjg.equals("")){
                strtemp1 += "otherzxjg,";
                strtemp2 += otherzxjg+",";
            }
            if (czrId !=null && !czrId.equals("")){
                strtemp1 += "czrId,";
                strtemp2 += czrId+",";
            }

            if (zwxx !=null ){
                strtemp1 += "zwxx,";
                strtemp2 += "'"+zwxx+"',";
            }
            if (dzqm !=null ){
                strtemp1 += "dzqm,";
                strtemp2 +="'"+dzqm+"',";
            }

            if (lxdh !=null ){
                strtemp1 += "lxdh,";
                strtemp2 += "'"+lxdh+"',";
            }
            if (ajlb !=null && !ajlb.equals("")){
                strtemp1 += "assistanceId,";
                strtemp2 += ajlb+",";
            }
            if (jdlb !=null && !jdlb.equals("")){
                strtemp1 += "jdtype,";
                strtemp2 += jdlb+",";
            }

            if (fwlb !=null && !fwlb.equals("")){
                strtemp1 += "type,";
                strtemp2 += fwlb+",";
            }


            strtemp1 =  strtemp1.substring(0, strtemp1.length()-1) +" )  ";
            strtemp2 =  strtemp2.substring(0, strtemp2.length()-1)+" )";
            String strSqlData = strtemp1+strtemp2;
            //System.out.println(strSqlData);

            jsReturn.put("info", "1");
            mapSystjxx.objectVoid(strSqlData);
            jsReturn.put("info", "0");
        }

        if (operation.equals("updateSeek")) {
            String seekId = jsStr.getString("seekId");
            String dsrxm = jsStr.getString("dsrxm");
            String dsrsfzh = jsStr.getString("dsrsfzh");
            String dsrimage = jsStr.getString("dsrimage");
            String zxsx = jsStr.getString("zxsx");
            String zxjg = jsStr.getString("zxjg");
            String zxjlwb = jsStr.getString("zxjlwb");
            String departmentId = jsStr.getString("departmentId");
            String otherzxjg = jsStr.getString("otherzxjg");
            String czrId = jsStr.getString("czrId");
            String image = jsStr.getString( "image");

            String jdlb = jsStr.getString("jdlb");
            String zwxx = jsStr.getString("zwxx");
            String dzqm = jsStr.getString("dzqm");
            String lxdh = jsStr.getString("lxdh");
            String ajlb = jsStr.getString("ajlb");
            String fwlb = jsStr.getString("fwlb");


            if (image != null && !image.trim().equals("")){
                dsrimage = GenerateImage(image);
            }

            String strSqlData ="update seek  set ";

            if (dsrxm !=null){ strSqlData += " dsrxm ='"+dsrxm+"',"; }
            if (dsrsfzh !=null){ strSqlData += " dsrsfzh ='"+dsrsfzh+"',"; }
            if (dsrimage !=null){ strSqlData += " dsrimage ='"+dsrimage+"',"; }
            if (zxsx !=null){ strSqlData += " zxsx ='"+zxsx+"',"; }
            if (zxjg !=null){ strSqlData += " zxjg ="+zxjg+","; }
            if (zxjlwb !=null){ strSqlData += " zxjlwb ='"+zxjlwb+"',"; }
            if (departmentId !=null  && !departmentId.equals("") ){ strSqlData += " departmentId ="+departmentId+","; }
            if (otherzxjg !=null && !otherzxjg.equals("") ){ strSqlData += " otherzxjg ="+otherzxjg+","; }
            if (czrId !=null && !czrId.equals("")){ strSqlData += " czrId ="+czrId+","; }

            if (jdlb !=null){ strSqlData += " jdtype ="+jdlb+","; }
            if (zwxx !=null){ strSqlData += " zwxx ='"+zwxx+"',"; }
            if (dzqm !=null){ strSqlData += " dzqm ='"+dzqm+"',"; }
            if (lxdh !=null){ strSqlData += " lxdh ='"+lxdh+"',"; }
            if (ajlb !=null && !ajlb.equals("") ){ strSqlData += " assistanceId ="+ajlb+","; }
            if (fwlb !=null && !fwlb.equals("")){ strSqlData += " type ="+fwlb+","; }



            strSqlData =strSqlData.substring(0,strSqlData.length()-1);
            strSqlData +=" where seekId ="+seekId;
            //System.out.println(strSqlData);
            jsReturn.put("info", "1");
            mapSystjxx.objectVoid(strSqlData);
            jsReturn.put("info", "0");
        }


        if (operation.equals("insertAuthentic")) {
            String AuthDepartId = jsStr.getString("AuthDepartId");
            String jfxm = jsStr.getString("jfxm");
            String yfxm = jsStr.getString("yfxm");
            String jfsfzh = jsStr.getString("jfsfzh");
            String yfsfzh = jsStr.getString("yfsfzh");
            String jfsjh = jsStr.getString("jfsjh");
            String yfsjh = jsStr.getString("yfsjh");
            String jfzz = jsStr.getString("jfzz");
            String yfzz = jsStr.getString("yfzz");
            String jfcz = jsStr.getString("jfcz");
            String yfcz = jsStr.getString("yfcz");
            String jfzp = jsStr.getString("jfzp");
            String yfzp = jsStr.getString("yfzp");
            String jfzw = jsStr.getString("jfzw");
            String yfzw = jsStr.getString("yfzw");
            String jffjxx = jsStr.getString("jffjxx");
            String yffjxx = jsStr.getString("yffjxx");
            String SummaryContext = jsStr.getString("SummaryContext");
            String Context = jsStr.getString("Context");
            String RegionId = jsStr.getString("RegionId");
            String assistanceId = jsStr.getString("assistanceId");
            String FairworkerId = jsStr.getString("FairworkerId");
            String UserContext = jsStr.getString("UserContext");
            String OtherContext = jsStr.getString("OtherContext");
            String YYSJ = jsStr.getString( "YYSJ");
            String zprId = jsStr.getString( "zprId");
            String zpsj = jsStr.getString( "zpsj");
            String SPSJ = jsStr.getString( "SPSJ");
            String isonline = jsStr.getString( "isonline");

            String jfid ="",yfid = "";

            if (jfxm == null) { jfxm ="";}
            if (jfsjh == null){ jfsjh ="";}
            if (jfzz == null){ jfzz ="";}
            if (jfcz == null){ jfcz ="";}
            if (jfzp == null){ jfzp ="";}
            if (jfzw == null){ jfzw ="";}

            if (yfxm == null){ yfxm ="";}
            if (yfsjh == null){ yfsjh ="";}
            if (yfzz == null){ yfzz ="";}
            if (yfcz == null){ yfcz ="";}
            if (yfzp == null){ yfzp ="";}
            if (yfzw == null){ yfzw ="";}

            SimpleDateFormat getdatetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String createdate = getdatetime.format(new Date());// new Date()为获取当前系统时间

            if (jfsfzh != null && !jfsfzh.equals("")){
                String sex;
                if (Integer.parseInt(jfsfzh.substring(16).substring(0, 1)) % 2 == 0) {
                    sex = "女";
                } else {
                    sex = "男";
                }
                String strSql ="select UserId from  register where sfzh ='"+jfsfzh+"'";
                List<Map<String, Object>> mapList= mapSystjxx.objectList(strSql);
                if (mapList.size() > 0){
                    for(Map<String, Object> maptemp :mapList){
                        jfid = maptemp.get("UserId").toString();
//                        strSql ="update register set realname ='"+jfxm+"',telephone='" +jfsjh+"',"
//                        		+ "address ='"+jfzz+"',nowszd = '"+jfcz+"',image ='"+jfzp+"',fingers ='"+jfzw+"'  "
//                        				+ "where UserId ="+jfid;
                        
                        strSql="update register set realname ='"+jfxm+"',telephone='" +jfsjh+"'";
                        
                        if(jfzz!=null&&!jfzz.equals("")) {
                            strSql+=",address = '"+jfzz+"'";
                        }
                        
                        if(jfcz!=null&&!jfcz.equals("")) {
                            strSql+=",nowszd = '"+jfcz+"'";
                        }
                        
                        if(jfzp!=null&&!jfzp.equals("")) {
                            strSql+=",image = '"+jfzp+"'";
                        }
                        
                        if(jfzw!=null&&!jfzw.equals("")) {
                            strSql+=",fingers ='"+jfzw+"'";
                        }
                        
                        strSql+=" where UserId ='"+jfid+"'";
                                         
                        
                        mapSystjxx.objectVoid(strSql);
                    }
                } else{

                    strSql ="insert register (realname,sfzh,telephone,address,nowszd,image,fingers,sex," +
                            "loginstate,registerdate,lastlogintime)  " +
                            "values ('"+jfxm+"','"+jfsfzh+"','"+jfsjh+"','"
                            +jfzz+"','"+jfcz+"','"+jfzp+"','"+jfzw+"','"+sex+"',0,'"+createdate+"','"+createdate+"' )";
                    String strSqlNextid = "SELECT auto_increment FROM information_schema.tables  " +
                            "WHERE table_name='register' and table_schema ='"+DataBaseName+"'";
                    jfid = mapSystjxx.objectInt(strSqlNextid)+"";
                    mapSystjxx.objectVoid(strSql);
                }
            }
            
            //System.out.println("+++++++++++++++++++++++++++++++++");
            //System.out.println(yfsfzh);
            if (yfsfzh != null && !yfsfzh.equals("")){
                String sex;
                if (Integer.parseInt(yfsfzh.substring(16).substring(0, 1)) % 2 == 0) {
                    sex = "女";
                } else {
                    sex = "男";
                }
                String strSql ="select UserId from  register where sfzh ='"+yfsfzh+"'";
                List<Map<String, Object>> mapList= mapSystjxx.objectList(strSql);
                if (mapList.size() > 0){
                    for(Map<String, Object> maptemp :mapList){
                        yfid = maptemp.get("UserId").toString();
                        strSql ="update register set realname ='"+yfxm+"',telephone='" +
                                yfsjh+"',address ='"+yfzz+"',nowszd = '"+yfcz+"',image ='"+yfzp+"',fingers ='"+yfzw+"'  where UserId ="+yfid;
                        //System.out.println("strSql:"+strSql);
                        mapSystjxx.objectVoid(strSql);
                    }
                } else{

                    strSql ="insert register (realname,sfzh,telephone,address,nowszd,image,fingers,sex," +
                            "loginstate,registerdate,lastlogintime)  " +
                            "values ('"+yfxm+"','"+yfsfzh+"','"+yfsjh+"','"
                            +yfzz+"','"+yfcz+"','"+yfzp+"','"+yfzw+"','"+sex+"',0,'"+createdate+"','"+createdate+"' )";
                    String strSqlNextid = "SELECT auto_increment FROM information_schema.tables  " +
                            "WHERE table_name='register' and table_schema ='"+DataBaseName+"'";
                    yfid = mapSystjxx.objectInt(strSqlNextid)+"";
                    mapSystjxx.objectVoid(strSql);
                }
            }


            String strtemp1;
            String strtemp2;

            if (yfsfzh != null && !yfsfzh.equals("")){

                if (isonline != null && !isonline.equals("")){
                    strtemp1="insert Authentic ( IsOnline,createdate,";
                    strtemp2 ="values("+isonline+",'"+createdate+"',";
                } else{
                    strtemp1="insert Authentic ( IsOnline,createdate,";
                    strtemp2 ="values(1,'"+createdate+"',";
                }
            } else{
                strtemp1="insert Authentic ( IsOnline,createdate,";
                strtemp2 ="values(0,'"+createdate+"',";

            }

            String strtemp3="insert speed (CreateDate,ModelType,State,";
            String strtemp4 ="values('"+createdate+"',1,0,";
            String strtemp5="insert speedhistory (CreateDate,ModelType,State,";
            String strtemp6 ="values('"+createdate+"',1,0,";

            if (AuthDepartId !=null && !AuthDepartId.equals("")){
                strtemp1 += "AuthDepartId,";
                strtemp2 += AuthDepartId+",";
            }
            if (jfid !=null && !jfid.equals("") ){
                strtemp1 += "UserId,";
                strtemp2 += jfid+",";
                strtemp3 += "registerId,";
                strtemp4 += jfid+",";
                strtemp5 += "registerId,";
                strtemp6 += jfid+",";
            }
            if (yfid !=null && !yfid.equals("")){
                strtemp1 += "OtherPartyId,";
                strtemp2 += yfid+",";
                strtemp3 += "partyId,";
                strtemp4 += yfid+",";
                strtemp5 += "partyId,";
                strtemp6 += yfid+",";
            }
            if (SummaryContext !=null && !SummaryContext.equals("")){
                strtemp1 += "SummaryContext,";
                strtemp2 += "'"+SummaryContext+"',";
            }
            if (Context !=null && !Context.equals("")){
                strtemp1 += "Context,";
                strtemp2 += "'"+Context+"',";
            }
            if (RegionId !=null && !RegionId.equals("")){
                strtemp1 += "RegionId,";
                strtemp2 += RegionId+",";
            }
            if (assistanceId !=null && !assistanceId.equals("")){
                strtemp1 += "assistanceId,";
                strtemp2 += assistanceId+",";
            }
            if (FairworkerId !=null && !FairworkerId.equals("")){
                strtemp1 += "FairworkerId,";
                strtemp2 += FairworkerId+",";
                strtemp3 += "fairworkerId,";
                strtemp4 += FairworkerId+",";
                strtemp5 += "fairworkerId,";
                strtemp6 += FairworkerId+",";
            }
            if (UserContext !=null){
                strtemp1 += "UserContext,";
                strtemp2 += "'"+UserContext+"',";
            }
            if (OtherContext !=null){
                strtemp1 += "OtherContext,";
                strtemp2 += "'"+OtherContext+"',";
            }
            if (YYSJ !=null && !YYSJ.equals("")){
                strtemp1 += "YYSJ,";
                strtemp2 += "'"+YYSJ+"',";
            }
            if (zprId !=null  && !zprId.equals("")){
                strtemp1 += "zprId,";
                strtemp2 += zprId+",";
            }
            if (zpsj !=null && !zpsj.equals("")){
                strtemp1 += "zpsj,";
                strtemp2 += "'"+zpsj+"',";
            }
            if (SPSJ !=null && !SPSJ.equals("")){
                strtemp1 += "SPSJ,";
                strtemp2 += "'"+SPSJ+"',";
            }
            strtemp1 =  strtemp1.substring(0, strtemp1.length()-1) +" )  ";
            strtemp2 =  strtemp2.substring(0, strtemp2.length()-1)+" )";


            String strSqlNextid = "SELECT auto_increment FROM information_schema.tables  " +
                    "WHERE table_name='authentic' and table_schema ='"+DataBaseName+"'";
            String modelid = mapSystjxx.objectStr(strSqlNextid);

            strtemp3 += "ModelId,";
            strtemp4 += modelid+",";
            strtemp5 += "ModelId,";
            strtemp6 += modelid+",";


            strtemp3 =  strtemp3.substring(0, strtemp3.length()-1) +" )  ";
            strtemp4 =  strtemp4.substring(0, strtemp4.length()-1)+" )";

            strtemp5 =  strtemp5.substring(0, strtemp5.length()-1) +" )  ";
            strtemp6 =  strtemp6.substring(0, strtemp6.length()-1)+" )";

            //System.out.println( strtemp1+strtemp2);
            //System.out.println( strtemp3+strtemp4);
            // System.out.println( strtemp5+strtemp6);

            String strtemp7 ="insert into dzjz (sczt,jztyle,ajId,type) values(0,0,"+modelid+",1)";
            String strtemp8 ="insert into dzjz (sczt,jztyle,ajId,type) values(0,1,"+modelid+",1)";
            String strtemp9 ="insert into dzjz (sczt,jztyle,ajId,type) values(0,2,"+modelid+",1)";
            jsReturn.put("info", "1");

            mapSystjxx.objectVoid( strtemp1+strtemp2);
            mapSystjxx.objectVoid( strtemp3+strtemp4);
            mapSystjxx.objectVoid( strtemp5+strtemp6);

            mapSystjxx.objectVoid( strtemp7);
            mapSystjxx.objectVoid( strtemp8);
            mapSystjxx.objectVoid( strtemp9);

            JSONArray jarr1 = JSONArray.parseArray(jffjxx);
            for (Object o : jarr1) {
                JSONObject fj = (JSONObject) o;
                String fjmc = fj.getString("fjmc");
                String fjtp = fj.getString("fjtp");
                //1 人民仲裁
                String strSqlData = "insert into access (accessinfo,userid,modeltype,modelid,accessname,accesstype) " +
                        "values ('" + fjtp + "'," + jfid + ",1," + modelid + ",'" + fjmc + "',0)";
                //System.out.println("strSqlData :"+strSqlData);
                mapSystjxx.objectVoid(strSqlData);
            }
            if (yffjxx !=null){
            	JSONArray jarr2 = JSONArray.parseArray(yffjxx);
                for (Object o : jarr2) {
                    JSONObject fj = (JSONObject) o;
                    String fjmc = fj.getString("fjmc");
                    String fjtp = fj.getString("fjtp");
                    //1 人民仲裁
                    String strSqlData = "insert into access (accessinfo,userid,modeltype,modelid,accessname,accesstype) " +
                            "values ('" + fjtp + "'," + yfid + ",1," + modelid + ",'" + fjmc + "',0)";
                    //System.out.println("strSqlData :"+strSqlData);
                    mapSystjxx.objectVoid(strSqlData);
                }
            }
            
            jsReturn.put("info", "0");
            jsReturn.put("AuthenticId", modelid);
        }


        if (operation.equals("findidAuthentic")) {
            String AuthenticId = jsStr.getString("AuthenticId");
            //属性包括读卡（读取身份证）、申请方姓名（文本，可编辑）、申请方身份证号（文本，可编辑）、申请方手机号（文本，做手机验证码校验）、
            //申请方指纹、申请方照片、被申请方姓名（文本，可编辑）、被申请方身份证号（文本、可编辑）、被申请方手机号（文本，做手机验证码校验）、
            //被申请方指纹、被申请方照片、案件类别、双方案情简介（语音转文字）、案件要件（根据案件类别显示应该上传的要件名称及个数）、预约时间。
            String strSqlpublic = "from Authentic a,register c,register d  " +
                    " where  a.UserId = c.UserId "  +
                    " and a.OtherPartyId = d.UserId ";
            String strSqlData="select a.AuthenticId, a.assistanceId,a.AuthDepartId ," +
                    " c.RealName as jfxm,c.sfzh as jfsfzh,c.address as jfzz,c.nowszd as jfcz,c.Telephone as jfsjh,CONVERT (c.Image USING utf8) as jfzp,CONVERT (c.fingers USING utf8) as jfzw,a.UserContext," +
                    " d.RealName as yfxm,d.sfzh as yfsfzh,d.address as yfzz,d.nowszd as yfcz,d.Telephone as yfsjh,CONVERT (d.Image USING utf8) as yfzp,CONVERT (d.fingers USING utf8) as yfzw,a.OtherContext," +
                    " date_format(a.yysj, '%Y-%m-%d %T')  as yysj,a.UserId,a.OtherPartyId ";
            String StrCondition=" and a.AuthenticId ="+AuthenticId;
            strSqlData = strSqlData + strSqlpublic + StrCondition ;
            //System.out.println("strSqlData:"+strSqlData);
            Map<String, Object> mapOne = mapSystjxx.objectOne(strSqlData);
            String jfid = mapOne.get("UserId").toString();
            String yfid = mapOne.get("OtherPartyId").toString();
            String strSqlData1 = "select   CONVERT (AccessInfo USING utf8) as fjtp,accessname as fjmc from access where userid ="+jfid+" and  modelid ="+AuthenticId;
            String strSqlData3 = "select   CONVERT (AccessInfo USING utf8) as fjtp,accessname as fjmc from access where userid ="+yfid+" and  modelid ="+AuthenticId;
            List<Map<String, Object>> mapListjf = mapSystjxx.objectList(strSqlData1);
            List<Map<String, Object>> mapListyf = mapSystjxx.objectList(strSqlData3);

            String dzjz = jsStr.getString("dzjz");
            //0 仲裁书  1 司法确认书 2 卷宗
            if (dzjz != null){
                String strSqlData2="select a.jzId,a.bh,date_format(a.cjsj, '%Y-%m-%d %T') as cjsj,b.WorkerName as cjr, a.accessurl, a.jztyle," +
                        "case when a.sczt = 0 then '否' when a.sczt = 1 then '是' when a.sczt = 2 then '无' end  as  sczt, " +
                        "case when a.jztyle = 0 then '仲裁书' when a.jztyle = 1 then '司法确认书' when a.jztyle = 2 then '卷宗' end  as  jztylemc " +
                        "from dzjz a left join fairworker b  on a.cjr = b.FairWorkerId " +
                        "where a.ajId ="+AuthenticId+" and  a.type = 1 ";
                //System.out.println(strSqlData2);
                List<Map<String, Object>>  mapList1 = mapSystjxx.objectList(strSqlData2);
                jsReturn.put("dzjz", mapList1);
            }

            jsReturn.put("info", mapOne);
            jsReturn.put("jffjxx", mapListjf);
            jsReturn.put("yffjxx", mapListyf);
        }

        if (operation.equals("updateAuthentic")) {
            String AuthenticId = jsStr.getString("AuthenticId");
            //String AuthDepartId = jsStr.getString("AuthenticId");
            String jfxm = jsStr.getString("jfxm");
            String yfxm = jsStr.getString("yfxm");
            String jfsfzh = jsStr.getString("jfsfzh");
            String yfsfzh = jsStr.getString("yfsfzh");
            String jfsjh = jsStr.getString("jfsjh");
            String yfsjh = jsStr.getString("yfsjh");
            String jfzz = jsStr.getString("jfzz");
            String yfzz = jsStr.getString("yfzz");
            String jfcz = jsStr.getString("jfcz");
            String yfcz = jsStr.getString("yfcz");

            String jfzp = jsStr.getString("jfzp");
            String yfzp = jsStr.getString("yfzp");
            String jfzw = jsStr.getString("jfzw");
            String yfzw = jsStr.getString("yfzw");

            String jffjxx = jsStr.getString("jffjxx");
            String yffjxx = jsStr.getString("yffjxx");

            String RegionId = jsStr.getString("RegionId");
            String assistanceId = jsStr.getString("assistanceId");
            String FairworkerId = jsStr.getString("FairworkerId");
            String UserContext = jsStr.getString("UserContext");
            String OtherContext = jsStr.getString("OtherContext");
            String YYSJ = jsStr.getString( "yysj");
            String zprId = jsStr.getString( "zprId");
            String UserId = jsStr.getString( "UserId");
            String OtherPartyId = jsStr.getString( "OtherPartyId");
            String strtemp1="update Authentic set ";

//            if (AuthDepartId ==null ){
//                jsReturn.put("info", "1");
//                return jsReturn;
//            }

            if (jfxm == null) { jfxm ="";}
            if (jfsjh == null){ jfsjh ="";}
            if (jfzz == null){ jfzz ="";}
            if (jfzp == null){ jfzp ="";}
            if (jfzw == null){ jfzw ="";}

            if (yfxm == null){ yfxm ="";}
            if (yfsjh == null){ yfsjh ="";}
            if (yfzz == null){ yfzz ="";}
            if (yfzp == null){ yfzp ="";}
            if (yfzw == null){ yfzw ="";}

            SimpleDateFormat getdatetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String createdate = getdatetime.format(new Date());// new Date()为获取当前系统时间


            if (jfsfzh != null && !jfsfzh.equals("")){
                String sex;
                if (Integer.parseInt(jfsfzh.substring(16).substring(0, 1)) % 2 == 0) {
                    sex = "女";
                } else {
                    sex = "男";
                }
                String strSql ="select UserId from  register where sfzh ='"+jfsfzh+"'";
                //System.out.println("strSql:"+strSql);
                List<Map<String, Object>> mapList= mapSystjxx.objectList(strSql);
                if (mapList.size() > 0){
                    for(Map<String, Object> maptemp :mapList){
                        UserId = maptemp.get("UserId").toString();
                        strSql ="update register set realname ='"+jfxm+"',telephone='" +
                                jfsjh+"',address ='"+jfzz+"',nowszd ='"+jfcz+"', image ='"+jfzp+"',fingers ='"+jfzw+"'  where UserId ="+UserId;
                        mapSystjxx.objectVoid(strSql);
                    }
                } else{

                    strSql ="insert register (realname,sfzh,telephone,address,nowszd,image,fingers,sex," +
                            "loginstate,registerdate,lastlogintime)  " +
                            "values ('"+jfxm+"','"+jfsfzh+"','"+jfsjh+"','"
                            +jfzz+"','"+jfcz+"','"+jfzp+"','"+jfzw+"','"+sex+"',0,'"+createdate+"','"+createdate+"' )";
                    String strSqlNextid = "SELECT auto_increment FROM information_schema.tables  " +
                            "WHERE table_name='register' and table_schema ='"+DataBaseName+"'";

                    UserId = mapSystjxx.objectInt(strSqlNextid)+"";
                    mapSystjxx.objectVoid(strSql);
                }
            }

            if (yfsfzh != null && !yfsfzh.equals("")){
                String sex;
                if (Integer.parseInt(yfsfzh.substring(16).substring(0, 1)) % 2 == 0) {
                    sex = "女";
                } else {
                    sex = "男";
                }
                String strSql ="select UserId from  register where sfzh ='"+yfsfzh+"'";

                List<Map<String, Object>> mapList= mapSystjxx.objectList(strSql);
                if (mapList.size() > 0){
                    for(Map<String, Object> maptemp :mapList){
                        OtherPartyId = maptemp.get("UserId").toString();
                        strSql ="update register set realname ='"+yfxm+"',telephone='" +
                                yfsjh+"',address ='"+yfzz+"',nowszd ='"+yfcz+"',image ='"+yfzp+"',fingers ='"+yfzw+"'  where UserId ="+OtherPartyId;
                        mapSystjxx.objectVoid(strSql);
                    }
                } else{

                    strSql ="insert register (realname,sfzh,telephone,address,nowszd,image,fingers,sex," +
                            "loginstate,registerdate,lastlogintime)  " +
                            "values ('"+yfxm+"','"+yfsfzh+"','"+yfsjh+"','"
                            +yfzz+"','"+jfcz+"','"+yfzp+"','"+yfzw+"','"+sex+"',0,'"+createdate+"','"+createdate+"' )";
                    String strSqlNextid = "SELECT auto_increment FROM information_schema.tables  " +
                            "WHERE table_name='register' and table_schema ='"+DataBaseName+"'";
                    OtherPartyId = mapSystjxx.objectInt(strSqlNextid)+"";
                    mapSystjxx.objectVoid(strSql);
                }
            }

//            if (!AuthDepartId.equals("")){
//                strtemp1 += "AuthDepartId ="+AuthenticId+",";
//            }
            if (UserId !=null && !UserId.equals("") ){
                strtemp1 += "UserId ="+UserId+",";
            }
            if (OtherPartyId !=null && !OtherPartyId.equals("")){
                strtemp1 += "OtherPartyId="+OtherPartyId+",";
            }

            if (RegionId !=null && !RegionId.equals("")){
                strtemp1 += "RegionId ="+RegionId+",";
            }
            if (assistanceId !=null && !assistanceId.equals("")){
                strtemp1 += "assistanceId ="+assistanceId+",";
            }
            if (FairworkerId !=null && !FairworkerId.equals("")){
                strtemp1 += "FairworkerId ="+FairworkerId+",";
            }
            if (UserContext !=null){
                strtemp1 += "UserContext ='"+UserContext+"',";
            }
            if (OtherContext !=null){
                strtemp1 += "OtherContext ='"+OtherContext+"',";
            }
            if (YYSJ !=null && !YYSJ.equals("")){
                strtemp1 += "YYSJ ='"+YYSJ+"',";
            }
            if (zprId !=null  && !zprId.equals("")){
                strtemp1 += "zprId="+zprId+",";
            }

            if (jffjxx !=null){
                String strSqlData;
                strSqlData = "delete from  access where userid ="+UserId+" and modelid =" +AuthenticId;
                //System.out.println("strSqlData :"+strSqlData);
                mapSystjxx.objectVoid(strSqlData);

                JSONArray jarr = JSONArray.parseArray(jffjxx);
                for (Object o : jarr) {
                    JSONObject fj = (JSONObject) o;
                    String fjmc = fj.getString("fjmc");
                    String fjtp = fj.getString("fjtp");
                    //1 人民仲裁
                    strSqlData = "insert into access (accessinfo,userid,modeltype,modelid,accessname,accesstype) " +
                            "values ('" + fjtp + "'," + UserId + ",1," + AuthenticId + ",'" + fjmc + "',0)";
                    //System.out.println("strSqlData :"+strSqlData);
                    mapSystjxx.objectVoid(strSqlData);
                }

            }

            if (yffjxx !=null){
                String strSqlData;
                strSqlData = "delete from  access where userid ="+OtherPartyId+" and modelid =" +AuthenticId;
                //System.out.println("strSqlData :"+strSqlData);
                mapSystjxx.objectVoid(strSqlData);

                JSONArray jarr = JSONArray.parseArray(yffjxx);
                for (Object o : jarr) {
                    JSONObject fj = (JSONObject) o;
                    String fjmc = fj.getString("fjmc");
                    String fjtp = fj.getString("fjtp");
                    //1 人民仲裁
                    strSqlData = "insert into access (accessinfo,userid,modeltype,modelid,accessname,accesstype) " +
                            "values ('" + fjtp + "'," + OtherPartyId + ",1," + AuthenticId + ",'" + fjmc + "',0)";
                    //System.out.println("strSqlData :"+strSqlData);
                    mapSystjxx.objectVoid(strSqlData);
                }

            }


            strtemp1 =  strtemp1.substring(0, strtemp1.length()-1) +"  where AuthenticId="+AuthenticId;
            jsReturn.put("info", "1");
            //System.out.println("strSqlData :"+strtemp1);
            mapSystjxx.objectVoid( strtemp1);

            jsReturn.put("info", "0");
        }


        if (operation.equals("haveAuthentic")) {
            String jfsfzh = jsStr.getString("jfsfzh");
            String yfsfzh = jsStr.getString("yfsfzh");
            String ajlbid = jsStr.getString("ajlbid");

            int jfid,yfid;
            String strSqlData ="select count(*) " +
                    "from register where sfzh ='"+jfsfzh+"'";
            jfid = mapSystjxx.objectInt(strSqlData);
            if (jfid ==0){
                jsReturn.put("info", "0");
                return jsReturn;
            }  else{
                strSqlData ="select UserId " +
                        "from register where sfzh ='"+jfsfzh+"'";
                jfid = mapSystjxx.objectInt(strSqlData);

            }
            strSqlData ="select count(*) " +
                    "from register where sfzh ='"+yfsfzh+"'";
            yfid = mapSystjxx.objectInt(strSqlData);
            if (yfid ==0){
                jsReturn.put("info", "0");
                return jsReturn;
            } else{
                strSqlData ="select UserId " +
                        "from register where sfzh ='"+yfsfzh+"'";
                yfid = mapSystjxx.objectInt(strSqlData);
            }

            strSqlData ="select count(*) as rowcount " +
                    "from  speed where registerId="+jfid+" and partyId ="+yfid+" and ModelType ="+ajlbid;
            int rowcount;
            //System.out.println("000:"+strSqlData);
            rowcount = mapSystjxx.objectInt(strSqlData);
            if (rowcount ==0){
                jsReturn.put("info", "0");
                return jsReturn;
            } else{
                strSqlData ="select state " +
                        "from  speed where registerId="+jfid+" and partyId ="+yfid+" and ModelType ="+ajlbid;

                List<Map<String, Object>> mapList= mapSystjxx.objectList(strSqlData);
                if (mapList.size() > 0){
                    for(Map<String, Object> maptemp :mapList){
                        rowcount = (int) maptemp.get("state");
                    }
                }
                if (rowcount ==3 ||rowcount ==4 || rowcount ==5){
                    jsReturn.put("info", "0");
                } else{
                    jsReturn.put("info", "1");
                }
            }
        }
        if (operation.equals("haveDzjz")) {
            String bh = jsStr.getString("bh");
            String cjr = jsStr.getString("cjr");
            String jzid = jsStr.getString("jzid");

            SimpleDateFormat getdatetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String createdate = getdatetime.format(new Date());// new Date()为获取当前系统时间
            String strSql;
            if (!bh.equals("")) {
                strSql ="update dzjz set sczt = 1,bh ='"+bh+"',cjr ="+cjr+",cjsj='"
                        +createdate+"' where type =1 and jzid ="+jzid;
            } else{
                strSql ="update dzjz set sczt = 2,bh ='',cjr ="+cjr+",accessurl ='', cjsj= null where type =1 and jzid ="+jzid;
            }

            //System.out.println("strSqlData :"+strSqlData);
            mapSystjxx.objectVoid(strSql);
            jsReturn.put("info", 0);
        }
        if (operation.equals("findidKnowbase")) {
            String knowid = jsStr.getString("knowid");
            String strSqlData="select  knowid,title,content,state from knowbase where knowid="+knowid;
            Map<String, Object> mapOne = mapSystjxx.objectOne(strSqlData);
            jsReturn.put("info", mapOne);
        }
        if (operation.equals("insertKnowbase")) {
            String title = jsStr.getString("title");
            String content = jsStr.getString("content");
            String state = jsStr.getString("state");
            String creater = jsStr.getString("creater");

            SimpleDateFormat getdatetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String createdate = getdatetime.format(new Date());// new Date()为获取当前系统时间

            String strSqlData="insert knowbase ( title,content,state,creater,createdate ) "+
                    "values ('"+title+"','"+content+"',"+state+","+creater+",'"+createdate+"')";
            //System.out.println(strSqlData);
            jsReturn.put("info", "1");
            mapSystjxx.objectVoid(strSqlData);
            jsReturn.put("info", "0");
        }

        if (operation.equals("updateKnowbase")) {
            String knowid = jsStr.getString("knowid");
            String title = jsStr.getString("title");
            String content = jsStr.getString("content");
            String state = jsStr.getString("state");
            String creater = jsStr.getString("creater");

            SimpleDateFormat getdatetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String createdate = getdatetime.format(new Date());// new Date()为获取当前系统时间

            String strSqlData="update knowbase set title='"+title+"',content='"
                    +content+"',state ="+state+",creater="+creater+",createdate ='"
                    +createdate+"' where knowid="+knowid;
            //System.out.println(strSqlData);
            jsReturn.put("info", "1");
            mapSystjxx.objectVoid(strSqlData);
            jsReturn.put("info", "0");
        }
        if (operation.equals("deleteKnowbase")) {
            String knowid = jsStr.getString("knowid");
            String state = jsStr.getString("state");

            String strSqlData="update knowbase set state ="+state+" where knowid="+knowid;
            //System.out.println(strSqlData);
            jsReturn.put("info", "1");
            mapSystjxx.objectVoid(strSqlData);
            jsReturn.put("info", "0");
        }
        /////////////////////////////////////

        if (operation.equals("findidCasebase")) {
            String caseid = jsStr.getString("caseid");
            String strSqlData="select  caseid,title,content,state," +
                    "case when type =1 then '文档' when type =2 then '视频' else '' end   as  type," +
                    "date_format(createdate, '%Y-%m-%d %T') as createdate,filepath  " +
                    "from casebase where caseid="+caseid;
            Map<String, Object> mapOne = mapSystjxx.objectOne(strSqlData);
            jsReturn.put("info", mapOne);
        }
        if (operation.equals("insertCasebase")) {
            String title = jsStr.getString("title");
            String content = jsStr.getString("content");
            String state = jsStr.getString("state");
            String creater = jsStr.getString("creater");

            String strSqlData="insert casebase ( title,content,state,creater,reading,filepath ) "+
                    "values ('"+title+"','"+content+"',"+state+","+creater+",0,'')";
            //System.out.println(strSqlData);

            String strSqlNextid = "SELECT auto_increment FROM information_schema.tables  " +
                    "WHERE table_name='casebase' and table_schema ='"+DataBaseName+"'";
            int  caseid = mapSystjxx.objectInt(strSqlNextid);

            jsReturn.put("info", "1");
            mapSystjxx.objectVoid(strSqlData);
            jsReturn.put("info", "0");
            jsReturn.put("caseid", caseid);
        }

        if (operation.equals("updateCasebase")) {
            String caseid = jsStr.getString("caseid");
            String title = jsStr.getString("title");
            String content = jsStr.getString("content");
            String creater = jsStr.getString("creater");
            String state = jsStr.getString("state");

            String strSqlData="update casebase set title='"+title+"',content='"
                    +content+"',creater="+creater+" ,state="+state+"  where caseid="+caseid;
            //System.out.println(strSqlData);
            jsReturn.put("info", "1");
            mapSystjxx.objectVoid(strSqlData);
            jsReturn.put("info", "0");
        }
        if (operation.equals("deleteCasebase")) {
            String caseid = jsStr.getString("caseid");
            String state = jsStr.getString("state");

            String strSqlData="update casebase set state ="+state+" where caseid="+caseid;
            //System.out.println(strSqlData);
            jsReturn.put("info", "1");
            mapSystjxx.objectVoid(strSqlData);
            jsReturn.put("info", "0");
        }

        if (operation.equals("readingCasebase")) {
            String caseid = jsStr.getString("caseid");
            String strSqlData="update casebase set reading =reading+1  where caseid="+caseid;
            //System.out.println(strSqlData);
            jsReturn.put("info", "1");
            mapSystjxx.objectVoid(strSqlData);
            jsReturn.put("info", "0");
        }
        //////////////////////////////

        if (operation.equals("findtjlx")) {
            String strSqlData = "select 0 as id,  '仲裁类别' as namedic  union all select assistanceId as id, title as namedic " +
                    "from  assistance where state  = 0 and assistancetype =1 "+
                    "order by id; ";
            //System.out.println("strSqlData:"+strSqlData);
            List<Map<String, Object>> mapList = mapSystjxx.objectList(strSqlData);
            jsReturn.put("info", mapList);
        }
        //社矫人员注册
        if (operation.equals("registerSocial")) {

            jsReturn.put("info", "1");
            String ryxm = jsStr.getString("ryxm");
            String ryxb = jsStr.getString("ryxb");
            String sfzh = jsStr.getString("sfzh");
            String sjhm = jsStr.getString("sjhm");
            String dlmm = jsStr.getString("dlmm");
            String dqid = jsStr.getString("dqid");
            String jgid = jsStr.getString("jgid");

            String SocialUserId;
            dlmm = encryptMD5(dlmm);
            SimpleDateFormat getdatetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String createdate = getdatetime.format(new Date());// new Date()为获取当前系统时间
            String sexTemp ="男";
            if (ryxb.equals("1")){
                sexTemp ="女";
            }
            if (ryxb.equals("0")){
                sexTemp ="男";
            }

            String strSqlData ="select count(*) from register where sfzh ='"+sfzh+"'";
            if (mapSystjxx.objectInt(strSqlData) == 0){

                String strSqlNextid = "SELECT auto_increment FROM information_schema.tables  " +
                        "WHERE table_name='register' and table_schema ='"+DataBaseName+"'";

                SocialUserId = mapSystjxx.objectInt(strSqlNextid)+"";

                strSqlData ="insert register (realname,sfzh,telephone,sex,loginstate,password,registerdate,lastlogintime )" +
                "values ('"+ryxm+"','"+sfzh+"','"+sjhm+"','"+sexTemp+"',0,'"+dlmm+"','"+createdate+"','"+createdate+"' )";
            } else{
                strSqlData ="select userid from register where sfzh ='"+sfzh+"'";
                SocialUserId = mapSystjxx.objectInt(strSqlData)+"";
                strSqlData="update register set realname ='"+ryxm+"',telephone='" +
                        sjhm+"',password ='"+dlmm+"', sex ='"+sexTemp+"'  where sfzh ='"+sfzh+"'";

            }

            mapSystjxx.objectVoid(strSqlData);

            strSqlData ="select count(*) from social where SocialIdCard ='"+sfzh+"'";

            if (mapSystjxx.objectInt(strSqlData) == 0){
                strSqlData="insert into social (SocialUserId,SocialUserName,Sex,SocialIdCard,SocialPhone,RegionId,DepartmentId,State) "+
                        "values ("+SocialUserId+",'"+ryxm+"',"+ryxb+",'"+sfzh+"','"+sjhm+"',"+dqid+","+jgid+",0)";
            } else{
                strSqlData="update social set SocialUserName ='"+ryxm+"',Sex="+ryxb+",SocialPhone='"+sjhm+
                        "',RegionId ="+dqid+",DepartmentId="+jgid+", SocialUserId ="+SocialUserId+"  where SocialIdCard='"+sfzh+"'";
            }

            mapSystjxx.objectVoid(strSqlData);
            jsReturn.put("info", "0");
        }


        //社矫课件类别
        if (operation.equals("findSocialVideoType")) {
            String strSqlData = "select DictionariesId as TypeId , DictionariesName as TypeName " +
                    "from  dictionaries where DictionariesTypeId =12  and  state  = 0 " +
                    "order by DictionariesId ";

            List<Map<String, Object>> mapList = mapSystjxx.objectList(strSqlData);
            jsReturn.put("info", mapList);

        }
        //社矫课件分页查询
        if (operation.equals("findPageSocialVideo")) {

            String StrCondition ="";

            String TypeId = jsStr.getString("TypeId");
            int pageSize = jsStr.getInteger("pageSize");
            int pageNum =  jsStr.getInteger("pageNum");
            int beginRow =  (pageNum -1)* pageSize;



            if (!TypeId.equals("") && !TypeId.equals("0")){
                StrCondition = StrCondition+ " and a.TypeId =" + TypeId ;
            }

            String strSqlData ="select a.SocialVideoId,a.Title,b.DictionariesName as  TypeName,date_format(a.CreateDate,'%Y-%m-%d %T') as CreateDate," +
                    "CONVERT (a.Cover USING utf8) as Cover,a.ReadNum ,a.Minutes ";
            String strSqlPublic = " from SocialVideo a, dictionaries b " +
                    "where a.state = 0  and  a.typeid = b.DictionariesId and a.del = 0 ";
            String StrLimit = " order by a.SocialVideoId desc limit "+beginRow+","+pageSize;
            String strSqlCount ="select count(*) as rowcount ";
            strSqlData  = strSqlData + strSqlPublic + StrCondition ;
            strSqlData  += StrLimit;
            strSqlCount = strSqlCount+strSqlPublic +StrCondition;
            //System.out.println("strSqlData:"+strSqlData);
            List<Map<String, Object>> mapList = mapSystjxx.objectList(strSqlData);
            int rowcount = mapSystjxx.objectInt(strSqlCount);
            int pageCount = rowcount/pageSize;
            int temp = rowcount%pageSize;
            if (temp > 0 ){
                pageCount +=1;
            }
            jsReturn.put("operation", operation);
            jsReturn.put("pageSize", pageSize);
            jsReturn.put("pageCount", pageCount);
            jsReturn.put("pageNum", pageNum);
            jsReturn.put("rowCount", rowcount);
            jsReturn.put("info", mapList);

        }

        //社矫课件查找ID
        if (operation.equals("findIdSocialVideo")) {
            String SocialVideoId = jsStr.getString("SocialVideoId");
            String SocialUserId  = jsStr.getString("SocialUserId");
            String strSqlData ="select SocialVideoId,Title,date_format(CreateDate,'%Y-%m-%d %T') as CreateDate,Video,ReadNum,LikedNum,Des  " +
                    "from SocialVideo   where  SocialVideoId ="+SocialVideoId;
            Map<String, Object> mapOne = mapSystjxx.objectOne(strSqlData);
            strSqlData ="select Liked  from  socialLiked where SocialUserId =" +SocialUserId+" and SocialVideoId="+SocialVideoId;

            Map<String, Object> mapOne1 = mapSystjxx.objectOne(strSqlData);
            if (mapOne1 == null ){
                jsReturn.put("Liked", 0 );
            } else{

                jsReturn.put("Liked", mapOne1.get("Liked") );
            }
            jsReturn.put("info", mapOne);
        }

        //社矫课件阅读
        if (operation.equals("readSocialVideo")) {

            String SocialVideoId = jsStr.getString("SocialVideoId");
            String strSqlData ="update  SocialVideo set ReadNum = ReadNum +1 where SocialVideoId = "+SocialVideoId;
            mapSystjxx.objectVoid(strSqlData);

            strSqlData ="select ReadNum from SocialVideo where  SocialVideoId = "+SocialVideoId;
            int ReadNum  = mapSystjxx.objectInt(strSqlData);

            //System.out.println("ReadNum:"+ReadNum);
            jsReturn.put("ReadNum", ReadNum);
        }

        //社矫课件点赞
        if (operation.equals("likedSocialVideo")) {
            String SocialVideoId = jsStr.getString("SocialVideoId");
            String SocialUserId  = jsStr.getString("SocialUserId");
            String Liked  = jsStr.getString("Liked");

            SimpleDateFormat getDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String createDate = getDateTime.format(new Date());// new Date()为获取当前系统时间

            String strSqlData ="select count(*) from SocialLiked " +
                    "where SocialVideoId = "+SocialVideoId+" and SocialUserId="+SocialUserId;
            //System.out.println("strSqlData1:"+strSqlData);
            int rowCount  = mapSystjxx.objectInt(strSqlData);
            if (rowCount ==0 ) {
                strSqlData ="insert into  SocialLiked (SocialVideoId,SocialUserId,Liked,CreateDate) values ( "
                        +SocialVideoId+","+SocialUserId+","+Liked+",'"+createDate+"')";
            } else{
                strSqlData ="update  SocialLiked set Liked ="+Liked+",CreateDate='"+createDate+
                        "'  where SocialVideoId = "+SocialVideoId+" and SocialUserId="+SocialUserId;
            }
            //System.out.println("strSqlData2:"+strSqlData);
            mapSystjxx.objectVoid(strSqlData);

            if (Integer.parseInt(Liked) ==0){
                strSqlData ="update  SocialVideo set LikedNum = LikedNum -1 where SocialVideoId = "+SocialVideoId;

            } else{
                strSqlData ="update  SocialVideo set LikedNum = LikedNum +1 where SocialVideoId = "+SocialVideoId;

            }
            //System.out.println("strSqlData3:"+strSqlData);
            mapSystjxx.objectVoid(strSqlData);
            strSqlData ="select LikedNum from SocialVideo where  SocialVideoId = "+SocialVideoId;
            //System.out.println("strSqlData4:"+strSqlData);
            int LikedNum  = mapSystjxx.objectInt(strSqlData);
            //System.out.println("LikedNum:"+LikedNum);
            jsReturn.put("LikedNum", LikedNum);
            jsReturn.put("Liked", Liked);
        }


        //社矫课件学习
        if (operation.equals("learnSocialVideo")) {

            String SocialVideoId = jsStr.getString("SocialVideoId");
            String SocialUserId  = jsStr.getString("SocialUserId");
            String strSqlData ="select count(*) from SocialLearn " +
                    "where SocialVideoId = "+SocialVideoId+" and SocialUserId="+SocialUserId;
            //System.out.println("strSqlData1:"+strSqlData);
            int rowCount  = mapSystjxx.objectInt(strSqlData);
            jsReturn.put("info", "1");
            if (rowCount == 0 ) {
                SimpleDateFormat getDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                String createDate = getDateTime.format(new Date());// new Date()为获取当前系统时间
                strSqlData ="insert into  SocialLearn (SocialVideoId,SocialUserId,CreateDate) values ( "
                        +SocialVideoId+","+SocialUserId+",'"+createDate+"')";
                mapSystjxx.objectVoid(strSqlData);
                jsReturn.put("info", "0");
            }
        }

        //社矫课件评论(增加)
        if (operation.equals("discussSocialVideo")) {

            String SocialVideoId = jsStr.getString("SocialVideoId");
            String SocialUserId  = jsStr.getString("SocialUserId");
            String  Discuss = jsStr.getString("Discuss");

            SimpleDateFormat getDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String createDate = getDateTime.format(new Date());// new Date()为获取当前系统时间
            String strSqlData ="insert into  socialdiscuss (SocialVideoId,SocialUserId,Discuss,CreateDate) values ( "
                    +SocialVideoId+","+SocialUserId+",'"+Discuss+"','"+createDate+"')";
            jsReturn.put("info", "1");
            //System.out.println("strSqlData1:"+strSqlData);
            mapSystjxx.objectVoid(strSqlData);
            jsReturn.put("info", "0");

        }

        //社矫课件评论(分页)
        if (operation.equals("findPageDiscuss")) {
            String SocialVideoId = jsStr.getString("SocialVideoId");
            int pageSize = jsStr.getInteger("pageSize");
            int pageNum =  jsStr.getInteger("pageNum");
            int beginRow =  (pageNum -1)* pageSize;

            String StrCondition ="";
            String strSqlData ="select b.RealName,CONVERT (b.Image USING utf8) as Image, a.Discuss,date_format(a.CreateDate,'%Y-%m-%d %T') as CreateDate," +
                    "CONVERT (b.Image USING utf8) as Image  ";
            String strSqlPublic = " from SocialDiscuss a, register b " +
                    "where a.SocialUserId = b.UserId  and  a.SocialVideoId = "+SocialVideoId;
            String StrLimit = " order by a.SocialVideoId desc limit "+beginRow+","+pageSize;
            String strSqlCount ="select count(*) as rowcount ";
            strSqlData  = strSqlData + strSqlPublic + StrCondition ;
            strSqlData  += StrLimit;
            strSqlCount = strSqlCount+strSqlPublic +StrCondition;

            //System.out.println("strSqlData:"+strSqlData);
            List<Map<String, Object>> mapList = mapSystjxx.objectList(strSqlData);
            int rowcount = mapSystjxx.objectInt(strSqlCount);
            int pageCount = rowcount/pageSize;
            int temp = rowcount%pageSize;
            if (temp > 0 ){
                pageCount +=1;
            }

            jsReturn.put("pageSize", pageSize);
            jsReturn.put("pageCount", pageCount);
            jsReturn.put("pageNum", pageNum);
            jsReturn.put("rowCount", rowcount);
            jsReturn.put("info", mapList);

        }

        //社矫人员判断
        if (operation.equals("userIdSocial")) {
            String userId = jsStr.getString("userId");
            String strSqlData ="select count(*)  from Social where SocialUserId =" +userId;
            int rowcount = mapSystjxx.objectInt(strSqlData);
            jsReturn.put("info", rowcount);

        }

        return jsReturn;
    }
    public JSONObject getPageSeek(String sfzh, String ajlb, String zxjg, String jdlb, String limit, String page) {

        sfzh = sfzh.trim();
        ajlb = ajlb.trim();
        zxjg = zxjg.trim();
        jdlb = jdlb.trim();
        limit = limit.trim();
        page = page.trim();

        int pagesize = Integer.parseInt(limit);
        int pagenum = Integer.parseInt(page);
        int beginrow = (pagenum - 1) * pagesize;

        String strSqlpublic = "from seek a,assistance b,fairworker c " +
                "where a.assistanceId = b.assistanceId " +
                "and a.czrId = c.FairWorkerId ";
        String strSqlData = "select a.seekId, a.dsrxm,a.dsrsfzh,b.title as ajlb, " +
                "case when zxjg = 0 then '已解决' when zxjg = 1 then  '未解决' " +
                "when  zxjg = 2 then '未解决，转其他部门'  else '无' end as zxjg ," +
                "c.WorkerName as cjr,date_format(a.czsj, '%Y-%m-%d %T')  as czsj ";
        String StrCondition = "";
        String strSqlCount = " select count(*) as rowcount ";
        String StrLimit = " order by czsj desc  limit " + beginrow + "," + pagesize;

        if (!sfzh.trim().equals("")) {
            StrCondition += " and a.dsrsfzh = '" + sfzh + "' ";
        }

        if (!ajlb.trim().equals("")) {
            StrCondition += " and a.assistanceId =" + ajlb;
        }

        if (!zxjg.trim().equals("")) {
            StrCondition += " and a.zxjg =" + zxjg;
        }

        if (!jdlb.trim().equals("")) {
            StrCondition += " and a.jdtype =" + jdlb;
        }
        strSqlData = strSqlData + strSqlpublic + StrCondition + StrLimit;
        strSqlCount = strSqlCount + strSqlpublic + StrCondition;
        //System.out.println("strSqlData:" + strSqlData);
        //System.out.println("strSqlCount:" + strSqlCount);
        List<Map<String, Object>> mapList = mapSystjxx.objectList(strSqlData);
        int rowcount = mapSystjxx.objectInt(strSqlCount);


        JSONObject jsReturn = new JSONObject();

        jsReturn.put("code",0);
        jsReturn.put("count",rowcount);
        jsReturn.put("msg", "成功");
        jsReturn.put("data", mapList);
        //System.out.println(jsReturn.toJSONString());

        return jsReturn;
    }

    public JSONObject getPageAuthenticZc(String ajzt,String isonline ,String sfzh, String ajlb, String ksrq, String jsrq, String limit, String page) {
        ajzt = ajzt.trim();
        isonline = isonline.trim();
        sfzh = sfzh.trim();
        ajlb = ajlb.trim();
        ksrq = ksrq.trim();
        jsrq = jsrq.trim();
        limit = limit.trim();
        page = page.trim();
        int pagesize = Integer.parseInt(limit);
        int pagenum = Integer.parseInt(page);
        int beginrow = (pagenum - 1) * pagesize;
        String strSqlpublic = "from authentic a " +
                "left join assistance b on a.assistanceId = b.assistanceId  " +
                "left join speed c on a.AuthenticId = c.ModelId " +
                "left join (select reg.*,authreg.authregId from register reg, authreginfo authreg where reg.UserId=authreg.dbId) d on a.sqfinfoId = d.authregId " +
                "left join (select reg.*,authreg.authregId from register reg, authreginfo authreg where reg.UserId=authreg.dbId) e on a.OtherPartyId = e.authregId ";
        String strSqlData = "select a.AuthenticId, d.RealName as jfxm,d.sfzh as jfsfzh,e.RealName as yfxm, " +
                "e.sfzh as yfsfzh,b.title as ajlb, " +
                "date_format(a.yysj, '%Y-%m-%d %T')  as yysj ,  " +
                "case when c.state=0 then '待仲裁' " +
                "when c.state=1 then '已指派' " +
                "when c.state=2 then '已排期' " +
                "when c.state=3 then '仲裁中' " +
                "when c.state=4 then '撤销' " +
                "when c.state=5 then '维持' " +
                "when c.state=6 then '仲裁流转' " +
                "when c.state=7 then '再指派' end  ajzt," +
                "date_format(a.CreateDate, '%Y-%m-%d %T')  as czsj ";
        String StrCondition = "";
        String strSqlCount = " select count(*) as rowcount ";
        String StrLimit = " order by a.CreateDate desc  limit " + beginrow + "," + pagesize;

        if (!ajzt.trim().equals("")) {
            StrCondition += " and c.state = "+ajzt ;
        }
        if (!isonline.trim().equals("")) {
            StrCondition += " and a.isonline = "+isonline ;
        }
        if (!sfzh.trim().equals("")) {
            StrCondition += " and ( d.sfzh='" + sfzh + "'  or e.sfzh='" + sfzh + "') ";
        }
        if (!ajlb.trim().equals("")) {
            StrCondition += " and a.assistanceId =" + ajlb;
        }

        if (!ksrq.trim().equals("")) {
            StrCondition += " and date_format(a.yysj, '%Y-%m-%d %T') >='"+ksrq+"'";
        }

        if (!jsrq.trim().equals("")) {
            StrCondition += " and date_format(a.yysj, '%Y-%m-%d %T') <='"+jsrq+"'";
        }
        strSqlData = strSqlData + strSqlpublic + StrCondition + StrLimit;
        strSqlCount = strSqlCount + strSqlpublic + StrCondition;
        //System.out.println("strSqlData:" + strSqlData);
        //System.out.println("strSqlCount:" + strSqlCount);
        List<Map<String, Object>> mapList = mapSystjxx.objectList(strSqlData);
        int rowcount = mapSystjxx.objectInt(strSqlCount);

        JSONObject jsReturn = new JSONObject();
        jsReturn.put("code",0);
        jsReturn.put("count",rowcount);
        jsReturn.put("msg", "成功");
        jsReturn.put("data", mapList);
        return jsReturn;
    }


    public JSONObject getPageAuthentic(String ajzt,String isonline ,String sfzh, String ajlb, String ksrq, String jsrq, String limit, String page) {
        ajzt = ajzt.trim();
        isonline = isonline.trim();
        sfzh = sfzh.trim();
        ajlb = ajlb.trim();
        ksrq = ksrq.trim();
        jsrq = jsrq.trim();
        limit = limit.trim();
        page = page.trim();

        int pagesize = Integer.parseInt(limit);
        int pagenum = Integer.parseInt(page);
        int beginrow = (pagenum - 1) * pagesize;




        String strSqlpublic = "from authentic a,assistance b, speed c ,register d,register e " +
                "where a.assistanceId = b.assistanceId " +
                "and a.AuthenticId = c.ModelId " +
                "and a.UserId = d.UserId " +
                "and a.OtherPartyId = e.UserId ";
        String strSqlData = "select a.AuthenticId, d.RealName as jfxm,d.sfzh as jfsfzh,e.RealName as yfxm, " +
                "e.sfzh as yfsfzh,b.title as ajlb, " +
                "date_format(a.yysj, '%Y-%m-%d %T')  as yysj ,  " +
                "case when c.state=0 then '待仲裁' " +
                "when c.state=1 then '已指派' " +
                "when c.state=2 then '已排期' " +
                "when c.state=3 then '仲裁中' " +
                "when c.state=4 then '撤销' " +
                "when c.state=5 then '维持' " +
                "when c.state=6 then '仲裁流转' " +
                "when c.state=7 then '再指派' end  ajzt," +
                "date_format(a.CreateDate, '%Y-%m-%d %T')  as czsj ";
        String StrCondition = "";
        String strSqlCount = " select count(*) as rowcount ";
        String StrLimit = " order by a.CreateDate desc  limit " + beginrow + "," + pagesize;

        if (!ajzt.trim().equals("")) {
            StrCondition += " and c.state = "+ajzt ;
        }
        if (!isonline.trim().equals("")) {
            StrCondition += " and a.isonline = "+isonline ;
        }
        if (!sfzh.trim().equals("")) {
            StrCondition += " and ( d.sfzh='" + sfzh + "'  or e.sfzh='" + sfzh + "') ";
        }
        if (!ajlb.trim().equals("")) {
            StrCondition += " and a.assistanceId =" + ajlb;
        }

        if (!ksrq.trim().equals("")) {
            StrCondition += " and date_format(a.yysj, '%Y-%m-%d %T') >='"+ksrq+"'";
        }

        if (!jsrq.trim().equals("")) {
            StrCondition += " and date_format(a.yysj, '%Y-%m-%d %T') <='"+jsrq+"'";
        }
        strSqlData = strSqlData + strSqlpublic + StrCondition + StrLimit;
        strSqlCount = strSqlCount + strSqlpublic + StrCondition;
        //System.out.println("strSqlData:" + strSqlData);
        //System.out.println("strSqlCount:" + strSqlCount);
        List<Map<String, Object>> mapList = mapSystjxx.objectList(strSqlData);
        int rowcount = mapSystjxx.objectInt(strSqlCount);

        JSONObject jsReturn = new JSONObject();
        jsReturn.put("code",0);
        jsReturn.put("count",rowcount);
        jsReturn.put("msg", "成功");
        jsReturn.put("data", mapList);
        return jsReturn;
    }

    public JSONObject getPageDZJZ(String sczt,String sfzh, String ajlb, String jzlb , String limit, String page) {
        sczt = sczt.trim();
        sfzh = sfzh.trim();
        ajlb = ajlb.trim();
        limit = limit.trim();
        page = page.trim();

        int pagesize = Integer.parseInt(limit);
        int pagenum = Integer.parseInt(page);
        int beginrow = (pagenum - 1) * pagesize;

        String strSqlpublic = "from dzjz f,dzjz i,dzjz h,assistance b,fairworker c,register d,register e ,speed g,authentic a  " +
                " where a.assistanceId = b.assistanceId " +
                "and a.UserId = d.UserId " +
                "and a.OtherPartyId = e.UserId " +
                "and a.AuthenticId = g.ModelId and g.ModelType =1  and  g.state in(4,5,6) "+
                "and a.zprId = c.FairWorkerId ";

        String strSqlData = "select a.AuthenticId, d.RealName as jfxm,d.sfzh as jfsfzh,e.RealName as yfxm, " +
                "e.sfzh as yfsfzh,b.title as ajlb, c.WorkerName as zpr," +
                "case when  g.state = 4 then '撤销' when g.state = 5  then '维持 ' when  g.state = 6 then '仲裁流转' end as ajzt ," +
                "case when  f.sczt = 0 then '否' when  f.sczt = 1 then '是' end tjssc," +
                "case when  i.sczt = 0 then '否' when  i.sczt = 1 then '是' when  i.sczt = 2 then '无'  end qrssc," +
                "case when  h.sczt = 0 then '否' when  h.sczt = 1 then '是' end jzsc," +
                "date_format(a.zpsj, '%Y-%m-%d %T')  as zpsj ";
        if (sczt.equals("0")){
            strSqlpublic += "and a.AuthenticId = f.ajId  and f.type = 1 and f.jztyle = 0 " +
                    "and a.AuthenticId = i.ajId  and i.type = 1  and i.jztyle = 1 " +
                    "and a.AuthenticId = h.ajId  and h.type = 1  and h.jztyle = 2 ";
            strSqlData += ",case when f.sczt +i.sczt + h.sczt >= 3  then '已上传'  else '未上传' end as sczt ";
        } else if (sczt.equals("1")){
            strSqlpublic += "and a.AuthenticId = f.ajId  and f.type = 1 and f.jztyle = 0 " +
                    "and a.AuthenticId = i.ajId  and i.type = 1  and i.jztyle = 1 " +
                    "and a.AuthenticId = h.ajId  and h.type = 1 and h.jztyle = 2 " +
                    "and ( f.sczt = 0 or i.sczt = 0 or h.sczt = 0 )";
            strSqlData += ",'未上传' as sczt ";
        } else if (sczt.equals("2")){
            strSqlpublic += "and a.AuthenticId = f.ajId  and f.type = 1 and f.sczt =1 and f.jztyle = 0 " +
                    "and a.AuthenticId = i.ajId  and i.type = 1 and i.sczt in (1,2) and i.jztyle = 1 " +
                    "and a.AuthenticId = h.ajId  and h.type = 1 and h.sczt =1 and h.jztyle = 2 ";
            strSqlData += ",'已上传' as sczt ";
        }

        String StrCondition = "";
        String strSqlCount = " select count(*) as rowcount ";
        String StrLimit = " order by a.CreateDate desc  limit " + beginrow + "," + pagesize;

        if (!sfzh.trim().equals("")) {
            StrCondition += " and ( d.sfzh='" + sfzh + "'  or e.sfzh='" + sfzh + "') ";
        }

        if (!ajlb.trim().equals("") && !ajlb.equals("0")) {

            StrCondition += " and a.assistanceId =" + ajlb;
        }
        strSqlData = strSqlData + strSqlpublic + StrCondition + StrLimit;
        strSqlCount = strSqlCount + strSqlpublic + StrCondition;
        //System.out.println("strSqlData:" + strSqlData);
        //System.out.println("strSqlCount:" + strSqlCount);
        List<Map<String, Object>> mapList = mapSystjxx.objectList(strSqlData);
        int rowcount = mapSystjxx.objectInt(strSqlCount);

        JSONObject jsReturn = new JSONObject();
        jsReturn.put("code",0);
        jsReturn.put("count",rowcount);
        jsReturn.put("msg", "成功");
        jsReturn.put("data", mapList);
        return jsReturn;
    }

    public JSONObject getPageKnowbase(String title, String state, String limit, String page) {
        title = title.trim();
        state = state.trim();
        limit = limit.trim();
        page = page.trim();

        int pagesize = Integer.parseInt(limit);
        int pagenum = Integer.parseInt(page);
        int beginrow = (pagenum - 1) * pagesize;

        String strSqlData = "select a.knowid,a.title,a.state,b.WorkerName as creater,date_format(a.createdate, '%Y-%m-%d %T') as createdate ";
        String strSqlpublic = " from Knowbase a,fairworker b where a.creater = b.FairWorkerId and a.title like '%" +title+"%' ";
        if (!state.equals("0")){
            strSqlpublic +=" and a.state="+state;
        } else {
            strSqlpublic +=" and a.state in ( 1,2) ";
        }

        String strSqlCount = " select count(*) as rowcount ";
        String StrLimit = " order by a.CreateDate desc  limit " + beginrow + "," + pagesize;


        strSqlData = strSqlData + strSqlpublic + StrLimit;
        strSqlCount = strSqlCount + strSqlpublic ;
        //System.out.println("strSqlData:" + strSqlData);
        //System.out.println("strSqlCount:" + strSqlCount);
        List<Map<String, Object>> mapList = mapSystjxx.objectList(strSqlData);
        int rowcount = mapSystjxx.objectInt(strSqlCount);

        JSONObject jsReturn = new JSONObject();
        jsReturn.put("code",0);
        jsReturn.put("count",rowcount);
        jsReturn.put("msg", "成功");
        jsReturn.put("data", mapList);
        return jsReturn;
    }

    public JSONObject getPageCasebase(String title, String type, String state, String limit, String page) {
        title = title.trim();
        type = type.trim();
        state = state.trim();
        limit = limit.trim();
        page = page.trim();

        int pagesize = Integer.parseInt(limit);
        int pagenum = Integer.parseInt(page);
        int beginrow = (pagenum - 1) * pagesize;

        String strSqlData = "select a.caseid,a.title," +
                "case when a.type =1 then '文档' else '视频' end as type," +
                "a.state," +
                "a.reading,b.WorkerName as creater,date_format(a.createdate, '%Y-%m-%d %T') as createdate ";
        String strSqlpublic = " from casebase a,fairworker b where a.creater = b.FairWorkerId and a.title like '%" +title+"%' ";
        if (!state.equals("0")){
            strSqlpublic +=" and a.state="+state;
        } else {
            strSqlpublic +=" and a.state in ( 1,2) ";
        }

        if (!type.equals("0")){
            strSqlpublic +=" and a.type="+type;
        } else {
            strSqlpublic +=" and a.type in ( 1,2) ";
        }

        String strSqlCount = " select count(*) as rowcount ";
        String StrLimit = " order by a.CreateDate desc  limit " + beginrow + "," + pagesize;


        strSqlData += strSqlpublic + StrLimit;
        strSqlCount += strSqlpublic ;
        //System.out.println("strSqlData:" + strSqlData);
        //System.out.println("strSqlCount:" + strSqlCount);
        List<Map<String, Object>> mapList = mapSystjxx.objectList(strSqlData);
        int rowcount = mapSystjxx.objectInt(strSqlCount);

        JSONObject jsReturn = new JSONObject();
        jsReturn.put("code",0);
        jsReturn.put("count",rowcount);
        jsReturn.put("msg", "成功");
        jsReturn.put("data", mapList);
        return jsReturn;
    }


    public String encryptMD5(String source) {
        if (source == null) {
            source = "";
        }
        Md5Hash md5 = new Md5Hash(source, "d");
        return md5.toString();
    }


    //base64字符串转化成图片
    //@paramimgStr base64字符串
    //@paramimageName 本地路径

    public String GenerateImage(String imgStr) {
        String returnStr ="";
        //对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) //图像数据为空
            return returnStr;

        BASE64Decoder decoder = new BASE64Decoder();
        int inttemp = imgStr.indexOf("base64") +7;
        String tempa = imgStr.substring(0,inttemp);
        String tempb = tempa.substring(11,inttemp-8);
        //System.out.println(DataBaseName);
        //System.out.println(FileUploadPath);
        UUID uuid = UUID.randomUUID();
        String  id =uuid.toString().replace("-", "");
        String imageName = FileUploadPath+"TPDoc/"+id+"."+tempb;
        //System.out.println("imageName:"+imageName);
        imgStr = imgStr.substring(inttemp);
        File dir = new File( FileUploadPath+"TPDoc");

        if (!dir.isDirectory()) {//文件目录不存在，就创建一个
            boolean mkdirs = dir.mkdirs();
            if (!mkdirs){
                return "上传失败";
            }
        }

        try {
            //Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) { //调整异常数据
                    b[i] += 256;
                }
            }
            //生成jpeg图片
            OutputStream out = new FileOutputStream(imageName);
            out.write(b);
            out.flush();
            out.close();
            returnStr = "/TPDoc/"+id+"."+tempb;
            //System.out.println("returnStr:"+returnStr);
            return returnStr;
        } catch (Exception e) {
            //System.out.println("11111eName");
            return returnStr;
        }
    }

}
