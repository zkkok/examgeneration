package edu.deakin.sit218.examgeneration.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "questions")
public class Question {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "questionid")
	protected int id;
	
	@NotNull (message = "A question is required")
	@Column(name = "question")
	protected String question;
	
	@NotNull (message = "An answer is required")
	@Column(name = "answer")
	protected String answer;
	
	@NotNull (message = "An area must be specified")
	@Column (name = "area")
	protected String area;
	
	public Question() {
	}
	
	public Question(String question, String answer, String area) {
		setQuestion(question);
		setAnswer(answer);
		setArea(area);
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

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Override
	public String toString() {
		return "question = [question: "+getQuestion()+ ", answer: " + getAnswer()+ ", area: " +getArea()+ "]";
	}

}