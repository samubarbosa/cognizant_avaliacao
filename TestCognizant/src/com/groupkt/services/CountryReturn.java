package com.groupkt.services;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import br.com.olx.po.PageObjectsElementos;
import br.com.olx.utils.WebDriverFactory;
import junit.framework.Assert;

public class CountryReturn {

	private WebDriver driver;
	private PageObjectsElementos poElementos;

	@Before
	public void setUP() {

		this.driver = WebDriverFactory.getInstance();
		this.poElementos = new PageObjectsElementos(driver);
	}

	@Test
	public void catchCountry() {

		poElementos.urlPage("http://services.groupkt.com/country/get/all");

		WebElement campoValor = driver.findElement(By.cssSelector("body"));

		JSONObject jsonConvertido = new JSONObject(campoValor.getText());

		// Localizando a primeira camada do json
		JSONObject firstLayer = jsonConvertido.getJSONObject("RestResponse");

		// Localizando a segunda camada do json
		JSONArray secondLayer = firstLayer.getJSONArray("result");

		StringBuilder listName = new StringBuilder();
		StringBuilder listAlpha2 = new StringBuilder();
		StringBuilder listAlpha3 = new StringBuilder();
		int countList = 0;

		// Convertendo o array para objeto json e coletar os dados pelas tags
		for (int i = 0; i < secondLayer.length(); i++) {
			JSONObject f = secondLayer.getJSONObject(i);
			listName.append(f.get("name")).append(", ");
			listAlpha2.append(f.get("alpha2_code")).append(", ");
			listAlpha3.append(f.get("alpha3_code")).append(", ");

			countList = i;
		}

		// Print dos resultados
		// System.out.println(listName);
		// System.out.println(listAlpha2);
		// System.out.println(listAlpha3);
		// System.out.println(countList);

		// validando a quantidade de paises
		Assert.assertEquals(countList, 248);

		// Verificando se o pais informado pelo usuário está presente
		// (Nome,alpha2_code ou aplha3_code)
		assertThat(listName.toString(), containsString("Cameroon"));
		assertThat(listAlpha2.toString(), containsString("CM"));
		assertThat(listAlpha3.toString(), containsString("CMR"));

	}

	@After
	public void tearDown() {

		driver.quit();

	}
}
