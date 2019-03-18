package com.springys.entity;

import lombok.Data;

/**
 * Created by yzd on 2019/3/18.
 */
@Data
public class News {
    private int id;
    private String message;//新闻内容
    private String picturepath;//照片路径
    private String vediopath;//视频路径
    private String createtime;//创建时间
    private String updatetime;//修改时间
    private int belong;//1root创建 0用户创建
    private int agreenum;//点赞数量
    private String author;//文章作者
    private int discussnum;//讨论数量
    private  int classifybelong;//属于新闻类别
    private  String title;//新闻标题
}
