package practice;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/practice/Gugudan.do")
public class Gugudan extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {

		resp.setContentType("text/html;charset=utf-8");
        int col = Integer.parseInt(req.getParameter("col"));
        String result = "";

		for (int i=2; i<=9; i+=col) {
			for(int j=1; j<=9; j++) {
				result += "<tr>";
				for(int k=0; k<col; k++) {
					if((i+k)>9) 
						break;
					result += "<td>" + String.valueOf(i+k) + " * " + String.valueOf(j) + " = " + String.valueOf((i+k)*j) + "</td>";   

				}
				result += "</tr>";
			}
		}
					
        PrintWriter out = resp.getWriter();
        out.append("<!DOCTYPE HTML>");
        out.append("<html>");
        out.append("<head><link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65\" crossorigin=\"anonymous\">");
        out.append("</head>");
        out.append("<body>");
        out.append("<h3>구구단 출력</h3>");
        out.append("<table class=\"table table-striped-columns\" border='1' style='width:600px; text-align:center;'>");
        out.append(result);
        out.append("</table>");
        out.append("</body>");
        out.append("</html>");

	}
	
}
