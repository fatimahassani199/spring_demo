package country.service.impl;

import country.dao.CountryDAO;
import country.model.Country;
import country.service.ICountryService;
import country.service.IServiceWorker;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class ServiceWorkerImpl implements IServiceWorker {
	@Autowired
	private CountryDAO countryDAO;
	@Autowired
	private ApplicationContext applicationContext;
	
	@Override
	public void dealWithCountryByCode(String language) {
		Country pays = countryDAO.getByCode(language);
		// car c'est prototype
		ICountryService countryService = applicationContext.getBean(ICountryService.class, pays);
		
		System.out.println("WELCOME : " + countryService.welcome());
		System.out.println("Devise is :" + countryService.devise());
	}

	@Override
	public void addNewCountry(String countryInfo) {
		String[] countryInformations = countryInfo.split(",", 5);
		countryDAO.addNewCountry(countryInformations );
		System.out.println(" Le pays est ajout�");
	}

	@Override
	public void deleteCountryByCode(String code) {
		countryDAO.deleteCountry(code);
		System.out.println("Le pays est supprim� ");
	}

	@Override
	public void ModifCountryByCode(String code,String newInfos) {
		countryDAO.modifCountry(code,newInfos);
		System.out.println("Le pays modifi� ");
	}

	@Override
	public List<String> continentCountriesByCode(String code) {
		return countryDAO.continentCountries(code);
	}

	@Override
	public Country getCountryInformations(String code) {
		Country country = countryDAO.getByCode(code);
		System.out.println(country.toString());
		return country;
	}
	
}
