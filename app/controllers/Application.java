package controllers;

import play.data.*;
import play.mvc.*;

import models.*;
import dao.*;

import views.html.*;

public class Application extends Controller {
  
	static Form<User> signupForm = Form.form(User.class);
	static Form<User> loginForm = Form.form(User.class);
	
    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }
    
    public static Result signupEntry() {
    	String error = flash("error");
    	return ok(signup.render(error, signupForm));
    }
    
    public static Result signup() {
		Form<User> filledForm = signupForm.bindFromRequest();
		if(filledForm.hasErrors()) {
			return badRequest("Error while filling form");
		} 
		else {
			System.out.println("Received Username: " + filledForm.get().getUsername() + " and Password: " + filledForm.get().getPassword());
			
			UserDao userDao = new UserDaoMongo();
			if (userDao.findUser(filledForm.get().getUsername()) != null) {
				flash("error", "Username already exists, choose a different one!");
				return redirect(routes.Application.signup());
			}
			else {
				userDao.saveUser(filledForm.get());
				return redirect(routes.Application.index());
			}    		  
		}
    }
    
    public static Result loginEntry() {
    	String error = flash("error");
    	return ok(login.render(error, loginForm));
    }
    
    public static Result login() {
    	Form<User> filledForm = loginForm.bindFromRequest();
		if(filledForm.hasErrors()) {
			return badRequest("Error while filling form");
		} 
		else {
			System.out.println("Received Username: " + filledForm.get().getUsername() + " and Password: " + filledForm.get().getPassword());
			UserDao userDao = new UserDaoMongo();
			if (userDao.findUser(filledForm.get().getUsername()) != null) {
				if (userDao.findUser(filledForm.get().getUsername()).getPassword().equals(filledForm.get().getPassword())) {
					session("loggedin", filledForm.get().getUsername());
					return redirect(routes.Application.index());
				}
				else {
					flash("error", "Username and Password don't match!");
					return redirect(routes.Application.loginEntry());
				}
			}
			else {
				flash("error", "Username does not exist!");
				return redirect(routes.Application.loginEntry());
			}    		  
		}
    }
       
  
}
