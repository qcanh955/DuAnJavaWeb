package com.javaweb.service.admin;

import java.util.List;
import java.util.Optional;

import com.javaweb.entities.ChucNang;
import com.javaweb.entities.NguoiDung;
import com.javaweb.entities.VaiTro;

public interface UserService {

	void deleteById(Long id);

	List<String> findUrlNd(String tennguoidung);

	NguoiDung findUrl(String tennguoidung);

	NguoiDung findById1(Long id);

	List<Long> findByIdvaitro(Long idnguoidung);

	NguoiDung findUrlChucNang(String tennguoidung);

	List<NguoiDung> findByTennguoidung(String tennguoidung);

	List<NguoiDung> getAllNguoiDung();

	Optional<NguoiDung> findNguoiDungById(Long id);

	void deleteNguoiDung(NguoiDung nd);

	NguoiDung findByTen(String tennguoidung);

	void updateNguoiDung(NguoiDung nd);

	void insertNguoiDung(NguoiDung nd);

	List<VaiTro> finAllVaiTro();

	List<ChucNang> finAllChucNang();

	

	

}
