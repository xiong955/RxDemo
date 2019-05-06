package com.xiong.rxdemo.bean;

import java.util.List;

/**
 * @author: xiong
 * @time: 2019/05/06
 * @说明:
 */
public class News {

    /**
     * code : 200
     * message : 成功!
     * result : [{"path":"https://news.163.com/19/0505/06/EED4MPVP0001875P.html","image":"http://cms-bucket.ws.126.net/2019/05/05/953323ac3f0c450c9782b6ec146f21b8.png?imageView&thumbnail=140y88&quality=85","title":"公交私车抢拽方向盘都可能构罪 加重情节不得缓刑","passtime":"2019-05-05 10:00:33"},{"path":"https://news.163.com/19/0505/06/EED4F0N50001875O.html","image":"http://cms-bucket.ws.126.net/2019/05/05/8c7280ef97ef4981a72f857a40d62a0b.png?imageView&thumbnail=140y88&quality=85","title":"继取消石油进口豁免后 美国又盯上了伊朗的铀出口","passtime":"2019-05-05 10:00:33"},{"path":"https://news.163.com/19/0505/06/EED422390001875O.html","image":"http://cms-bucket.ws.126.net/2019/05/05/430fda04a7dc4ed3b6a94170648156fa.png?imageView&thumbnail=140y88&quality=85","title":"3分钟看完＂股神＂10大金句:我希望自己能够活更久","passtime":"2019-05-05 10:00:33"},{"path":"https://news.163.com/19/0505/06/EED2PG9N000187VE.html","image":"http://cms-bucket.ws.126.net/2019/05/05/f7b11af0e81a4981bdb6473fecea656b.png?imageView&thumbnail=140y88&quality=85","title":"女出纳侵占公司资产打赏男主播490万 自称能解压","passtime":"2019-05-05 10:00:33"},{"path":"https://news.163.com/19/0505/06/EED2FT2A0001875N.html","image":"http://cms-bucket.ws.126.net/2019/05/05/6eb29cba561a4464b3d787ba14c5ae98.png?imageView&thumbnail=140y88&quality=85","title":"蔡英文座舰演习中播放日本演歌改编曲 台网友怒了","passtime":"2019-05-05 10:00:33"},{"path":"https://news.163.com/19/0505/05/EED1V8LL0001875P.html","image":"http://cms-bucket.ws.126.net/2019/05/05/ad3a90df691f472ab00d09b640d11bd9.png?imageView&thumbnail=140y88&quality=85","title":"男子长期在外地打工 突然接到妻子来电＂我怀孕了＂","passtime":"2019-05-05 10:00:33"},{"path":"https://news.163.com/19/0505/05/EED16DTK0001875P.html","image":"http://cms-bucket.ws.126.net/2019/05/05/c1b05aa2de604161aafab475e97f4587.png?imageView&thumbnail=140y88&quality=85","title":"农商行员工私转客户百余万潜逃 法院判银行无过错","passtime":"2019-05-05 10:00:33"},{"path":"https://tech.163.com/19/0505/09/EEDD0MTC00097U7T.html","image":"http://cms-bucket.ws.126.net/2019/05/05/42129c95c98d4bc2a92ec11ce89a327b.png?imageView&thumbnail=140y88&quality=85","title":"消费者组织称iPhone电池寿命被苹果高估达51%","passtime":"2019-05-05 10:00:33"},{"path":"https://news.163.com/19/0505/05/EED0SG900001875P.html","image":"http://cms-bucket.ws.126.net/2019/05/05/4a721513409143199f062333549e5e48.png?imageView&thumbnail=140y88&quality=85","title":"媒体:送赵雨思进斯坦福的 是老百姓买的脑心通胶囊","passtime":"2019-05-05 10:00:33"},{"path":"https://news.163.com/19/0505/01/EECK93MK000187VE.html","image":"http://cms-bucket.ws.126.net/2019/05/05/38a32f02fb8c410aaa2a33a2a22a6789.png?imageView&thumbnail=140y88&quality=85","title":"11岁中国男孩现场提问＂人性问题＂ 巴菲特支招了","passtime":"2019-05-05 10:00:33"},{"path":"https://news.163.com/19/0505/01/EECH403M0001875O.html","image":"http://cms-bucket.ws.126.net/2019/05/05/1ee0ce521cf440cfa234a02e02ed478e.png?imageView&thumbnail=140y88&quality=85","title":"13岁男生和父亲来感恩 巴菲特为其回答1个人生问题","passtime":"2019-05-05 10:00:33"},{"path":"https://news.163.com/19/0505/00/EECG90A30001875O.html","image":"http://cms-bucket.ws.126.net/2019/05/05/a8349974658047e6af6fda114cbd76df.png?imageView&thumbnail=140y88&quality=85","title":"巴菲特再谈比特币投机：如在拉斯维加斯赌博","passtime":"2019-05-05 10:00:33"},{"path":"https://news.163.com/19/0505/00/EECFK7T20001875P.html","image":"http://cms-bucket.ws.126.net/2019/05/05/aee77d2586ce45e9a62c41502ae3f332.png?imageView&thumbnail=140y88&quality=85","title":"起底步长制药＂金融帝国＂:毛利率多年维持＂80%+＂","passtime":"2019-05-05 10:00:33"},{"path":"https://news.163.com/19/0505/00/EECEC19H00018AOP.html","image":"http://cms-bucket.ws.126.net/2019/05/05/afdabf2b90d248829fb370691f810790.png?imageView&thumbnail=140y88&quality=85","title":"花4370万送女上斯坦福会被追责吗?律师详解5大焦点","passtime":"2019-05-05 10:00:33"},{"path":"https://news.163.com/19/0505/00/EECDPF6200018AOR.html","image":"http://cms-bucket.ws.126.net/2019/05/05/fcc2019fa9164e63aa60a0c032aa6267.png?imageView&thumbnail=140y88&quality=85","title":"如何抓住5G投资机会?巴菲特:子公司会涉足相关行业","passtime":"2019-05-05 10:00:33"},{"path":"https://news.163.com/19/0504/22/EEC6O6GK0001899N.html","image":"http://cms-bucket.ws.126.net/2019/05/04/92942e489e4d41ffb009f7a68ee08bf8.png?imageView&thumbnail=140y88&quality=85","title":"国家卫健委：应尽量避免学龄前儿童使用手机和电脑","passtime":"2019-05-05 10:00:33"},{"path":"https://news.163.com/19/0504/21/EEC6DGPS0001899N.html","image":"http://cms-bucket.ws.126.net/2019/05/04/1b3dfa760c924c7c9a9e81a12eac376a.png?imageView&thumbnail=140y88&quality=85","title":"第八师石河子市原党委书记案细节:跟兵团党委叫板","passtime":"2019-05-05 10:00:33"},{"path":"https://news.163.com/19/0504/21/EEC50MRM0001899N.html","image":"http://cms-bucket.ws.126.net/2019/05/04/63962b2e3c2a43c0ae77afcd4945abf9.png?imageView&thumbnail=140y88&quality=85","title":"德云社回应吴鹤臣众筹:私人行为 郭德纲将提供帮助","passtime":"2019-05-05 10:00:33"},{"path":"https://news.163.com/19/0504/21/EEC3IA270001899N.html","image":"http://cms-bucket.ws.126.net/2019/05/04/946fa0f172574330b253ae0cade43011.png?imageView&thumbnail=140y88&quality=85","title":"网红店排号7000多人 店家:特殊情况 店里500张桌子","passtime":"2019-05-05 10:00:33"},{"path":"https://news.163.com/19/0504/20/EEC2UBKJ0001899N.html","image":"http://cms-bucket.ws.126.net/2019/05/04/d0d65ba08f974eb6b51d5ea2b721be3f.png?imageView&thumbnail=140y88&quality=85","title":"巴菲特:中国潜能没完全发挥 对投资中国持开放态度","passtime":"2019-05-05 10:00:33"}]
     */

    private int code;
    private String message;
    private List<ResultBean> result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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
         * path : https://news.163.com/19/0505/06/EED4MPVP0001875P.html
         * image : http://cms-bucket.ws.126.net/2019/05/05/953323ac3f0c450c9782b6ec146f21b8.png?imageView&thumbnail=140y88&quality=85
         * title : 公交私车抢拽方向盘都可能构罪 加重情节不得缓刑
         * passtime : 2019-05-05 10:00:33
         */

        private String path;
        private String image;
        private String title;
        private String passtime;

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPasstime() {
            return passtime;
        }

        public void setPasstime(String passtime) {
            this.passtime = passtime;
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "path='" + path + '\'' +
                    ", image='" + image + '\'' +
                    ", title='" + title + '\'' +
                    ", passtime='" + passtime + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "News{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
    }
}
