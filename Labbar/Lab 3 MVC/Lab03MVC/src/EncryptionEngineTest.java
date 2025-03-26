import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EncryptionEngineTest {

	@Test
	public void testDecrypt() {
		// Arrange / set-up
		Controller controller = null; // dummy controller
		EncryptionEngine e = new EncryptionEngine(controller);
		e.setEncryptionKey(3); // String or int, depending on your implementation
		// Act and Assert in one line
		assertEquals(e.decrypt("DEF"), "ABC");
		// Another Act and Assert
		assertEquals(e.decrypt("ABC"), "XYZ");
	}

	@Test
	public void testEncrypt() {
		// Arrange / set-up
		Controller controller = null; // dummy controller
		EncryptionEngine e = new EncryptionEngine(controller);
		e.setEncryptionKey(3); // String or int, depending on your implementation
		// Act and Assert in one line
		assertEquals(e.encrypt("ABC"), "DEF");
		// Another Act and Assert
		assertEquals(e.encrypt("XYZ"), "ABC");
	}

}
