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

public class ReferencesServlet extends HttpServlet implements Servlet {

    private final ReferenceService referenceService;

    public ReferencesServlet() {
        this.referenceService = new ReferenceService();
    }

    public void init() throws ServletException {
        super.init();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");

        LinkedList<Reference> references = this.referenceService.getAll();

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        String json = gson.toJson(references);

        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");

        System.out.println("Dopost");

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        String jsonString = IOUtils.toString(request.getInputStream());
        Reference referenceCreateData = gson.fromJson(jsonString, Reference.class);

        Reference reference = this.referenceService.create(referenceCreateData);
        System.out.println(reference.getId());

        PrintWriter out = response.getWriter();
        out.print(gson.toJson(reference));
        out.flush();
    }
}