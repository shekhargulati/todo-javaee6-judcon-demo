package com.shekhar.todo.servlets;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shekhar.todo.domain.User;
import com.shekhar.todo.domain.UserBuilder;
import com.shekhar.todo.ejbs.UserDao;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Inject
	UserDao userDao;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		User user = userDao.findUser(Long.valueOf(userId));
		
		response.getWriter().print("Found User : "+user);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = UserBuilder.user().withEmail("test_user@testemail.com")
				.withPassword("password")
				.withAddedHobby("running")
				.withAddedHobby("swimming")
				.withAddedHobby("coding")
				.build();
		userDao.createUser(user);
		
		response.getWriter().print("User created : "+user);
	}

}
