package com.xiong.rxdemo.bean;

import java.util.List;

/**
 * @author: xiong
 * @time: 2019/05/06
 * @说明:
 */
public class News {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * is_hot : true
         * code : +60
         * name : 马来西亚
         * abbr : MY
         */

        private boolean is_hot;
        private String code;
        private String name;
        private String abbr;

        public boolean isIs_hot() {
            return is_hot;
        }

        public void setIs_hot(boolean is_hot) {
            this.is_hot = is_hot;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAbbr() {
            return abbr;
        }

        public void setAbbr(String abbr) {
            this.abbr = abbr;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "is_hot=" + is_hot +
                    ", code='" + code + '\'' +
                    ", name='" + name + '\'' +
                    ", abbr='" + abbr + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "News{" +
                "data=" + data +
                '}';
    }
}
