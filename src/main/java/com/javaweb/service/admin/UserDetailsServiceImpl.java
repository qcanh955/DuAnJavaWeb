package com.javaweb.service.admin;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.javaweb.entities.NguoiDung;
import com.javaweb.entities.VaiTro;
 
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
 
//    @Autowired
//    private NguoiDungDAO nguoiDungDAO;
// 
//    @Autowired
//    private VaiTroDAO vaiTroDAO;
	@Autowired
	UserService userService;
	
//	@Autowired
//	VaiTroService vaiTroService;
 
    @Override
    public UserDetails loadUserByUsername(String tennguoidung) throws UsernameNotFoundException {
//        NguoiDung nguoiDung = this.nguoiDungDAO.findUserAccount(tennguoidung);
    	NguoiDung nguoiDung = this.userService.findByTen(tennguoidung);
        if (nguoiDung == null) {
            System.out.println("Không tìm thấy nguời dùng " + tennguoidung);
            throw new UsernameNotFoundException("Người dùng " + tennguoidung + " không thể tìm thấy ở trong kho dữ liệu");
        };
 
//        System.out.println("Không tìm thấy người dùng: " + nguoiDung);
 
        // [ROLE_USER, ROLE_ADMIN,..]
//        List<VaiTro> roleNames = this.vaiTroService.findByTenvaitro(nguoiDung.getManguoidung());
// 
//        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
//        if (roleNames != null) {
//            for (String role : roleNames) {
//                // ROLE_USER, ROLE_ADMIN,..
//                GrantedAuthority authority = new SimpleGrantedAuthority(role);
//                grantList.add(authority);
//            }
//        }
// 
//        UserDetails userDetails = (UserDetails) new User(nguoiDung.getTennguoidung(), //
//                nguoiDung.getPassword(), grantList);
 
//        return userDetails;
//        return new MyUserDetails(nguoiDung);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        List<VaiTro> roles = nguoiDung.getVaitro();
        for (VaiTro role : roles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getTenvaitro()));
        }

        return new org.springframework.security.core.userdetails.User(
                nguoiDung.getTennguoidung(), nguoiDung.getPassword(), grantedAuthorities);
    }
 
}
