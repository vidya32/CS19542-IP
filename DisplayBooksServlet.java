import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.math.BigDecimal;


@WebServlet("/displayBooks")
public class DisplayBooksServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("Display Books Servlet Called");
    	try (Connection conn = DBConnection.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM BOOK");

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            while (rs.next()) {
                out.println("<p>ACCNO: " + rs.getInt("ACCNO") +
                            ", TITLE: " + rs.getString("TITLE") +
                            ", AUTHOR: " + rs.getString("AUTHOR") +
                            ", PUBLISHER: " + rs.getString("PUBLISHER") +
                            ", EDITION: " + rs.getString("EDITION") +
                            ", PRICE: " + rs.getBigDecimal("PRICE") + "</p>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
