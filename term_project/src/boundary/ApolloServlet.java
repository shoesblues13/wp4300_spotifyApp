package boundary;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boundary.TemplateProcessor;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;
import logiclayer.ApolloLogicImpl;

/**
 * Servlet implementation class ApolloServlet
 */
@WebServlet("/ApolloServlet")
public class ApolloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private TemplateProcessor processor;
	//Global variables used in the application:
	private String templateDir = "/WEB-INF/templates"; //directory for templates
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApolloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(ServletConfig config) throws ServletException {
		super.init(config);
		processor = new TemplateProcessor(templateDir, getServletContext());
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
		// TODO Auto-generated method stub
				String page = request.getParameter("page");//get page name
				String templateName = "index.html";
				ApolloLogicImpl logicImpl = new ApolloLogicImpl();
				DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
				SimpleHash root = new SimpleHash(db.build());
				root.put("name", page);
			
					if (page.equals("create")) {
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
						}
					}
					else if (page.equals("login")){
						String uname = request.getParameter("uname");
						String pword = request.getParameter("pword");
						int check = logicImpl.signIn(uname, pword);
						String name = logicImpl.getName(uname);
						if (check != 0){
							root.put("name", name);
							root.put("user", check);
							templateName="userHome.html";
						}
						else{
							
							templateName="signIn.html";
						}
						
						
					}
					else if(page.equals("userHome")){
						String button1 = request.getParameter("button1");
						String button2 = request.getParameter("button2");
						String button3 = request.getParameter("button3");
						if (button1 != null){
							root.put("check", "it works");
							templateName="test.ftl";
						}
						else if (button2 != null){
							templateName="newParty.html";
							
						}
						else if (button3 != null){
							templateName="viewParty.html";
						}
						
					}
					else if (page.equals("validate")){
						String name= request.getParameter("name");
						String input = request.getParameter("input");
						if (name.equals("email")){
							
						}
						else if (name.equals("uname")){
							
						}
						else {
							
						}
						
					}
					else {
						root.put("check", "NOOOOOOOOOOO");
					}
					processor.processTemplate(templateName, root, request, response);
				}
	}


