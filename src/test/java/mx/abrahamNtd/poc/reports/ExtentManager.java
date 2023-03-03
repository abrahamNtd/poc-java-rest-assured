package mx.abrahamNtd.poc.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.io.File;

public class ExtentManager {
    private static ExtentReports extent;
    private static String reportFileName = "Functional_Test_Report" + ".html";
    private static String fileSeperator = System.getProperty("file.separator");
    private static String reportFilepath = System.getProperty("user.dir") + fileSeperator + "build" + fileSeperator + "reports" + fileSeperator + "TestReport";
    private static String reportFileLocation =  reportFilepath + fileSeperator + reportFileName;

    public static ExtentReports getInstance() {
        if (extent == null)
            createInstance();
        return extent;
    }

    public static ExtentReports createInstance() {
        String fileName = getReportPath(reportFilepath);
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle(reportFileName);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(reportFileName);
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Operating system name", System.getProperty("os.name"));
        extent.setSystemInfo("JRE vendor name", System.getProperty("java.vendor"));
        extent.setSystemInfo("JRE version number", System.getProperty("java.version"));
        return extent;
    }

    private static String getReportPath (String path) {
        File testDirectory = new File(path);
        if (!testDirectory.exists()) {
            if (testDirectory.mkdir()) {
                System.out.println("Directory: " + path + " is created!" );
                return reportFileLocation;
            } else {
                System.out.println("Failed to create directory: " + path);
                return System.getProperty("user.dir");
            }
        } else {
            System.out.println("Directory already exists: " + path);
        }
        return reportFileLocation;
    }
}
