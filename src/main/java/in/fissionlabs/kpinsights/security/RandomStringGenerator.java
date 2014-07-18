package in.fissionlabs.kpinsights.security;


public class RandomStringGenerator {
	
	private static final Integer PASSWORD_LENGTH = 12;
	private static final Integer AUTHENTICATION_TOKEN_LENGTH = 32;
	
	private static StringBuffer buffer;
	private static String[] authenticationCharacters = {"a","b","c","d","e","f","g","h","i","j",
										 				"k","l","m","n","o","p","q","r","s","t",
														 "u","v","w","x","y","z",
														 "A","B","C","D","E","F","G","H","I","J",
														 "K","L","M","N","O","P","Q","R","S","T",
														 "U","V","W","X","Y","Z",
														 "1","2","3","4","5","6","7","8","9","0",
														 "!","@","#","$","%","^","&","*","(",")",
														 "_","+","-","="};

	public static String generateTemporaryPassword(){
		return getNewToken(PASSWORD_LENGTH);
	}
	
	public static String generateAuthenticationToken(){
		return getNewToken(AUTHENTICATION_TOKEN_LENGTH);
	}
	
	private static String getNewToken(Integer size){
		buffer = new StringBuffer();
		Integer index = 0;
		
		for(Integer i = 0; i<size; i++){
			index = (int) (Math.random() * authenticationCharacters.length);
			buffer.append(authenticationCharacters[index]);
		}
		return buffer.toString();
	}
	
	
}
