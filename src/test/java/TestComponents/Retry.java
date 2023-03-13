package TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
 
	/*RetryAnalyser, which allows you to rerun a failed test method a set amount of times before declaring it as failed.
	 * IRetryAnalyzer in TestNG is an interface that can retry the failed test.
	 */
	
	int count=0;
	int maxReTry=1;// how many times you want to rerun
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		if(count<maxReTry)
		{
			count++;
			return true;
		}
		return false;
	}

}
