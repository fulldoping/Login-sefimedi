package cotroller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Member;
import service.face.MemberService;
import service.impl.MemberServiceImpl;

@WebServlet	("/member/login")
public class MemberLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//MemberService 객체 생성
	private MemberService memberService = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/member/login [GET] 로그인 페이지");
		
		//VIEW 지정
		req.getRequestDispatcher("/WEB-INF/views/member/login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Member member = memberService.getLoginMember(req);
		
		boolean login = memberService.login(member);
		
		if(login) {
			//로그인 사용자의 정보 얻어오기
			member = memberService.info(member);
			
			HttpSession session = req.getSession();
			session.setAttribute("login", login);
			session.setAttribute("userId", member.getUserId());
			session.setAttribute("userNick", member.getUserNick());
		}
	
		resp.sendRedirect("/main");
	}
}
