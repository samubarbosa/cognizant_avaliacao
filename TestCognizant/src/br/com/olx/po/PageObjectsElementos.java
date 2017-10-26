package br.com.olx.po;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObjectsElementos {

	WebDriver driver;

	public PageObjectsElementos(WebDriver driver) {
		this.driver = driver;
	}

	// Acessando o site da olx
	public void urlPage(String url) {
		driver.get(url);

	}

	// Clicando no Estado de SÃ£o Paulo
	public void selectState() {
		driver.findElement(By.id("state-sp")).click();

	}

	// Realizando a busca
	public void searchProduct(String value) {
		driver.findElement(By.id("searchtext")).sendKeys(value);
		driver.findElement(By.id("searchbutton")).click();

	}

	// Clicar para fechar a propaganda
	public void closeAdvertising() {
		driver.findElement(By.id("sas_defaultCloseButton_7322835")).click();

	}

	// aguardando propaganda
	public void waitAdvertising() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sas_48217")));
	}

	// aguardar carregamento da pagina de pesquisa
	public void waitLoadPage() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("searchtext")));
	}

	// aguadar a pagina carregar com o resultado da pesquisa
	public void waitForResult(String wordkey) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.titleContains(wordkey + " em São Paulo - Encontramos " + wordkey + " | OLX"));
	}

	// imprimindo o resultado de acordo com a quantidade informada
	public void resulTitlePrice(int quantity) {

		List<WebElement> listTitle = driver.findElements(By.cssSelector(".OLXad-list-link"));
		List<WebElement> listPrice = driver.findElements(By.cssSelector(".OLXad-list-price"));

		for (int i = 0; i < quantity; i++) {

			String firstTitle = listTitle.get(i).getAttribute("title");
			String firstPrice = listPrice.get(i).getText();
			System.out.println(firstTitle + " " + firstPrice);

		}
	}
	//Selecionando a segunda pagina da busca
	public void navPage() {
		// Indo para a segunda pagina
		List<WebElement> countNumber = driver.findElements(By.cssSelector("div ul li[class='item number']"));
		countNumber.get(0).click();

	}
	
	//Selecionando o segundo anuncio da segunda pagina
	@SuppressWarnings("deprecation")
	public void selectFirstAd() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.textToBePresentInElement(By.cssSelector("div ul li[class='item number active']"),
				"2"));
		List<WebElement> firstAD = driver.findElements(By.cssSelector(".OLXad-list-link"));
		firstAD.get(0).click();
	}
	
	// metodo para tirar printscreen
	public void takeScreenshot(String nome) {
		File arquivo;
		try {
			arquivo = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(arquivo, new File("evidencias/" + nome + ".jpeg"));
		} catch (Exception e) {
		}

	}

}
