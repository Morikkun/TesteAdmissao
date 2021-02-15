package tests;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestSignin {

	@Test
	public void testSerasaLoginWithoutCPF() throws InterruptedException {
		String path = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", path + "/src/test/resources/chromedriver.exe");

		WebDriver navegador = new ChromeDriver();

		navegador.manage().window().maximize();
		navegador.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		navegador.get("https://www.serasaconsumidor.com.br");

		navegador.findElement(By.xpath("//*[@id=\"__next\"]/div/div[4]/div/div[1]/div[2]/button")).click();
		navegador.findElement(By.xpath("//*[@id=\"valgrind\"]/div/div[2]/div/div/form/button")).click();
		String mensagemRecebida = navegador.findElement(By.className("_1fpPRNS1")).getText();
		String mensagemEsperada = "Por favor preencha seu CPF.";

		org.junit.Assert.assertEquals(mensagemRecebida, mensagemEsperada);

		Thread.sleep(2000);
		navegador.close();
	}

	@Test
	public void testSerasaLoginWrongCPF() throws InterruptedException {
		String path = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", path + "/src/test/resources/chromedriver.exe");

		WebDriver navegador = new ChromeDriver();

		navegador.manage().window().maximize();
		navegador.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		navegador.get("https://www.serasaconsumidor.com.br");

		navegador.findElement(By.xpath("//*[@id=\"__next\"]/div/div[4]/div/div[1]/div[2]/button")).click();
		navegador.findElement(By.id("cpf")).sendKeys("99999999999");
		navegador.findElement(By.xpath("//*[@id=\"valgrind\"]/div/div[2]/div/div/form/button")).click();
		String mensagemRecebida = navegador.findElement(By.className("_1fpPRNS1")).getText();

		String mensagemEsperada = "Você precisa informar um CPF válido.";

		Assert.assertEquals(mensagemRecebida, mensagemEsperada);

		Thread.sleep(2000);
		navegador.close();
	}

	@Test
	public void testSerasaLoginRightCPF() throws InterruptedException {
		String path = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", path + "/src/test/resources/chromedriver.exe");

		WebDriver navegador = new ChromeDriver();

		navegador.manage().window().maximize();
		navegador.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		navegador.get("https://www.serasaconsumidor.com.br");

		navegador.findElement(By.xpath("//*[@id=\"__next\"]/div/div[4]/div/div[1]/div[2]/button")).click();
		navegador.findElement(By.id("cpf")).sendKeys("22754216022");
		navegador.findElement(By.xpath("//*[@id=\"valgrind\"]/div/div[2]/div/div/form/button")).click();

		navegador.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		String urlRecebida = navegador.getCurrentUrl();
		Assert.assertTrue(urlRecebida.contains("area-cliente"));

		Thread.sleep(2000);
		navegador.close();
	}

}