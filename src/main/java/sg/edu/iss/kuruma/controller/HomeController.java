package sg.edu.iss.kuruma.controller;

import javax.servlet.http.HttpSession;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("username")
public class HomeController {
	    	
	@RequestMapping("/home") 
	public String home(Model model, @Param("entry") String entry, HttpSession session) {

		model.addAttribute("entry", entry); 
		session.setAttribute("username", "Guest");
		return "home"; 
		 }
	
	@RequestMapping("/contactus") 
	public String contactus(Model model, HttpSession session) {
		return "contactus"; 
		 }
	

	
	@RequestMapping("/aboutus") 
	public String aboutus(Model model, HttpSession session) {
		return "aboutus"; 
		 }
	
	@RequestMapping("/") 
	public String home1(Model model, HttpSession session) {
		return "home"; }
	
	@RequestMapping("/loan") 
	public String loan(Model model, HttpSession session) {
		return "loan"; 
		 }
	
}