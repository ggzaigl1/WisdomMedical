package com.example.handsomelibrary.model;

import java.io.Serializable;

/**
 * Created by Stefan on 2018/7/19.
 * 我的咨询
 */
public class ListConsultBean implements Serializable {

    /**
     * id : 116
     * cusId : 55
     * username : null
     * docId : 2
     * serviceId : 2
     * visitMemberId : 54
     * diseaseName : 空调病
     * consultContent : 真的不舒服
     * image :
     * reserveDate : 2018-09-09
     * reserveTime : 2
     * orderNo : SP1809171004522740
     * orderStatus : 6
     * payType : null
     * isEvaluation : null
     * gmtCreate : 2018-09-17 10:05:27
     * gmtModified : null
     * memberName : null
     * visitMemberName : 张静
     * gender : 0
     * birthday : 1999-04-24
     * age : null
     * servicePrice : 200.00
     * appDocEntityVO : {"id":2,"docNo":"DBZD1809081417063Y4","docName":"李国庆","titleId":"2","docPhotoUrl":"","hospitalId":2,"departmentId":6,"specialty":null,"docInfo":null,"fans":null,"grade":null,"score":5,"commentCount":null,"diagnoseCount":60,"serviceCount":null,"serviceIncome":null,"duties":null,"personalDocPrice":null,"isExpert":null,"isTcm":null,"isShow":null,"isCheck":null,"isMarried":null,"isFreeClinicr":null,"isStopDiagnosis":null,"gmtCreate":null,"gmtModified":null,"nickname":null,"email":null,"mobile":null,"sex":null,"hospitalName":null,"departmentName":"放射科","hospicalName":"武汉同济医院","titleName":"主治医师","isFollow":null,"docUsername":"doc01","listDocService":null,"listEvaluationTagDoc":null,"listEvaluationCusDoc":null}
     */

    private int id;
    private int cusId;
    private Object username;
    private int docId;
    private int serviceId;
    private int visitMemberId;
    private String diseaseName;
    private String consultContent;
    private String image;
    private String reserveDate;
    private String reserveTime;
    private String orderNo;
    private int orderStatus;
    private Object payType;
    private String chatGroupId = "";
    private Integer isEvaluation;
    private String gmtCreate;
    private Object gmtModified;
    private Object memberName;
    private String visitMemberName;
    private String gender;
    private String birthday;
    private Object age;
    private String servicePrice;
    private AppDocEntityVOBean appDocEntityVO;

    public String getChatGroupId() {
        return chatGroupId;
    }

    public void setChatGroupId(String chatGroupId) {
        this.chatGroupId = chatGroupId;
    }

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

    public Object getUsername() {
        return username;
    }

    public void setUsername(Object username) {
        this.username = username;
    }

    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getVisitMemberId() {
        return visitMemberId;
    }

    public void setVisitMemberId(int visitMemberId) {
        this.visitMemberId = visitMemberId;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public String getConsultContent() {
        return consultContent;
    }

    public void setConsultContent(String consultContent) {
        this.consultContent = consultContent;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getReserveDate() {
        return reserveDate;
    }

    public void setReserveDate(String reserveDate) {
        this.reserveDate = reserveDate;
    }

    public String getReserveTime() {
        return reserveTime;
    }

    public void setReserveTime(String reserveTime) {
        this.reserveTime = reserveTime;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Object getPayType() {
        return payType;
    }

    public void setPayType(Object payType) {
        this.payType = payType;
    }

    public Integer getIsEvaluation() {
        return isEvaluation;
    }

    public void setIsEvaluation(Integer isEvaluation) {
        this.isEvaluation = isEvaluation;
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

    public Object getMemberName() {
        return memberName;
    }

    public void setMemberName(Object memberName) {
        this.memberName = memberName;
    }

    public String getVisitMemberName() {
        return visitMemberName;
    }

    public void setVisitMemberName(String visitMemberName) {
        this.visitMemberName = visitMemberName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Object getAge() {
        return age;
    }

    public void setAge(Object age) {
        this.age = age;
    }

    public String getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(String servicePrice) {
        this.servicePrice = servicePrice;
    }

    public AppDocEntityVOBean getAppDocEntityVO() {
        return appDocEntityVO;
    }

    public void setAppDocEntityVO(AppDocEntityVOBean appDocEntityVO) {
        this.appDocEntityVO = appDocEntityVO;
    }

    public static class AppDocEntityVOBean implements Serializable {
        /**
         * id : 2
         * docNo : DBZD1809081417063Y4
         * docName : 李国庆
         * titleId : 2
         * docPhotoUrl :
         * hospitalId : 2
         * departmentId : 6
         * specialty : null
         * docInfo : null
         * fans : null
         * grade : null
         * score : 5.0
         * commentCount : null
         * diagnoseCount : 60
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
         * gmtCreate : null
         * gmtModified : null
         * nickname : null
         * email : null
         * mobile : null
         * sex : null
         * hospitalName : null
         * departmentName : 放射科
         * hospicalName : 武汉同济医院
         * titleName : 主治医师
         * isFollow : null
         * docUsername : doc01
         * listDocService : null
         * listEvaluationTagDoc : null
         * listEvaluationCusDoc : null
         */

        private int id;
        private String docNo;
        private String docName;
        private String titleId;
        private String docPhotoUrl;
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
        private Object gmtCreate;
        private Object gmtModified;
        private Object nickname;
        private Object email;
        private Object mobile;
        private Object sex;
        private Object hospitalName;
        private String departmentName;
        private String hospicalName;
        private String titleName;
        private Object isFollow;
        private String docUsername;
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

        public String getDocPhotoUrl() {
            return docPhotoUrl;
        }

        public void setDocPhotoUrl(String docPhotoUrl) {
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

        public Object getNickname() {
            return nickname;
        }

        public void setNickname(Object nickname) {
            this.nickname = nickname;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public Object getMobile() {
            return mobile;
        }

        public void setMobile(Object mobile) {
            this.mobile = mobile;
        }

        public Object getSex() {
            return sex;
        }

        public void setSex(Object sex) {
            this.sex = sex;
        }

        public Object getHospitalName() {
            return hospitalName;
        }

        public void setHospitalName(Object hospitalName) {
            this.hospitalName = hospitalName;
        }

        public String getDepartmentName() {
            return departmentName;
        }

        public void setDepartmentName(String departmentName) {
            this.departmentName = departmentName;
        }

        public String getHospicalName() {
            return hospicalName;
        }

        public void setHospicalName(String hospicalName) {
            this.hospicalName = hospicalName;
        }

        public String getTitleName() {
            return titleName;
        }

        public void setTitleName(String titleName) {
            this.titleName = titleName;
        }

        public Object getIsFollow() {
            return isFollow;
        }

        public void setIsFollow(Object isFollow) {
            this.isFollow = isFollow;
        }

        public String getDocUsername() {
            return docUsername;
        }

        public void setDocUsername(String docUsername) {
            this.docUsername = docUsername;
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