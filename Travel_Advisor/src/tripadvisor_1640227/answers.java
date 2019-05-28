/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tripadvisor_1640227;

/**
 *
 * @author reddyjyoshnagurrala
 */
public class answers {
    public String question_id;
    public String q_user_id;
    public String question;
    public String answer;
    public String attraction;
    public answers(String question_id, String q_user_id, String attraction,String question, String answer)
    {
        this.question_id=question_id;
        this.q_user_id=q_user_id;
        this.attraction=attraction;
        this.question=question;
        this.answer=answer;
    }

    public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }

    public String getQ_user_id() {
        return q_user_id;
    }

    public void setQ_user_id(String q_user_id) {
        this.q_user_id = q_user_id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAttraction() {
        return attraction;
    }

    public void setAttraction(String attraction) {
        this.attraction = attraction;
    }
    
}
