package edu.deakin.sit218.examgeneration.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.deakin.sit218.examgeneration.dao.QuestionDAO;
import edu.deakin.sit218.examgeneration.dao.QuestionDAOImpl;
import edu.deakin.sit218.examgeneration.entity.Question;

@Controller
public class ExamGenController {
	@GetMapping("/examgen")
	public String showHome() {
		return "examgen";
	}
	
	@RequestMapping("/generatequestions")
	public String generatequestions(@RequestParam("areaofexam") String area, Model model) {
		try {
			String jdbcURL = "jdbc:mysql://localhost/questionanswerschema?user=lecturerdbadmin&password=lecturerdbadmin";
			Connection conn = DriverManager.getConnection(jdbcURL);
			String query = "Select * FROM questions WHERE area = ?";	
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, area);

			ResultSet result = statement.executeQuery();
			
		
			List<String> questions = new ArrayList<String>();
			while (result.next()) {
				String question = result.getString("question");
				questions.add(question);
			}
			
			List<String> selectedquestions = new ArrayList<String>();
			for(int i = 1; i < 6; i++){
				Random randomNumber = new Random();
				int j = randomNumber.nextInt(questions.size());
				selectedquestions.add("<br>Q" + i + ": " + questions.get(j));
			}
			
			String finalresult = selectedquestions.toString().replace(",", "")
										.replace("[", "")
										.replace("]", "");
			
			
					model.addAttribute("message", finalresult);		
			return "examgen";
			
		}catch(Exception e) {
			model.addAttribute("message",e.getMessage());
			return "examgen";
		}
		

		

		
	}
}