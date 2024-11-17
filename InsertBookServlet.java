import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.math.BigDecimal;

@WebServlet("/insertBook")
public class InsertBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String publisher = request.getParameter("publisher");
        String edition = request.getParameter("edition");
        String price = request.getParameter("price");
        
        System.out.println("Add Book Servlet Called");
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Publisher: " + publisher);
        System.out.println("Edition: " + edition);
        System.out.println("Price: " + price);

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO BOOK (TITLE, AUTHOR, PUBLISHER, EDITION, PRICE) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, title);
            stmt.setString(2, author);
            stmt.setString(3, publisher);
            stmt.setString(4, edition);
            stmt.setBigDecimal(5, new BigDecimal(price));
            stmt.executeUpdate();

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("Book added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
