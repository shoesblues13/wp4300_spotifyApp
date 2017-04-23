package boundary;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;
import logiclayer.ApolloLogicImpl;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TemplateProcessor processor;
	String templateName = "test.ftl";
	ApolloLogicImpl logicImpl = new ApolloLogicImpl();
	DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
	SimpleHash root = new SimpleHash(db.build());
	private String templateDir = "/WEB-INF/templates"; //directory for templates
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        processor = new TemplateProcessor(templateDir, getServletContext());
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String page = request.getParameter("page");//get page name
		root.put("name", page);
		root.put("check", "login");
		String signIn = request.getParameter("signIn");
		String signUp = request.getParameter("signUp");
			if (page.equals("index")){
				if (signIn != null)
					templateName="signIn.html";
				else if (signUp !=null)
					templateName="createAccount.html";
				
			}
			else if (page.equals("success")){
				templateName="signIn.html";
			}
			else if (page.equals("create")) {
				String fname = request.getParameter("fname");
				String lname = request.getParameter("lname");
				String email = request.getParameter("email");
				String uname = request.getParameter("uname");
				String pword = request.getParameter("pword");
				String pword2 = request.getParameter("pword2");
				if(!pword.equals(pword2)){
					templateName="test.ftl";
					root.put("check", "pwords arent equal");
				}
				else {
					int check = logicImpl.createUser(fname, lname, email, uname, pword);
					if (check != -1){
						templateName="signupSuccess.html";
					}
					else {
						
						templateName = "../../createAccount.html";
					}
					processor.processTemplate(templateName, root, request, response);
				}
			}
			else if (page.equals("login")){
				String uname = request.getParameter("uname");
				String pword = request.getParameter("pword");
				int check = logicImpl.signIn(uname, pword);
				String name = logicImpl.getName(uname);
				if (check != 0){
					HttpSession session=request.getSession();
					session.setAttribute("name", name);
					session.setAttribute("user", check);
					session.setAttribute("template", "userHome.html");
					
				}
				else{
					templateName="signIn.html";
				}
				
			}
			processor.processTemplate(templateName, root, request, response);
		}
	}

