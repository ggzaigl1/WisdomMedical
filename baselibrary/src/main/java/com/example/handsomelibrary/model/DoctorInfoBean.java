package com.example.handsomelibrary.model;


import java.io.Serializable;
import java.util.List;

/**
 * Created by Stefan on 2018/7/30.
 * 医生详情
 */
public class DoctorInfoBean implements Serializable {

    /**
     * commentCount : 2
     * departmentId : 1
     * departmentName : 儿科
     * diagnoseCount : 100
     * docInfo : 为人民服务
     * docName : 李季松
     * docNo : 21832496
     * gmtCreate : 2018-07-26 17:27:07
     * hospicalName : 武汉同济医院
     * hospitalId : 2
     * id : 17
     * isFollow : 1
     * listDocService : [{"docNo":"21832496","gmtCreate":"2018-07-26 17:27:07","id":27,"isNeedSchedule":2,"isOpenService":1,"serviceName":"图文咨询","servicePrice":100,"serviceTypeId":1},{"docNo":"21832496","gmtCreate":"2018-07-26 17:27:07","id":28,"isNeedSchedule":1,"isOpenService":1,"serviceName":"视频问诊","servicePrice":200,"serviceTypeId":2},{"docNo":"21832496","gmtCreate":"2018-07-26 17:27:07","id":29,"isNeedSchedule":1,"isOpenService":1,"serviceName":"语音问诊","servicePrice":300,"serviceTypeId":3},{"docNo":"21832496","gmtCreate":"2018-07-26 17:27:07","id":30,"isNeedSchedule":1,"isOpenService":1,"serviceName":"家庭医生","servicePrice":400,"serviceTypeId":4},{"docNo":"21832496","gmtCreate":"2018-07-26 17:27:07","id":31,"isNeedSchedule":1,"isOpenService":1,"serviceName":"远程会诊","servicePrice":500,"serviceTypeId":5}]
     * listEvaluationCusDoc : [{"docNo":"21832496","evaluationContent":"好好好","gmtCreate":"2018-08-08 16:44:13","id":23,"photoUrl":"/photo/20180803/94dae6725f2e41a39d0c027ad8185a78.png","serviceTypeId":1,"sorce":3,"userId":25,"username":"yyy"},{"docNo":"21832496","evaluationContent":"很棒","gmtCreate":"2018-08-08 16:32:27","id":22,"photoUrl":"/photo/20180803/94dae6725f2e41a39d0c027ad8185a78.png","serviceTypeId":1,"sorce":0,"userId":25,"username":"yyy"}]
     * listEvaluationTagDoc : [{"docNo":"21832496","evaluationCount":0,"gmtCreate":"2018-07-26 17:27:07","gmtModified":"2018-08-08 17:09:20","id":107,"tagId":1,"tagName":"医术高明"},{"docNo":"21832496","evaluationCount":0,"gmtCreate":"2018-07-26 17:27:07","gmtModified":"2018-08-08 17:09:20","id":108,"tagId":2,"tagName":"很有医德"},{"docNo":"21832496","evaluationCount":0,"gmtCreate":"2018-07-26 17:27:07","gmtModified":"2018-08-08 17:09:20","id":109,"tagId":3,"tagName":"态度很好"},{"docNo":"21832496","evaluationCount":0,"gmtCreate":"2018-07-26 17:27:07","gmtModified":"2018-08-08 17:09:20","id":110,"tagId":4,"tagName":"治疗效果好"},{"docNo":"21832496","evaluationCount":0,"gmtCreate":"2018-07-26 17:27:07","gmtModified":"2018-08-08 17:09:20","id":111,"tagId":5,"tagName":"耐心"},{"docNo":"21832496","evaluationCount":0,"gmtCreate":"2018-07-26 17:27:07","gmtModified":"2018-08-08 17:09:20","id":112,"tagId":6,"tagName":"态度很差"},{"docNo":"21832496","evaluationCount":0,"gmtCreate":"2018-07-26 17:27:07","gmtModified":"2018-08-08 17:09:20","id":113,"tagId":7,"tagName":"治疗没有效果"}]
     * score : 3.0
     * specialty : 白内障、近视眼
     * titleId : 2
     * titleName : 主治医师
     */

    private int commentCount;
    private int departmentId;
    private String departmentName;
    private int diagnoseCount;
    private String docInfo;
    private String docName;
    private String docNo;
    private String gmtCreate;
    private String hospicalName;
    private int hospitalId;
    private int id;
    private int isFollow;
    private double score;
    private String specialty;
    private String titleId;
    private String titleName;
    private List<ListDocServiceBean> listDocService;
    private List<ListEvaluationCusDocBean> listEvaluationCusDoc;
    private List<ListEvaluationTagDocBean> listEvaluationTagDoc;

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getDiagnoseCount() {
        return diagnoseCount;
    }

    public void setDiagnoseCount(int diagnoseCount) {
        this.diagnoseCount = diagnoseCount;
    }

    public String getDocInfo() {
        return docInfo;
    }

    public void setDocInfo(String docInfo) {
        this.docInfo = docInfo;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocNo() {
        return docNo;
    }

    public void setDocNo(String docNo) {
        this.docNo = docNo;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getHospicalName() {
        return hospicalName;
    }

    public void setHospicalName(String hospicalName) {
        this.hospicalName = hospicalName;
    }

    public int getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(int hospitalId) {
        this.hospitalId = hospitalId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsFollow() {
        return isFollow;
    }

    public void setIsFollow(int isFollow) {
        this.isFollow = isFollow;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getTitleId() {
        return titleId;
    }

    public void setTitleId(String titleId) {
        this.titleId = titleId;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public List<ListDocServiceBean> getListDocService() {
        return listDocService;
    }

    public void setListDocService(List<ListDocServiceBean> listDocService) {
        this.listDocService = listDocService;
    }

    public List<ListEvaluationCusDocBean> getListEvaluationCusDoc() {
        return listEvaluationCusDoc;
    }

    public void setListEvaluationCusDoc(List<ListEvaluationCusDocBean> listEvaluationCusDoc) {
        this.listEvaluationCusDoc = listEvaluationCusDoc;
    }

    public List<ListEvaluationTagDocBean> getListEvaluationTagDoc() {
        return listEvaluationTagDoc;
    }

    public void setListEvaluationTagDoc(List<ListEvaluationTagDocBean> listEvaluationTagDoc) {
        this.listEvaluationTagDoc = listEvaluationTagDoc;
    }

    public static class ListDocServiceBean implements Serializable{
        /**
         * docNo : 21832496
         * gmtCreate : 2018-07-26 17:27:07
         * id : 27
         * isNeedSchedule : 2
         * isOpenService : 1
         * serviceName : 图文咨询
         * servicePrice : 100.0
         * serviceTypeId : 1
         */

        private String docNo;
        private String gmtCreate;
        private int id;
        private int isNeedSchedule;
        private int isOpenService;
        private String serviceName;
        private double servicePrice;
        private int serviceTypeId;

        public String getDocNo() {
            return docNo;
        }

        public void setDocNo(String docNo) {
            this.docNo = docNo;
        }

        public String getGmtCreate() {
            return gmtCreate;
        }

        public void setGmtCreate(String gmtCreate) {
            this.gmtCreate = gmtCreate;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIsNeedSchedule() {
            return isNeedSchedule;
        }

        public void setIsNeedSchedule(int isNeedSchedule) {
            this.isNeedSchedule = isNeedSchedule;
        }

        public int getIsOpenService() {
            return isOpenService;
        }

        public void setIsOpenService(int isOpenService) {
            this.isOpenService = isOpenService;
        }

        public String getServiceName() {
            return serviceName;
        }

        public void setServiceName(String serviceName) {
            this.serviceName = serviceName;
        }

        public double getServicePrice() {
            return servicePrice;
        }

        public void setServicePrice(double servicePrice) {
            this.servicePrice = servicePrice;
        }

        public int getServiceTypeId() {
            return serviceTypeId;
        }

        public void setServiceTypeId(int serviceTypeId) {
            this.serviceTypeId = serviceTypeId;
        }
    }

    public static class ListEvaluationCusDocBean implements Serializable{
        /**
         * docNo : 21832496
         * evaluationContent : 好好好
         * gmtCreate : 2018-08-08 16:44:13
         * id : 23
         * photoUrl : /photo/20180803/94dae6725f2e41a39d0c027ad8185a78.png
         * serviceTypeId : 1
         * sorce : 3.0
         * userId : 25
         * username : yyy
         */

        private String docNo;
        private String evaluationContent;
        private String gmtCreate;
        private int id;
        private String photoUrl;
        private int serviceTypeId;
        private double sorce;
        private int userId;
        private String username;

        public String getDocNo() {
            return docNo;
        }

        public void setDocNo(String docNo) {
            this.docNo = docNo;
        }

        public String getEvaluationContent() {
            return evaluationContent;
        }

        public void setEvaluationContent(String evaluationContent) {
            this.evaluationContent = evaluationContent;
        }

        public String getGmtCreate() {
            return gmtCreate;
        }

        public void setGmtCreate(String gmtCreate) {
            this.gmtCreate = gmtCreate;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPhotoUrl() {
            return photoUrl;
        }

        public void setPhotoUrl(String photoUrl) {
            this.photoUrl = photoUrl;
        }

        public int getServiceTypeId() {
            return serviceTypeId;
        }

        public void setServiceTypeId(int serviceTypeId) {
            this.serviceTypeId = serviceTypeId;
        }

        public double getSorce() {
            return sorce;
        }

        public void setSorce(double sorce) {
            this.sorce = sorce;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }

    public static class ListEvaluationTagDocBean implements Serializable {
        /**
         * docNo : 21832496
         * evaluationCount : 0
         * gmtCreate : 2018-07-26 17:27:07
         * gmtModified : 2018-08-08 17:09:20
         * id : 107
         * tagId : 1
         * tagName : 医术高明
         */

        private String docNo;
        private int evaluationCount;
        private String gmtCreate;
        private String gmtModified;
        private int id;
        private int tagId;
        private String tagName;
        private boolean isSelector;

        public ListEvaluationTagDocBean(int evaluationCount, String tagName, boolean isSelector) {
            this.evaluationCount = evaluationCount;
            this.tagName = tagName;
            this.isSelector = isSelector;
        }

        public String getDocNo() {
            return docNo;
        }

        public void setDocNo(String docNo) {
            this.docNo = docNo;
        }

        public int getEvaluationCount() {
            return evaluationCount;
        }

        public void setEvaluationCount(int evaluationCount) {
            this.evaluationCount = evaluationCount;
        }

        public String getGmtCreate() {
            return gmtCreate;
        }

        public void setGmtCreate(String gmtCreate) {
            this.gmtCreate = gmtCreate;
        }

        public String getGmtModified() {
            return gmtModified;
        }

        public void setGmtModified(String gmtModified) {
            this.gmtModified = gmtModified;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getTagId() {
            return tagId;
        }

        public void setTagId(int tagId) {
            this.tagId = tagId;
        }

        public String getTagName() {
            return tagName;
        }

        public void setTagName(String tagName) {
            this.tagName = tagName;
        }

        public boolean isIselector() {
            return isSelector;
        }

        public void setIselector(boolean isSelector) {
            this.isSelector = isSelector;
        }
    }
}