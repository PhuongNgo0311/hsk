	package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import ConnectDB.Database;
import entity.GiaoVien;
import entity.LopHoc;

public class LopHoc_Dao {
	ArrayList<LopHoc> dslh;
	LopHoc lh;
	public LopHoc_Dao() {
		dslh = new ArrayList<LopHoc>();
	}
	public ArrayList<LopHoc> docTuBang(){
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "Select * from lophoc";
			Statement statement = con.createStatement();
			
			ResultSet rs = statement.executeQuery(sql);
			
			while (rs.next()) {
				String ma = rs.getString(1);
				String ten = rs.getString(2);
				String gv = rs.getString(3);
				int siSo = rs.getInt(4);
				LopHoc lh = new LopHoc(ma, ten, new GiaoVien(gv), siSo);
				dslh.add(lh);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dslh;
	}
	
	public ArrayList<LopHoc> getLopTheoMaLop(String maLop){
		try {
			Connection con = Database.getInstance().getConnection();
			PreparedStatement stmt = null;
			String sql = "Select * from lophoc where maLop = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maLop);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String ma = rs.getString(1);
				String ten = rs.getString(2);
				String gv = rs.getString(3);
				int siSo = rs.getInt(4);
				LopHoc lh = new LopHoc(ma, ten, new GiaoVien(gv), siSo);
				dslh.add(lh);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dslh;
	}
	public boolean create(LopHoc lh){
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into LopHoc values(?,?,?,?)");
			stmt.setString(1, lh.getMaLH());
			stmt.setString(2, lh.getTenLH());
			stmt.setString(3, lh.getGiaoVien().getMaGV());
			stmt.setInt(4, lh.getSiSo());
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n>0;
	}
	public boolean update(LopHoc lh){
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update LopHoc set tenLop=?, maGiaoVien=?, siSo=? where maLop = ?");
			stmt.setString(1, lh.getTenLH());
			stmt.setString(2, lh.getGiaoVien().getMaGV());
			stmt.setInt(3, lh.getSiSo());
			stmt.setString(4, lh.getMaLH());
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n>0;
	}
	public boolean delete(String maLop){
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete from LopHoc where maLop = ?");
			stmt.setString(1, maLop);
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n>0;
	}
}
