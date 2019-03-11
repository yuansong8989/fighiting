package com.springys.Controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.springys.Common.*;
import com.springys.Dao.JpaRepository;
import com.springys.Service.Servicemain;
import com.springys.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by yzd on 2019/2/1.
 */
@CrossOrigin
@Slf4j
@org.springframework.stereotype.Controller
public class Controller {
    String a;
    @Resource
    private Servicemain servicemain;
    @Resource
    private RuseltData ruseltData;
    static List<String> b = new ArrayList<>();
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisUtils redisUtils;

    @RequestMapping("main")
    public Uniti LoginMain(@RequestBody RequestObject request1) {
        if (request1.getData().getNumber() == 0) {
            return ResultMain.error(MessageCode.error);
        } else {
            ruseltData = servicemain.getDataBaseList();
            return ResultMain.success(MessageCode.success, ruseltData);
        }
    }

    @RequestMapping("exitDatabase")
    public Uniti exitDatabase(@RequestBody Five five) {
        if (five.getData() == null) {
            return ResultMain.error(MessageCode.error);
        } else {
            try {
                MyConfig myConfig = new MyConfig();
                myConfig.setPassword(five.getData().getPassword());
                myConfig.setUserName(five.getData().getUserName());
                myConfig.setPort(five.getData().getPort());
                myConfig.setSortName(five.getData().getSortName());
                myConfig.setAuthenticationDatabase(five.getData().getAuthenticationDatabase());
                myConfig.setCharSetName(five.getData().getCharSetName());
                String config = JSONObject.toJSONString(myConfig);
                SimpleDateFormat dateFormat = new SimpleDateFormat();
                five.getData().setCreate_time(new Date());
                servicemain.exitDatabase(config, five);
                log.info("我爱我家");
                return ResultMain.success(MessageCode.success, null);
            } catch (Exception e) {
                e.printStackTrace();
                return ResultMain.error(MessageCode.Exception1);
            }
        }
    }

    @RequestMapping("deleteDatabase")
    public Uniti deleteDatabase(@RequestBody RequestObject requestObject) {
        log.info("当前异常是" + requestObject.getData());
        if (requestObject.getData().getId() > 0) {
            servicemain.deleteDatabase(requestObject.getData().getId());
            return ResultMain.success(MessageCode.success, null);
        } else {
            return ResultMain.error(MessageCode.error);
        }
    }
//书写注册功能 注册用户密码存至redis中 login登陆也是

    @RequestMapping("login")
    public Uniti login(@RequestBody RequestObject requestObject) throws Exception {
        redisUtils.creatRedis("password", "123456");

        if (requestObject.getData() == null) {
            log.info("输入密码为空");
            return ResultMain.error(MessageCode.error);
        } else {
            if (servicemain.login(redisUtils.requestValue("password")) == null) {
                log.info("密码错误");
                return ResultMain.error(MessageCode.error);
            } else {
                log.info("登陆成功");
//                JSONObject jsonObject =new JSONObject();
//                jsonObject.put("token", MD5Utils.md5password());
                Long a = 122L;
                JwtToken jwtToken = new JwtToken();
                b.add(jwtToken.createToken(a));//生成taken string
                JSONObject jsonObject = new JSONObject();
                b.add(MD5Utils.md5password());
                jsonObject.put("token", b);
                log.info("输出b" + b.get(0));
                log.info("输出b" + b.get(1));
//                log.info(MD5Utils.md5password(b));
//                Map<String, Claim> map = new HashMap<>();
//                map = JwtToken.verifyToken(b);
//                Set<String> set = map.keySet();
//                Iterator<String> it = set.iterator();
//                while (it.hasNext()) {
//                    log.info(it.next());
//                }
//                System.out.println(map.get("user_id"));
                return ResultMain.success1(jsonObject);
            }
        }
    }

    @RequestMapping("check")
    public String check() {
        return "成功";
    }

    public static String getB(int i) {
        return b.get(i);
    }


    @RequestMapping("redis")
    @ResponseBody
    public String redisTest(@RequestBody RdisKV rdisKV) {
        String redisValue = stringRedisTemplate.opsForValue().get(rdisKV.getKey());
        if (StringUtils.isEmpty(redisValue)) {
            stringRedisTemplate.opsForValue().set(rdisKV.getKey(), rdisKV.getValue());
            return "操作成功！";
        }
        if (!redisValue.equals(rdisKV.getValue())) {
            stringRedisTemplate.opsForValue().set(rdisKV.getKey(), rdisKV.getValue());
            return "操作成功！";
        } else {
            a = "dsdada";
            ValueOperations valueOperations1 = redisTemplate.opsForValue();
            valueOperations1.set("name3", a, 1, TimeUnit.HOURS);
            ValueOperations valueOperations = redisTemplate.opsForValue();
            String name3 = (String) valueOperations.get("name3");
            System.out.println(name3);
            return String.format("redis中已存在[key=%s,value=%s]的数据！", rdisKV.getKey(), rdisKV.getValue());

        }
    }

//    @RequestMapping("cache")
//    public String cacheTest() {
//        String value = servicemain.getValue();
//        log.info(value);
//        return value;
//    }

    //查询某某用户信息
    @RequestMapping("SelectInformation")
    public Uniti SelectInformation(@RequestBody RequestObject requestObject) {
        //先查询redis中是否存在该用户信息，如果没有查询数据库然后存至redis
//        List<String> list=new ArrayList<>();
//        list.add("123");
//        list.add("455");
//        list.add("ddd");
//        redisTemplate.opsForList().leftPush("user1",list);
//        List<String> list1=(List<String>)redisTemplate.opsForList().leftPop("user1");
////        list1.get(0);
//        log.info("输出左边存入栈第一个元素："+list1.get(0));
//        return ResultMain.success1("成功");
        String requestuser = requestObject.getData().getUser();
        List<String> list = stringRedisTemplate.opsForList().range(requestuser, 0, -1);
        if (list.size() == 0) {
            BigData bigData = servicemain.Sinformation(requestuser);
            stringRedisTemplate.opsForList().leftPush(requestuser, bigData.getPlantform_val());
            stringRedisTemplate.opsForList().leftPush(requestuser, bigData.getPlatform_name());
            stringRedisTemplate.opsForList().leftPush(requestuser, String.valueOf(bigData.getPlatform_id()));
            log.info("查询数据库里的数据添加进入redis");
            List<String> list2 = stringRedisTemplate.opsForList().range(requestuser, 0, -1);
            return ResultMain.success1(list2);
        } else {
            stringRedisTemplate.opsForHash().put("hash1", "a", "b");
            stringRedisTemplate.opsForHash().put("hash1", "v", "d");
            stringRedisTemplate.opsForHash().put("hash1", "v1", "d2");
            Map<Object, Object> map = stringRedisTemplate.opsForHash().entries("hash1");
            Map<Object, Object> map1 = stringRedisTemplate.opsForHash().entries("hash");
            log.info("存在查询的redis数据");
            log.info("返回第一个个map值：" + map.get("a"));//b
            log.info("返回第一个个map值：" + map1.get("1"));//2
            for (Map.Entry<Object, Object> entry : map1.entrySet()) {
                log.info("Key = " + entry.getKey() + ", Value = " + entry.getValue());
            }
            return ResultMain.success1(list);
        }
    }

    //前端传入md5密码进入服务端 ，服务端根据用户名并获取 该密码 然后进行MD5 将该md5对比传入的md5数据进行判断 则登陆成功 用户名与明文密码存到redis 用map存
    @Autowired
    private Md5PR md5PR;

    @RequestMapping("Md5Login")

    public Uniti Md5Login(@RequestBody Md5Password md5Password) {
        String password = MD5Utils.md5password1(md5Password.getMd5password());//用户密码MD5
        Map<Object, Object> map = stringRedisTemplate.opsForHash().entries("users");
        Set<Object> set = map.keySet();
        for (Object r : set) {
            log.info("输出set：" + r);
        }
        boolean checkpassword = md5PR.RequestUserName(md5Password.getUsername(), map);//判断是否有该名字
        if (checkpassword) {
            log.info("判断密码");
            String b1 = (String) map.get(md5Password.getUsername());
            String c1 = MD5Utils.md5password1(b1);//正确密码
            if (password.equals(c1)) {
                return ResultMain.success1("登陆成功");
            } else {
                return ResultMain.success1("密码错误");
            }
        } else {
            return ResultMain.success1("没有该用户用户名");
        }
    }

    @Autowired
    private JpaRepository jpaRepository;

    //        @RequestMapping("/Jparunning")
//        public Uniti  Jparunning(@RequestBody RequestObject requestObject){
//            List<Students> list=new ArrayList<>();
//            list=jpaRepository.findAll();
//            Integer id=requestObject.getData().getNumber();
//            System.out.println(id);
//            Students one = jpaRepository.getOne(id);
//            return ResultMain.success1(one);
//        }
    @RequestMapping("Jparunning1")
    public Uniti Jparunning1(@RequestBody RequestObject requestObject) {
        Integer id = requestObject.getData().getNumber();
        System.out.println(id);
        List<Students> one = jpaRepository.select1(id);
        return ResultMain.success1(one);
    }

    //分页查询
    @ResponseBody
    @RequestMapping("PageSelect")
    public Uniti PageSelect(@RequestBody RequestObject requestObject) {
        PageInfo<Students> pageInfo = servicemain.PageSelect(requestObject.getData().getPageNum(), requestObject.getData().getPageSize());
//   pageInfo.setOrderBy("uid DESC");
        log.info(pageInfo.getList().toString());
        return ResultMain.success1(pageInfo);
    }

    //批量文件上传 上传至我的数据库 其他为空暂时不上传
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Uniti upload(@RequestParam("file") MultipartFile[] multipartFile) {
        if (multipartFile.length == 0) {
            return ResultMain.error(MessageCode.error);
        } else {
            try {
                for (int i = 0; i < multipartFile.length; i++) {
//                    multipartFile[i].transferTo(new File("path"));
                    FileUtils.writeByteArrayToFile(new File("g:/upload/" + multipartFile[i].getOriginalFilename()), multipartFile[i].getBytes());//字节数组
                    servicemain.InsertFileName(multipartFile[i].getOriginalFilename());
                }

                return ResultMain.success1("成功");
            } catch (IOException e) {
                e.printStackTrace();
                return ResultMain.error(MessageCode.error);
            }
        }
    }

    //文件下载
    @RequestMapping("/testHttpMessageDown")
    @ResponseBody
    public String download(HttpServletResponse response) throws IOException {
        File file = new File("g:/upload/zc.txt" );
        String filename = "我唉你.txt";
        InputStream is = new FileInputStream(file);//抽象类必须依靠子类fILE***实现 输入流
        // 要一次读取多个字节时，经常用到InputStream.available()方法，这个方法可以在读写操作前先得知数据流里有多少个字节可以读取。
//        is.read(body);//读取b.length个字节的数据放到b数组中
        filename = new String(filename.getBytes("UTF-8"), "ISO8859-1");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attchement;filename=" + filename);
        response.setStatus(200);
//        headers.add("Content-Disposition", "attchement;filename=" + file.getName());
//        HttpStatus statusCode = HttpStatus.OK;
        OutputStream out = response.getOutputStream();
        byte[] body = new byte[is.available()];//读取字节s数量
        int i = 0;
//        while (i!=1){
//            out.write(body,0,i);
//            i=is.read(body);
//        }
        while ((i = is.read(body)) != -1) {
            out.write(body, 0, i);
        }
//        out.write(body);
        is.close();
        out.close();

        return "成功";
    }


    @RequestMapping("PublicJavaTools")
    @ResponseBody//写入http正文 需要使用该注解
    public Uniti JavaTools() throws IOException {
        IOUtils ioUtils = new IOUtils();
        File file = new File("g:/upload/java学xi.txt");
        File file1 = new File("g:/upload/1.txt");
        InputStream is = new FileInputStream(file);//输入流
        OutputStream outputStream = new FileOutputStream(file1);//输出流
        byte[] bytes = null;
//        String g="ddkdmd";
//        ioUtils.toInputStream(g);
        bytes = new byte[is.available()];
        is.read(bytes);//获取byte【】字节数组
//        outputStream.write(bytes);
        ioUtils.write(bytes, outputStream);
//        ioUtils.copyLarge(is,outputStream);
        is.close();
        outputStream.close();
        return ResultMain.success1(bytes);
    }

    @RequestMapping("downfile")
    @ResponseBody
    public String downFile(HttpServletResponse response) throws IOException {
        String fileUrl = "g:/upload/package-lock.json";
        String fileName = "package-lock.json";
        File file = new File(fileUrl);
        response.setContentType("application/force-download");
        response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        fis = new FileInputStream(file);
        bis = new BufferedInputStream(fis);
        byte[] buffer = new byte[bis.available()];
        OutputStream outputStream = response.getOutputStream();
        int i = bis.read(buffer);
        while (i != 1) {
            outputStream.write(buffer, 0, i);
            i = bis.read(buffer);
        }
        outputStream.close();
        bis.close();
        fis.close();
        return "辰翁工";
    }

    //批量删除文件 先查询 该文件名字
    @Transactional
    @RequestMapping("/deletefile")
    @ResponseBody
    public Uniti deleteFile(@RequestBody RequestObject requestObject) throws IOException {
        int number[] = requestObject.getData().getFileid();
//        String j = servicemain.selectUrl2(1).getFile_url();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < number.length; i++) {
            log.info("ss" + number[i]);
            int a = number[i];
            list.add(servicemain.selectUrl2(a).getFile_url());
            servicemain.delete(number[i]);
        }
        String headerUrl = "g:/upload/";
        for (int i = 0; i < number.length; i++) {
//            list.get(i).concat(headerUrl);//得到路劲
            File file = new File(headerUrl.concat(list.get(i)));
            FileUtils.deleteQuietly(file);
        }
//     File file=new File("eeqe");
//     FileUtils.deleteDirectory(file);
        return ResultMain.success1("删除成功");
    }

    //根据脚本名称来模糊查询脚本
    @RequestMapping("NoClearSelect")
    @ResponseBody
    public Uniti NoClearSelect(@RequestBody RequestObject requestObject) {
        List<Students> list = servicemain.ClearSelect(requestObject.getData().getMohuName());//list中存在所要查询的学生对象
        List<String> listFileName = new ArrayList<>();
        String headerUrl = "g:/upload/";
        for (Students students : list) {
            listFileName.add(headerUrl.concat(students.getFile_url()));
        }
        return ResultMain.success1(listFileName);

    }

    //点击脚本分类 查询出该类别下所有的脚本文件 //添加时间
    @RequestMapping("classify")
    @ResponseBody
    public Uniti classifySelect(@RequestBody RequestObject requestObject) {
        int a = requestObject.getData().getClassfiyid();//输入分类id
        List<Students> list = servicemain.classifySelect(a);
        List<String> list1 = new ArrayList<>();
        for (Students students : list) {
            list1.add(students.getFile_url());
        }
        return ResultMain.success1(list1);
    }

    //添加脚本分类 当前脚本分类有5个
    @RequestMapping("addclassify")
    @ResponseBody
    public Uniti addClassify(@RequestBody RequestObject requestObject) {
        List<classIfy> list = requestObject.getData().getList();//传入的新增分类名称
        servicemain.addClassify(list);//插入成功
        //设置uid相等于id

        return ResultMain.success1("插入成功");
    }

    //给脚本分类 给脚本文件分类
    @RequestMapping("fileclassify")
    @ResponseBody
    public Uniti fileClassify(@RequestBody RequestObject requestObject) {
        int a = requestObject.getData().getFileuid();
        int b = requestObject.getData().getFileid1();
        servicemain.fileClassify(a, b);
        return ResultMain.success1("分类成功");
    }

    //分页查询某类别的脚本
    @RequestMapping("pageselectfile")
    @ResponseBody
    public Uniti pageSelectFile(@RequestBody RequestObject requestObject) {
        FilePage filePage = servicemain.pageFIle(requestObject.getData().getPageNum(), requestObject.getData().getPageSize(), requestObject.getData().getClassfiyid());
        return ResultMain.success1(filePage);
    }

    //文本替换
    @RequestMapping("replacefile")
    @ResponseBody
    public Uniti replaceFile(@RequestParam("id") int id, @RequestParam("file") MultipartFile file) throws Exception {
        FileUtils.writeByteArrayToFile(new File("g:/upload/" + file.getOriginalFilename()), file.getBytes());
//   servicemain.updatetime(file.getOriginalFilename(), id);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String a=simpleDateFormat.format(new Date());
//         Date date1=  simpleDateFormat.parse(a);
//        log.info("日期格式1"+date1);
        Timestamp timestamp=Timestamp.valueOf(a);
        log.info("日期"+timestamp);
//        Timestamp d = new Timestamp(System.currentTimeMillis());
//        log.info("日期格式2"+d);
//        Calendar calendar=Calendar.getInstance();
//        String nb=simpleDateFormat.format(calendar.getTime());
//        log.info(nb);
        servicemain.updatetime1(id,timestamp);
        return ResultMain.success1("替换成功");
    }
    //运行脚本文件
}