
package in.fissionlabs.kpinsights.main;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.codec.Base64;

public class StartApp {

	public static void main(String[] args){
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		String emailAddress = "bismoy.murasing@fissionlabs.in";
		String password = "hello123";
	
		System.out.println(passwordEncoder.encode(password));
		String unEncodedPassword = emailAddress.concat("+").concat(password); 
		
		System.out.println("unEncodedPassword -- "+unEncodedPassword);
		
		byte[] bytes = unEncodedPassword.getBytes();
		
		String encodedString = new String(Base64.encode(bytes)); 
		System.out.println("encodedString -- "+encodedString);
	
		String decodedCredentials = new String(Base64.decode(encodedString.getBytes()));
		System.out.println(decodedCredentials);
		String[] credentials = decodedCredentials.split("[\\+:]");
		
		for(String string : credentials){
			System.out.println(string);
		}
	}

}
