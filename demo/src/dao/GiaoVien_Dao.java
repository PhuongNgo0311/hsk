package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ConnectDB.Database;
import entity.GiaoVien;


public class GiaoVien_Dao {
	List<entity.GiaoVien> dsgv;
	public GiaoVien_Dao() {
		dsgv = new ArrayList<entity.GiaoVien>();
	}
	public List<entity.GiaoVien> docTuBang(){
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "Select * from giaovien";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while (rs.next()) {
				String ma = rs.getString(1);
				String ten = rs.getString(2);
				GiaoVien gv = new GiaoVien(ma, ten);
				dsgv.add(gv);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsgv;
	}
}
