package gui;

import java.awt.Color;
import java.awt.Font;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import ConnectDB.Database;
import dao.GiaoVien_Dao;
import dao.LopHoc_Dao;
import entity.GiaoVien;
import entity.LopHoc;

public class FrmLopHoc extends JFrame implements ActionListener, MouseListener{
	 
	private JTextField txtMaLop ;
	private JTextField txtTenLop;
	private JTextField txtSiSo;
	private JComboBox cboGVCN;
	private JButton btnThem;
	private JButton btnLuu;
	private JButton btnSua;
	private JButton btnXoa;
	private JButton btnTimGV,btnTimLop;
	private DefaultTableModel dataModel;
	private JTable table;
	LopHoc_Dao dslh = new LopHoc_Dao();

	public FrmLopHoc() {
		setTitle("Thông tin lớp h�?c");
		setSize(1000, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		Box b, b1, b2, b3, b4, b44, b5, b6, b7;
		//Dùng Box layout
		add(b = Box.createVerticalBox()); //Box theo chi�?u d�?c
		b.add(Box.createVerticalStrut(10)); //Tạo khoảng cách theo chi�?u d�?c
		b.add(b1 = Box.createHorizontalBox()); b.add(Box.createVerticalStrut(10)); //b1 -> b7: Box theo chi�?u ngang
		b.add(b2 = Box.createHorizontalBox()); b.add(Box.createVerticalStrut(10)); 
		b.add(b3 = Box.createHorizontalBox()); b.add(Box.createVerticalStrut(10)); 
		b.add(b4 = Box.createHorizontalBox()); b.add(Box.createVerticalStrut(10)); 
		b.add(b44 = Box.createHorizontalBox()); b.add(Box.createVerticalStrut(10)); 
		b.add(b5 = Box.createHorizontalBox()); b.add(Box.createVerticalStrut(10)); 
		b.add(b6 = Box.createHorizontalBox()); b.add(Box.createVerticalStrut(10)); 
		b.add(b7 = Box.createHorizontalBox()); b.add(Box.createVerticalStrut(10)); 

		JLabel lblTieuDe, lblMaLop, lblTenLop, lblGVCN, lblSiSo;
		b1.add(lblTieuDe = new JLabel("THÔNG TIN LỚP HỌC", JLabel.CENTER));
		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 26));

		b2.add(lblMaLop = new JLabel("Mã lớp: ", JLabel.RIGHT)); b2.add(txtMaLop = new JTextField());
		b3.add(lblTenLop = new JLabel("Tên lớp: ", JLabel.RIGHT)); b3.add(txtTenLop = new JTextField());
		b4.add(lblGVCN = new JLabel("Giáo viên chủ nhiệm: ", JLabel.RIGHT)); 
		b4.add(cboGVCN = new JComboBox<String>());
		cboGVCN.setEditable(false);

//		// Lấy danh sách giáo viên và thêm trực tiếp vào JComboBox
//		GiaoVien_Dao dsgv = new GiaoVien_Dao();
//		List<GiaoVien> list = dsgv.docTuBang();
//		for (GiaoVien gv : list) {
//		    cboGVCN.addItem(gv.getMaGV());
//		}

		b44.add(lblSiSo = new JLabel("Sỉ số : ", JLabel.RIGHT)); b44.add(txtSiSo = new JTextField());
		
		lblMaLop.setPreferredSize(lblGVCN.getPreferredSize());
		lblTenLop.setPreferredSize(lblGVCN.getPreferredSize());
		lblSiSo.setPreferredSize(lblGVCN.getPreferredSize());
		
		b5.add(btnThem = new JButton("Thêm"));
		b5.add(btnLuu= new JButton("Lưu"));
		b5.add(btnSua = new JButton("Sửa"));
		b5.add(btnXoa = new JButton("Xóa"));
		b5.add(btnTimGV = new JButton("Tìm theo mã giáo viên"));
		b5.add(btnTimLop = new JButton("Tìm theo mã lớp"));

		String[] headers = {"Mã lớp", "Tên lớp", "Giáo viên CN", "Sỉ số"};
		dataModel = new DefaultTableModel(headers , 0);
		JScrollPane scroll;
		b6.add(scroll = new JScrollPane(table = new JTable(dataModel)));
		scroll.setBorder(BorderFactory.createTitledBorder("Danh sách lớp h�?c"));

		b7.add(Box.createHorizontalStrut(600));
		
		btnThem.addActionListener(this);
		btnLuu.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnTimGV.addActionListener(this);
		btnTimLop.addActionListener(this);
		table.addMouseListener(this);
		//Khi chương trình chạy, nạp toàn bộ danh sách lớp h�?c lên bảng
		Database.getInstance().connect();
		updateComboBox();
		updateTableData();
	
		
		
	}

	private void updateComboBox() {
		// TODO Auto-generated method stub
		GiaoVien_Dao dsgv = new GiaoVien_Dao();
		List<GiaoVien> list = dsgv.docTuBang();
		for(GiaoVien gv : list)
			cboGVCN.addItem(gv.getMaGV());
	}
	
	private void updateTableData() {
		// TODO Auto-generated method stub
		LopHoc_Dao dslh = new LopHoc_Dao();
		List<LopHoc> list = dslh.docTuBang();
		for(LopHoc lh : list) {
			String [] rowData = {lh.getMaLH(),lh.getTenLH(),lh.getGiaoVien().getMaGV(), lh.getSiSo()+""};
			dataModel.addRow(rowData);
		}	
		table.setModel(dataModel);
	}
	
	private LopHoc reverSPFromTextFile() {
	    String malop = txtMaLop.getText().trim();
	    String tenlop = txtTenLop.getText().trim();
	    String gcc = cboGVCN.getSelectedItem().toString().trim();
	    int siso = Integer.parseInt(txtSiSo.getText().trim());
	    return new LopHoc(malop, tenlop, new GiaoVien(gcc), siso);
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
	    int row = table.getSelectedRow();
	    if(row >= 0) {
	        txtMaLop.setText(dataModel.getValueAt(row, 0).toString());
	        txtTenLop.setText(dataModel.getValueAt(row, 1).toString());
	        cboGVCN.setSelectedItem(dataModel.getValueAt(row, 2).toString());
	        txtSiSo.setText(dataModel.getValueAt(row, 3).toString());
	    }
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(btnThem)) {
		LopHoc lh = reverSPFromTextFile();
			if(dslh.create(lh)) {
				Object[] rowData = {txtMaLop.getText(), txtTenLop.getText(),cboGVCN.getSelectedItem().toString(), txtSiSo.getText()};
				dataModel.addRow(rowData);
			}
		}
		
		else if(o.equals(btnLuu)) {
		    LopHoc lh = reverSPFromTextFile();
		    int confirm = JOptionPane.showConfirmDialog(this, "Bạn có muốn lưu lớp này không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
		    if(confirm == JOptionPane.YES_OPTION) {
		        if(dslh.create(lh)) {
		            Object[] rowData = {txtMaLop.getText(), txtTenLop.getText(), cboGVCN.getSelectedItem().toString(), txtSiSo.getText()};
		            dataModel.addRow(rowData);
		            JOptionPane.showMessageDialog(this, "Lưu thành công!");
		        } else {
		            JOptionPane.showMessageDialog(this, "Lưu thất bại!");
		        }
		    }
		}
		
	
		else if(o.equals(btnXoa)) {
		    int row = table.getSelectedRow();
		    if(row >= 0) {
		        String maLop = dataModel.getValueAt(row, 0).toString();
		        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa lớp này không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
		        if(confirm == JOptionPane.YES_OPTION) {
		            if(dslh.delete(maLop)) {
		                dataModel.removeRow(row);
		                JOptionPane.showMessageDialog(this, "Xóa thành công!");
		            } else {
		                JOptionPane.showMessageDialog(this, "Xóa thất bại!");
		            }
		        }
		    } else {
		        JOptionPane.showMessageDialog(this, "Vui lòng chọn lớp cần xóa!");
		    }
		}
		else if(o.equals(btnSua)) {
		    int row = table.getSelectedRow();
		    if(row >= 0) {
		        LopHoc lh = reverSPFromTextFile();
		        lh.setMaLH(dataModel.getValueAt(row, 0).toString()); // Đảm bảo không thay đổi mã lớp
		        if(dslh.update(lh)) {
		            dataModel.setValueAt(lh.getTenLH(), row, 1);
		            dataModel.setValueAt(lh.getGiaoVien().getMaGV(), row, 2);
		            dataModel.setValueAt(lh.getSiSo() + "", row, 3);
		            JOptionPane.showMessageDialog(this, "Sửa thành công!");
		        } else {
		            JOptionPane.showMessageDialog(this, "Sửa thất bại!");
		        }
		    } else {
		        JOptionPane.showMessageDialog(this, "Vui lòng chọn lớp cần sửa!");
		    }
		}
		else if(o.equals(btnTimLop)) {
		    String maLop = JOptionPane.showInputDialog(this, "Nhập mã lớp cần tìm:");
		    if(maLop != null && !maLop.trim().isEmpty()) {
		        List<LopHoc> list = dslh.getLopTheoMaLop(maLop.trim());
		        if(list.isEmpty()) {
		            JOptionPane.showMessageDialog(this, "Không tìm thấy lớp học có mã: " + maLop);
		        } else {
		            // Cập nhật bảng chỉ với lớp tìm được
		            dataModel.setRowCount(0); // Xóa dữ liệu cũ trong bảng
		            for(LopHoc lh : list) {
		                String[] rowData = {lh.getMaLH(), lh.getTenLH(), lh.getGiaoVien().getMaGV(), String.valueOf(lh.getSiSo())};
		                dataModel.addRow(rowData);
		            }
		        }
		    } else {
		        JOptionPane.showMessageDialog(this, "Mã lớp không được để trống!");
		    }
		    
		}
	}
	
		
}
