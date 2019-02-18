package com.example.handsomelibrary.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 初夏小溪 on 2018/9/20 0020.
 * 心理咨询
 */
public class MentalHealthBean implements Serializable {


    /**
     * listQuestion : [{"id":1,"type":1,"name":"您会头晕或者晕倒吗？","options":"1%2%3%4","gmtCreate":null,"gmtModified":null,"listOption":[{"id":1,"name":"从不","score":10,"gmtCreate":null,"gmtModified":null}]}]
     * answers : {7=4, 8=4, 9=4, 10=3}
     */

    private String answers = "";
    private List<ListQuestionBean> listQuestion;

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public List<ListQuestionBean> getListQuestion() {
        return listQuestion;
    }

    public void setListQuestion(List<ListQuestionBean> listQuestion) {
        this.listQuestion = listQuestion;
    }

    public static class ListQuestionBean {
        /**
         * id : 1
         * type : 1
         * name : 您会头晕或者晕倒吗？
         * options : 1%2%3%4
         * gmtCreate : null
         * gmtModified : null
         * listOption : [{"id":1,"name":"从不","score":10,"gmtCreate":null,"gmtModified":null}]
         */

        private int id;
        private int type;
        private String name = "";
        private String options = "";
        private Object gmtCreate;
        private Object gmtModified;
        private List<ListOptionBean> listOption;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOptions() {
            return options;
        }

        public void setOptions(String options) {
            this.options = options;
        }

        public Object getGmtCreate() {
            return gmtCreate;
        }

        public void setGmtCreate(Object gmtCreate) {
            this.gmtCreate = gmtCreate;
        }

        public Object getGmtModified() {
            return gmtModified;
        }

        public void setGmtModified(Object gmtModified) {
            this.gmtModified = gmtModified;
        }

        public List<ListOptionBean> getListOption() {
            return listOption;
        }

        public void setListOption(List<ListOptionBean> listOption) {
            this.listOption = listOption;
        }

        public static class ListOptionBean {
            /**
             * id : 1
             * name : 从不
             * score : 10
             * gmtCreate : null
             * gmtModified : null
             */

            private int id;
            private String name = "";
            private int score;
            private Object gmtCreate;
            private Object gmtModified;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }

            public Object getGmtCreate() {
                return gmtCreate;
            }

            public void setGmtCreate(Object gmtCreate) {
                this.gmtCreate = gmtCreate;
            }

            public Object getGmtModified() {
                return gmtModified;
            }

            public void setGmtModified(Object gmtModified) {
                this.gmtModified = gmtModified;
            }
        }
    }
}
