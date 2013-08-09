package controllers;

import java.io.File;
import java.util.List;

import play.data.*;
import play.mvc.*;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import processor.*;

import models.*;

import views.html.*;

public class Application extends Controller {

	static Form<User> signupForm = Form.form(User.class);
	static Form<User> loginForm = Form.form(User.class);
	static Form<Query> tripguruForm = Form.form(Query.class);
	static Form<Editorial> editorialForm = Form.form(Editorial.class);
	static Form<Trip> tripForm = Form.form(Trip.class);
	
	public static Result index() {
		String user = session("user");
		return ok(index.render(user));
	}

	public static Result signupEntry() {
		String error = flash("error");
		String user = session("user");
		return ok(signup.render(error, user, signupForm));
	}

	public static Result signup() {

		Form<User> filledForm = signupForm.bindFromRequest();

		if (filledForm.hasErrors()) {
			return badRequest("Error while filling form");
		} else {
			String signupResult = SignupManager.signup(filledForm.get());
			if (signupResult.equals("user exists")) {
				flash("error",
						"Username already exists, choose a different one!");
				return redirect(routes.Application.signup());
			} else {
				session("user", filledForm.get().getUsername());
				return redirect(routes.Application.index());
			}
		}

	}

	public static Result loginEntry() {
		String error = flash("error");
		String user = session("user");
		return ok(login.render(error, user, loginForm));
	}

	public static Result login() {
		Form<User> filledForm = loginForm.bindFromRequest();
		if (filledForm.hasErrors()) {
			return badRequest("Error while filling form");
		} else {
			String loginResult = LoginManager.login(filledForm.get());

			if (loginResult.equals("logged in")) {
				session("user", filledForm.get().getUsername());
				return redirect(routes.Application.index());
			} else if (loginResult.equals("password invalid")) {
				flash("error", "Username and Password don't match!");
				return redirect(routes.Application.loginEntry());
			} else {
				flash("error", "Username does not exist!");
				return redirect(routes.Application.loginEntry());
			}
		}
	}

	public static Result logout() {
		session().clear();
		return redirect(routes.Application.index());
	}

	public static Result tripGuruEntry() {
		String user = session("user");
		String error = flash("error");
		String msg = flash("msg");
		return ok(tripguru.render(error, msg, user, tripguruForm));
	}

	public static Result tripGuru() {
		Form<Query> filledForm = tripguruForm.bindFromRequest();
		String result = null;
		if (filledForm.hasErrors())
			return badRequest("Error while filling form");
		else {

			if (session("user") != null)
				filledForm.get().setUsername(session("user"));
			result = QueryManager.saveQuery(filledForm.get());

			if (result.equals("success"))
				flash("msg", "Your query has been successfully posted");
			else
				flash("error",
						"Looks like the form was not properly filled, try again!");

			return redirect(routes.Application.tripGuruEntry());
		}
	}

	public static Result oldQA() {
		String user = session("user");
		return ok(oldqa.render(user));
	}

	public static Result editorial() {
		String user = session("user");
		List<Editorial> editorials = EditorialManager.getAllEditorials();
		return ok(editorial.render(user, editorials));
	}

	public static Result editorialEntry() {
		String user = session("user");
		String error = flash("error");
		String msg = flash("msg");
		return ok(editorialEntry.render(error, msg, user, editorialForm));
	}

	public static Result submitEditorial() {
		Form<Editorial> filledForm = editorialForm.bindFromRequest();
		filledForm.get().setAuthor(session("user"));
		String result = EditorialManager.saveEditorial(filledForm.get());
		if ((result != null) && (result.equals("saved")))
			flash("msg", "Your editorial has been successfully saved");
		else
			flash("error", "Something went wrong! Please try again");

		return redirect(routes.Application.editorialEntry());
	}

	public static Result myAccount() {
		String username = session("user");
		String error = flash("error");
		String msg = flash("msg");
		User user = MyAccountManager.getUser(username);
		return ok(myAccount.render(error, msg, user));
	}

	public static Result uploadProfilePic() {
		String username = session("user");
		MultipartFormData body = request().body().asMultipartFormData();
		FilePart picture = body.getFile("picture");
		if (picture != null) {
			File file = picture.getFile();
			if(MyAccountManager.saveProfilePic(username, file))
				flash("msg", "Updated profile picture.");
			else
				flash("error", "Profile picture upload failed.");
		} else {
			flash("error", "Profile picture upload failed.");
		}
		return redirect(routes.Application.myAccount());
	}
	
	public static Result tripEntry() {
		String username = session("user");
		String error = flash("error");
		String jsonPlaces = TripManager.getPlacesAsJson();
		return ok(tripEntry.render(error, username, jsonPlaces, tripForm));
	}
	
	public static Result trip() {
		//TODO
		return TODO;
	}
	
	public static Result contactUs() {
		String username = session("user");
		return ok(contactUs.render(username));
	}

}
