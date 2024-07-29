package com.java.train.generator.server;

import com.java.train.generator.util.FreemarkerUtil;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ServerGenerator {


    static  String toPath="\\train\\member\\src\\main\\java\\com\\java\\train\\member\\mapper\\";
    private  static final String AbsolutePath=new File("").getAbsolutePath();

//        static {
//            new File(toPath).mkdirs();
//        };

//    public static  void maind(String[] args) throws IOException, TemplateException{
//        FreemarkerUtil.initConfig("mapper.ftl");
//        Map<String ,Object> params=new HashMap<>();
//        params.put("entity","Member");
//        params.put("module","member");
//        FreemarkerUtil.generator(getToPath+"MemberMapper.java",params);
//
//    }

    public static void main(String[] args) throws TemplateException, IOException {
        String module="member";
        String name="Ticket";
        genMapper(module,"mapper",name);    //生成mapper
        genService(module,"service",name);  //生成service
        //genReq(module,"req",name);          //生成req
        //genResp(module,"resp",name);        //生成resp
        genServiceImpl(module,"service",name);//生成ServiceImpl
        //genController(module,"controller",name); //生产controller

    }
    private static void  genController(String module, String lei, String name) throws IOException, TemplateException {
        String addPath="\\train\\"+module+"\\src\\main\\java\\com\\java\\train\\"+module+"\\"+lei+"\\";
        String toPath=AbsolutePath+addPath;
        FreemarkerUtil.initConfig("Controller.ftl");
        Map<String,Object> params=new HashMap<>();
        params.put("entity",name);
        params.put("module",module);
        FreemarkerUtil.generator(toPath+name+"Controller.java",params);

    }
    private static void  genServiceImpl(String module, String lei, String name) throws IOException, TemplateException {
        String addPath="\\train\\"+module+"\\src\\main\\java\\com\\java\\train\\"+module+"\\"+lei+"\\";
        String toPath=AbsolutePath+addPath;
        FreemarkerUtil.initConfig("serviceImpl.ftl");
        Map<String,Object> params=new HashMap<>();
        params.put("entity",name);
        params.put("module",module);
        FreemarkerUtil.generator(toPath+name+"ServiceImpl.java",params);

    }
    private static void  genMapper(String module, String lei, String name) throws IOException, TemplateException {
        String addPath="\\train\\"+module+"\\src\\main\\java\\com\\java\\train\\"+module+"\\"+lei+"\\";
        String toPath=AbsolutePath+addPath;
        FreemarkerUtil.initConfig("mapper.ftl");
        Map<String,Object> params=new HashMap<>();
        params.put("entity",name);
        params.put("module",module);
        FreemarkerUtil.generator(toPath+name+"Mapper.java",params);

    }

    private static void  genService(String module, String lei, String name) throws IOException, TemplateException {
        String addPath="\\train\\"+module+"\\src\\main\\java\\com\\java\\train\\"+module+"\\"+lei+"\\";
        String toPath=AbsolutePath+addPath;
        FreemarkerUtil.initConfig("service.ftl");
        Map<String,Object> params=new HashMap<>();
        params.put("entity",name);
        params.put("module",module);
        FreemarkerUtil.generator(toPath+name+"Service.java",params);
    }

    private static void  genReq(String module, String lei, String name) throws IOException, TemplateException {
        String addPath="\\train\\"+module+"\\src\\main\\java\\com\\java\\train\\"+module+"\\"+lei+"\\";
        String toPath=AbsolutePath+addPath;
        FreemarkerUtil.initConfig("queryResp.ftl");
        Map<String,Object> params=new HashMap<>();
        params.put("entity",name);
        params.put("module",module);
        FreemarkerUtil.generator(toPath+name+"Req.java",params);

    }
    private static void  genResp(String module, String lei, String name) throws IOException, TemplateException {
        String addPath="\\train\\"+module+"\\src\\main\\java\\com\\java\\train\\"+module+"\\"+lei+"\\";
        String toPath=AbsolutePath+addPath;
        FreemarkerUtil.initConfig("queryReq.ftl");
        Map<String,Object> params=new HashMap<>();
        params.put("entity",name);
        params.put("module",module);
        FreemarkerUtil.generator(toPath+name+"Resp.java",params);

    }
}
