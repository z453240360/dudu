package com.dodo.marcket.bean;

import java.util.List;

/**
 * 作者：东东
 * 日期：2018/12/11 001114:17
 * 描述：
 */
public class KeFuBean {


    /**
     * list : [{"answer":"","answerDate":"2018-12-11 14:45:48","problem":"hhbhg","problemDate":"2018-12-11 14:45:48"}]
     * total : 1
     */

    private int total;
    private List<ListBean> list;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * answer :
         * answerDate : 2018-12-11 14:45:48
         * problem : hhbhg
         * problemDate : 2018-12-11 14:45:48
         */

        private String answer;
        private String answerDate;
        private String problem;
        private String problemDate;

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public String getAnswerDate() {
            return answerDate;
        }

        public void setAnswerDate(String answerDate) {
            this.answerDate = answerDate;
        }

        public String getProblem() {
            return problem;
        }

        public void setProblem(String problem) {
            this.problem = problem;
        }

        public String getProblemDate() {
            return problemDate;
        }

        public void setProblemDate(String problemDate) {
            this.problemDate = problemDate;
        }
    }
}
