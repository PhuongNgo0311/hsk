package entity;

import java.util.Objects;

public class LopHoc {
	private String maLH;
	private String tenLH;
	private GiaoVien giaoVien;
	private int siSo;
	public LopHoc() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LopHoc(String maLH) {
		super();
		this.maLH = maLH;
	}
	public LopHoc(String maLH, String tenLH, GiaoVien giaoVien, int siSo) {
		super();
		this.maLH = maLH;
		this.tenLH = tenLH;
		this.giaoVien = giaoVien;
		this.siSo = siSo;
	}
	public String getMaLH() {
		return maLH;
	}
	public void setMaLH(String maLH) {
		this.maLH = maLH;
	}
	public String getTenLH() {
		return tenLH;
	}
	public void setTenLH(String tenLH) {
		this.tenLH = tenLH;
	}
	public GiaoVien getGiaoVien() {
		return giaoVien;
	}
	public void setGiaoVien(GiaoVien giaoVien) {
		this.giaoVien = giaoVien;
	}
	public int getSiSo() {
		return siSo;
	}
	public void setSiSo(int siSo) {
		this.siSo = siSo;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maLH);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LopHoc other = (LopHoc) obj;
		return Objects.equals(maLH, other.maLH);
	}
	@Override
	public String toString() {
		return "LopHoc [maLH=" + maLH + ", tenLH=" + tenLH + ", giaoVien=" + giaoVien + ", siSo=" + siSo + "]";
	}
	
}
