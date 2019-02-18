package com.example.handsomelibrary.model;


import java.io.Serializable;
import java.util.List;

/**
 * Created by Stefan on 2018/7/30.
 */
public class ListToAppBean implements Serializable {

    /**
     * first : 1
     * limit : 10
     * offset : 0
     * pageNo : 1
     * pageSize : 10
     * rows : [{"departmentId":1,"departmentName":"儿科","diagnoseCount":100,"docInfo":"为人民服务","docName":"李季松","docNo":"D1RH180908142100UYD","docUsername":"doc09","gmtCreate":"2018-07-26 17:27:07","hospicalName":"武汉同济医院","hospitalId":2,"id":17,"listDocService":[{"docNo":"D1RH180908142100UYD","gmtCreate":"2018-07-27 16:30:49","id":47,"isNeedSchedule":2,"isOpenService":1,"serviceName":"图文咨询","servicePrice":100,"serviceTypeId":1},{"docNo":"D1RH180908142100UYD","gmtCreate":"2018-07-27 16:30:49","id":48,"isNeedSchedule":1,"isOpenService":1,"serviceName":"视频问诊","servicePrice":200,"serviceTypeId":2},{"docNo":"D1RH180908142100UYD","gmtCreate":"2018-07-27 16:30:49","id":49,"isNeedSchedule":1,"isOpenService":1,"serviceName":"语音问诊","servicePrice":300,"serviceTypeId":3},{"docNo":"D1RH180908142100UYD","gmtCreate":"2018-07-27 16:30:49","id":50,"isNeedSchedule":1,"isOpenService":1,"serviceName":"家庭医生","servicePrice":400,"serviceTypeId":4},{"docNo":"D1RH180908142100UYD","gmtCreate":"2018-07-27 16:30:49","id":51,"isNeedSchedule":1,"isOpenService":1,"serviceName":"远程会诊","servicePrice":500,"serviceTypeId":5}],"score":3,"specialty":"白内障、近视眼","titleId":"2","titleName":"主治医师"},{"departmentId":1,"departmentName":"儿科","diagnoseCount":50,"docInfo":"为人民服务","docName":"林永寿","docNo":"DWG4180908142046VUX","docUsername":"doc08","gmtCreate":"2018-07-26 17:26:48","hospicalName":"武汉同济医院","hospitalId":2,"id":16,"listDocService":[{"docNo":"DWG4180908142046VUX","gmtCreate":"2018-07-27 16:30:46","id":42,"isNeedSchedule":2,"isOpenService":1,"serviceName":"图文咨询","servicePrice":100,"serviceTypeId":1},{"docNo":"DWG4180908142046VUX","gmtCreate":"2018-07-27 16:30:46","id":43,"isNeedSchedule":1,"isOpenService":1,"serviceName":"视频问诊","servicePrice":200,"serviceTypeId":2},{"docNo":"DWG4180908142046VUX","gmtCreate":"2018-07-27 16:30:46","id":44,"isNeedSchedule":1,"isOpenService":1,"serviceName":"语音问诊","servicePrice":300,"serviceTypeId":3},{"docNo":"DWG4180908142046VUX","gmtCreate":"2018-07-27 16:30:46","id":45,"isNeedSchedule":1,"isOpenService":1,"serviceName":"家庭医生","servicePrice":400,"serviceTypeId":4},{"docNo":"DWG4180908142046VUX","gmtCreate":"2018-07-27 16:30:46","id":46,"isNeedSchedule":1,"isOpenService":1,"serviceName":"远程会诊","servicePrice":500,"serviceTypeId":5}],"score":3,"specialty":"白内障、近视眼","titleId":"2","titleName":"主治医师"},{"departmentId":3,"departmentName":"全科","diagnoseCount":366,"docInfo":"为人民服务","docName":"张艳玲","docNo":"DCRN1809081420340WJ","docUsername":"doc07","gmtCreate":"2018-07-26 17:26:09","hospicalName":"武汉同济医院","hospitalId":2,"id":15,"listDocService":[{"docNo":"DCRN1809081420340WJ","gmtCreate":"2018-07-27 16:30:34","id":37,"isNeedSchedule":2,"isOpenService":1,"serviceName":"图文咨询","servicePrice":100,"serviceTypeId":1},{"docNo":"DCRN1809081420340WJ","gmtCreate":"2018-07-27 16:30:34","id":38,"isNeedSchedule":1,"isOpenService":1,"serviceName":"视频问诊","servicePrice":200,"serviceTypeId":2},{"docNo":"DCRN1809081420340WJ","gmtCreate":"2018-07-27 16:30:34","id":39,"isNeedSchedule":1,"isOpenService":1,"serviceName":"语音问诊","servicePrice":300,"serviceTypeId":3},{"docNo":"DCRN1809081420340WJ","gmtCreate":"2018-07-27 16:30:34","id":40,"isNeedSchedule":1,"isOpenService":1,"serviceName":"家庭医生","servicePrice":400,"serviceTypeId":4},{"docNo":"DCRN1809081420340WJ","gmtCreate":"2018-07-27 16:30:34","id":41,"isNeedSchedule":1,"isOpenService":1,"serviceName":"远程会诊","servicePrice":500,"serviceTypeId":5}],"score":3,"specialty":"白内障、近视眼","titleId":"2","titleName":"主治医师"},{"departmentId":3,"departmentName":"全科","diagnoseCount":457,"docInfo":"为人民服务","docName":"陈谷","docNo":"D879180908142026BKQ","docUsername":"doc06","gmtCreate":"2018-07-26 17:25:48","hospicalName":"武汉同济医院","hospitalId":2,"id":14,"listDocService":[{"docNo":"D879180908142026BKQ","gmtCreate":"2018-07-27 16:29:25","id":32,"isNeedSchedule":2,"isOpenService":1,"serviceName":"图文咨询","servicePrice":100,"serviceTypeId":1},{"docNo":"D879180908142026BKQ","gmtCreate":"2018-07-27 16:29:25","id":33,"isNeedSchedule":1,"isOpenService":1,"serviceName":"视频问诊","servicePrice":200,"serviceTypeId":2},{"docNo":"D879180908142026BKQ","gmtCreate":"2018-07-27 16:29:26","id":34,"isNeedSchedule":1,"isOpenService":1,"serviceName":"语音问诊","servicePrice":300,"serviceTypeId":3},{"docNo":"D879180908142026BKQ","gmtCreate":"2018-07-27 16:29:26","id":35,"isNeedSchedule":1,"isOpenService":1,"serviceName":"家庭医生","servicePrice":400,"serviceTypeId":4},{"docNo":"D879180908142026BKQ","gmtCreate":"2018-07-27 16:29:26","id":36,"isNeedSchedule":1,"isOpenService":1,"serviceName":"远程会诊","servicePrice":500,"serviceTypeId":5}],"score":3,"specialty":"白内障、近视眼","titleId":"2","titleName":"主治医师"},{"departmentId":1,"departmentName":"儿科","diagnoseCount":120,"docInfo":"为人民服务","docName":"李质","docNo":"DSHU180908142011X5W","docUsername":"doc05","gmtCreate":"2018-07-26 17:24:37","hospicalName":"武汉协和医院","hospitalId":1,"id":13,"listDocService":[{"docNo":"DSHU180908142011X5W","gmtCreate":"2018-07-26 17:27:07","id":27,"isNeedSchedule":2,"isOpenService":1,"serviceName":"图文咨询","servicePrice":100,"serviceTypeId":1},{"docNo":"DSHU180908142011X5W","gmtCreate":"2018-07-26 17:27:07","id":28,"isNeedSchedule":1,"isOpenService":1,"serviceName":"视频问诊","servicePrice":200,"serviceTypeId":2},{"docNo":"DSHU180908142011X5W","gmtCreate":"2018-07-26 17:27:07","id":29,"isNeedSchedule":1,"isOpenService":1,"serviceName":"语音问诊","servicePrice":300,"serviceTypeId":3},{"docNo":"DSHU180908142011X5W","gmtCreate":"2018-07-26 17:27:07","id":30,"isNeedSchedule":1,"isOpenService":1,"serviceName":"家庭医生","servicePrice":400,"serviceTypeId":4},{"docNo":"DSHU180908142011X5W","gmtCreate":"2018-07-26 17:27:07","id":31,"isNeedSchedule":1,"isOpenService":1,"serviceName":"远程会诊","servicePrice":500,"serviceTypeId":5}],"score":3,"specialty":"白内障、近视眼","titleId":"1","titleName":"住院医师"},{"departmentId":1,"departmentName":"儿科","diagnoseCount":70,"docInfo":"为人民服务","docName":"田硕","docNo":"DJBU180908141956VY3","docUsername":"doc04","gmtCreate":"2018-07-09 10:10:28","hospicalName":"武汉协和医院","hospitalId":1,"id":11,"listDocService":[{"docNo":"DJBU180908141956VY3","gmtCreate":"2018-07-26 17:26:48","id":22,"isNeedSchedule":2,"isOpenService":1,"serviceName":"图文咨询","servicePrice":100,"serviceTypeId":1},{"docNo":"DJBU180908141956VY3","gmtCreate":"2018-07-26 17:26:48","id":23,"isNeedSchedule":1,"isOpenService":1,"serviceName":"视频问诊","servicePrice":200,"serviceTypeId":2},{"docNo":"DJBU180908141956VY3","gmtCreate":"2018-07-26 17:26:48","id":24,"isNeedSchedule":1,"isOpenService":1,"serviceName":"语音问诊","servicePrice":300,"serviceTypeId":3},{"docNo":"DJBU180908141956VY3","gmtCreate":"2018-07-26 17:26:48","id":25,"isNeedSchedule":1,"isOpenService":1,"serviceName":"家庭医生","servicePrice":400,"serviceTypeId":4},{"docNo":"DJBU180908141956VY3","gmtCreate":"2018-07-26 17:26:48","id":26,"isNeedSchedule":1,"isOpenService":1,"serviceName":"远程会诊","servicePrice":500,"serviceTypeId":5}],"score":1.5,"specialty":"白内障、鼻炎、耳鸣","titleId":"3","titleName":"副主任医师"},{"departmentId":1,"departmentName":"儿科","diagnoseCount":80,"docInfo":"为人民服务","docName":"张丽","docNo":"D0AE180908141940KSX","docUsername":"doc03","gmtCreate":"2018-07-06 14:55:38","hospicalName":"武汉协和医院","hospitalId":1,"id":10,"listDocService":[{"docNo":"D0AE180908141940KSX","gmtCreate":"2018-07-26 17:26:09","id":17,"isNeedSchedule":2,"isOpenService":1,"serviceName":"图文咨询","servicePrice":100,"serviceTypeId":1},{"docNo":"D0AE180908141940KSX","gmtCreate":"2018-07-26 17:26:09","id":18,"isNeedSchedule":1,"isOpenService":1,"serviceName":"视频问诊","servicePrice":200,"serviceTypeId":2},{"docNo":"D0AE180908141940KSX","gmtCreate":"2018-07-26 17:26:09","id":19,"isNeedSchedule":1,"isOpenService":1,"serviceName":"语音问诊","servicePrice":300,"serviceTypeId":3},{"docNo":"D0AE180908141940KSX","gmtCreate":"2018-07-26 17:26:09","id":20,"isNeedSchedule":1,"isOpenService":1,"serviceName":"家庭医生","servicePrice":400,"serviceTypeId":4},{"docNo":"D0AE180908141940KSX","gmtCreate":"2018-07-26 17:26:09","id":21,"isNeedSchedule":1,"isOpenService":1,"serviceName":"远程会诊","servicePrice":500,"serviceTypeId":5}],"score":2.5,"specialty":"白内障、近视眼","titleId":"3","titleName":"副主任医师"},{"departmentId":2,"departmentName":"内科","diagnoseCount":30,"docInfo":"为人民服务","docName":"王曙光","docNo":"DLWP180908141904PKT","docUsername":"doc02","hospicalName":"武汉同济医院","hospitalId":2,"id":3,"listDocService":[{"docNo":"DLWP180908141904PKT","gmtCreate":"2018-07-26 17:25:48","id":12,"isNeedSchedule":2,"isOpenService":1,"serviceName":"图文咨询","servicePrice":100,"serviceTypeId":1},{"docNo":"DLWP180908141904PKT","gmtCreate":"2018-07-26 17:25:48","id":13,"isNeedSchedule":1,"isOpenService":1,"serviceName":"视频问诊","servicePrice":200,"serviceTypeId":2},{"docNo":"DLWP180908141904PKT","gmtCreate":"2018-07-26 17:25:48","id":14,"isNeedSchedule":1,"isOpenService":1,"serviceName":"语音问诊","servicePrice":300,"serviceTypeId":3},{"docNo":"DLWP180908141904PKT","gmtCreate":"2018-07-26 17:25:48","id":15,"isNeedSchedule":1,"isOpenService":1,"serviceName":"家庭医生","servicePrice":400,"serviceTypeId":4},{"docNo":"DLWP180908141904PKT","gmtCreate":"2018-07-26 17:25:48","id":16,"isNeedSchedule":1,"isOpenService":1,"serviceName":"远程会诊","servicePrice":500,"serviceTypeId":5}],"score":4.5,"specialty":"内分泌系统、鼻炎、青光眼","titleId":"4","titleName":"主任医师"},{"departmentId":2,"departmentName":"内科","diagnoseCount":60,"docInfo":"为人民服务","docName":"牛华鹏","docNo":"DBZD1809081417063Y4","docUsername":"doc01","hospicalName":"武汉同济医院","hospitalId":2,"id":2,"listDocService":[{"docNo":"DBZD1809081417063Y4","gmtCreate":"2018-07-26 17:24:37","id":7,"isNeedSchedule":2,"isOpenService":1,"serviceName":"图文咨询","servicePrice":100,"serviceTypeId":1},{"docNo":"DBZD1809081417063Y4","gmtCreate":"2018-07-26 17:24:37","id":8,"isNeedSchedule":1,"isOpenService":1,"serviceName":"视频问诊","servicePrice":200,"serviceTypeId":2},{"docNo":"DBZD1809081417063Y4","gmtCreate":"2018-07-26 17:24:37","id":9,"isNeedSchedule":1,"isOpenService":1,"serviceName":"语音问诊","servicePrice":300,"serviceTypeId":3},{"docNo":"DBZD1809081417063Y4","gmtCreate":"2018-07-26 17:24:37","id":10,"isNeedSchedule":1,"isOpenService":1,"serviceName":"家庭医生","servicePrice":400,"serviceTypeId":4},{"docNo":"DBZD1809081417063Y4","gmtCreate":"2018-07-26 17:24:37","id":11,"isNeedSchedule":1,"isOpenService":1,"serviceName":"远程会诊","servicePrice":500,"serviceTypeId":5}],"score":5,"specialty":"内分泌系统、鼻炎、青光眼","titleId":"2","titleName":"主治医师"},{"departmentId":2,"departmentName":"内科","diagnoseCount":10,"docInfo":"为人民服务","docName":"林东汉","docNo":"D2RG1809081421153VH","docUsername":"doc10","hospicalName":"武汉协和医院","hospitalId":1,"id":1,"listDocService":[{"docNo":"D2RG1809081421153VH","gmtCreate":"2018-07-09 10:10:28","id":2,"isNeedSchedule":2,"isOpenService":1,"serviceName":"图文咨询","servicePrice":100,"serviceTypeId":1},{"docNo":"D2RG1809081421153VH","gmtCreate":"2018-07-09 10:10:28","id":3,"isNeedSchedule":1,"isOpenService":1,"serviceName":"视频问诊","servicePrice":200,"serviceTypeId":2},{"docNo":"D2RG1809081421153VH","gmtCreate":"2018-07-09 10:10:28","id":4,"isNeedSchedule":1,"isOpenService":1,"serviceName":"语音问诊","servicePrice":300,"serviceTypeId":3},{"docNo":"D2RG1809081421153VH","gmtCreate":"2018-07-09 10:10:28","id":5,"isNeedSchedule":1,"isOpenService":1,"serviceName":"家庭医生","servicePrice":400,"serviceTypeId":4},{"docNo":"D2RG1809081421153VH","gmtCreate":"2018-07-09 10:10:28","id":6,"isNeedSchedule":1,"isOpenService":1,"serviceName":"远程会诊","servicePrice":500,"serviceTypeId":5}],"score":3.5,"specialty":"呼吸系统，消化系统、内分泌系统、鼻炎、青光眼","titleId":"4","titleName":"主任医师"}]
     * total : 10
     * totalPages : 1
     */

    private int first;
    private int limit;
    private int offset;
    private int pageNo;
    private int pageSize;
    private int total;
    private int totalPages;
    private List<RowsBean> rows;

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
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

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean implements Serializable {
        /**
         * departmentId : 1
         * departmentName : 儿科
         * diagnoseCount : 100
         * docInfo : 为人民服务
         * docName : 李季松
         * docNo : D1RH180908142100UYD
         * docUsername : doc09
         * gmtCreate : 2018-07-26 17:27:07
         * hospicalName : 武汉同济医院
         * hospitalId : 2
         * id : 17
         * listDocService : [{"docNo":"D1RH180908142100UYD","gmtCreate":"2018-07-27 16:30:49","id":47,"isNeedSchedule":2,"isOpenService":1,"serviceName":"图文咨询","servicePrice":100,"serviceTypeId":1},{"docNo":"D1RH180908142100UYD","gmtCreate":"2018-07-27 16:30:49","id":48,"isNeedSchedule":1,"isOpenService":1,"serviceName":"视频问诊","servicePrice":200,"serviceTypeId":2},{"docNo":"D1RH180908142100UYD","gmtCreate":"2018-07-27 16:30:49","id":49,"isNeedSchedule":1,"isOpenService":1,"serviceName":"语音问诊","servicePrice":300,"serviceTypeId":3},{"docNo":"D1RH180908142100UYD","gmtCreate":"2018-07-27 16:30:49","id":50,"isNeedSchedule":1,"isOpenService":1,"serviceName":"家庭医生","servicePrice":400,"serviceTypeId":4},{"docNo":"D1RH180908142100UYD","gmtCreate":"2018-07-27 16:30:49","id":51,"isNeedSchedule":1,"isOpenService":1,"serviceName":"远程会诊","servicePrice":500,"serviceTypeId":5}]
         * score : 3.0
         * specialty : 白内障、近视眼
         * titleId : 2
         * titleName : 主治医师
         */

        private int departmentId;
        private String departmentName;
        private int diagnoseCount;
        private String docInfo;
        private String docName;
        private String docNo;
        private String docUsername;
        private String gmtCreate;
        private String hospicalName;
        private int hospitalId;
        private int id;
        private double score;
        private String specialty;
        private String titleId;
        private String titleName;
        private List<ListDocServiceBean> listDocService;

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

        public String getDocUsername() {
            return docUsername;
        }

        public void setDocUsername(String docUsername) {
            this.docUsername = docUsername;
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

        public static class ListDocServiceBean implements Serializable {
            /**
             * docNo : D1RH180908142100UYD
             * gmtCreate : 2018-07-27 16:30:49
             * id : 47
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
    }
}
