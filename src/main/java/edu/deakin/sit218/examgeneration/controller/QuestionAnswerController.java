package edu.deakin.sit218.examgeneration.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.deakin.sit218.examgeneration.dao.QuestionDAO;
import edu.deakin.sit218.examgeneration.dao.QuestionDAOImpl;
import edu.deakin.sit218.examgeneration.entity.Question;


@Controller
public class QuestionAnswerController {
	
	@GetMapping("/question-answer")
	public String showForm(Model model) {
		
		Question question = new Question();
		
		model.addAttribute("question",question);
		return "question-answer";
	}
	
	@RequestMapping("/processForm")
	public String QuestionAnswer(@Valid @ModelAttribute("question") Question question, BindingResult validationErrors, Model model) {
		if(validationErrors.hasErrors())
			return "question-answer";
		
		QuestionDAO dao = new QuestionDAOImpl();
		
		if(!dao.existsQuestion(question)) {
			dao.insertQuestion(question);
		}
		
		question = dao.retrieveQuestion(question);
		model.addAttribute("message", "Previous question Submitted successfully Area: " + question.getArea());
		dao.updateQuestion(question);

		return "question-answer";
		
	}
}