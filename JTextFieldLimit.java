import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/*
 * This class permit us to limit the number of characters into a 
 * JTextField by using the method setDocument
 */


class JTextFieldLimit extends PlainDocument {
	private int limit;
  
	JTextFieldLimit(int limit) {
		super();
		this.limit = limit;
	}

	JTextFieldLimit(int limit, boolean upper) {
		super();
		this.limit = limit;
	}

	public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
		if (str == null)
			return;

		if ((getLength() + str.length()) <= limit) 
		{
			super.insertString(offset, str, attr);
		}	
	}
	
}
