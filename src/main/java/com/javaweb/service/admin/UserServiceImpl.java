package com.javaweb.service.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.common.Handle;
import com.javaweb.entities.ChucNang;
import com.javaweb.entities.NguoiDung;
import com.javaweb.entities.VaiTro;
import com.javaweb.repository.ChucNangRepository;
import com.javaweb.repository.NguoiDungRepository;
import com.javaweb.repository.VaiTroRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	NguoiDungRepository nguoiDungRepository;

	@Autowired
	ChucNangRepository chucNangRepository;

	@Autowired
	ChucNangService chucNangService;

	@Autowired
	VaiTroRepository vaiTroRepository;

	@Override
	public List<ChucNang> finAllChucNang() {
		return this.chucNangService.findAllChucNang();
	}

	@Override
	public List<VaiTro> finAllVaiTro() {
		return this.vaiTroRepository.listVaiTro();
	}

	@Override
	public void insertNguoiDung(NguoiDung nd) {
		this.nguoiDungRepository.insertNguoiDung(nd.getId(), nd.getManguoidung(), nd.getTennguoidung(),
				Handle.resolutionEncrypt(nd.getPassword()), nd.getEmail(), nd.getGender(), nd.getPhone(), nd.getCreateday(),
				nd.getNguoitao(), nd.getUpdateday(), nd.getNguoiupdate(), nd.getIsdelete());

		if (nd.getVaitro() != null) {
			for (VaiTro vt : nd.getVaitro()) {
				this.nguoiDungRepository.insertNguoiDungVaVaitro(nd.getId(), vt.getId());
			}
		}

	}

	@Override
	public void updateNguoiDung(NguoiDung nd) {
		this.nguoiDungRepository.updateNguoiDung(nd.getManguoidung(), nd.getTennguoidung(), nd.getPassword(),
				nd.getEmail(), nd.getGender(), nd.getPhone(), nd.getUpdateday(), nd.getNguoiupdate(), nd.getIsdelete(),
				nd.getId());
		this.nguoiDungRepository.deleteNguoiDungVaVaitro(nd.getId());

		if (nd.getVaitro() != null) {
			for (VaiTro vt : nd.getVaitro()) {
				this.nguoiDungRepository.insertNguoiDungVaVaitro(nd.getId(), vt.getId());
			}
		}

	}

	@Override
	public NguoiDung findByTen(String tennguoidung) {
		return nguoiDungRepository.findByTen(tennguoidung);
	}

	@Override
	public void deleteNguoiDung(NguoiDung nd) {
		this.nguoiDungRepository.updateNguoiDung(nd.getManguoidung(), nd.getTennguoidung(), nd.getPassword(),
				nd.getEmail(), nd.getGender(), nd.getPhone(), nd.getUpdateday(), nd.getNguoiupdate(), nd.getIsdelete(),
				nd.getId());
	}

	@Override
	public Optional<NguoiDung> findNguoiDungById(Long id) {
		return nguoiDungRepository.findNguoiDungById(id);
	}

	@Override
	public List<NguoiDung> getAllNguoiDung() {
		return nguoiDungRepository.getAllNguoiDung();
	}

	@Override
	public List<NguoiDung> findByTennguoidung(String tennguoidung) {
		return this.nguoiDungRepository.findByTennguoidung(tennguoidung);
	}

	@Override
	public NguoiDung findUrlChucNang(String tennguoidung) {
		NguoiDung nd = this.findByTen(tennguoidung);

		return nd;
	}



	@Override
	public List<Long> findByIdvaitro(Long idnguoidung) {
		return nguoiDungRepository.findByIdvaitro(idnguoidung);
	}

	@Override
	public NguoiDung findById1(Long id) {
		return nguoiDungRepository.findById1(id);
	}



	@Override
	public NguoiDung findUrl(String tennguoidung) {
		return nguoiDungRepository.findUrl(tennguoidung, tennguoidung);
	}

	@Override
	public List<String> findUrlNd(String tennguoidung) {
		return nguoiDungRepository.findUrlNd(tennguoidung);
	}

	@Override
	public void deleteById(Long id) {
		nguoiDungRepository.findById(id);
	}

	
	
}
