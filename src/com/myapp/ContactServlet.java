package com.myapp;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class ContactServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Retrieving form data from the request
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String message = request.getParameter("message");

        // Null or empty check for form parameters
        if (name == null || name.trim().isEmpty() || email == null || email.trim().isEmpty() || message == null || message.trim().isEmpty()) {
            out.println("<h2 style='color:red;'>All fields are required! Please fill in all the fields.</h2>");
            return;
        }

        // Database operation
        try (Connection conn = DBUtil.connect()) {

            // Ensure DB connection is successful
            if (conn == null) {
                out.println("<h2 style='color:red;'>Database connection failed! Please try again later.</h2>");
                return;
            }

            // Insert the message data into the contact_form table
            String sql = "INSERT INTO contact_form (name, email, message) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, message);

            // Execute the statement
            int rows = stmt.executeUpdate();
            if (rows > 0) {
            	out.println("<script type='text/javascript'>");
            	out.println("alert('Thank you! Your message has been submitted successfully. We will get back to you soon.');");
            	out.println("</script>");

     
            } else {
            	out.println("<script type='text/javascript'>"
                        + "alert('Oops! Submission failed. Please try again later.');"
                        + "</script>");

            }


        } catch (SQLException e) {
            out.println("<h2 style='color:red;'>Database error: " + e.getMessage() + "</h2>");
            e.printStackTrace();  // For debugging purposes
        }
    }
}
