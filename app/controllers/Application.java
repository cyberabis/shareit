package controllers;

import play.data.*;
import play.mvc.*;
import processor.LoginManager;
import processor.SignupManager;

import models.*;

import views.html.*;

public class Application extends Controller {
  
	static Form<User> signupForm = Form.form(User.class);
	static Form<User> loginForm = Form.form(User.class);
	
    public static Result index() {
    	String user = session("connected");
        return ok(index.render(user));
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
			String signupResult = SignupManager.signup(filledForm.get());
			if (signupResult.equals("user exists")) {
				flash("error", "Username already exists, choose a different one!");
				return redirect(routes.Application.signup());
			}
			else
				return redirect(routes.Application.index()); 		  
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
			String loginResult = LoginManager.login(filledForm.get());
			
			if (loginResult.equals("logged in")) {
				session("loggedin", filledForm.get().getUsername());
				return redirect(routes.Application.index());
			}
			else if (loginResult.equals("password invalid")) {
				flash("error", "Username and Password don't match!");
				return redirect(routes.Application.loginEntry());
			}
			else {
				flash("error", "Username does not exist!");
				return redirect(routes.Application.loginEntry());
			}
		}
    }   
  
}
