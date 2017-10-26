package br.com.olx.testes;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import br.com.olx.po.PageObjectsElementos;
import br.com.olx.utils.WebDriverFactory;

public class PrimeiroDesafio {

	private WebDriver driver;
	private PageObjectsElementos poElementos;

	@Before
	public void setUP() {

		this.driver = WebDriverFactory.getInstance();
		this.poElementos = new PageObjectsElementos(driver);
	}

	@Test
	public void primeiroTest() {

		// Guardando em variavel o que sera pesquisado
		String nameProduct = "iphone";

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

		// Exibicao dos Resultados de acordo com a quantidade informada
		poElementos.resulTitlePrice(5);

	}

	@After
	public void tearDown() {

		driver.quit();
	}

}
