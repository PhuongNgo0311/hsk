package entity;

import java.util.Objects;

public class GiaoVien {
	private String maGV;
	private String tenGV;
	public GiaoVien() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GiaoVien(String maGV) {
		super();
		this.maGV = maGV;
	}
	public GiaoVien(String maGV, String tenGV) {
		super();
		this.maGV = maGV;
		this.tenGV = tenGV;
	}
	public String getMaGV() {
		return maGV;
	}
	public void setMaGV(String maGV) {
		this.maGV = maGV;
	}
	public String getTenGV() {
		return tenGV;
	}
	public void setTenGV(String tenGV) {
		this.tenGV = tenGV;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maGV);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GiaoVien other = (GiaoVien) obj;
		return Objects.equals(maGV, other.maGV);
	}
	@Override
	public String toString() {
		return "GiaoVien [maGV=" + maGV + ", tenGV=" + tenGV + "]";
	}
	
}
