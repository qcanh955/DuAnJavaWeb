package com.javaweb.service.admin;

import java.util.List;
import java.util.Optional;

import com.javaweb.entities.ChucNang;

public interface ChucNangService {

	void deleteById(Long id);

	List<String> maapi();

	List<ChucNang> findChucnangByTennguoidung(String tennguoidung);

	List<ChucNang> findByMachucnang(String machucnang);

	long count(Long id);

	int updateChucNang(ChucNang cn);

	int insertChucNang(ChucNang cn);

	List<ChucNang> findByTenchucnang(String tenchucnang);

	Optional<ChucNang> findById(Long id);

	int deleteChucNang(List<ChucNang> cn);

	Optional<ChucNang> findByChucNangEditId(Long id);

	List<ChucNang> getAllChucNangParent();

	List<ChucNang> getAllChucNang();

	List<ChucNang> findAllChucNang();

	List<ChucNang> findByTenchucnangContainingIgnoreCase(String tenchucnang);

	

	

}
