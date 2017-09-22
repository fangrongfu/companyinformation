package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Company;
import util.JdbcConnection;


/**
 * @author: fangrongfu;
 * @version: 1.0; @date： 2017年7月27日 下午3:45:57;
 */
public class Dao {
	private String sql = null;// 动态SQL语句
	private PreparedStatement ps = null;// 创建sql语句执行对象
	private Connection con = JdbcConnection.getConnection();// 调用工具类进行本地数据库连接
	private Connection con1 = JdbcConnection.getConnectionOne();// 调用工具类进行外部数据库连接
	private Company company = new Company();
	private List<Company> list = new ArrayList<Company>();
	
	// JDBC添加数据到数据库,将公告信息添加到外部数据库
	public void addCompany(Company company) {
		try {
			sql = "insert into company values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			ps = con1.prepareStatement(sql);
			ps.setString(1, company.getN_name());
			ps.setString(2, company.getN_code());
			ps.setString(3, company.getC_full_name());
			ps.setString(4, company.getC_English_name());
			ps.setString(5, company.getC_listed_market());
			ps.setString(6, company.getC_listing_date());
			ps.setString(7, company.getC_issue_price());
			ps.setString(8, company.getC_lead_underwriter());
			ps.setString(9, company.getC_establishment_date());
			ps.setString(10, company.getC_registered_capital());
			ps.setString(11, company.getC_organization_type());
			ps.setString(12, company.getC_organization_form());
			ps.setString(13, company.getC_board_secretary());
			ps.setString(14, company.getC_company_phone());
			ps.setString(15, company.getC_secretary_call());
			ps.setString(16, company.getC_company_fax());
			ps.setString(17, company.getC_secretary_fax());
			ps.setString(18, company.getC_company_email());
			ps.setString(19, company.getC_secretary_email());
			ps.setString(20, company.getC_company_website());
			ps.setString(21, company.getC_company_maibox());
			ps.setString(22, company.getC_information_disclosure_website());
			ps.setString(23, company.getC_securities_history());
			ps.setString(24, company.getC_registered_address());
			ps.setString(25, company.getC_office_address());
			ps.setString(26, company.getC_company_profile());
			ps.setString(27, company.getC_management_scope());
			int i = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// 查询公司表中的公司的名字和股票代码
	public List<Company> selectFirm() {
		sql = "select f_name,f_code from firm";
		ResultSet rs = null;
		try {
			ps = con1.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Company company = new Company();
				company.setN_name(rs.getString(1));
				company.setN_code(rs.getString(2));
				list.add(company);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// 关闭声明
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	// JDBC添加数据到数据库，通过公司名字查询数据库信息来判断添加信息是否重复
	public boolean selectCompany(Company company){
		sql = "select n_name from company where n_name = ?";
		ResultSet rs = null;
		boolean b = true;
		try {
			ps = con1.prepareStatement(sql);
			ps.setString(1, company.getN_name());
			rs = ps.executeQuery();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			if(rs.next()) {
				b = true;	
			}else {
				b = false;;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}
}
