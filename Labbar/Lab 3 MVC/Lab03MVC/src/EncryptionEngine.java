public class EncryptionEngine {

	private Controller _controller;
	private int encryptionKey;


	public EncryptionEngine(Controller controller) {
		_controller = controller;
		encryptionKey = 0;
	}

	public void setEncryptionKey(int key) {		
		encryptionKey = key;
	}

	public int getEncryptionKey() {
		return encryptionKey;
	}  


	public String encrypt(String InputText) {
		char[]nArray = new char[InputText.length()];
		for(int i=0; i<InputText.length(); i++) {
			int chEncrypt =(InputText.codePointAt(i)+ getEncryptionKey());
			chEncrypt = ((chEncrypt-'A')%26)+'A';
			if(InputText.codePointAt(i) >= 'A' && InputText.codePointAt(i) <= 'Z') {
				nArray[i]=(char)chEncrypt;
			}
			else {
				return "Error: Only capital letters A-Z are allowed!";
			}
		}
		return String.copyValueOf(nArray);
	}

	public String decrypt(String text) {
		char[] nArray = new char[text.length()];
		for(int i=0; i<text.length(); i++) {
			int myChar=(text.codePointAt(i) - getEncryptionKey());
			myChar =(((myChar-'A')+26)% 26)+'A';
			if(text.codePointAt(i)>= 'A'&& text.codePointAt(i) <= 'Z') {
				nArray[i]=(char)myChar;
			}
			else {
				return "Error: Only capital letters A-Z are allowed!";
			}
		}
		return String.copyValueOf(nArray);
	}
}
