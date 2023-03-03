package mx.abrahamNtd.poc.reports;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.ExtentReports;

import java.util.HashMap;
import java.util.Map;

public class ExtentTestManager {

    static Map<Integer, BasicTestReport> extentTestMap = new HashMap<Integer, BasicTestReport>();
    static ExtentReports extent = ExtentManager.getInstance();

    public static synchronized BasicTestReport getTest() {
        return (BasicTestReport) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
    }

    public static synchronized void endTest() {
        extent.flush();
    }

    public static synchronized BasicTestReport startTest(String testName) {
      BasicTestReport basicTestReport = new BasicTestReport(extent, testName);
      extentTestMap.put((int) (long) (Thread.currentThread().getId()), basicTestReport);
      return basicTestReport;
    }
}
