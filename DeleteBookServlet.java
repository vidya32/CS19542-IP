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


@WebServlet("/deleteBook")
public class DeleteBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the ACCNO parameter from the form
        int accno = Integer.parseInt(request.getParameter("accno"));

        response.setContentType("text/html");
        try (Connection conn = DBConnection.getConnection()) {
            // SQL Delete Query
            String sql = "DELETE FROM BOOK WHERE ACCNO = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, accno);

            int rowsDeleted = stmt.executeUpdate();

            PrintWriter out = response.getWriter();
            if (rowsDeleted > 0) {
                out.println("<p>Book deleted successfully!</p>");
            } else {
                out.println("<p>Error: Book with ACCNO " + accno + " not found.</p>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("<p>Error deleting the book.</p>");
        }
    }
}
