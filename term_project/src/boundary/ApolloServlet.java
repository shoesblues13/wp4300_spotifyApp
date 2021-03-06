package boundary;

import java.io.IOException;

import java.io.PrintWriter;

import java.util.List;


import java.lang.reflect.Type;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import boundary.TemplateProcessor;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;
import freemarker.template.SimpleSequence;
import freemarker.template.TemplateModelException;
import logiclayer.ApolloLogicImpl;
import objectlayer.BringList;
import objectlayer.Party;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

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
				String bringitem = request.getParameter("bringitem");
				String musicEdit = request.getParameter("musicEdit");
				String guestListEdit = request.getParameter("guestListEdit");
				
				String templateName = "test.ftl";
				String home = request.getParameter("home");
				String logout = request.getParameter("logout");
				String signIn = request.getParameter("signIn");
				String signUp = request.getParameter("signUp");
				String trending = request.getParameter("trending");
				ApolloLogicImpl logicImpl = new ApolloLogicImpl();
				HttpSession session = request.getSession(false);
				DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
				SimpleHash root = new SimpleHash(db.build());
				root.put("name", page);
				root.put("again", "");
				
				
				if(logout !=null && session !=null){
					session.invalidate();
					processor.processTemplate("index.html", root, request, response);
					
				}
				else if (home != null && session != null){
					session = request.getSession(false);
					String name;
					int user;
					synchronized(session){
						name = (String) session.getAttribute("name");
						user = (int) session.getAttribute("user");
						SimpleSequence partiesSeq = logicImpl.getParties(user, db);
						SimpleSequence userSeq = logicImpl.getUserInvited(user, db);
						root.put("partiesSeq", partiesSeq);
						root.put("userSeq", userSeq);
						root.put("name", name);
						templateName="userHome.html";
						
					}
					
				}
				else {
					if (trending != null){
						getFiveTrending(response);
					}
					else if(bringitem != null & session !=null){
						addItem(request,bringitem);
					}
					else if(musicEdit != null && session != null){
						addMusic(request,musicEdit);
					}
					else if(guestListEdit != null && session !=null){
						addGuest(request,guestListEdit, response);
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
						String signin = request.getParameter("signin");
						if (signin == null){
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
						else {
							templateName="signIn.html";
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
							}
							SimpleSequence partiesSeq = logicImpl.getParties(check, db);
							SimpleSequence userSeq = logicImpl.getUserInvited(check, db);
							SimpleSequence partyIdSeq = logicImpl.getPartyIds(check, db);
							root.put("partiesSeq", partiesSeq);
							root.put("partyIdSeq", partyIdSeq);
							root.put("userSeq", userSeq);
							root.put("name", name);
							root.put("user", check);
							templateName="userHome.html";
						}
						else{
							templateName="signIn.html";
						}
					}
					else if(page.equals("userHome") && session != null){
							session = request.getSession(false);
							
								String button2 = request.getParameter("button2");
								String button3 = request.getParameter("button3");
								String button4 = request.getParameter("button4");
								
							if (button2 != null){
								templateName="newParty.html";
							}
							else if (button3 != null){
								SimpleSequence publicParties = logicImpl.getPublicPartys(db);
								root.put("publicParties", publicParties);
								templateName="viewPublicParties.html";
							}
							else if (button4!=null){
								String party = request.getParameter("party");
								int user_id;
								synchronized(session){
									user_id = (int) session.getAttribute("user");
								}
								Party p = logicImpl.getParty(party, user_id);
								int party_id= p.getPartyId();
								synchronized(session){
									 session.setAttribute("party_id",party_id);
								}
								SimpleSequence sb = logicImpl.getGuestList(party_id, db);
								SimpleSequence musicSeq = logicImpl.getMusicList(party_id, db);
								SimpleSequence bringSeq = logicImpl.getBringList(party_id, db);
								
								
								root.put("guestList", sb);
								root.put("musicSeq", musicSeq);
								root.put("bringSeq", bringSeq);
								root.put("partyName", p.getName());
								root.put("partyDesc", p.getDescription());
								root.put("address", p.getLocation());
								root.put("timeStart", p.getStime());
								root.put("timeEnd", p.getEtime());
								root.put("owner", "yes");
								templateName="viewParty.html";
							}
						
					}
					else if (page.equals("newParty") && session !=null){
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
							SimpleSequence sb = logicImpl.getGuestList(party_id, db);
							SimpleSequence musicSeq = logicImpl.getMusicList(party_id, db);
							SimpleSequence bringSeq = logicImpl.getBringList(party_id, db);
							root.put("guestList", sb);
							root.put("musicSeq", musicSeq);
							root.put("bringSeq", bringSeq);
							Party p = logicImpl.getParty(party_id);
							root.put("partyName", p.getName());
							root.put("partyDesc", p.getDescription());
							root.put("address", p.getLocation());
							root.put("timeStart", p.getStime());
							root.put("timeEnd", p.getEtime());
							root.put("owner", "yes");
							templateName="viewParty.html";
						}
						else {
							templateName="test.ftl";
							root.put("check", desc);
							
						}
					}
					else if(page.equals("viewPublicParties")){
						String party = request.getParameter("party");
						int user_id;
						synchronized(session){
							user_id = (int) session.getAttribute("user");
						}
						Party p = logicImpl.getParty(party, user_id);
						int party_id= p.getPartyId();
						synchronized(session){
							 session.setAttribute("party_id",party_id);
						}
						SimpleSequence sb = logicImpl.getGuestList(party_id, db);
						SimpleSequence musicSeq = logicImpl.getMusicList(party_id, db);
						SimpleSequence bringSeq = logicImpl.getBringList(party_id, db);
						
						
						root.put("guestList", sb);
						root.put("musicSeq", musicSeq);
						root.put("bringSeq", bringSeq);
						root.put("partyName", p.getName());
						root.put("partyDesc", p.getDescription());
						root.put("address", p.getLocation());
						root.put("timeStart", p.getStime());
						root.put("timeEnd", p.getEtime());
						templateName="viewParty.html";
						root.put("owner", "no");
					}
					/*else if (page.equals("viewParty") && session !=null){
						int party_id;
						String addBringList = request.getParameter("addItem");
						String addMusicList = request.getParameter("addSong");
						String bringListInput = request.getParameter("newItem");
						String musicListInput = request.getParameter("newSong");
						session = request.getSession(false);
						int hostId;
						synchronized(session){
							hostId = (int) session.getAttribute("user");
							party_id = (int) session.getAttribute("party_id");
						}
						
						if(addBringList!=null){
							logicImpl.addBringList(bringListInput, party_id);
						}
						if(addMusicList!=null){
							logicImpl.addMusicList(musicListInput, party_id);
						}
						
						
						
						SimpleSequence sb = logicImpl.getGuestList(party_id, db);
						SimpleSequence musicSeq = logicImpl.getMusicList(party_id, db);
						SimpleSequence bringSeq = logicImpl.getBringList(party_id, db);
						
						
						root.put("guestList", sb);
						root.put("musicSeq", musicSeq);
						root.put("bringSeq", bringSeq);
						Party p = logicImpl.getParty(party_id);
						root.put("partyName", p.getName());
						root.put("partyDesc", p.getDescription());
						root.put("address", p.getLocation());
						root.put("timeStart", p.getStime());
						root.put("timeEnd", p.getEtime());
						templateName="viewParty.html";
					}*/
					else if (session == null){
						root.put("again", "Session has expired. Please Sign in again!");
						templateName="signIn.html";
					}
					else {	
						root.put("check", page);
					}
				
				}
				processor.processTemplate(templateName, root, request, response);
				
			}	
	
			private void getFiveTrending(HttpServletResponse response){
				ApolloLogicImpl logicImpl = new ApolloLogicImpl();
				List<Party> partys = logicImpl.getTrending();
				response.setContentType("application/json");
				Gson gson = new Gson();
				Type type = new TypeToken<List<Party>>(){}.getType();
				String json = gson.toJson(partys,type);
				try {
					PrintWriter out = response.getWriter();
					out.println("this is a test");
				}catch (IOException e){
					e.printStackTrace();
				}
				
			}
			
			public void addItem(HttpServletRequest request, String item) {
				ApolloLogicImpl logicImpl = new ApolloLogicImpl();
				String bringListInput = item;
				HttpSession session = request.getSession(false);
				int party_id;
				synchronized(session){
					party_id = (int) session.getAttribute("party_id");
				}
				logicImpl.addBringList(bringListInput, party_id);
								
			}
			
			public void addMusic(HttpServletRequest request,  String item){
				ApolloLogicImpl logicImpl = new ApolloLogicImpl();
				String musicListInput = item;
				HttpSession session = request.getSession(false);
				int party_id;
				synchronized(session){
					party_id = (int) session.getAttribute("party_id");
				}
				logicImpl.addMusicList(musicListInput, party_id);
			}
			public void addGuest(HttpServletRequest request,  String item, HttpServletResponse response){
				ApolloLogicImpl logicImpl = new ApolloLogicImpl();
				String guestListInput = item;
				HttpSession session = request.getSession(false);
				int party_id;
				synchronized(session){
					party_id = (int) session.getAttribute("party_id");
				}
				int test =logicImpl.addGuestList(guestListInput, party_id);
				Gson gson = new Gson();
				String json;
				if (test == -1){
					json = gson.toJson("false");
				}
				else {
					json=gson.toJson("true");
				}
				
				
				try {
					PrintWriter out = response.getWriter();
					out.println(json);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	}

	


