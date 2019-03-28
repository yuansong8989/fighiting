package com.springys.Service;

import com.github.pagehelper.PageInfo;
import com.springys.entity.*;

import java.util.Date;
import java.util.List;

/**
 * Created by yzd on 2019/2/2.
 */

public interface Servicemain {
    RuseltData getDataBaseList();

    void exitDatabase(String config, Five five);

//    void deleteDatabase(int id);

    SelectPassword login(String password);

    BigData Sinformation(String user);

    PageInfo<Students> PageSelect(int pageNum, int pageSize);

    Students selectUrl2(int number);

    void delete(int number);//删除脚本文件记录

    List<Students> ClearSelect(String filename);//模糊查询脚本文件

    void InsertFileName(String fileName);

    List<Students> classifySelect(int classsifyid);

    void addClassify(List<Classify> list);

    void fileClassify(int fileuid, int fileid1);

    FilePage pageFIle(int pageNum, int pageSize, int id);

    void updatetime(String name, int id);

    void updatetime1(int id, Date date);

    //    校园后台管理系统登陆
    boolean selectUser(User user);
    //校园后台管理系统用户管理
    FilePage  pageUser(int pageNum,int pageSize);
}
