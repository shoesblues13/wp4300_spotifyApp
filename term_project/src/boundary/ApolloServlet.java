package boundary;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import boundary.TemplateProcessor;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;
import freemarker.template.SimpleSequence;
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
				String templateName = "test.ftl";
				String logout = request.getParameter("logout");
				String signIn = request.getParameter("signIn");
				String signUp = request.getParameter("signUp");
				ApolloLogicImpl logicImpl = new ApolloLogicImpl();
				HttpSession session = request.getSession(false);
				String servletName = "apollo";
				DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
				SimpleHash root = new SimpleHash(db.build());
				root.put("name", page);
				if(logout !=null){
					session.invalidate();
					request.getRequestDispatcher("index.html").include(request, response);
					
				}
				else if (page.equals("index")){
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
						
					}
				}
				else if (page.equals("login")){
					String uname = request.getParameter("uname");
					String pword = request.getParameter("pword");
					int check = logicImpl.signIn(uname, pword);
					String name = logicImpl.getName(uname);
					if (check != 0){
						session=request.getSession();
						synchronized(session){
						session.setAttribute("name", name);
						session.setAttribute("user", check);
						session.setAttribute("template", "userHome.html");
						root.put("test",session.getAttribute("user"));
						}
						SimpleSequence partiesSeq = logicImpl.getParties(uname, db);
						SimpleSequence userSeq = logicImpl.getUserInvited(uname, db);
						
						root.put("name", name);
						root.put("user", check);
						templateName="userHome.html";
						
						
					}
					else{
						templateName="signIn.html";
					}
					
					
				}
				else {
					if(page.equals("userHome")){
						session = request.getSession();
						if (session !=null){
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
						else {
							templateName="signIn.html";
						}
						
					}
					else if (page.equals("newParty")){
						String name = request.getParameter("pname");
						String desc = request.getParameter("pdesc");
						String address = request.getParameter("address");
						String timeStart = request.getParameter("timeStart");
						String timeEnd = request.getParameter("timeEnd");
						String radio = request.getParameter("partyType");
						session = request.getSession();
						int hostId;
						synchronized(session){
							hostId = (int) session.getAttribute("user");
						}
						Boolean pub = true;
						if (!radio.equals("public"))
							pub = false;
						int party_id = logicImpl.createParty(name,timeStart, timeEnd, desc, address, pub,hostId);
						if (party_id > 0){
							synchronized(session){
								session.setAttribute("party_id", party_id);
							}
							root.put("partyName", name);
							root.put("partyDesc", desc);
							root.put("address", address);
							root.put("timeStart", timeStart);
							root.put("timeEnd", timeEnd);
							templateName="viewParty.html";
						}
						else {
							templateName="test.ftl";
							root.put("check", desc);
							
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
						
						root.put("check", page);
					}
				}
				processor.processTemplate(templateName, root, request, response);
			}	
	}


