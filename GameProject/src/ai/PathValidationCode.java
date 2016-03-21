package ai;

/**
 * This class defines possible return codes for path validation  
 * @author eshinig
 *
 */
public enum PathValidationCode 
{
	PATH_OK(0), PATH_NOT_CONTINOUS(1), PATH_NO_EXIT_ENTRY(2), PATH_MANY_EXIT_ENTRY(3), PATH_MANY_ROUTES_FOUND(4);
	private int code;
	
	private PathValidationCode(int c) 
	{
		code = c;
	}

	public int getCode() 
	{
		return code;
	}
}