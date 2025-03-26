

// This is a dummy Controller, so the View compiles
public class Controller {
	private View _view;
	private EncryptionEngine _encryptionEngine;

	public Controller(){
		_encryptionEngine = new EncryptionEngine(this);
		_view = new View(this);
	}

	public void encryptButtonClicked() {
		String text = _view.getInputText();
		try {
			int key =Integer.parseInt(_view.getEncryptionKey());
			_encryptionEngine.setEncryptionKey(key);
			String encryptText = _encryptionEngine.encrypt(text);
			_view.setOutputText(encryptText);
		} catch (Exception e) {
			_view.setOutputText("Error:- Incorrect key entry!");
		}
	}

	public void decryptButtonClicked() {
		String text = _view.getInputText();
		try {
			int key =Integer.parseInt(_view.getEncryptionKey());
			_encryptionEngine.setEncryptionKey(key);
			String decryptText = _encryptionEngine.decrypt(text);
			_view.setOutputText(decryptText);
		} catch (Exception e) {
			_view.setOutputText("Error:- Incorrect key entry!");
		}
	}
	
}