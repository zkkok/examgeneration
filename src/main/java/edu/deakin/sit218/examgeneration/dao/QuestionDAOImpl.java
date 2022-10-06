package edu.deakin.sit218.examgeneration.dao;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import edu.deakin.sit218.examgeneration.entity.Question;

public class QuestionDAOImpl implements QuestionDAO{

	private SessionFactory factory;
	
	public QuestionDAOImpl() {
		factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Question.class)
				.buildSessionFactory();
	}
	
	
	@Override
	public void insertQuestion(Question question) {
		Session session = factory.getCurrentSession();
		try {		
			session.beginTransaction();
		
			session.save(question);
		
			session.getTransaction().commit();
		}finally {
			session.close();
		}
	}
	
	@Override
	public boolean existsQuestion(Question question) {
		Session session = factory.getCurrentSession();
		try {		
			session.beginTransaction();
		
			String hql = "from Question where question = '" + question.getQuestion() + "' and answer = '" + question.getAnswer()+ "'and area = '" + question.getArea() + "'";
			List<Question> questions = session.createQuery(hql).getResultList();
			
			return !questions.isEmpty();
		}finally {
			session.close();
		}
	}
	
	
	@Override
	public Question retrieveQuestion(Question question) {
		Session session = factory.getCurrentSession();
		try {		
			session.beginTransaction();
		
			String hql = "from Question where question = '" + question.getQuestion() + "' and answer = '" + question.getAnswer()+ "'and area = '" + question.getArea() + "'";
			List<Question> questions = session.createQuery(hql).getResultList();
			
			if(questions.isEmpty()) {
				throw new RuntimeException("There is no client: " + question.toString());
			}
			else if (questions.size() > 1) {
				throw new RuntimeException("More than one client exists: " + question.toString());				
			}
			else {
				return questions.get(0);
			}
		}finally {
			session.close();
		}
	}


	@Override
	public void updateQuestion(Question question) {
		Session session = factory.getCurrentSession();
		try {		
			session.beginTransaction();
		
			session.update(question);
		
			session.getTransaction().commit();
		}finally {
			session.close();
		}
	}
	
	@Override
	protected void finalize() throws Throwable {
		factory.close();
	}
}