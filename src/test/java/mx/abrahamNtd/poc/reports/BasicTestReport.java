package mx.abrahamNtd.poc.reports;

import java.io.PrintStream;
import java.io.StringWriter;
import org.apache.commons.io.output.WriterOutputStream;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.ExtentReports;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;
import static io.restassured.filter.log.RequestLoggingFilter.logRequestTo;
import static io.restassured.filter.log.ResponseLoggingFilter.logResponseTo;

public class BasicTestReport {

  private StringWriter requestWriter;
  private StringWriter responceWriter;
  private PrintStream requestStream;
  private PrintStream responceStream;
  private ExtentTest test;
  private String description;

  public BasicTestReport(ExtentReports extent, String testName){
    test = extent.createTest(testName);
    requestWriter = new StringWriter();
    responceWriter = new StringWriter();
    requestStream = new PrintStream(new WriterOutputStream(requestWriter), true);
    responceStream = new PrintStream(new WriterOutputStream(responceWriter), true);
  }

  public ExtentTest getTest() {
    return test;
  }

  public void log(Status type, String status) {
    test.log(type, status);
    test.log(Status.INFO, "Description: </br></br><pre>" + description + "</pre></br></br>");
    try {
      test.log(type, "Request: </br></br>  <pre><code>" + requestWriter.toString().replaceAll("<","&60").replaceAll(">","&62") + " </code></pre>");
      test.log(type, "Responce: </br></br> <pre><code>" + responceWriter.toString().replaceAll("<","&60").replaceAll(">","&62") + " </code></pre>");
    }catch(Exception e) {
      test.log(Status.FAIL, "</br></br><pre>Error while log the request and the responce on the test report</pre></br></br>");
    }
  }

  public RequestSpecification given(String description) {
    this.description = description;
    return io.restassured.RestAssured.given().filter(logRequestTo(requestStream)).filter(logResponseTo(responceStream));
  }
}
