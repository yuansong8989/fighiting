package com.springys.Dao;

import com.springys.Common.Assist;
import com.springys.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
/**
 * Created by yzd on 2019/2/1.
 */
@Component
@Mapper

public interface MainDao {
    List<Aj> getDataBaseList();

    void exitDatabase(@Param("config") String config, @Param("db_name") String db_name, @Param("alias") String alias, @Param("create_time") Date create_time, @Param("db_type_id") int db_type_id, @Param("dbserver_config_id") int dbserver_config_id, @Param("repository_id") int repository_id, @Param("update_time") Date update_time);

//    void deleteDatabase(@Param("id") int id);
    SelectPassword login(@Param("password")String password);
    BigData Sinformation(@Param("user")String user);
    List<Students> pageSelect(@Param("id") int id);
   Students selectUrl(@Param("number") int number);
   void delete(@Param("id") int id);
   List<Students> ClearSelect(@Param("filename") String filename);

   void InsertFileName(@Param("filename") String filename);
   List<Students> classifySelect(@Param("classify") int classify);
   void addClassify(@Param("classList") List<Classify> classList);
   void fileClassify(@Param("fileuid") int fileuid,@Param("fileid1") int fileid1);
   int selectCount(@Param("id") int id);
   void updatetime(@Param("name") String name,@Param("id") int id);
   void updatetime1(@Param("id") int id,@Param("date") Date date);
//   List<Students> selectStudents(Assist assist);
    //校园后台登陆
   List<User>  selectUser(Assist assist);
    //校园后台用户管理
//List<User> pageUser(Assist assist);
    int userCount();
    //邮箱搜索用户
    void deleteUser(@Param("list") List<Integer> list);
    void updateBanLogin(@Param("user") User user);
    void registUser(@Param("user") User user );
    void updateLasttime(@Param("lastlogintime") String lastlogintime,@Param("id") int id);
    //新闻管理模块
    List<News> selectNews(Assist assist);
    //增加新闻
    int insertNews(@Param("news") News news);
    //增加用户发布新闻数量
    int updateNewsnum(@Param("studentid") String studentid);
    //新闻数量
    int newsCount();
    int deleteNews(@Param("list") List<Integer> list);
    //excel
    @Select("select * from user")
     List<User> list();
    List<Classify> selectClassify();
    void insertHead(@Param("path") String path);
    //插入新闻数量
    int updateNewsJson(@Param("userjson") String userjson,@Param("id") int id);
    //更新关注用户
    int updateFollowUser(@Param("a") String a,@Param("id") int id);
//更新被关注用户
    int updateByFollowUser(@Param("a")String a, @Param("id") int id);
}