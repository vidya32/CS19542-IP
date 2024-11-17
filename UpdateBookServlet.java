import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.math.BigDecimal;
import java.sql.*;

@WebServlet("/updateBook")
public class UpdateBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int accno = Integer.parseInt(request.getParameter("accno"));
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String publisher = request.getParameter("publisher");
        String edition = request.getParameter("edition");
        String priceStr = request.getParameter("price");
        
        BigDecimal price = null;
        if (priceStr != null && !priceStr.isEmpty()) {
            price = new BigDecimal(priceStr);
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (price == null || price.compareTo(BigDecimal.ZERO) < 0) {
            out.println("<p>Error: Price must be a non-negative number.</p>");
            return;
        }

        try (Connection conn = DBConnection.getConnection()) {
            String checkSql = "SELECT COUNT(*) FROM BOOK WHERE ACCNO = ?";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
                checkStmt.setInt(1, accno);
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next() && rs.getInt(1) == 0) {
                    out.println("<p>Error: Book with ACCNO " + accno + " does not exist.</p>");
                    return;
                }
            }

            String sql = "UPDATE BOOK SET TITLE = ?, AUTHOR = ?, PUBLISHER = ?, EDITION = ?, PRICE = ? WHERE ACCNO = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, title);
                stmt.setString(2, author);
                stmt.setString(3, publisher);
                stmt.setString(4, edition);
                stmt.setBigDecimal(5, price);
                stmt.setInt(6, accno);

                int rowsUpdated = stmt.executeUpdate();
                if (rowsUpdated > 0) {
                    out.println("<p>Book updated successfully!</p>");
                } else {
                    out.println("<p>Error: Book with ACCNO " + accno + " not found.</p>");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("<p>Error updating the book: " + e.getMessage() + "</p>");
        }
    }
}
