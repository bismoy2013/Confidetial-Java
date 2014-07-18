package in.fissionlabs.kpinsights.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("PasswordService")
public class PasswordServiceImpl implements PasswordService{
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public boolean match(String password, String dbPassword)
	{
		return passwordEncoder.matches(password, dbPassword);
	}
	
	@Override
	public String encodePassword(String password)
	{
		return passwordEncoder.encode(password);
	}

	public BCryptPasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	

}
