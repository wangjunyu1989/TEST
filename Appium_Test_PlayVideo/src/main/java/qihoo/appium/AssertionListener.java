//package qihoo.appium;
//
//import org.testng.ITestResult;
//import org.testng.TestListenerAdapter;
//
//import org.junit.
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static java.util.Arrays.copyOf;
//
//
//public class AssertionListener extends TestListenerAdapter {
//
//    @Override
//    public void onTestStart(ITestResult result) {
//
//        com.qihoo.test.qihoo.Assertion.errors.clear();
//        com.qihoo.test.qihoo.Assertion.flag = true;
//    }
//
//
//
//    @Override
//    public void onTestFailure(ITestResult tr) {
//        this.handleAssertion(tr);
//    }
//
//
//
//    @Override
//    public void onTestSkipped(ITestResult tr) {
//        this.handleAssertion(tr);
//    }
//
//    @Override
//    public void onTestSuccess(ITestResult tr) {
//        this.handleAssertion(tr);
//    }
//
//    private int index = 0;
//
//
//    private void handleAssertion(ITestResult tr){
//        if(!com.qihoo.test.qihoo.Assertion.flag){
//
//            Throwable throwable = tr.getThrowable();
//            if(throwable==null){
//                throwable = new Throwable();
//            }
//            StackTraceElement[] traces = throwable.getStackTrace();
//
//            StackTraceElement[] alltrace = new StackTraceElement[0];
//
//            for (Error e :com.qihoo.test.qihoo.Assertion.errors) {
//
//                StackTraceElement[] errorTraces = e.getStackTrace();
//                //
//                StackTraceElement[] et = getKeyStackTrace(tr, errorTraces);
//                StackTraceElement[] message = handleMess(e.getMessage(),tr);
//
//                index = 0;
//                alltrace = merge(alltrace, message);
//                alltrace = merge(alltrace, et);
//            }
//
//            if(traces!=null){
//                traces = getKeyStackTrace(tr, traces);
//                alltrace = merge(alltrace, traces);
//            }
//
//            throwable.setStackTrace(alltrace);
//            tr.setThrowable(throwable);
//
//            com.qihoo.test.qihoo.Assertion.flag = true;
//            com.qihoo.test.qihoo.Assertion.errors.clear();
//
//            tr.setStatus(ITestResult.FAILURE);
//        }
//    }
//
//
//    private StackTraceElement[] getKeyStackTrace(ITestResult tr, StackTraceElement[] stackTraceElements){
//
//        List<StackTraceElement> ets = new ArrayList<>();
//
//        for (StackTraceElement stackTraceElement : stackTraceElements) {
//
//            if(stackTraceElement.getClassName().equals(tr.getTestClass().getName())){
//                ets.add(stackTraceElement);
//                index = stackTraceElement.getLineNumber();
//            }
//        }
//        return ets.toArray(new StackTraceElement[ets.size()]);
//
//    }
//
//
//    private StackTraceElement[] merge(StackTraceElement[] traces1, StackTraceElement[] traces2){
//
//        StackTraceElement[] result = copyOf(traces1, traces1.length + traces2.length);
//        System.arraycopy(traces2, 0, result, traces1.length, traces2.length);
//        return result;
//    }
//
//    //收集错误信息
//    private StackTraceElement[] handleMess(String mess,ITestResult tr){
//        String message = "\n������Ϣ: "+mess;
//        String method = "\n��������:"+tr.getMethod().getMethodName();
//        String className = "\n������:"+tr.getTestClass().getRealClass().getSimpleName();
//        return new StackTraceElement[]{
//                new StackTraceElement(message,
//                        method,
//                        className+"\n�����к�",
//                        index)};
//    }
//}