package com.javaweb.service.admin;

import java.util.List;
import java.util.Optional;

import com.javaweb.entities.ChucNang;
import com.javaweb.entities.VaiTro;

public interface VaiTroService {

	void deleteById(Long id);

	List<Long> findChucnangVaitro(Long idvaitro);

	List<VaiTro> findByTenvaitro(String tenvaitro);

	List<VaiTro> findByMavaitro(String mavaitro);

	Optional<VaiTro> findByVaitroId(Long id);

	void updateVaitro(VaiTro vt);

	List<VaiTro> listVaiTro();

	void insertVaitro(VaiTro vt);

	List<ChucNang> findAllChucNang();

	



}
