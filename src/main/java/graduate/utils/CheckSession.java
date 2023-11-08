package graduate.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CheckSession {
//	Dùng để kiểm tra xem username đã được khởi tạo trong session hay chưa, chưa thì khởi tạo và gán giá trị bằng null
	public void checkUsername(HttpServletRequest request) {
		HttpSession session=request.getSession();
		String username = (String) session.getAttribute("username");
		if (username == null) {
		    username = null; // Gán giá trị null nếu chưa được khởi tạo
		    session.setAttribute("username", username);
		}

	}
//	Dùng để kiểm tra xem role đã được khởi tạo trong session hay chưa, chưa thì khởi tạo và gán giá trị bằng null
	public void checkRole(HttpServletRequest request) {
		HttpSession session=request.getSession();
		String role = (String) session.getAttribute("role");
		if (role == null) {
		    role = "guest"; // Gán giá trị null nếu chưa được khởi tạo
		    session.setAttribute("role", role);
		}

	}
	
}
