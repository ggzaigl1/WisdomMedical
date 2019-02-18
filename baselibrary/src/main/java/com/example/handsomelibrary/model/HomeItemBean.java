package com.example.handsomelibrary.model;

import java.io.Serializable;
import java.util.List;

/**
 * 首页 列表实体类
 * Created by fangs on 2018/6/27.
 */
public class HomeItemBean implements Serializable {

    /**
     * offset : 0
     * limit : 10
     * pageNo : 1
     * pageSize : 10
     * rows : [{"id":14,"docNo":"23550420","docName":"陈谷","titleId":"2","docPhotoUrl":null,"hospitalId":2,"departmentId":3,"specialty":"白内障、近视眼","docInfo":"为人民服务","fans":null,"grade":null,"score":3,"commentCount":null,"diagnoseCount":457,"serviceCount":null,"serviceIncome":null,"duties":null,"personalDocPrice":null,"isExpert":null,"isTcm":null,"isShow":null,"isCheck":null,"isMarried":null,"isFreeClinicr":null,"isStopDiagnosis":null,"gmtCreate":"2018-07-26 17:25:48","gmtModified":null,"hospicalName":"武汉同济医院","departmentName":"全科","titleName":"主治医师","isFollow":null,"listDocService":[{"id":12,"docNo":"23550420","serviceTypeId":1,"servicePrice":100,"isOpenService":1,"isNeedSchedule":2,"gmtCreate":"2018-07-26 17:25:48","gmtModified":null,"serviceName":"图文咨询"},{"id":13,"docNo":"23550420","serviceTypeId":2,"servicePrice":200,"isOpenService":1,"isNeedSchedule":1,"gmtCreate":"2018-07-26 17:25:48","gmtModified":null,"serviceName":"视频问诊"},{"id":14,"docNo":"23550420","serviceTypeId":3,"servicePrice":300,"isOpenService":1,"isNeedSchedule":1,"gmtCreate":"2018-07-26 17:25:48","gmtModified":null,"serviceName":"语音问诊"},{"id":15,"docNo":"23550420","serviceTypeId":4,"servicePrice":400,"isOpenService":1,"isNeedSchedule":1,"gmtCreate":"2018-07-26 17:25:48","gmtModified":null,"serviceName":"家庭医生"},{"id":16,"docNo":"23550420","serviceTypeId":5,"servicePrice":500,"isOpenService":1,"isNeedSchedule":1,"gmtCreate":"2018-07-26 17:25:48","gmtModified":null,"serviceName":"远程会诊"}],"listEvaluationTagDoc":null,"listEvaluationCusDoc":null},{"id":15,"docNo":"23492600","docName":"张艳玲","titleId":"2","docPhotoUrl":null,"hospitalId":2,"departmentId":3,"specialty":"白内障、近视眼","docInfo":"为人民服务","fans":null,"grade":null,"score":3,"commentCount":null,"diagnoseCount":366,"serviceCount":null,"serviceIncome":null,"duties":null,"personalDocPrice":null,"isExpert":null,"isTcm":null,"isShow":null,"isCheck":null,"isMarried":null,"isFreeClinicr":null,"isStopDiagnosis":null,"gmtCreate":"2018-07-26 17:26:09","gmtModified":null,"hospicalName":"武汉同济医院","departmentName":"全科","titleName":"主治医师","isFollow":null,"listDocService":[{"id":17,"docNo":"23492600","serviceTypeId":1,"servicePrice":100,"isOpenService":1,"isNeedSchedule":2,"gmtCreate":"2018-07-26 17:26:09","gmtModified":null,"serviceName":"图文咨询"},{"id":18,"docNo":"23492600","serviceTypeId":2,"servicePrice":200,"isOpenService":1,"isNeedSchedule":1,"gmtCreate":"2018-07-26 17:26:09","gmtModified":null,"serviceName":"视频问诊"},{"id":19,"docNo":"23492600","serviceTypeId":3,"servicePrice":300,"isOpenService":1,"isNeedSchedule":1,"gmtCreate":"2018-07-26 17:26:09","gmtModified":null,"serviceName":"语音问诊"},{"id":20,"docNo":"23492600","serviceTypeId":4,"servicePrice":400,"isOpenService":1,"isNeedSchedule":1,"gmtCreate":"2018-07-26 17:26:09","gmtModified":null,"serviceName":"家庭医生"},{"id":21,"docNo":"23492600","serviceTypeId":5,"servicePrice":500,"isOpenService":1,"isNeedSchedule":1,"gmtCreate":"2018-07-26 17:26:09","gmtModified":null,"serviceName":"远程会诊"}],"listEvaluationTagDoc":null,"listEvaluationCusDoc":null},{"id":13,"docNo":"11320427","docName":"李质","titleId":"1","docPhotoUrl":null,"hospitalId":1,"departmentId":1,"specialty":"白内障、近视眼","docInfo":"为人民服务","fans":null,"grade":null,"score":3,"commentCount":null,"diagnoseCount":120,"serviceCount":null,"serviceIncome":null,"duties":null,"personalDocPrice":null,"isExpert":null,"isTcm":null,"isShow":null,"isCheck":null,"isMarried":null,"isFreeClinicr":null,"isStopDiagnosis":null,"gmtCreate":"2018-07-26 17:24:37","gmtModified":null,"hospicalName":"武汉协和医院","departmentName":"儿科","titleName":"住院医师","isFollow":null,"listDocService":[{"id":7,"docNo":"11320427","serviceTypeId":1,"servicePrice":100,"isOpenService":1,"isNeedSchedule":2,"gmtCreate":"2018-07-26 17:24:37","gmtModified":null,"serviceName":"图文咨询"},{"id":8,"docNo":"11320427","serviceTypeId":2,"servicePrice":200,"isOpenService":1,"isNeedSchedule":1,"gmtCreate":"2018-07-26 17:24:37","gmtModified":null,"serviceName":"视频问诊"},{"id":9,"docNo":"11320427","serviceTypeId":3,"servicePrice":300,"isOpenService":1,"isNeedSchedule":1,"gmtCreate":"2018-07-26 17:24:37","gmtModified":null,"serviceName":"语音问诊"},{"id":10,"docNo":"11320427","serviceTypeId":4,"servicePrice":400,"isOpenService":1,"isNeedSchedule":1,"gmtCreate":"2018-07-26 17:24:37","gmtModified":null,"serviceName":"家庭医生"},{"id":11,"docNo":"11320427","serviceTypeId":5,"servicePrice":500,"isOpenService":1,"isNeedSchedule":1,"gmtCreate":"2018-07-26 17:24:37","gmtModified":null,"serviceName":"远程会诊"}],"listEvaluationTagDoc":null,"listEvaluationCusDoc":null}]
     * total : 10
     * totalPages : 1
     * first : 1
     */

    private int offset;
    private int limit;
    private int pageNo;
    private int pageSize;
    private int total;
    private int totalPages;
    private int first;
    private List<RowsBean> rows;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean implements Serializable{
        /**
         * id : 14
         * docNo : 23550420
         * docName : 陈谷
         * titleId : 2
         * docPhotoUrl : null
         * hospitalId : 2
         * departmentId : 3
         * specialty : 白内障、近视眼
         * docInfo : 为人民服务
         * fans : null
         * grade : null
         * score : 3.0
         * commentCount : null
         * diagnoseCount : 457
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
         * gmtCreate : 2018-07-26 17:25:48
         * gmtModified : null
         * hospicalName : 武汉同济医院
         * departmentName : 全科
         * titleName : 主治医师
         * isFollow : null
         * listDocService : [{"id":12,"docNo":"23550420","serviceTypeId":1,"servicePrice":100,"isOpenService":1,"isNeedSchedule":2,"gmtCreate":"2018-07-26 17:25:48","gmtModified":null,"serviceName":"图文咨询"},{"id":13,"docNo":"23550420","serviceTypeId":2,"servicePrice":200,"isOpenService":1,"isNeedSchedule":1,"gmtCreate":"2018-07-26 17:25:48","gmtModified":null,"serviceName":"视频问诊"},{"id":14,"docNo":"23550420","serviceTypeId":3,"servicePrice":300,"isOpenService":1,"isNeedSchedule":1,"gmtCreate":"2018-07-26 17:25:48","gmtModified":null,"serviceName":"语音问诊"},{"id":15,"docNo":"23550420","serviceTypeId":4,"servicePrice":400,"isOpenService":1,"isNeedSchedule":1,"gmtCreate":"2018-07-26 17:25:48","gmtModified":null,"serviceName":"家庭医生"},{"id":16,"docNo":"23550420","serviceTypeId":5,"servicePrice":500,"isOpenService":1,"isNeedSchedule":1,"gmtCreate":"2018-07-26 17:25:48","gmtModified":null,"serviceName":"远程会诊"}]
         * listEvaluationTagDoc : null
         * listEvaluationCusDoc : null
         */

        private int id;
        private String docNo = "";
        private String docName = "";
        private String titleId = "";
        private String docPhotoUrl = "";
        private int hospitalId;
        private int departmentId;
        private String specialty = "";
        private String docInfo = "";
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
        private String gmtCreate = "";
        private Object gmtModified;
        private String hospicalName = "";
        private String departmentName = "";
        private String titleName = "";
        private Object isFollow;
        private Object listEvaluationTagDoc;
        private Object listEvaluationCusDoc;
        private List<ListDocServiceBean> listDocService;

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

        public String getSpecialty() {
            return specialty;
        }

        public void setSpecialty(String specialty) {
            this.specialty = specialty;
        }

        public String getDocInfo() {
            return docInfo;
        }

        public void setDocInfo(String docInfo) {
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

        public Object getIsFollow() {
            return isFollow;
        }

        public void setIsFollow(Object isFollow) {
            this.isFollow = isFollow;
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

        public List<ListDocServiceBean> getListDocService() {
            return listDocService;
        }

        public void setListDocService(List<ListDocServiceBean> listDocService) {
            this.listDocService = listDocService;
        }

        public static class ListDocServiceBean {
            /**
             * id : 12
             * docNo : 23550420
             * serviceTypeId : 1
             * servicePrice : 100.0
             * isOpenService : 1
             * isNeedSchedule : 2
             * gmtCreate : 2018-07-26 17:25:48
             * gmtModified : null
             * serviceName : 图文咨询
             */

            private int id;
            private String docNo;
            private int serviceTypeId;
            private double servicePrice;
            private int isOpenService;
            private int isNeedSchedule;
            private String gmtCreate;
            private Object gmtModified;
            private String serviceName;

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

            public int getServiceTypeId() {
                return serviceTypeId;
            }

            public void setServiceTypeId(int serviceTypeId) {
                this.serviceTypeId = serviceTypeId;
            }

            public double getServicePrice() {
                return servicePrice;
            }

            public void setServicePrice(double servicePrice) {
                this.servicePrice = servicePrice;
            }

            public int getIsOpenService() {
                return isOpenService;
            }

            public void setIsOpenService(int isOpenService) {
                this.isOpenService = isOpenService;
            }

            public int getIsNeedSchedule() {
                return isNeedSchedule;
            }

            public void setIsNeedSchedule(int isNeedSchedule) {
                this.isNeedSchedule = isNeedSchedule;
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

            public String getServiceName() {
                return serviceName;
            }

            public void setServiceName(String serviceName) {
                this.serviceName = serviceName;
            }
        }
    }
}
