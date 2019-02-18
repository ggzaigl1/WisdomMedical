package com.example.handsomelibrary.model;

import java.io.Serializable;

/**
 * Created by Stefan on 2018/7/13.
 * 我的医生 关注医生
 */
public class ListFollowDocBean implements Serializable {

    /**
     * id : 107
     * cusId : 2
     * docId : 17
     * gmtCreate : 2018-09-03 15:30:18
     * gmtModified : null
     * appDocEntityVO : {"id":17,"docNo":"21832496","docName":"李季松","titleId":"2","docPhotoUrl":null,"hospitalId":2,"departmentId":1,"specialty":null,"docInfo":null,"fans":null,"grade":null,"score":3,"commentCount":null,"diagnoseCount":100,"serviceCount":null,"serviceIncome":null,"duties":null,"personalDocPrice":null,"isExpert":null,"isTcm":null,"isShow":null,"isCheck":null,"isMarried":null,"isFreeClinicr":null,"isStopDiagnosis":null,"gmtCreate":"2018-09-03 15:30:18","gmtModified":null,"hospicalName":"武汉同济医院","departmentName":"儿科","titleName":"主治医师","isFollow":1,"listDocService":null,"listEvaluationTagDoc":null,"listEvaluationCusDoc":null}
     */

    private int id;
    private int cusId;
    private int docId;
    private String gmtCreate;
    private Object gmtModified;
    private AppDocEntityVOBean appDocEntityVO;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCusId() {
        return cusId;
    }

    public void setCusId(int cusId) {
        this.cusId = cusId;
    }

    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Object getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Object gmtModified) {
        this.gmtModified = gmtModified;
    }

    public AppDocEntityVOBean getAppDocEntityVO() {
        return appDocEntityVO;
    }

    public void setAppDocEntityVO(AppDocEntityVOBean appDocEntityVO) {
        this.appDocEntityVO = appDocEntityVO;
    }

    public static class AppDocEntityVOBean implements Serializable {
        /**
         * id : 17
         * docNo : 21832496
         * docName : 李季松
         * titleId : 2
         * docPhotoUrl : null
         * hospitalId : 2
         * departmentId : 1
         * specialty : null
         * docInfo : null
         * fans : null
         * grade : null
         * score : 3.0
         * commentCount : null
         * diagnoseCount : 100
         * serviceCount : null
         * serviceIncome : null
         * duties : null
         * personalDocPrice : null
         * isExpert : null
         * isTcm : null
         * isShow : null
         * isCheck : null
         * isMarried : null
         * isFreeClinicr : null
         * isStopDiagnosis : null
         * gmtCreate : 2018-09-03 15:30:18
         * gmtModified : null
         * hospicalName : 武汉同济医院
         * departmentName : 儿科
         * titleName : 主治医师
         * isFollow : 1
         * listDocService : null
         * listEvaluationTagDoc : null
         * listEvaluationCusDoc : null
         */

        private int id;
        private String docNo;
        private String docName;
        private String titleId;
        private Object docPhotoUrl;
        private int hospitalId;
        private int departmentId;
        private Object specialty;
        private Object docInfo;
        private Object fans;
        private Object grade;
        private double score;
        private Object commentCount;
        private int diagnoseCount;
        private Object serviceCount;
        private Object serviceIncome;
        private Object duties;
        private Object personalDocPrice;
        private Object isExpert;
        private Object isTcm;
        private Object isShow;
        private Object isCheck;
        private Object isMarried;
        private Object isFreeClinicr;
        private Object isStopDiagnosis;
        private String gmtCreate;
        private Object gmtModified;
        private String hospicalName;
        private String departmentName;
        private String titleName;
        private int isFollow;
        private Object listDocService;
        private Object listEvaluationTagDoc;
        private Object listEvaluationCusDoc;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDocNo() {
            return docNo;
        }

        public void setDocNo(String docNo) {
            this.docNo = docNo;
        }

        public String getDocName() {
            return docName;
        }

        public void setDocName(String docName) {
            this.docName = docName;
        }

        public String getTitleId() {
            return titleId;
        }

        public void setTitleId(String titleId) {
            this.titleId = titleId;
        }

        public Object getDocPhotoUrl() {
            return docPhotoUrl;
        }

        public void setDocPhotoUrl(Object docPhotoUrl) {
            this.docPhotoUrl = docPhotoUrl;
        }

        public int getHospitalId() {
            return hospitalId;
        }

        public void setHospitalId(int hospitalId) {
            this.hospitalId = hospitalId;
        }

        public int getDepartmentId() {
            return departmentId;
        }

        public void setDepartmentId(int departmentId) {
            this.departmentId = departmentId;
        }

        public Object getSpecialty() {
            return specialty;
        }

        public void setSpecialty(Object specialty) {
            this.specialty = specialty;
        }

        public Object getDocInfo() {
            return docInfo;
        }

        public void setDocInfo(Object docInfo) {
            this.docInfo = docInfo;
        }

        public Object getFans() {
            return fans;
        }

        public void setFans(Object fans) {
            this.fans = fans;
        }

        public Object getGrade() {
            return grade;
        }

        public void setGrade(Object grade) {
            this.grade = grade;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public Object getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(Object commentCount) {
            this.commentCount = commentCount;
        }

        public int getDiagnoseCount() {
            return diagnoseCount;
        }

        public void setDiagnoseCount(int diagnoseCount) {
            this.diagnoseCount = diagnoseCount;
        }

        public Object getServiceCount() {
            return serviceCount;
        }

        public void setServiceCount(Object serviceCount) {
            this.serviceCount = serviceCount;
        }

        public Object getServiceIncome() {
            return serviceIncome;
        }

        public void setServiceIncome(Object serviceIncome) {
            this.serviceIncome = serviceIncome;
        }

        public Object getDuties() {
            return duties;
        }

        public void setDuties(Object duties) {
            this.duties = duties;
        }

        public Object getPersonalDocPrice() {
            return personalDocPrice;
        }

        public void setPersonalDocPrice(Object personalDocPrice) {
            this.personalDocPrice = personalDocPrice;
        }

        public Object getIsExpert() {
            return isExpert;
        }

        public void setIsExpert(Object isExpert) {
            this.isExpert = isExpert;
        }

        public Object getIsTcm() {
            return isTcm;
        }

        public void setIsTcm(Object isTcm) {
            this.isTcm = isTcm;
        }

        public Object getIsShow() {
            return isShow;
        }

        public void setIsShow(Object isShow) {
            this.isShow = isShow;
        }

        public Object getIsCheck() {
            return isCheck;
        }

        public void setIsCheck(Object isCheck) {
            this.isCheck = isCheck;
        }

        public Object getIsMarried() {
            return isMarried;
        }

        public void setIsMarried(Object isMarried) {
            this.isMarried = isMarried;
        }

        public Object getIsFreeClinicr() {
            return isFreeClinicr;
        }

        public void setIsFreeClinicr(Object isFreeClinicr) {
            this.isFreeClinicr = isFreeClinicr;
        }

        public Object getIsStopDiagnosis() {
            return isStopDiagnosis;
        }

        public void setIsStopDiagnosis(Object isStopDiagnosis) {
            this.isStopDiagnosis = isStopDiagnosis;
        }

        public String getGmtCreate() {
            return gmtCreate;
        }

        public void setGmtCreate(String gmtCreate) {
            this.gmtCreate = gmtCreate;
        }

        public Object getGmtModified() {
            return gmtModified;
        }

        public void setGmtModified(Object gmtModified) {
            this.gmtModified = gmtModified;
        }

        public String getHospicalName() {
            return hospicalName;
        }

        public void setHospicalName(String hospicalName) {
            this.hospicalName = hospicalName;
        }

        public String getDepartmentName() {
            return departmentName;
        }

        public void setDepartmentName(String departmentName) {
            this.departmentName = departmentName;
        }

        public String getTitleName() {
            return titleName;
        }

        public void setTitleName(String titleName) {
            this.titleName = titleName;
        }

        public int getIsFollow() {
            return isFollow;
        }

        public void setIsFollow(int isFollow) {
            this.isFollow = isFollow;
        }

        public Object getListDocService() {
            return listDocService;
        }

        public void setListDocService(Object listDocService) {
            this.listDocService = listDocService;
        }

        public Object getListEvaluationTagDoc() {
            return listEvaluationTagDoc;
        }

        public void setListEvaluationTagDoc(Object listEvaluationTagDoc) {
            this.listEvaluationTagDoc = listEvaluationTagDoc;
        }

        public Object getListEvaluationCusDoc() {
            return listEvaluationCusDoc;
        }

        public void setListEvaluationCusDoc(Object listEvaluationCusDoc) {
            this.listEvaluationCusDoc = listEvaluationCusDoc;
        }
    }
}
