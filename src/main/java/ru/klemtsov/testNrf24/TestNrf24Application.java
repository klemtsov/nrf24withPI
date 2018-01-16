package ru.klemtsov.testNrf24;

import com.m32dn.nrf24pi.Nrf24Controller;
import com.m32dn.nrf24pi.Nrf24Factory;
import com.m32dn.nrf24pi.exception.NrfException;
import com.m32dn.nrf24pi.impl.AddressImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
public class TestNrf24Application {

	public static void main(String[] args) {

		SpringApplication.run(TestNrf24Application.class, args);
		try {
			Process p = Runtime.getRuntime().exec("gpio load spi");
			p.waitFor();
			byte[] a = {(byte) 0x41, (byte) 0x41, (byte) 0x41, (byte) 0x41, (byte) 0x41,
					(byte) 0x41, (byte) 0x41, (byte) 0x41, (byte) 0x41, (byte) 0x41,
					(byte) 0x41, (byte) 0x41, (byte) 0x41, (byte) 0x41, (byte) 0x41,
					(byte) 0x41, (byte) 0x41, (byte) 0x41, (byte) 0x41, (byte) 0x41,
					(byte) 0x41};
			Nrf24Controller c = Nrf24Factory.getInstance();
			c.start();
			c.sendSynchronized(new AddressImpl(0x41,0x41,0x41,0x41,0x41), ByteBuffer.wrap(a));
			c.stop();
		} catch (NrfException | IOException | InterruptedException ex) {
			Logger.getLogger(TestNrf24Application.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
