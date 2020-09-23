package com.xzx.service;


import com.alibaba.fastjson.JSONObject;
import com.xzx.common.WordToPdf;
import com.xzx.dao.MapSystjxx;
import com.xzx.model.Post;
import com.xzx.model.PostAnnex;
import com.xzx.model.PostReply;
import com.xzx.model.PostReplyAnnex;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SerPostReply {

    @Value("${file.uploadFolder}")
    public String uploadFolder;

    @Value("${file.DataBaseName}")
    private String DataBaseName;


    @Value("${file.staticAccessPath}")
    private String staticAccessPath;
    @Resource
    private MapSystjxx mapSystjxx;


    public JSONObject wordToPdf(String obj) {

        JSONObject jsReturn = new JSONObject();
        jsReturn.put("Code", "11111111");
        JSONObject jsStr = JSONObject.parseObject((String) obj);
        String pathA = jsStr.getString("path");
        String folder = jsStr.getString("folder");

        UUID uuid = UUID.randomUUID();
        String id = uuid.toString().replace("-", "");
        if (folder == null || folder.equals("")) {
            folder = "apppdf";
        }
        String path = uploadFolder + folder + "/";
        File dir = new File(path);
        if (!dir.isDirectory()) {//文件目录不存在，就创建一个
            boolean mkdirs = dir.mkdirs();
            if (!mkdirs) {
                jsReturn.put("Msg", "创建文件夹失败");
                return jsReturn;
            }
        }

        String dataPath = staticAccessPath.substring(0, staticAccessPath.length() - 2) + folder + "/" + id + ".pdf";
        String pathB = uploadFolder + folder + "/" + id + ".pdf";
        WordToPdf d = new WordToPdf();
        d.wordToPDF(pathA, pathB);
        jsReturn.put("Code", "00000000");
        jsReturn.put("Msg", dataPath);
        return jsReturn;
    }

    public JSONObject fileUpload(MultipartFile uploadFile, String folder) {
        JSONObject jsReturn = new JSONObject();
        jsReturn.put("Code", "11111111");
        String path = uploadFolder + folder + "/";
        //java生成ID
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString().replace("-", "");
        File dir = new File(path);
        if (!dir.isDirectory()) {//文件目录不存在，就创建一个
            boolean mkdirs = dir.mkdirs();
            if (!mkdirs) {
                jsReturn.put("Msg", "上传失败");
            }
        }
        try {
            String originalFileName = uploadFile.getOriginalFilename();
            if (originalFileName == null) {
                jsReturn.put("Msg", "上传失败");
            }
            String extensionName = originalFileName.substring(originalFileName.lastIndexOf("."));
            extensionName = extensionName.toLowerCase();

            String filename = id + extensionName;
            //服务端保存的文件对象
            File fileServer = new File(dir, filename);
            //2，实现上传
            uploadFile.transferTo(fileServer);
            if (extensionName.equals(".docx") || extensionName.equals(".doc")) {
                WordToPdf d = new WordToPdf();
                String worfile = path + filename;
                String pdffile = path + id + ".pdf";
                d.wordToPDF(worfile, pdffile);
                filename = id + ".pdf";
            }
            String dataPath = "/" + folder + "/" + filename;
            jsReturn.put("Code", "00000000");
            jsReturn.put("Msg", dataPath);


            return jsReturn;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsReturn;
    }

    public JSONObject getPostById(String ajid) {

        JSONObject jsReturn = new JSONObject();
        jsReturn.put("Code", "11111111");
        jsReturn.put("Msg", "获取post信息失败");
        String strSqlData = "select a.tzid,a.ajid ,a.jfid ,a.yfid ,a.title,a.tznr,a.cjrid ,a.kklx," +
                "date_format(a.cjsj, '%Y-%m-%d %T') as cjsj," +
                "date_format(a.hfjs, '%Y-%m-%d %T') as hfjs,CONVERT (b.Image USING utf8) as Image,b.workerName " +
                "from post a, fairworker b where a.cjrid = b.FairWorkerId and a.ajid =" + ajid;
        Map<String, Object> mapOne = mapSystjxx.objectOne(strSqlData);
        if (mapOne != null) {
            jsReturn.put("Code", "00000000");
            jsReturn.put("Msg", "获取post信息成功");
            mapOne.put("listPostAnnex", null);
            strSqlData = "select * from postAnnex where tzid =" + mapOne.get("tzid");
            List<Map<String, Object>> mapList = mapSystjxx.objectList(strSqlData);
            mapOne.put("listPostAnnex", mapList);
        }
        jsReturn.put("data", mapOne);
        return jsReturn;
    }

    public JSONObject getPageAuthentic(String ajZt, String isOnLine, String sfZh, String ajLb,
                                       String ksRq, String jsRq, String workerId, String limit, String page) {
        ajZt = ajZt.trim();
        isOnLine = isOnLine.trim();
        sfZh = sfZh.trim();
        ajLb = ajLb.trim();
        ksRq = ksRq.trim();
        jsRq = jsRq.trim();
        workerId = workerId.trim();
        int pageSize = Integer.parseInt(limit);
        int pageNum = Integer.parseInt(page);
        int beginRow = (pageNum - 1) * pageSize;

        String strSqlPublic = "from authentic a,assistance b, speed c ,register d,register e " +
                "where a.assistanceId = b.assistanceId " +
                "and a.AuthenticId = c.ModelId " +
                "and a.UserId = d.UserId " +
                "and a.OtherPartyId = e.UserId ";
        String strSqlData = "select a.AuthenticId as ajid, a.UserId as jfid,d.RealName as jfxm,d.sfZh as jfsfzh, a.OtherPartyId as yfid,e.RealName as yfxm, " +
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
                "date_format(a.CreateDate, '%Y-%m-%d %T')  as czsj,a.FairworkerId as workerid  ";
        String StrCondition = "";
        String strSqlCount = " select count(*) as rowcount ";
        String StrLimit = " order by a.CreateDate desc  limit " + beginRow + "," + pageSize;

        if (!ajZt.equals("") && !ajZt.equals("-1")) {
            StrCondition += " and c.state = " + ajZt;
        }
        if (!isOnLine.equals("") && !isOnLine.equals("-1")) {
            StrCondition += " and a.isonline = " + isOnLine;
        }
        if (!sfZh.equals("")) {
            StrCondition += " and ( d.sfzh='" + sfZh + "'  or e.sfzh='" + sfZh + "') ";
        }
        if (!ajLb.equals("") && !ajLb.equals("0")) {
            StrCondition += " and a.assistanceId =" + ajLb;
        }

        if (!ksRq.equals("")) {
            StrCondition += " and date_format(a.yysj, '%Y-%m-%d %T') >='" + ksRq + "'";
        }

        if (!jsRq.equals("")) {
            StrCondition += " and date_format(a.yysj, '%Y-%m-%d %T') <='" + jsRq + "'";
        }
        if (!jsRq.equals("")) {
            StrCondition += " and date_format(a.yysj, '%Y-%m-%d %T') <='" + jsRq + "'";
        }

        if (!workerId.equals("")) {
            System.out.println("workerId:" + workerId);
            StrCondition += " and a.FairworkerId =" + workerId;
        }
        strSqlData = strSqlData + strSqlPublic + StrCondition + StrLimit;

        strSqlCount = strSqlCount + strSqlPublic + StrCondition;

        List<Map<String, Object>> mapList = mapSystjxx.objectList(strSqlData);
        int rowcount = mapSystjxx.objectInt(strSqlCount);

        JSONObject jsReturn = new JSONObject();
        jsReturn.put("code", 0);
        jsReturn.put("count", rowcount);
        jsReturn.put("msg", "成功");
        jsReturn.put("data", mapList);
        return jsReturn;
    }

    public JSONObject getPageReply(String ajId, String limit, String page) {

        int pageSize = Integer.parseInt(limit);
        int pageNum = Integer.parseInt(page);
        int beginRow = (pageNum - 1) * pageSize;
        JSONObject jsReturn = new JSONObject();


        String strSqlPublic = "from postReply a where  a.ajId =" + ajId;
        String strSqlData = "select a.hfid,a.ajid ,a.hfnr ,a.cjrid ,a.cjrlx ," +
                "date_format(a.cjsj, '%Y-%m-%d %T') as cjsj ";
        String strSqlCount = "select count(*) as rowcount ";
        String StrLimit = " order by a.cjSj desc  limit " + beginRow + "," + pageSize;

        strSqlData = strSqlData + strSqlPublic + StrLimit;
        strSqlCount = strSqlCount + strSqlPublic;

        List<Map<String, Object>> mapList = mapSystjxx.objectList(strSqlData);
        List<Object> mapListDo = new ArrayList<Object>();

        for (Map<String, Object> map : mapList) {
            String hfId = map.get("hfid").toString();
            //System.out.println("hfId:"+hfId);


            String strSqlDataHf = "select * from  postReplyAnnex  where hfId =" + hfId;
            List<Map<String, Object>> mapListFj = mapSystjxx.objectList(strSqlDataHf);
            if (mapListFj != null) {
                map.put("listPostReplyAnnex", mapListFj);
            }

            String strSqlDatacjr;
            int cjrlx = (int) map.get("cjrlx");
            int cjrid = (int) map.get("cjrid");
            if (cjrlx ==0){
                strSqlDatacjr = "select CONVERT (Image USING utf8) as Image,WorkerName as cjrname from   fairworker  where  FairWorkerId=" + cjrid;

            } else{

                strSqlDatacjr = "select CONVERT (Image USING utf8) as Image,RealName as cjrname from   register  where  UserId=" + cjrid;
            }

            Map<String, Object> mapOne = mapSystjxx.objectOne(strSqlDatacjr);
            if (mapOne != null) {
                map.put("Image",mapOne.get("Image"));
                map.put("cjrname",mapOne.get("cjrname"));
            }
            mapListDo.add(map);
        }

        int rowCount = mapSystjxx.objectInt(strSqlCount);

        int pageCount = rowCount / pageSize;
        int temp = rowCount % pageSize;
        if (temp > 0) {
            pageCount += 1;
        }
        jsReturn.put("pageSize", pageSize);
        jsReturn.put("pageCount", pageCount);
        jsReturn.put("pageNum", pageNum);
        jsReturn.put("Code", "00000000");
        jsReturn.put("count", rowCount);
        jsReturn.put("Msg", "成功获取PageReply列表信息");
        jsReturn.put("data", mapListDo);

        //System.out.println(mapListDo);

        return jsReturn;
    }

    public JSONObject getPageReplyByUser(String ajId, String userId,String limit, String page) {

        int pageSize = Integer.parseInt(limit);
        int pageNum = Integer.parseInt(page);
        int beginRow = (pageNum - 1) * pageSize;
        JSONObject jsReturn = new JSONObject();

        jsReturn.put("Code", "11111111");
        jsReturn.put("Msg", "获取PageReply列表信息失败");

        String strSqlPublic = "from (select a.hfid,a.ajid,a.hfnr,a.cjrid,a.cjrlx,date_format(a.cjsj, '%Y-%m-%d %T') as cjsj " +
                "from postReply a,post b " +
                "where  a.ajId =" + ajId+" and  a.ajId =b.ajId and a.kklx = 0 and (b.jfid ="+userId+" or b.yfid ="+userId+") " +
                "union all select a.hfid,a.ajid,a.hfnr,a.cjrid,a.cjrlx,date_format(a.cjsj, '%Y-%m-%d %T') as cjsj " +
                "from postReply a,post b " +
                "where  a.ajId =" + ajId+" and  a.ajId =b.ajId and a.kklx = 1 and (b.jfid ="+userId+" ))aaa";
        String strSqlData = "select hfid,ajid ,hfnr ,cjrid ,cjsj,cjrlx ";
        String strSqlCount = "select count(*) as rowcount ";
        String StrLimit = " order by aaa.cjsj desc  limit " + beginRow + "," + pageSize;

        strSqlData = strSqlData + strSqlPublic + StrLimit;
        strSqlCount = strSqlCount + strSqlPublic;

        List<Map<String, Object>> mapList = mapSystjxx.objectList(strSqlData);
        List<Object> mapListDo = new ArrayList<Object>();

        for (Map<String, Object> map : mapList) {
            String hfId = map.get("hfid").toString();
            //System.out.println("hfId:"+hfId);
            String strSqlDataHf = "select * from  postReplyAnnex  where hfId =" + hfId;
            List<Map<String, Object>> mapListFj = mapSystjxx.objectList(strSqlDataHf);
            if (mapListFj != null) {
                map.put("listPostReplyAnnex", mapListFj);
            }

            String strSqlDatacjr;
            int cjrlx = (int) map.get("cjrlx");
            int cjrid = (int) map.get("cjrid");
            if (cjrlx ==0){
                strSqlDatacjr = "select CONVERT (Image USING utf8) as Image,WorkerName as cjrname from   fairworker  where  FairWorkerId=" + cjrid;

            } else{

                strSqlDatacjr = "select CONVERT (Image USING utf8) as Image,RealName as cjrname from   register  where  UserId=" + cjrid;
            }

            Map<String, Object> mapOne = mapSystjxx.objectOne(strSqlDatacjr);
            if (mapOne != null) {
                map.put("Image",mapOne.get("Image"));
                map.put("cjrname",mapOne.get("cjrname"));
            }

            mapListDo.add(map);
        }
        int rowCount = mapSystjxx.objectInt(strSqlCount);

        int pageCount = rowCount / pageSize;
        int temp = rowCount % pageSize;
        if (temp > 0) {
            pageCount += 1;
        }
        jsReturn.put("pageSize", pageSize);
        jsReturn.put("pageCount", pageCount);
        jsReturn.put("pageNum", pageNum);
        jsReturn.put("Code", "00000000");
        jsReturn.put("count", rowCount);
        jsReturn.put("Msg", "成功获取PageReply列表信息");
        jsReturn.put("data", mapListDo);

        return jsReturn;
    }

    public JSONObject insertPost(Post post) {

        JSONObject jsReturn = new JSONObject();
        jsReturn.put("Code", "11111111");
        jsReturn.put("Msg", "失败添加PageReply信息");

        int ajId = post.getAjid();
        int yfId = post.getYfid();
        int jfId = post.getJfid();
        int kkLx = post.getKklx();
        //System.out.println("strSqlData:" + post.getListPostAnnex());
        List<PostAnnex> listPostAnnex = post.getListPostAnnex();
        String Title = post.getTitle();
        String tzNr = post.getTznr();
        int cjrId = post.getCjrid();
        String cjSj = getDatetime();


        String strTemp1 = "insert post (cjrId,ajId,yfId,jfId,Title,cjSj,tzNr,kkLx) ";
        String strTemp2 = "values(" + cjrId + "," + ajId + "," + yfId + "," + jfId + ",'" + Title + "','" + cjSj + "','" + tzNr + "'," + kkLx + ")";

        String strSqlData = strTemp1 + strTemp2;
        //System.out.println("strSqlData:"+strSqlData);
        String strSqlNextid = "SELECT auto_increment FROM information_schema.tables  " +
                "WHERE table_name='post' and table_schema ='" + DataBaseName + "'";
        int tzid = mapSystjxx.objectInt(strSqlNextid);
        mapSystjxx.objectVoid(strSqlData);
        if (listPostAnnex !=null){
            for (PostAnnex postAnnex : listPostAnnex) {
                String fjmc = postAnnex.getFjmc();
                int fjlx = postAnnex.getFjlx();
                String fjurl = postAnnex.getFjurl();
                String strSqlDataFj = "insert into PostAnnex (tzid,fjlx,fjurl,fjmc) " +
                        "values (" + tzid + "," + fjlx + ",'" + fjurl + "','" + fjmc + "')";
                //System.out.println("strSqlData :" + strSqlDataFj);
                mapSystjxx.objectVoid(strSqlDataFj);
            }
        }

        jsReturn.put("Code", "00000000");
        jsReturn.put("Msg", "成功添加PageReply信息");
        return jsReturn;
    }


    public JSONObject updatePost(Post post) {

        JSONObject jsReturn = new JSONObject();
        jsReturn.put("Code", "11111111");
        jsReturn.put("Msg", "修改PageReply信息失败");
        //System.out.println("updatePost :");
        int jfId = post.getJfid();
        List<PostAnnex> listPostAnnex = post.getListPostAnnex();
        String Title = post.getTitle();
        String tzNr = post.getTznr();
        int tzId = post.getTzid();
        int kkLx = post.getKklx();
        int yfId = post.getYfid();

        //System.out.println("updatePost tzId :" + tzId);
        String strSqlData = "update post set jfid =" + jfId + ", yfId =" + yfId + ",kkLx =" + kkLx + ",Title ='" + Title + "',tzNr='" + tzNr + "' where tzid=" + tzId;
        //System.out.println("updatePost strSqlData :" + strSqlData);
        mapSystjxx.objectVoid(strSqlData);
        strSqlData = "delete from  PostAnnex  where tzid=" + tzId;
        mapSystjxx.objectVoid(strSqlData);

        for (PostAnnex postAnnex : listPostAnnex) {
            String fjmc = postAnnex.getFjmc();
            int fjlx = postAnnex.getFjlx();
            String fjurl = postAnnex.getFjurl();
            String strSqlDataFj = "insert into PostAnnex (tzid,fjlx,fjurl,fjmc) " +
                    "values (" + tzId + "," + fjlx + ",'" + fjurl + "','" + fjmc + "')";
            //System.out.println("strSqlData :" + strSqlDataFj);
            mapSystjxx.objectVoid(strSqlDataFj);
        }
        jsReturn.put("Code", "00000000");
        jsReturn.put("Msg", "修改PageReply信息成功");
        return jsReturn;
    }

    public JSONObject insertReply(PostReply postReply) {


        //System.out.println(postReply);
        JSONObject jsReturn = new JSONObject();
        jsReturn.put("Code", "11111111");
        jsReturn.put("Msg", "失败添加PageReply信息");

        List<PostReplyAnnex> listPostReplyAnnex = postReply.getListPostReplyAnnex();

        int ajId = postReply.getAjid();
        String hfNr = postReply.getHfnr();

        int cjrId = postReply.getCjrid();


        String strSqlKklx = "SELECT kklx FROM post where  ajid = " +ajId;

        int kkLx = mapSystjxx.objectInt(strSqlKklx);
        int cjrLx = postReply.getCjrlx();
        String cjSj = getDatetime();
        String strTemp1 = "insert postReply (cjrId,ajId,hfNr,cjSj,kkLx,cjrlx) ";
        String strTemp2 = "values(" + cjrId + "," + ajId + ",'" + hfNr + "','" + cjSj + "',"+kkLx+","+cjrLx+")";

        String strSqlData = strTemp1 + strTemp2;
        //System.out.println("strSqlData111112223232 :" + strSqlData);
        String strSqlNextid = "SELECT auto_increment FROM information_schema.tables  " +
                "WHERE table_name='postReply' and table_schema ='" + DataBaseName + "'";
        int hfId = mapSystjxx.objectInt(strSqlNextid);

        mapSystjxx.objectVoid(strSqlData);
        if (listPostReplyAnnex !=null){
            for (PostReplyAnnex postReplyAnnex : listPostReplyAnnex) {
                String fjmc = postReplyAnnex.getFjmc();
                int fjlx = postReplyAnnex.getFjlx();
                String fjurl = postReplyAnnex.getFjurl();
                String strSqlDataFj = "insert into postReplyAnnex (hfid,fjlx,fjurl,fjmc) " +
                        "values (" + hfId + "," + fjlx + ",'" + fjurl + "','" + fjmc + "')";
                //System.out.println("strSqlData :" + strSqlDataFj);
                mapSystjxx.objectVoid(strSqlDataFj);
            }
        }
        jsReturn.put("Code", "00000000");
        jsReturn.put("Msg", "成功添加PageReply信息");
        return jsReturn;
    }

    public JSONObject getCaseWorkerCount(String workerId) {

        JSONObject jsReturn = new JSONObject();
        jsReturn.put("Code", "11111111");
        jsReturn.put("Msg", "失败获取信息");
        String strSqlPublic;
        if (workerId.equals("")){
            strSqlPublic = " where a.IsOnline in (0,1) ";

        } else {
            strSqlPublic = " where a.IsOnline in (0,1) and a.fairWorkerId =" + workerId;
        }


        String strSqlData = "select" +
                " case when a.IsOnline =0 and  b.State =0 then '在线办理待仲裁'" +
                " when a.IsOnline =0 and  b.State =1 then '在线办理已指派'" +
                " when a.IsOnline =0 and  b.State =2 then '在线办理已排期'" +
                " when a.IsOnline =0 and  b.State =3 then '在线办理仲裁中'" +
                " when a.IsOnline =0 and  b.State =4 then '在线办理撤销'" +
                " when a.IsOnline =0 and  b.State =5 then '在线办理维持'" +
                " when a.IsOnline =0 and  b.State =6 then '在线办理仲裁流转'" +
                " when a.IsOnline =0 and  b.State =7 then '在线办理再指派'" +
                " when a.IsOnline =1 and  b.State =0 then '现场办理待仲裁'" +
                " when a.IsOnline =1 and  b.State =1 then '现场办理已指派'" +
                " when a.IsOnline =1 and  b.State =2 then '现场办理已排期'" +
                " when a.IsOnline =1 and  b.State =3 then '现场办理仲裁中'" +
                " when a.IsOnline =1 and  b.State =4 then '现场办理撤销'" +
                " when a.IsOnline =1 and  b.State =5 then '现场办理维持'" +
                " when a.IsOnline =1 and  b.State =6 then '现场办理仲裁流转'" +
                " when a.IsOnline =1 and  b.State =7 then '现场办理再指派'" +
                " end as stateName, count(*) as rowCount" +
                " from  authentic a,speed b" +strSqlPublic +
                " and   a.AuthenticId = b.ModelId" +
                " group by a.IsOnline,b.State";

        //System.out.println("strSqlData:" + strSqlData);
        //System.out.println("strSqlCount:" + strSqlCount);
        List<Map<String, Object>> mapList = mapSystjxx.objectList(strSqlData);
        jsReturn.put("Code", "00000000");
        jsReturn.put("Msg", "成功获取列表信息");
        jsReturn.put("data", mapList);

        return jsReturn;
    }


    public JSONObject getCaseState() {
        JSONObject jsReturn = new JSONObject();
        jsReturn.put("Code", "11111111");
        jsReturn.put("Msg", "失败获取信息");
        String strSqlData =
                "select -1 as code,'案件状态' as name union " +
                        "select 0 as code,'待仲裁' as name union " +
                        "select 1 as code,'已指派' as name union " +
                        "select 2 as code,'仲裁中' as name union " +
                        "select 3 as code,'撤销' as name union " +
                        "select 5 as code,'维持' as name union " +
                        "select 6 as code,'仲裁流转' as name union " +
                        "select 7 as code,'再指派' as name  ";
//        System.out.println("strSqlData:" + strSqlData);
        List<Map<String, Object>> mapList = mapSystjxx.objectList(strSqlData);
        jsReturn.put("Code", "00000000");
        jsReturn.put("Msg", "成功获取信息");
        jsReturn.put("data", mapList);

        return jsReturn;
    }

    public JSONObject getAjByUserId(String userId, String limit, String page) {

        int pageSize = Integer.parseInt(limit);
        int pageNum = Integer.parseInt(page);
        int beginRow = (pageNum - 1) * pageSize;

        JSONObject jsReturn = new JSONObject();

        jsReturn.put("Code", "11111111");
        jsReturn.put("Msg", "失败获取信息");

        String strSqlPublic = "from ( select a.AuthenticId as ajid,b.title as ajlb, date_format(a.CreateDate, '%Y-%m-%d %T')  as czsj " +
                "        from authentic a,assistance b ,post c " +
                "        where c.kklx = 0 and a.assistanceId = b.assistanceId  and a.AuthenticId = c.ajid  and ( a.UserId =" + userId +" or a.OtherPartyId =" + userId +") " +
                "        union all " +
                "        select a.AuthenticId as ajid,b.title as ajlb, date_format(a.CreateDate, '%Y-%m-%d %T')  as czsj " +
                "        from authentic a,assistance b ,post c " +
                "        where c.kklx = 1 and a.assistanceId = b.assistanceId  and a.AuthenticId = c.ajid  and a.UserId =" + userId + ") aaa ";

        String strSqlData = "select  ajid,ajlb, czsj ";

        String strSqlCount = "select count(*) as rowcount ";
        String StrLimit = " order by aaa.czsj desc  limit " + beginRow + "," + pageSize;

        strSqlData = strSqlData + strSqlPublic + StrLimit;
        strSqlCount = strSqlCount + strSqlPublic;
        //System.out.println("strSqlData:" + strSqlData);
        //System.out.println("strSqlCount:" + strSqlCount);
        List<Map<String, Object>> mapList = mapSystjxx.objectList(strSqlData);
        int rowCount = mapSystjxx.objectInt(strSqlCount);
        int pageCount = rowCount / pageSize;
        int temp = rowCount % pageSize;
        if (temp > 0) {
            pageCount += 1;
        }
        jsReturn.put("pageSize", pageSize);
        jsReturn.put("pageCount", pageCount);
        jsReturn.put("pageNum", pageNum);
        jsReturn.put("Code", "00000000");
        jsReturn.put("Msg", "成功获取信息");
        jsReturn.put("count", rowCount);
        jsReturn.put("data", mapList);
        return jsReturn;
    }


    public String getDatetime() {
        SimpleDateFormat getDatetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String createDate = getDatetime.format(new Date());// new Date()为获取当前系统时间
        return createDate;
    }


}
