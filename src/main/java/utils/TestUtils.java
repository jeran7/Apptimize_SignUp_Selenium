package utils;

import java.security.SecureRandom;

import config.TestConstants;

//import org.apache.bcel.classfile.Constant;

public class TestUtils {


	public static String generateRandomEmail(){
		StringBuffer baseEmail = new StringBuffer(TestConstants.EMAIL);
		baseEmail.append("+");
		SecureRandom rnd = new SecureRandom();
		for( int i = 0; i < 4; i++ ) 
			baseEmail.append( TestConstants.ALLCHARS.charAt( rnd.nextInt(TestConstants.ALLCHARS.length()) ) );
		return baseEmail.toString();
	}

}
