// Package usage declaration
package ie.gmit.sw;

//Library imports
import java.io.*;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;

import javax.servlet.*;
import javax.servlet.http.*;

// Declaration of Service Poll Handler class
public class ServicePollHandler extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private BlockingQueue<List<Results>> outQ = new ArrayBlockingQueue<List<Results>>(60);
	private List<Results> results = new ArrayList<Results>();
	
	public void init() throws ServletException {
		ServletContext ctx = getServletContext();
	}// End of init function

	// checkQueue for the job u requested by searching head of queue for id
	private List checkExistingQ(String title) throws InterruptedException  {
		List<Results> temp = new ArrayList<Results>();
		boolean found = false;
		
		// Iterate through the service queue to look for specific queue
		outQ = QueueService.getOutQ();
		for(int i = 0;i < outQ.size(); i++){
			temp = outQ.peek();
			System.out.println("Found... "+ temp.get(i).getNewDoc());
			System.out.println("The following: "+ title);
			String tString = temp.get(i).getNewDoc();
			if(temp != null && tString.equals(title)){
				found = true;
				//poll the queue every 10 second
				temp = outQ.poll();
			}// End of if
		}// End of for
		if(found == true){
			System.out.println("I exist!");
			return temp;
		}// End of if
		else{
			return temp = null;	
		}// End of else
	}// End of checkExistingQ function
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html"); 
		PrintWriter out = resp.getWriter(); 
		
		String title = req.getParameter("txtTitle");
		String taskNumber = req.getParameter("frmTaskNumber");
		int counter = 1;
		if (req.getParameter("counter") != null){
			counter = Integer.parseInt(req.getParameter("counter"));
			counter++;
		}// End of if
		
		// Try to check existing queue
		try {
			results = checkExistingQ(title);
		}// End of try
		// Catch exceptions
		catch(InterruptedException e) {
			e.printStackTrace();
		}// End of catch
		
		if(results != null) {
			System.out.println("Results are not empty...");
			out.printf("<html><head><title>Document Similarity Application</title>");
		    out.print("<div class='centered'><table>");
		    out.printf("<h1 align=\"center\"><b>%s</b></h1>", title);
		    out.print("<tr><th>Document title</th><th>Similarity</th></tr>");
		    // Create table for all the documents
		    for (int i =0; i<results.size(); i++) {
				out.print("<tr><td>");
				out.print(results.get(i).getNewDoc());
				out.print("</td><td>");
				out.print(results.get(i).getOldDoc());
				out.print("</td><td>");
				out.printf("%.0f %%", Double.valueOf(results.get(i).getResult())*60);
				out.print("</td></tr>");
		    }// End of for
		    
		    out.println();
		    out.print("</table></div>");
		    // Home button
		    out.printf("<p align=\"center\">"
			    + "<button onclick=\"window.location.href='index.jsp'\">Home</button>"
			    + "</p>");
		    out.print("</body></html>");
		}// End of if
		else {
			out.print("<html><head><title>A JEE Application for Measuring Document Similarity</title>");		
			out.print("</head>");		
			out.print("<body>");
			out.print("<H1>Processing request for Job#: " + taskNumber + "</H1>");
			out.print("<H3>Document Title: " + title + "</H3>");
			out.print("<b><font color=\"ff0000\">A total of " + counter + " polls have been made for this request.</font></b> ");
			out.print("Place the final response here... a nice table (or graphic!) of the document similarity...");
			out.print("<form name=\"frmRequestDetails\">");
			out.print("<input name=\"txtTitle\" type=\"hidden\" value=\"" + title + "\">");
			out.print("<input name=\"frmTaskNumber\" type=\"hidden\" value=\"" + taskNumber + "\">");
			out.print("<input name=\"counter\" type=\"hidden\" value=\"" + counter + "\">");
			out.print("</form>");								
			out.print("</body>");	
			out.print("</html>");	
			out.print("<script>");
			out.print("var wait=setTimeout(\"document.frmRequestDetails.submit();\", 5000);"); //Refresh every 5 seconds
			out.print("</script>");
		}// End of else
	}// End of doGet

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
 	}// End of doPost
}// End of ServicePollHandler class