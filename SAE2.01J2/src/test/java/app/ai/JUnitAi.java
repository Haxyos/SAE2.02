package app.ai;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class JUnitAi {

	@Test
	void testCodeErreur() {
		API api = new API();
		assertEquals(api.getCodeErr(), 200);
	}
	
	@Test
	void testUrl() {
		API api = new API();

		assertEquals(api.getUrl().toString(), "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=" + api.getKey());
	}
	
	
	@Test
	void testKey() {
		API api = new API();
		
		assertEquals(api.getKey(), "AIzaSyAJVgJVwXPU5eImYwnnHdcXFy0xeAU4QzM");
	}
}
