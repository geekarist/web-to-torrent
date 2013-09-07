package com.github.geekarist.wtt;

import static org.fest.assertions.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

public class TorrentTest {

	@Test
	public void shouldFindTorrent() throws IOException, NoSuchAlgorithmException {
		// G
		String query = "Oblivion 2013";
		// W
		File torrent = Torrent.find(query);
		// T
		assertThat(torrent.getPath()).isEqualTo("045E85F2EBC24A875A64FE2E9AC9B61F7AAD0499.torrent");
		assertThat(sha1(torrent)).isEqualTo("045E85F2EBC24A875A64FE2E9AC9B61F7AAD0499");
	}

	private String sha1(File torrent) throws IOException, NoSuchAlgorithmException {
		MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
		InputStream input = null;

		try {
			input = new FileInputStream(torrent);
			StringBuilder builder = new StringBuilder();
			while (!builder.toString().endsWith("4:info")) {
				builder.append((char) input.read()); // It's ASCII anyway.
			}
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			for (int data; (data = input.read()) > -1; output.write(data)) {
				// Just write to output
			}
			sha1.update(output.toByteArray(), 0, output.size() - 1);
		} finally {
			if (input != null)
				try {
					input.close();
				} catch (IOException ignore) {
				}
		}

		byte[] hash = sha1.digest(); // Here's your hash. Do your thing with it.
		return hex(hash);
	}

	final protected static char[] hexArray = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
			'E', 'F' };

	public static String hex(byte[] bytes) {
		char[] hexChars = new char[bytes.length * 2];
		int v;
		for (int j = 0; j < bytes.length; j++) {
			v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}

}
