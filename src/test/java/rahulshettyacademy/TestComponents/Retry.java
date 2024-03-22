package rahulshettyacademy.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

	int count = 0;
	int maxTry = 1;
	
	@Override
	public boolean retry(ITestResult result) {
		
		if(count<maxTry)
		{
			count++;
			return true;						//it go to test
		}
		return false;							//it stops retrying here 
	}

	//940964b04e9146059f32deefc1c69244
	
}
