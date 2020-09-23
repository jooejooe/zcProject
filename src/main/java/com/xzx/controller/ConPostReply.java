package com.xzx.controller;

import com.alibaba.fastjson.JSONObject;
import com.xzx.model.Post;
import com.xzx.model.PostAuthentic;
import com.xzx.model.PostReply;
import com.xzx.service.SerPostReply;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/postReply")

@Api(value = "案件消息相关接口", description = "postReply")

public class ConPostReply {
    @Resource
    private SerPostReply serPostReply;

    /////////////////////////////////////////////////////////
    @PostMapping("wordToPdf")
    @ApiOperation(value = "word文件转pdf文件",
            notes = "word文件转pdf文件"
                    + "<br/>例子:localhost:5663/postReply/wordToPdf"
                    + "<br/>body:  {'path': 'http://118.25.144.143:5664/judicial/FileUpload/e7d2ebc1a1f4e3739a76ec1ccf2ee2a1.docx'}",
            produces = "application/json")
    public JSONObject wordToPdf( @ApiParam(name = "post",
            value = "folder:选填项 存放文件夹，默认apppdf "
                    + "<br/>path：必填项 word文件路径",
            required = true)
                                     @RequestBody String obj) {
        return serPostReply.wordToPdf(obj);
    }

    /////////////////////////////////////////////////////////
    @PostMapping("fileUpload")
    @ApiOperation(value = "文件上传并转文件",
            notes = "word文件上传并转pdf文件"
                    + "<br/>例子:localhost:5663/postReply/fileUpload"
                    + "<br/>body:  {'path': 'http://118.25.144.143:5664/judicial/FileUpload/e7d2ebc1a1f4e3739a76ec1ccf2ee2a1.docx'}",
            produces = "application/json")

    public JSONObject fileUpload( MultipartFile uploadFile, HttpServletRequest request) {
        String folder = request.getParameter("folder");
        return serPostReply.fileUpload(uploadFile,folder);
    }
    /////////////////////////////////////////////////////////
    @GetMapping("getCaseState")
    @ApiOperation(value = "获取案件状态",
            notes = "获取案件状态"
                    + "<br/>例子:localhost:5663/postReply/getCaseState",
            produces = "application/json")

    @ApiResponses({
            @ApiResponse(code = 200, message = "ok", response=Post.class),
    })
    public JSONObject getCaseState() {
        return serPostReply.getCaseState( );
    }
    /////////////////////////////////////////////////////////
    @GetMapping("getPageAuthentic")
    @ApiOperation(value = "获取案件查询列表",
            notes = "获取案件查询列表"
                    + "<br/>例子:localhost:5663/postReply/getPageAuthentic",
            produces = "application/json")

    @ApiResponses({
            @ApiResponse(code = 200, message = "ok", response= PostAuthentic.class),
    })

    public JSONObject getPageAuthentic(
            @ApiParam("案件状态:选填项,默认为空(全部)") @RequestParam(defaultValue = "") String ajzt,
            @ApiParam("办理类型:选填项,默认为空(全部)") @RequestParam(defaultValue = "") String isonline,
            @ApiParam("身份证号:选填项,默认为空(全部)") @RequestParam(defaultValue = "") String sfzh,
            @ApiParam("案件类别:选填项,默认为空(全部)") @RequestParam(defaultValue = "") String ajlb,
            @ApiParam("开始日期:选填项,默认为空(全部)") @RequestParam(defaultValue = "") String ksrq,
            @ApiParam("结束日期:选填项,默认为空(全部)") @RequestParam(defaultValue = "") String jsrq,
            @ApiParam("工作人员ID:选填项,默认为空(全部)") @RequestParam(defaultValue = "") String workerid,
            @ApiParam("当前页数:必填项,默认为 1 数值") @RequestParam(defaultValue = "1") String limit,
            @ApiParam("每页条数:必填项,默认为10 数值") @RequestParam(defaultValue = "10") String page){
            return serPostReply.getPageAuthentic(ajzt,isonline,sfzh,ajlb,ksrq,jsrq,workerid,limit,page);
        //return serPostReply.getCaseState( );
    }
    /////////////////////////////////////////////////////////
    @GetMapping("getPostById")
    @ApiOperation(value = "获取案件消息",
            notes = "获取案件消息"
                    + "<br/>例子:localhost:5663/postReply/getPostById?ajId=147",
            produces = "application/json")

    @ApiResponses({
            @ApiResponse(code = 200, message = "ok", response=Post.class),
    })
    public JSONObject getPostById(
            @ApiParam("案件ID:必填项") @RequestParam(defaultValue = "0") String ajId) {
        return serPostReply.getPostById(ajId);
    }

    /////////////////////////////////////////////////////////
    @GetMapping("getPageReply")
    @ApiOperation(value = "获取案件消息回复列表",
            notes = "获取案件消息回复列表"
                    + "<br/>例子:localhost:5663/postReply/getPageReply?ajId=0&limit=10&page=1",
            produces = "application/json")

    @ApiResponses({
            @ApiResponse(code = 200, message = "ok", response=PostReply.class),
    })

    public JSONObject getPageReply(
            @ApiParam("案件ID:必填项,默认为0") @RequestParam(defaultValue = "0") String ajId,
            @ApiParam("每页条数:必填项,默认为10") @RequestParam(defaultValue = "10") String limit,
            @ApiParam("当前页数:必填项,默认为 1") @RequestParam(defaultValue = "1") String page) {
        return serPostReply.getPageReply(ajId, limit, page);
    }

    /////////////////////////////////////////////////////////
    @GetMapping("getAjByUserId")
    @ApiOperation(value = "获取案件消息列表（甲被申请方）",
            notes = "获取案件消息列表"
                    + "<br/>例子:localhost:5663/postReply/getAjByUserId?userId=207&limit=10&page=1",
            produces = "application/json")

    public JSONObject getAjByUserId(
            @ApiParam("用户ID:必填项,默认为0")  @RequestParam(defaultValue = "0") String userId,
            @ApiParam("每页条数:必填项,默认为10") @RequestParam(defaultValue = "10") String limit,
            @ApiParam("当前页数:必填项,默认为 1") @RequestParam(defaultValue = "1") String page) {
        return serPostReply.getAjByUserId(userId, limit, page);
    }
    /////////////////////////////////////////////////////////

    @GetMapping("getPageReplyByUser")
    @ApiOperation(value = "获取案件消息回复列表（甲被申请方）",
            notes = "获取案件消息回复列表"
                    + "<br/>例子:localhost:5663/postReply/getAjByUserId?userId=207&limit=10&page=1",
            produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok", response=Post.class),
    })

    public JSONObject getPageReplyByUser(
            @ApiParam("案件ID:必填项,默认为0")  @RequestParam(defaultValue = "0") String ajId,
            @ApiParam("用户ID:必填项,默认为0")  @RequestParam(defaultValue = "0") String userId,
            @ApiParam("每页条数:必填项,默认为10") @RequestParam(defaultValue = "10") String limit,
            @ApiParam("当前页数:必填项,默认为 1") @RequestParam(defaultValue = "1") String page) {
        return serPostReply.getPageReplyByUser(ajId,userId, limit, page);
    }
    /////////////////////////////////////////////////////////
    @PostMapping("insertPost")
    @ApiOperation(value = "增加案件消息贴",
            notes = "增加案件消息贴"
                    + "<br/>例子:localhost:5663/postReply/insertPost"
                    + "<br/>body: {'ajid': 0, 'cjrid': 0, 'jfid': 0, 'title': 'string', 'yfid': 0}",
            produces = "application/json")
    public JSONObject insertPost( @ApiParam(name = "post",
            value = "ajid：必填项"
                    + "<br/>title：必填项"
                    + "<br/>jfid：必填项"
                    + "<br/>yfid：选填项"
                    + "<br/>cjrid：必填项"
                    + "<br/>tzid：不填项"
                    + "<br/>cjsj：不填项"
                    + "<br/>hfsj：不填项",
            required = true)
            @RequestBody Post post) {
        return serPostReply.insertPost(post);
    }
    /////////////////////////////////////////////////////////
    @PostMapping("updatePost")
    @ApiOperation(value = "修改案件消息贴",
            notes = "修改案件消息贴"
                    + "<br/>例子:localhost:5663/postReply/updatePost",
            produces = "application/json")
    public JSONObject updatePost( @ApiParam(name = "post",
            value = "tzid：必填项"
                    + "<br/>title：必填项"
                    + "<br/>jfid：必填项"
                    + "<br/>yfid：选填项"
                    + "<br/>cjrid：不填项"
                    + "<br/>ajid：不填项"
                    + "<br/>cjsj：不填项"
                    + "<br/>hfsj：不填项",
            required = true)
                                  @RequestBody Post post) {
        return serPostReply.updatePost(post);
    }
    /////////////////////////////////////////////////////////
    @PostMapping("insertReply")
    @ApiOperation(value = "增加案件消息回复",
            notes = "增加案件消息回复",
            produces = "application/json")
    public JSONObject insertReply( @ApiParam(name = "postReply",
            value = "ajid：必填项"
                    + "<br/>hfnr：选填项"
                    + "<br/>fjlx：选填项"
                    + "<br/>fjurl：选填项"
                    + "<br/>cjrid：必填项"
                    + "<br/>fjmc：选填项"
                    + "<br/>cjrlx: 创建人类型（0 工作人员，1甲被申请方）：必填项"
                    + "<br/>cjsj：不填项",
            required = true)
                                  @RequestBody PostReply postReply) {

        //System.out.println(postReply);
        return serPostReply.insertReply (postReply);
    }

    /////////////////////////////////////////////////////////
    @GetMapping("getCaseWorkerCount")
    @ApiOperation(value = "获取工作人员相关案件状态的数量",
            notes = "获取工作人员相关案件状态的数量"
            + "<br/>例子:localhost:5663/postReply/getCaseWorkerCount?workerId=657"
            + "<br/>返回说明:"
            + "<br/>stateName:案件状态名称"
            + "<br/>rowCount:案件状态数量",
            produces = "application/json")

    public JSONObject getCaseWorkerCount(
            @ApiParam("工作人员ID:必填项") @RequestParam(defaultValue = "") String workerId) {
        return serPostReply.getCaseWorkerCount(workerId);
    }







}
