package br.com.olx.testes;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import br.com.olx.po.PageObjectsElementos;
import br.com.olx.utils.WebDriverFactory;

public class SegundoDesafio {

	private WebDriver driver;
	private PageObjectsElementos poElementos;

	@Before
	public void setUP() {

		this.driver = WebDriverFactory.getInstance();
		this.poElementos = new PageObjectsElementos(driver);
	}

	@Test
	public void segundoTest() {
		//WebDriverWait wait = new WebDriverWait(driver, 30);

		// Guardando em variavel o que sera pesquisado
		String nameProduct = "guarda roupa";

		// Acessando o site da olx
		poElementos.urlPage("http://www.olx.com.br");

		// Clicando no Estado de São Paulo
		poElementos.selectState();

		// Metodo de espera ate que o campo de busca esteja visivel
		poElementos.waitLoadPage();
		// fechando propaganda
		poElementos.waitAdvertising();
		poElementos.closeAdvertising();

		// Realizando a busca
		poElementos.searchProduct(nameProduct);

		// Espera ate a pagina carregar a busca
		poElementos.waitForResult(nameProduct);

		// Indo para a segunda pagina
		poElementos.navPage();

		// //fechando novamente o anuncio
		// poElementos.waitAdvertising();
		// poElementos.closeAdvertising();

		// Selecionando o primeiro a anuncio da segunda pagina
		poElementos.selectFirstAd();

		// tirando o print da venda
		poElementos.takeScreenshot("PrintAD");

	}

	 @After
	 public void tearDown() {
	 driver.quit();
	
	 }

}
