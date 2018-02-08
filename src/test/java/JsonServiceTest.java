
import org.junit.Test;

import com.cgir.Alice;
import com.cgir.JsonService;

import org.junit.Assert;
import org.junit.Before;

public class JsonServiceTest {

	JsonService _service;

	@Before
	public void init() {
		_service = new JsonService();
	}

	@Test
	public void test001_getAliceVersionInJSON_shouldReturnCurrentVersion() {
		Assert.assertEquals("1.0", _service.getAliceVersionInJSON());
	}

	@Test
	public void test002_getAliceNameInJSON_shouldReturnCurrentVersion() {
		Assert.assertEquals("Alice", _service.getAliceNameInJSON());
	}

	@Test
	public void test003_getAliceInJSON_shouldReturnCurrentVersion() {
		Alice alice = _service.getAliceInJSON();

		Assert.assertTrue(alice instanceof Alice);
		Assert.assertEquals("1.1", alice.getVersion());
		Assert.assertEquals("Alice", alice.getName());
	}
}
