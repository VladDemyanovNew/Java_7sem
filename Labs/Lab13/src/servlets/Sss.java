import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Sss extends HttpServlet implements Servlet {

    public void init() throws ServletException {
        System.out.println("Servlet:Sss");
        super.init();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Servlet:Sss:doGet");

        String fileName = request.getParameter("fileName");
        String fileStoragePath = getServletContext().getInitParameter("file-storage-path");
        String filePath = fileStoragePath + "\\" + fileName;
        File file = new File(filePath);

        System.out.println(filePath);

        this.outputFile(file, response);
    }

    protected void outputFile(File file, HttpServletResponse response)
            throws IOException {
        response.setContentType("application/msword");
        response.addHeader("Content-Disposition", "attachment; filename=" + file.getName());
        response.setContentLength((int)file.length());

        FileInputStream in = new FileInputStream(file);
        BufferedInputStream buffer = new BufferedInputStream(in);
        ServletOutputStream out = response.getOutputStream();

        int readBytes = 0;
        while ((readBytes = buffer.read()) != -1) {
            out.write(readBytes);
        }
    }
}