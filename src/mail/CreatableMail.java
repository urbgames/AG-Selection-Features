package mail;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.mail.internet.InternetAddress;

public class CreatableMail {

	private static SenderMail mail = new SenderMail("urbgames2@gmail.com", "C0nT4T3ste", SenderMail.Protocols.SSL);

	public static void sendMail(String pathFile, String text) {
		try {
			InternetAddress to = new InternetAddress("urbgames@gmail.com");
			String addresslist = Files.readAllLines(Paths.get("emails.txt")).toString().replaceAll("\\[|\\]|\\ ", "");
			InternetAddress[] toCC = InternetAddress.parse(addresslist);
			new Thread(new Runnable() {
				public void run() {
					mail.sendMail(to, toCC, "Resultado do experimento GA", "Fim do experimento " + text,
							new File[] { new File(pathFile) });
				}
			}).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
