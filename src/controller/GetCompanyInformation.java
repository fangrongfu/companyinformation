package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import dao.Dao;
import entity.Company;

/**
 * @author  fangrongfu
 * @version 1.0
 * @time    2017年8月23日下午5:15:27
 */
public class GetCompanyInformation {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "lib\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		Dao dao = new Dao();
		List<Company> l = new ArrayList<Company>();
		l = dao.selectFirm();
		for (Company company : l) {
			//driver = new ChromeDriver();
			driver.manage().window().maximize();
			String n_code = company.getN_code();
			String n_name = company.getN_name();
			String u = "http://vip.stock.finance.sina.com.cn/corp/go.php/vCI_CorpInfo/stockid/";
			String ur = u.concat(n_code);
			String url = ur.concat(".phtml");
			//System.out.println(url);
			driver.get(url);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			List<WebElement> list1 = driver.findElements(By.className("ccl"));
			List<WebElement> list = driver.findElements(By.className("cc"));
			if(list1.size() == 0 || list.size() == 0) {
				continue;
			}else {
				company.setN_code(n_code);
				company.setN_name(n_name);
				company.setC_full_name(list1.get(0).getText());
				company.setC_English_name(list1.get(1).getText());
				company.setC_listed_market(list.get(0).getText());
				company.setC_listing_date(list.get(1).getText());
				company.setC_issue_price(list.get(2).getText());
				company.setC_lead_underwriter(list.get(3).getText());
				company.setC_establishment_date(list.get(4).getText());
				company.setC_registered_capital(list.get(5).getText());
				company.setC_organization_type(list.get(6).getText());
				company.setC_organization_form(list.get(7).getText());
				company.setC_board_secretary(list.get(8).getText());
				company.setC_company_phone(list.get(9).getText());
				company.setC_secretary_call(list.get(10).getText());
				company.setC_company_fax(list.get(11).getText());
				company.setC_secretary_fax(list.get(12).getText());
				company.setC_company_email(list.get(13).getText());
				company.setC_secretary_email(list.get(14).getText());
				company.setC_company_website(list.get(15).getText());
				company.setC_company_maibox(list.get(16).getText());
				company.setC_information_disclosure_website(list.get(17).getText());
				company.setC_securities_history(list1.get(2).getText());
				company.setC_registered_address(list1.get(3).getText());
				company.setC_office_address(list1.get(4).getText());
				company.setC_company_profile(list1.get(5).getText());
				company.setC_management_scope(list1.get(6).getText());
				//System.out.println(company);
				boolean b = dao.selectCompany(company);
				//System.out.println(b);
				if(b) {
	                continue;
				}else {
					dao.addCompany(company);
				}	
			}
		/*	if(list.size() == 0) {
				driver.quit();
			}else {
				String s = list.get(0).getText().replaceAll("\\s*", "");
				System.out.println(s);
				String c_full_name = s.substring(5, s.length());
				String s1 = list.get(1).getText().replaceAll("\\s*", "");
				System.out.println(s1);
				String c_url =s1.substring(5, s1.length());
				String s2 = list.get(2).getText().replaceAll("\\s*", "");
				System.out.println(s2);
				String c_tell =s2.substring(5, s2.length());
				String s3 = list.get(3).getText().replaceAll("\\s*", "");
				System.out.println(s3);
				String c_secretary =s3.substring(3, s3.length());
				String s4 = list.get(4).getText().replaceAll("\\s*", "");
				System.out.println(s4);
				String c_secretary_email =s4.substring(8, s4.length());
				String s5 = list.get(5).getText().replaceAll("\\s*", "");
				System.out.println(s5);
				String c_secretary_tell =s5.substring(5, s5.length());
				String s6 = list.get(6).getText().replaceAll("\\s*", "");
				System.out.println(s6);
				String c_corporation =s6.substring(3, s6.length());
				String s7 = list.get(7).getText().replaceAll("\\s*", "");
				System.out.println(s7);
				String c_sponsor_booker =s7.substring(5, s7.length());
				String s8 = list.get(8).getText().replaceAll("\\s*", "");
				System.out.println(s8);
				String c_transaction_model =s8.substring(5, s8.length());
				String s9 = list.get(9).getText().replaceAll("\\s*", "");
				System.out.println(s9);
				String c_listing_date =s9.substring(5, s9.length());
				String s10 = list.get(10).getText().replaceAll("\\s*", "");
				System.out.println(s10);
				String c_found_date =s10.substring(5, s10.length());
				String s11 = list.get(11).getText().replaceAll("\\s*", "");
				System.out.println(s11);
				String c_ =s11.substring(6, s11.length());
				String s12 = list.get(12).getText().replaceAll("\\s*", "");
				System.out.println(s12);
				String c_register_money =s12.substring(5, s12.length());
				String s13 = list.get(13).getText().replaceAll("\\s*", "");
				System.out.println(s13);
				String c_area =s13.substring(5, s13.length());
				String s14 = list.get(14).getText().replaceAll("\\s*", "");
				System.out.println(s14);
				String c_address =s14.substring(5, s14.length());
				company.setC_address(c_address);
				company.setC_area(c_area);
				company.setC_corporation(c_corporation);
				company.setC_found_date(c_found_date);
				company.setC_full_name(c_full_name);
				company.setC_listing_date(c_listing_date);
				company.setC_register_money(c_register_money);
				company.setC_secretary(c_secretary);
				company.setC_secretary_email(c_secretary_email);
				company.setC_secretary_tell(c_secretary_tell);
				company.setC_transaction_model(c_transaction_model);
				company.setC_url(c_url);
				company.setN_code(n_code);
				company.setN_name(n_name);
				company.setC_sponsor_booker(c_sponsor_booker);
				company.setC_tell(c_tell);
				System.out.println(company);
				dao.addCompany(company);
				driver.quit();
			}*/
		}
		driver.quit();
	}
}
