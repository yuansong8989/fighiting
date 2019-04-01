package com.springys.Service.implement;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springys.Common.Assist;
import com.springys.Common.QuartzManager;
import com.springys.Common.RequestResultEnum;
import com.springys.Common.TokenResult;
import com.springys.Dao.MainDao;
import com.springys.Service.Servicemain;
import com.springys.entity.*;
import com.springys.exception.ComputeException;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.usermodel.Row;
import org.quartz.SchedulerException;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by yzd on 2019/2/2.
 */
@Service
@Slf4j
public class ServiceImplements implements Servicemain {
    @Resource
    private MainDao mainDao;

    @Override
    public RuseltData getDataBaseList() {
        List<Aj> all = mainDao.getDataBaseList();//查出全部数据
        List<Aj> next1 = all.stream().filter(allstream -> {
            if (allstream.getRepository_id() == 1) {
                return allstream.getRepository_id() == 1;
            }
            return false;
        }).collect(Collectors.toList());
        for (int i = 0; i < next1.size(); i++) {
            if (next1.get(i).getRepository_id() == 1) {
                next1.get(i).setDefault_port("3306");
            } else if (next1.get(i).getRepository_id() == 3) {
                next1.get(i).setDefault_port("27017");
            } else {
                next1.get(i).setDefault_port(null);
            }
            next1.get(i).setIp("10.10.1.188");
        }
        List<Aj> next2 = all.stream().filter(allstream -> {
            if (allstream.getRepository_id() == 2) {
                return allstream.getRepository_id() == 2;
            }
            return false;
        }).collect(Collectors.toList());
        for (int i = 0; i < next2.size(); i++) {
            if (next2.get(i).getRepository_id() == 1) {
                next2.get(i).setDefault_port("3306");
            } else if (next2.get(i).getRepository_id() == 3) {
                next2.get(i).setDefault_port("27017");
            } else {
                next2.get(i).setDefault_port(null);
            }
            next2.get(i).setIp("10.10.1.188");
        }
        List<Aj> next3 = all.stream().filter(allstream -> {
            if (allstream.getRepository_id() == 3) {
                return allstream.getRepository_id() == 3;
            }
            return false;
        }).collect(Collectors.toList());
        for (int i = 0; i < next3.size(); i++) {
            if (next3.get(i).getRepository_id() == 1) {
                next3.get(i).setDefault_port("3306");
            } else if (next3.get(i).getRepository_id() == 3) {
                next3.get(i).setDefault_port("27017");
            } else {
                next3.get(i).setDefault_port(null);
            }
            next3.get(i).setIp("10.10.1.188");
        }
        RuseltData ruseltData = new RuseltData();
        ruseltData.setDos(next1);
        ruseltData.setDw(next2);
        ruseltData.setDm(next3);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
        return ruseltData;
    }

    @Override
    public void exitDatabase(String config, Five five) {
        mainDao.exitDatabase(config, five.getData().getDb_name(), five.getData().getAlias(), five.getData().getCreate_time(), five.getData().getDb_type_id(), five.getData().getDbserver_config_id(), five.getData().getRepository_id(), five.getData().getUpdate_time());
    }


    //    @Override
//    public void deleteDatabase(int id) {
//        mainDao.deleteDatabase(id);
//    }
    @Override
    public SelectPassword login(String password) {
        return mainDao.login(password);
    }

    @CachePut("cache1")
    public String getValue() {
        return new Date().toString();
    }

    @Override
    public BigData Sinformation(String user) {
        return mainDao.Sinformation(user);
    }

    @Override
    public PageInfo<Students> PageSelect(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize, "id ASC");
        List<Students> list = mainDao.pageSelect(1);//得到全部结果,紧跟着的第一个select方法会被分页
        PageInfo<Students> pageInfo = new PageInfo<>();
        pageInfo.setList(list);
        return pageInfo;
    }


    @Override
    @Transactional
    public Students selectUrl2(int number) {
        Students a = mainDao.selectUrl(number);
        log.info("此处数据为：" + a);
        return a;
    }

    @Override
    @Transactional
    public void delete(int number) {
        mainDao.delete(number);
    }

    @Override
    public List<Students> ClearSelect(String filename) {
        List<Students> list = mainDao.ClearSelect(filename);
        return list;
    }

    @Override
    public void InsertFileName(String fileName) {
        mainDao.InsertFileName(fileName);
    }
    public void InsertHead(String fileName) {
        mainDao.insertHead(fileName);
    }
    @Override
    public List<Students> classifySelect(int classsifyid) {
        return mainDao.classifySelect(classsifyid);
    }

    @Override
    public void addClassify(List<Classify> list) {
        mainDao.addClassify(list);
    }

    @Override
    public void fileClassify(int fileuid, int fileid1) {
        mainDao.fileClassify(fileuid, fileid1);
    }

    @Override
    public void updatetime(String name, int id) {
        mainDao.updatetime(name, id);
    }

    @Override
    public void updatetime1(int id, Date date) {
        log.info("" + date);
        mainDao.updatetime1(id, date);
    }

    @Resource
    private QuartzManager manager;

    public void init() {
        log.info("进去了service");
//
        Students students = new Students();
        students.setFile_url("111");
        students.setId(1);
        students.setName("sss");
        students.setPassword("sss");
        students.setUid(2);
        try {
            manager.addjob(students);
//           log.info("数据为1："+students.getFile_url());
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    //token判断
    public TokenResult addUserinfo(Token token) {
        TokenResult result = new TokenResult();
        result.setName(token.getUsername());
        Assist assist = new Assist();
        assist.andEq("username", token.getUsername());
        assist.andEq("password", token.getPassword());
        List<User> users = mainDao.selectUser(assist);
        String role = users.get(0).getRole();
        String roles[] = new String[]{role};
        result.setRoles(roles);
        result.setIntroduction("http://10.10.8.88");//头像图片地址
        result.setAvatar(users.get(0).getStudentid());
        return result;
    }

    //    校园发布管理系统登陆
    @Override
    public boolean selectUser(User user) {

        Assist assist = new Assist();
        assist.andEq("username", user.getUsername());
        assist.andEq("password", user.getPassword());
        User user1 = mainDao.selectUser(assist).get(0);
        if (user1 != null) {
            if (user1.getUsername().equals(user.getUsername()) && user1.getPassword().equals(user.getPassword())) {
                if (user1.getBanlogin() == 0) {
                    throw new ComputeException(RequestResultEnum.login_ban);
                }
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String currentTime = sdf.format(new Date());
                mainDao.updateLasttime(currentTime, user1.getId());
                return true;
            }
            return false;
        }
        return false;
    }

    //校园发布系统用户注册
    public void registUser(User user) {
        String check = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z_]{6,12}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(user.getPassword());
        if (this.nameSizeCheck(user.getStudentid()) && this.passwordSizeCheck(user.getPassword())) {
            if (this.failString(user.getUsername())) {
//            if (this.failString(user.getUsername())) {
                if (this.emailCheck(user.getEmail())) {
                    if (matcher.matches()) {
                        if (this.emaildoubleCheck(user)) {
                            mainDao.registUser(user);
                        } else {
                            throw new ComputeException(RequestResultEnum.email_doouble);
                        }
                    } else {
                        throw new ComputeException(RequestResultEnum.password_error);
                    }
                } else {
                    throw new ComputeException(RequestResultEnum.format_error);
                }
            } else {
                throw new ComputeException(RequestResultEnum.fail_username);
            }
//            }
//            else {
//                throw new ComputeException(RequestResultEnum.fail_username);
//            }
//     boolean a=   user.getPassword().matches("[a-zA-Z0-9]+");
        } else {
            throw new ComputeException(RequestResultEnum.format_error);
        }
    }

    //恶性中文名判断
    public boolean failString(String sentence) {
        if (sentence != null) {
            if (sentence.indexOf("sb") != -1 || sentence.indexOf("hmp") != -1) {
                return false;
            }
            if (sentence.indexOf("尼玛") != -1 || sentence.indexOf("草") != -1) {
                return false;
            }
            if (sentence.indexOf("我日") != -1 || sentence.indexOf("傻逼") != -1) {
                return false;
            }
            if (sentence.indexOf("煞笔") != -1 || sentence.indexOf("傻比") != -1) {
                return false;
            }
            if (sentence.indexOf("强奸") != -1 || sentence.indexOf("约炮") != -1) {
                return false;
            }
            if (sentence.indexOf("吸毒") != -1 || sentence.indexOf("出售") != -1) {
                return false;
            }
            if (sentence.indexOf("上床") != -1 || sentence.indexOf("我靠") != -1) {
                return false;
            }
            return true;
        }
        return false;
    }

    //字符串长度名称 格式校验
    public boolean nameSizeCheck(String studentid) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(studentid);
        if (studentid.length() == 12 && isNum.matches()) {
            return true;
        }
        return false;
    }

    //字符串密码 长度判断
    public boolean passwordSizeCheck(String password) {
        if (password.length() > 0) {
            if (password.length() >= 6) {
                if (password.length() <= 12) {
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    //注册重复名称校验
    public boolean registCheck(User user) {

        Assist assist = new Assist();
        assist.andEq("studentid", user.getStudentid());
        assist.andEq("password", user.getPassword());
        List<User> list = mainDao.selectUser(assist);
        if (list.size() != 0) {
            if (list.get(0).getUsername().equals(user.getStudentid())) {
                return true;
            }
            return false;
        }
        return false;
    }

    //重复邮箱校验 查得邮箱返回true
    public boolean emaildoubleCheck(User user) {
        Assist assist = new Assist();
        assist.andEq("email", user.getEmail());
        List<User> list = mainDao.selectUser(assist);
        if (list.size() == 0) {
//            if (list.get(0).getEmail().equals(user.getEmail())) {
//                return true;
//            }
            return true;
        }
        return false;
    }

    @Override
    public FilePage pageFIle(int pageNum, int pageSize, int id) {
        PageHelper.startPage(pageNum, pageSize);
        List<Students> list = mainDao.pageSelect(id);
//        for(Students students1 : list){
//            log.info("名称为:"+students1.getFile_url());
//        }
//        List<Students> list2 =list.stream().filter(t -> t.getUid()==id).collect(Collectors.toList());//查出所有uid=id的学生
        List<String> list1 = new ArrayList<>();
        FilePage filePage = new FilePage();
        for (Students students : list) {
            list1.add(students.getFile_url());
//          students.getFile_url()
        }

        int count = mainDao.selectCount(id);
        filePage.setTotal(count);
        filePage.setList(list1);
        return filePage;
    }

    //分页用户
    @Override
    public FilePage pageUser(int pageNum, int pageSize) {
        Assist assist = new Assist();
        PageHelper.startPage(pageNum, pageSize);
        List<User> users = mainDao.selectUser(assist);
        int count = mainDao.userCount();//总数
        FilePage filePage = new FilePage();
        filePage.setTotal(count);
        filePage.setList1(users);
        return filePage;
    }

    //分页新闻
    public FilePage pageNews(int pageNum, int pageSize) {
        Assist assist = new Assist();
        PageHelper.startPage(pageNum, pageSize);
        List<News> news = mainDao.selectNews(assist);
        int count = mainDao.newsCount();//总数
        FilePage filePage = new FilePage();
        filePage.setTotal(count);
        filePage.setNews(news);
        return filePage;
    }

    //邮箱格式检验
    public boolean emailCheck(String email) {
        if (email == null) {
            return false;
        }
        if (email.endsWith("@qq.com") && email.length() <= 18) {
            return true;
        }
        return false;
    }

    //用户学号 只允许 201510414424 12位数字
    public boolean studentIdCheck(String studentId) {
        if (studentId == null) {
            return false;
        }
        if (studentId.length() != 12) {
            return false;
        }
        for (int i = 0; i < studentId.length(); i++) {
            if (!Character.isDigit(studentId.charAt(i))) {//如果某个不为数字 则报错
                return false;
            }
        }
        return true;
    }

    //按照邮箱筛选 查询
    public List<User> emailSearch(String a) {
        Assist assist = new Assist();
//        assist.andEq("email", a);
        assist.andLike("email", "%" + a + "%");
        if (mainDao.selectUser(assist).size() > 0) {
            return mainDao.selectUser(assist);
        }
        return null;
    }

    //按照昵称筛选
    public FilePage usernameSearch(SearchUser searchUser) {
        Assist assist = new Assist();
        if (searchUser.getSafeindex() == null && searchUser.getBanlogin() == null) {
            String a = searchUser.getTerm();
            assist.orLike("username", "%" + a + "%");
            assist.orLike("studentid", "%" + a + "%");
            assist.orLike("email", "%" + a + "%");
            List<User> user1 = mainDao.selectUser(assist);
            int total = user1.size();
            PageHelper.startPage(searchUser.getPageNum(), searchUser.getPagesize());
            List<User> user = mainDao.selectUser(assist);
            FilePage filePage = new FilePage();
            filePage.setList1(user);
            filePage.setTotal(total);
            return filePage;
        }
        if (searchUser.getSafeindex() != null && searchUser.getBanlogin() == null) {
            String a = searchUser.getTerm();
            assist.orLike("username", "%" + a + "%");
            assist.orLike("studentid", "%" + a + "%");
            assist.orLike("email", "%" + a + "%");
            PageHelper.startPage(searchUser.getPageNum(), searchUser.getPagesize());
            List<User> user1 = mainDao.selectUser(assist);
            List<User> user2 = user1.stream().filter(b -> b.getSafeindex() == searchUser.getSafeindex()).collect(Collectors.toList());
            int total = user1.size();
            List<User> user = mainDao.selectUser(assist);
            FilePage filePage = new FilePage();
            filePage.setList1(user2);
            filePage.setTotal(total);
            return filePage;
        }
        if (searchUser.getSafeindex() == null && searchUser.getBanlogin() != null) {
            String a = searchUser.getTerm();
            assist.orLike("username", "%" + a + "%");
            assist.orLike("studentid", "%" + a + "%");
            assist.orLike("email", "%" + a + "%");
            PageHelper.startPage(searchUser.getPageNum(), searchUser.getPagesize());
            List<User> user1 = mainDao.selectUser(assist);
            List<User> user2 = user1.stream().filter(b -> b.getBanlogin() == searchUser.getBanlogin()).collect(Collectors.toList());
            int total = user1.size();

            List<User> user = mainDao.selectUser(assist);
            FilePage filePage = new FilePage();
            filePage.setList1(user2);
            filePage.setTotal(total);
            return filePage;
        } else {
//        Assist assist = new Assist();
            String a = searchUser.getTerm();
            assist.orLike("username", "%" + a + "%");
            assist.orLike("studentid", "%" + a + "%");
            assist.orLike("email", "%" + a + "%");
//        assist.andEq("safeindex",searchUser.getSafeindex());
//        assist.andEq("banlogin",searchUser.getBanlogin());
            PageHelper.startPage(searchUser.getPageNum(), searchUser.getPagesize());
            List<User> user1 = mainDao.selectUser(assist);
            List<User> user2 = user1.stream().filter(b -> b.getBanlogin() == searchUser.getBanlogin() && b.getSafeindex() == searchUser.getSafeindex()).collect(Collectors.toList());
            int total = user1.size();
            List<User> user = mainDao.selectUser(assist);
            FilePage filePage = new FilePage();
            filePage.setList1(user2);
            filePage.setTotal(total);
            return filePage;
        }
    }

    //按照学号搜索筛选
    public List<User> studentIdSearch(String a) {
        Assist assist = new Assist();
//        assist.andEq("studentid", a);
        assist.andLike("studentid", "%" + a + "%");
        if (mainDao.selectUser(assist).size() > 0) {
            return mainDao.selectUser(assist);
        }
        return null;
    }

    //批量删除用户
    public boolean deleteUser(FilePage filePage) {
        if (filePage != null) {
            List<User> list = filePage.getList1();
            List<Integer> list1 = new ArrayList<>();
            for (User user : list) {
                list1.add(user.getId());
            }
            mainDao.deleteUser(list1);
            return true;
        }
        return false;
    }

    //批量删除新闻
    public boolean deleteNews(FilePage filePage) {
        if (filePage != null) {
            List<News> news = filePage.getNews();
            List<Integer> list1 = new ArrayList<>();
            for (News news1 : news) {
                list1.add(news1.getId());
            }
            int a = mainDao.deleteNews(list1);
            if (a > 0) {
                return true;
            }
            return false;
        }
        return false;
    }

    //等级升降筛选
    public FilePage sortgrade(SearchUser searchUser) {
        Assist assist = new Assist();
        if (searchUser.getAsc() == 1) {
            //返回升序数据
            Assist.WhereOrder whereOrder = new Assist().new WhereOrder("grade", true);
            assist.setOrder(whereOrder);
            List<User> list1 = mainDao.selectUser(assist);
            PageHelper.startPage(searchUser.getPageNum(), 10);
            List<User> list = mainDao.selectUser(assist);
            FilePage filePage = new FilePage();
            filePage.setTotal(list1.size());
            filePage.setList1(list);
            return filePage;

        }
        if (searchUser.getDesc() == 1) {
            //返回降序数据
            Assist.WhereOrder whereOrder = new Assist().new WhereOrder("grade", false);
            assist.setOrder(whereOrder);
            List<User> list1 = mainDao.selectUser(assist);
            PageHelper.startPage(searchUser.getPageNum(), 10);
            List<User> list = mainDao.selectUser(assist);
            FilePage filePage = new FilePage();
            filePage.setTotal(list1.size());
            filePage.setList1(list);
            return filePage;
        }
        return null;
    }

    //安全指数升降筛选
    public List<User> sortSafeIndex(SearchUser searchUser) {

        Assist assist = new Assist();
        if (searchUser.getAsc() == 1) {
            //返回升序数据
            Assist.WhereOrder whereOrder = new Assist().new WhereOrder("safeindex", true);
            assist.setOrder(whereOrder);
            PageHelper.startPage(searchUser.getPageNum(), 20);
            List<User> list = mainDao.selectUser(assist);
            return list;

        }
        if (searchUser.getDesc() == 1) {
            //返回降序数据
            Assist.WhereOrder whereOrder = new Assist().new WhereOrder("safeindex", false);
            assist.setOrder(whereOrder);
            PageHelper.startPage(searchUser.getPageNum(), 20);
            List<User> list = mainDao.selectUser(assist);
            return list;
        }
        return null;
    }

    //批量修改用户登陆权限 可登陆 已禁止
    public void updateBanLogin(User user) {
        if (user != null) {
//            List<User> list = filePage.getList1();
//            List<Integer> list1 = new ArrayList<>();
//            for (User user : list) {
//                list1.add(user.getId());
//            }
            mainDao.updateBanLogin(user);
        }
    }

    //校园新闻 后台 新闻板块
    //root 新闻添加
    public boolean addNews(News news) {
//        Picture picture = new Picture();
//        for (int i = 0; i < files.length; i++) {
////                    multipartFile[i].transferTo(new File("path"));
//            FileUtils.writeByteArrayToFile(new File("g:/upload/" + files[i].getOriginalFilename()), files[i].getBytes());//字节数组
////           this.InsertFileName(files[i].getOriginalFilename());
//            //对象转String存入picturepath
//            picture.getPicturePaths().add(files[i].getOriginalFilename());
//        }
//        String s = JSON.toJSONString(picture);
        if (news.getMessage() == null) {
            throw new ComputeException(RequestResultEnum.message_EMPTY);
        }
        if (news.getTitle() == null) {
            throw new ComputeException(RequestResultEnum.title_EMPTY);
        }
        Assist assist = new Assist();
        assist.andEq("studentid", news.getUserstudentid());
        List<User> users = mainDao.selectUser(assist);
        String author = users.get(0).getUsername();
        news.setBelong(1);
        news.setAuthor(author);
        news.setUserstudentid(news.getUserstudentid());
        news.setStatus(0);
//        str=str.Replace("abc","ABC");
        String s = news.getMessage();
        String b = s.substring(0, s.length() - 4);
        String c = b.substring(3, b.length());
        news.setMessage(c);
        news.setLooknum(0);
//        news.setPicturepath(s);
        if (mainDao.insertNews(news) > 0) {
            return true;
        }
//        mainDao.insertNews(news);
        return false;
    }

    //用户新闻添加 传入作者学号 且发布新闻数量加1
    public boolean userAddNews(News news) {
        if (news.getMessage() == null) {
            throw new ComputeException(RequestResultEnum.message_EMPTY);
        }
        if (news.getTitle() == null) {
            throw new ComputeException(RequestResultEnum.title_EMPTY);
        }
//        news.setBelong(1);
//        news.setAuthor("管理员");

        if (mainDao.insertNews(news) > 0) {
            if (mainDao.updateNewsnum(news.getAuthor()) > 0) {
                return true;
            }
            return false;
        }
//        mainDao.insertNews(news);
        return false;
    }
//获取娱乐5条
    public List<News> getHappyNews(int i){
        Assist assist =new Assist();
        if(i==2){
            assist.andEq("classifybelong",2);
        }
        if(i==1){
            assist.andEq("classifybelong",1);
        }
        if(i==3){
            assist.andEq("classifybelong",3);
        }
        if(i==4){
            assist.andEq("classifybelong",4);
        }
        if(i==5){
            assist.andEq("classifybelong",5);
        }
        if(i==6){
            assist.andEq("classifybelong",6);
        }
        List<News> list =mainDao.selectNews(assist);
        List<News> listEnd=new ArrayList<>();
        listEnd.add(list.get(list.size()-1));
        listEnd.add(list.get(list.size()-2));
        listEnd.add(list.get(list.size()-3));
        listEnd.add(list.get(list.size()-4));
        listEnd.add(list.get(list.size()-5));
        return listEnd;


    }
    //获取全部分类
    public List<Classify> getClassify(){
List<Classify> list=mainDao.selectClassify();
return list;
    }
    //新闻筛选查询 传入 标题 以及分类 以及作者
    public List<News> findNews(News news) {
        List<News> list = new ArrayList<>();
        if (news.getTitle() != null) {
            Assist assist = new Assist();
            assist.andLike("title", "%" + news.getTitle() + "%");
            List<News> news1 = mainDao.selectNews(assist);
            if (news1 != null) {
                list = news1;
            }
        }
        if (news.getAuthor() != null) {
            Assist assist = new Assist();
            assist.andEq("author", news.getAuthor());
            List<News> list1 = mainDao.selectNews(assist);
            if (list1 != null) {
                for (News news1 : list1) {
                    list.add(news1);
                }
            }
        }
        if (news.getClassifybelong() > -1) {
            Assist assist = new Assist();
            assist.andEq("classifybelong", news.getClassifybelong());
            List<News> list1 = mainDao.selectNews(assist);
            if (list1 != null) {
                for (News news1 : list1) {
                    list.add(news1);
                }
            }
        }
        if (news == null) {
            Assist assist = new Assist();
            List<News> list1 = mainDao.selectNews(assist);
            list = list1;
        }
        return list;
    }
//excel

    //设置样式
    private HSSFCellStyle getColumnTopStyle(HSSFWorkbook workbook) {
//        CellStyle cellStyle = workbook.createCellStyle();
//        CellStyle cellStyle =workbook.createCellStyle();
//        HSSFWorkbook wb = new HSSFWorkbook();
//        HSSFWorkbook workbook = new HSSFWorkbook();

        HSSFSheet sheet = workbook.createSheet();

        HSSFCellStyle cellStyle = workbook.createCellStyle();
//        HSSFSheet sheet = workbook.createSheet();
//        HSSFCellStyle setBorder = workbook.createCellStyle()
//        cellStylezzz
//        HSSFCellStyle cellStyle =
        cellStyle.setFillForegroundColor((short) 13);// 设置背景色
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框

        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
        HSSFFont font = workbook.createFont();
        font.setFontName("黑体");
        font.setFontHeightInPoints((short) 16);//设置字体大小

        HSSFFont font2 = workbook.createFont();
        font2.setFontName("仿宋_GB2312");
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
        font2.setFontHeightInPoints((short) 12);
        sheet.setColumnWidth(0, 3766); //第一个参数代表列id(从0开始),第2个参数代表宽度值
        cellStyle.setFont(font);//选择需要用到的字体格式
        sheet.setColumnWidth(0, 3766); //第一个参数代表列id(从0开始),第2个参数代表宽度值
        cellStyle.setWrapText(true);//设置自动换行
        Region region1 = new Region(0, (short) 0, 0, (short) 6);
        sheet.addMergedRegion(region1);

//        cellStyle.setBorderLeft(BorderStyle.THIN);
//        cellStyle.setBorderRight(BorderStyle.THIN);
//        cellStyle.setBorderTop(BorderStyle.THIN);
//        //设置自动换行
//        cellStyle.setWrapText(false);
//        cellStyle.setAlignment(HorizontalAlignment.CENTER);
//        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        return cellStyle;
    }

    //写入EXCLE
    public void exportTest(List<User> list, HttpServletResponse response) throws IOException {
        File file = new File("用户表.xlsx");
        HSSFWorkbook workbook = new HSSFWorkbook();
        //设置表的牙样式
//        HSSFWorkbook workbook = new HSSFWorkbook();
//
//        HSSFSheet sheet = workbook.createSheet();
//
//        HSSFCellStyle cellStyle = workbook.createCellStyle();
        HSSFCellStyle cellStyle = getColumnTopStyle(workbook);
        HSSFSheet sheet = workbook.createSheet();
        int index = 0;
        Row row0 = sheet.createRow(index++);
        //设置第一行
        row0.createCell(0).setCellValue("序号");
        row0.createCell(1).setCellValue("昵称");
        row0.createCell(2).setCellValue("密码");
        row0.createCell(3).setCellValue("邮箱");
        row0.createCell(4).setCellValue("等级");
        row0.createCell(5).setCellValue("学号");
        row0.createCell(6).setCellValue("加入时间");
        row0.createCell(7).setCellValue("新闻浏览量");
        row0.createCell(8).setCellValue("上一次登陆时间");
        //把查询结果放入到对应的列
        for (User user : list) {
            Row row = sheet.createRow(index++);
            row.createCell(0).setCellValue(user.getId());
            row.createCell(1).setCellValue(user.getUsername());
            row.createCell(2).setCellValue(user.getPassword());
            row.createCell(3).setCellValue(user.getEmail());
            row.createCell(4).setCellValue(user.getGrade());
            row.createCell(5).setCellValue(user.getStudentid());
            row.createCell(6).setCellValue(user.getCreatetime());
            row.createCell(7).setCellValue(user.getSeenum());
            row.createCell(8).setCellValue(user.getLastlogintime());
        }

        for (int m = 0; m <= sheet.getLastRowNum(); m++) {
            Row rowStyle = sheet.getRow(m);
            for (int n = 0; n < rowStyle.getLastCellNum(); n++) {
                rowStyle.getCell(n).setCellStyle(cellStyle);
            }
        }
        response.reset();
        response.setHeader("Content-Disposition", "attachment;filename=\"" + new String(file.getName().getBytes("utf-8"), "ISO8859-1") + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        OutputStream os = new BufferedOutputStream(response.getOutputStream());
        workbook.write(os);
        os.flush();
        os.close();
        workbook.close();
    }
}

