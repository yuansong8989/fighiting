package com.springys.Service.implement;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springys.Common.Assist;
import com.springys.Common.QuartzManager;
import com.springys.Dao.MainDao;
import com.springys.Service.Servicemain;
import com.springys.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    @Override
    public void deleteDatabase(int id) {
        mainDao.deleteDatabase(id);
    }

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
        log.info(""+date);
        mainDao.updatetime1(id, date);
    }
    @Resource
    private QuartzManager manager;
    public void init(){
        log.info("进去了service");
//
        Students students =new Students();
        students.setFile_url("111");
        students.setId(1);
        students.setName("sss");
        students.setPassword("sss");
        students.setUid(2);
        try{
            manager.addjob(students);
//           log.info("数据为1："+students.getFile_url());
        }catch (SchedulerException e){
e.printStackTrace();
        }
    }
    //    校园后台管理系统登陆
    @Override
    public boolean selectUser(User user) {

            Assist assist=new Assist();
            assist.andEq("username",user.getUsername());
            assist.andEq("password",user.getPassword());
            User user1 =mainDao.selectUser(assist).get(0);
            if(user1==null){
                return false;
            }
            if(user1.getUsername().equals(user.getUsername())&&user1.getPassword().equals(user.getPassword())){
                return true;
            }
        return false;
        }
    @Override
    public FilePage pageFIle(int pageNum, int pageSize,int id) {
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

    @Override
    public FilePage pageUser(int pageNum, int pageSize) {
        Assist assist=new Assist();
        PageHelper.startPage(pageNum, pageSize);
       List<User> users= mainDao.selectUser(assist);
       int count=mainDao.userCount();//总数
        FilePage filePage=new FilePage();
        filePage.setTotal(count);
        filePage.setList1(users);
        return filePage;
    }
    //邮箱格式检验
    public boolean emailCheck(String email){
        if(email==null){
            return false;
        }
        if(email.endsWith(".com")){
            return true;
        }
        return false;
    }
        //用户学号 只允许 201510414424 12位数字
    public boolean studentIdCheck(String studentId){
        if(studentId==null){
            return false;
        }
        if(studentId.length()!=12){
            return false;
        }
        for (int i = 0; i < studentId.length(); i++){
            if(!Character.isDigit(studentId.charAt(i))){//如果某个不为数字 则报错
                return false;
            }
        }
        return true;
    }
    //按照邮箱筛选 查询
    public User emailSearch(String a){
        Assist assist=new Assist();
        assist.andEq("email",a);
        if(mainDao.selectUser(assist).get(0)!=null){
            return mainDao.selectUser(assist).get(0);
        }
return null;
    }
    //按照学号搜索筛选
    public User studentIdSearch(String a){
        Assist assist=new Assist();
        assist.andEq("username",a);
        if(mainDao.selectUser(assist).get(0)!=null){
            return mainDao.selectUser(assist).get(0);
        }
        return null;
    }
    //批量删除用户
    public boolean deleteUser(FilePage filePage){
        if(filePage!=null){
            List<User> list=filePage.getList1();
            List<Integer > list1=new ArrayList<>();
            for(User user : list){
                list1.add(user.getId());
            }
            mainDao.deleteUser(list1);
            return true;
        }
        return false;
    }
//等级升降筛选
    public List<User> sortgrade(SearchUser searchUser){
        Assist assist=new Assist();
        if(searchUser.getAsc()==1){

            //返回升序数据
        }
        if(searchUser.getDesc()==1){
            //返回降序数据

        }
    }
}

