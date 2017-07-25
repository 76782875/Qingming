package com.example.administrator.qingming.model;

import java.util.List;

/**
 * Created by Administrator on 2017/5/22 0022.
 */

public class MyCaseModel {

    /**
     * status : 200
     * message : 获取成功
     * result : [{"id":"111","case_state":"2","ay":"路逃狱了","ah_number":"2017(民)第 0071 号","wtr":"张杰","dfdsr":"谢娜","sffs":"1","dlf":"151","jzf":null,"name":"嫖老师","sarq":"2017-07-04 00:00:00","ajlx":"1","police":"","procuratorate":null,"court":"","detention":"","slbm":"公安","ssbd":"51","ssjd":"0","ssdw":"0","bzsm":null},{"id":"116","case_state":"0","ay":"654564","ah_number":"2017(民)第 0071 号","wtr":"65256","dfdsr":"6545","sffs":"1","dlf":"654","jzf":null,"name":"嫖老师","sarq":"2017-07-07 00:00:00","ajlx":"1","police":"","procuratorate":null,"court":"","detention":"","slbm":"5145","ssbd":"54145","ssjd":"0","ssdw":"0","bzsm":null},{"id":"117","case_state":"2","ay":"445","ah_number":"2017(民)第 0071 号","wtr":"6565","dfdsr":"65156","sffs":"2","dlf":"654486","jzf":"6545","name":"嫖老师","sarq":"2017-07-07 00:00:00","ajlx":"1","police":"","procuratorate":null,"court":"","detention":"","slbm":"6565","ssbd":"6545","ssjd":"0","ssdw":"0","bzsm":"null"},{"id":"121","case_state":"5","ay":"gsg","ah_number":"2017(民)第 0071 号","wtr":"gaga","dfdsr":"gaga","sffs":"1","dlf":"56","jzf":"4","name":"嫖老师","sarq":"2017-07-07 00:00:00","ajlx":"1","police":"","procuratorate":null,"court":"","detention":"","slbm":"gaga","ssbd":"15","ssjd":"0","ssdw":"0","bzsm":"65789"},{"id":"122","case_state":"4","ay":"564","ah_number":"2017(民)第 0071 号","wtr":"564","dfdsr":"654","sffs":"1","dlf":"561","jzf":"654","name":"嫖老师","sarq":"2017-07-07 00:00:00","ajlx":"1","police":"","procuratorate":null,"court":"","detention":"","slbm":"654","ssbd":"56","ssjd":"0","ssdw":"0","bzsm":""},{"id":"123","case_state":"4","ay":"89489","ah_number":"2017(刑)第 0011 号","wtr":"684489","dfdsr":"84","sffs":"1","dlf":"651","jzf":"65","name":"嫖老师","sarq":"2017-07-07 00:00:00","ajlx":"2","police":"6+2","procuratorate":null,"court":"","detention":"654","slbm":"65445","ssbd":"65","ssjd":"1","ssdw":"0","bzsm":"654465"},{"id":"124","case_state":"6","ay":"564","ah_number":"2017(民)第 0071 号","wtr":"6+5","dfdsr":"65","sffs":"1","dlf":"654","jzf":"654","name":"嫖老师","sarq":"2017-07-07 00:00:00","ajlx":"1","police":"","procuratorate":"","court":"","detention":"","slbm":"51","ssbd":"56","ssjd":"0","ssdw":"0","bzsm":"6+56"},{"id":"125","case_state":"7","ay":"68","ah_number":"2017(民)第 0071 号","wtr":"98","dfdsr":"989","sffs":"1","dlf":"651","jzf":"654","name":"嫖老师","sarq":"2017-07-07 00:00:00","ajlx":"1","police":"","procuratorate":"","court":"","detention":"","slbm":"5","ssbd":"54","ssjd":"0","ssdw":"0","bzsm":"+65"},{"id":"126","case_state":"2","ay":"894","ah_number":"2017(刑)第 0011 号","wtr":"894","dfdsr":"65","sffs":"2","dlf":"654","jzf":"654","name":"嫖老师","sarq":"2017-07-07 00:00:00","ajlx":"2","police":"95","procuratorate":"654","court":"654","detention":"654","slbm":"65","ssbd":"84","ssjd":"1","ssdw":"0","bzsm":"8489"},{"id":"127","case_state":"5","ay":"+65+6","ah_number":"2017(刑)第 0011 号","wtr":"654546","dfdsr":"65156","sffs":"2","dlf":"654","jzf":"89","name":"嫖老师","sarq":"2017-07-07 00:00:00","ajlx":"2","police":"123","procuratorate":"456","court":"789","detention":"230","slbm":"654564","ssbd":"651564","ssjd":"2","ssdw":"0","bzsm":"184546"},{"id":"128","case_state":"4","ay":"56","ah_number":"2017(刑)第 0011 号","wtr":"54546","dfdsr":"561","sffs":"1","dlf":"651","jzf":"654","name":"嫖老师","sarq":"2017-07-07 00:00:00","ajlx":"2","police":"65","procuratorate":"654","court":"98","detention":"654","slbm":"65","ssbd":"321","ssjd":"1","ssdw":"1","bzsm":"65465"},{"id":"129","case_state":"4","ay":"65","ah_number":"2017(民)第 0071 号","wtr":"65","dfdsr":"6+5","sffs":"1","dlf":"54","jzf":"561","name":"嫖老师","sarq":"2017-07-07 00:00:00","ajlx":"1","police":"","procuratorate":"","court":"","detention":"","slbm":"654","ssbd":"52","ssjd":"0","ssdw":"1","bzsm":"654"},{"id":"135","case_state":"1","ay":",,.,m","ah_number":"2017(民)第011号","wtr":"gh","dfdsr":"hsh","sffs":"1","dlf":"654","jzf":"65","name":"嫖老师","sarq":"2017-07-19 00:00:00","ajlx":"1","police":"","procuratorate":"","court":"","detention":"","slbm":"shs","ssbd":"15","ssjd":"1","ssdw":"4","bzsm":"98"}]
     */

    private int status;
    private String message;
    private List<ResultBean> result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 111
         * case_state : 2
         * ay : 路逃狱了
         * ah_number : 2017(民)第 0071 号
         * wtr : 张杰
         * dfdsr : 谢娜
         * sffs : 1
         * dlf : 151
         * jzf : null
         * name : 嫖老师
         * sarq : 2017-07-04 00:00:00
         * ajlx : 1
         * police :
         * procuratorate : null
         * court :
         * detention :
         * slbm : 公安
         * ssbd : 51
         * ssjd : 0
         * ssdw : 0
         * bzsm : null
         */

        private String id;
        private String case_state;
        private String ay;
        private String ah_number;
        private String wtr;
        private String dfdsr;
        private String sffs;
        private int dlf;
        private int jzf;
        private String name;
        private String sarq;
        private int ajlx;
        private String police;
        private String procuratorate;
        private String court;
        private String detention;
        private String slbm;
        private String ssbd;
        private String ssjd;
        private String ssdw;
        private String bzsm;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCase_state() {
            return case_state;
        }

        public void setCase_state(String case_state) {
            this.case_state = case_state;
        }

        public String getAy() {
            return ay;
        }

        public void setAy(String ay) {
            this.ay = ay;
        }

        public String getAh_number() {
            return ah_number;
        }

        public void setAh_number(String ah_number) {
            this.ah_number = ah_number;
        }

        public String getWtr() {
            return wtr;
        }

        public void setWtr(String wtr) {
            this.wtr = wtr;
        }

        public String getDfdsr() {
            return dfdsr;
        }

        public void setDfdsr(String dfdsr) {
            this.dfdsr = dfdsr;
        }

        public String getSffs() {
            return sffs;
        }

        public void setSffs(String sffs) {
            this.sffs = sffs;
        }

        public int getDlf() {
            return dlf;
        }

        public void setDlf(int dlf) {
            this.dlf = dlf;
        }

        public int getJzf() {
            return jzf;
        }

        public void setJzf(int jzf) {
            this.jzf = jzf;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSarq() {
            return sarq;
        }

        public void setSarq(String sarq) {
            this.sarq = sarq;
        }

        public int getAjlx() {
            return ajlx;
        }

        public void setAjlx(int ajlx) {
            this.ajlx = ajlx;
        }

        public String getPolice() {
            return police;
        }

        public void setPolice(String police) {
            this.police = police;
        }

        public String getProcuratorate() {
            return procuratorate;
        }

        public void setProcuratorate(String procuratorate) {
            this.procuratorate = procuratorate;
        }

        public String getCourt() {
            return court;
        }

        public void setCourt(String court) {
            this.court = court;
        }

        public String getDetention() {
            return detention;
        }

        public void setDetention(String detention) {
            this.detention = detention;
        }

        public String getSlbm() {
            return slbm;
        }

        public void setSlbm(String slbm) {
            this.slbm = slbm;
        }

        public String getSsbd() {
            return ssbd;
        }

        public void setSsbd(String ssbd) {
            this.ssbd = ssbd;
        }

        public String getSsjd() {
            return ssjd;
        }

        public void setSsjd(String ssjd) {
            this.ssjd = ssjd;
        }

        public String getSsdw() {
            return ssdw;
        }

        public void setSsdw(String ssdw) {
            this.ssdw = ssdw;
        }

        public String getBzsm() {
            return bzsm;
        }

        public void setBzsm(String bzsm) {
            this.bzsm = bzsm;
        }
    }
}
