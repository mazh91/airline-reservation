
public enum ErrorCodes {
	NORMAL(0, "OK"),
	INVALID_FIELD(-1, "Text in context contains invalid characters"),
	EMPTY_FIELD(-2, "Required field is empty");
	
	  private final int id;
	  private final String msg;

	  private ErrorCodes(int id, String msg) {
	    this.id = id;
	    this.msg = msg;
	  }

	  public int getId() {
	    return this.id;
	  }

	  public String getMsg() {
	    return this.msg;
	  }
}
