package com.springys.Service.implement;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springys.Common.Assist;
import com.springys.Common.QuartzManager;
import com.springys.Common.RequestResultEnum;
import com.springys.Dao.MainDao;
import com.springys.Service.Servicemain;
import com.springys.entity.*;
import com.springys.exception.ComputeException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.quartz.SchedulerException;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
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

    @Override
    public List<Students> classifySelect(int classsifyid) {
        return mainDao.classifySelect(classsifyid);
    }

    @Override
    public void addClassify(List<classIfy> list) {
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

    //    校园发布管理系统登陆
    @Override
    public boolean selectUser(User user) {

        Assist assist = new Assist();
        assist.andEq("studentid", user.getStudentid());
        assist.andEq("password", user.getPassword());
        User user1 = mainDao.selectUser(assist).get(0);
        if (user1 != null) {
            if (user1.getStudentid().equals(user.getStudentid()) && user1.getPassword().equals(user.getPassword())) {
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
    public List<User> usernameSearch(SearchUser searchUser) {
        Assist assist = new Assist();
        String a=searchUser.getTerm();
        assist.orLike("username", "%" + a + "%");
        assist.orLike("studentid", "%" + a + "%");
        assist.orLike("email", "%" + a + "%");
        PageHelper.startPage(searchUser.getPageNum(), searchUser.getPagesize());
        List<User> user = mainDao.selectUser(assist);
        if (user.size() > 0) {
            return user;
        }
        return null;
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
       int a= mainDao.deleteNews(list1);
        if(a>0){
            return true;
        }
        return false;
    }
    return false;
}
    //等级升降筛选
    public List<User> sortgrade(SearchUser searchUser) {
        Assist assist = new Assist();
        if (searchUser.getAsc() == 1) {
            //返回升序数据
            Assist.WhereOrder whereOrder = new Assist().new WhereOrder("grade", true);
            assist.setOrder(whereOrder);
            PageHelper.startPage(searchUser.getPageNum(), 20);
            List<User> list = mainDao.selectUser(assist);
            return list;

        }
        if (searchUser.getDesc() == 1) {
            //返回降序数据
            Assist.WhereOrder whereOrder = new Assist().new WhereOrder("grade", false);
            assist.setOrder(whereOrder);
            PageHelper.startPage(searchUser.getPageNum(), 20);
            List<User> list = mainDao.selectUser(assist);
            return list;
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
    public boolean addNews(News news, MultipartFile [] files) throws IOException {
        Picture picture=new Picture();
        for (int i = 0; i < files.length; i++) {
//                    multipartFile[i].transferTo(new File("path"));
            FileUtils.writeByteArrayToFile(new File("g:/upload/" + files[i].getOriginalFilename()), files[i].getBytes());//字节数组
//           this.InsertFileName(files[i].getOriginalFilename());
            //对象转String存入picturepath
            picture.getPicturePaths().add(files[i].getOriginalFilename());
        }
        String s= JSON.toJSONString(picture);
        if (news.getMessage() == null) {
            throw new ComputeException(RequestResultEnum.message_EMPTY);
        }
        if (news.getTitle() == null) {
            throw new ComputeException(RequestResultEnum.title_EMPTY);
        }
        news.setBelong(1);
        news.setAuthor("管理员");
        news.setPicturepath(s);
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

}

