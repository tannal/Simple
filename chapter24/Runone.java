import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.junit.runner.Description;
import com.seaofnodes.simple.Chapter24Test;

public class RunOne {
    public static void main(String[] args) {
        JUnitCore core = new JUnitCore();

        // 添加監聽器來實時打印進度
        core.addListener(new RunListener() {
            @Override
            public void testStarted(Description description) {
                System.out.println("\n🚀 Running: " + description.getMethodName());
            }

            @Override
            public void testFinished(Description description) {
                System.out.println("✅ Finished: " + description.getMethodName());
            }
        });

        // 執行請求
        System.out.println("--- Sea of Nodes Test Runner ---");
        Request request = Request.method(Chapter24Test.class, "codegen");
        Result result = core.run(request);

        // 打印詳細結果
        System.out.println("\n--------------------------------");
        if (result.wasSuccessful()) {
            System.out.println("🎉 TEST PASSED!");
        } else {
            System.err.println("❌ TEST FAILED (" + result.getFailureCount() + " failure)");
            for (Failure failure : result.getFailures()) {
                System.err.println("\nMessage: " + failure.getMessage());
                System.err.println("Trace:\n" + failure.getTrace());
            }
        }
        System.out.println("Time elapsed: " + result.getRunTime() + " ms");
        System.out.println("--------------------------------");

        System.exit(0);
    }
}