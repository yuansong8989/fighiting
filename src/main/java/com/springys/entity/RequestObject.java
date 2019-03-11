package com.springys.entity;

import lombok.Data;

import java.util.List;

/**
 * Created by yzd on 2019/2/2.
 */
@Data
public class RequestObject {
    private Data data;

    @lombok.Data
    public static class Data {
        private Integer number;
        private int id;
        private String password;
        private String user;
        private int pageNum;
        private int pageSize;
        //file
        private int fileid[];
        private String mohuName;
        private int classfiyid;//脚本分类id
        private List<classIfy> list;//脚本分类name
        private int fileuid;//文件分类uid
        private int fileid1;
    }
}