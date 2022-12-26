import javax.servlet.*;
import javax.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.io.BufferedReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.apache.commons.io.IOUtils;

import services.*;
import beans.*;

public class CommentsServlet extends HttpServlet implements Servlet {

    private final CommentService commentService;

    public CommentsServlet() {
        this.commentService = new CommentService();
    }

    public void init() throws ServletException {
        super.init();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");

        int referenceId = Integer.parseInt(request.getParameter("referenceId"));
        LinkedList<Comment> comments = this.commentService.getAllByReferenceId(referenceId);

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        String json = gson.toJson(comments);

        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        builder.setDateFormat("yyyy-MM-dd");
        Gson gson = builder.create();

        String jsonString = IOUtils.toString(request.getInputStream());
        System.out.println(jsonString);
        Comment commentCreateData = gson.fromJson(jsonString, Comment.class);
        System.out.println(commentCreateData.getText());

        Comment comment = this.commentService.create(commentCreateData);

        PrintWriter out = response.getWriter();
        out.print(gson.toJson(comment));
        out.flush();
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        this.commentService.delete(id);
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        String jsonString = IOUtils.toString(request.getInputStream());
        Comment commentUpdateData = gson.fromJson(jsonString, Comment.class);

        this.commentService.update(id, commentUpdateData);
    }
}