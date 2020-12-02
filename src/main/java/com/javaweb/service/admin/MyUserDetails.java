package com.javaweb.service.admin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.javaweb.entities.ChucNang;
import com.javaweb.entities.NguoiDung;


public class MyUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
private NguoiDung nguoidung;

Set<GrantedAuthority> authorities=null;
	
	
	public MyUserDetails(NguoiDung nguoidung) {
		this.nguoidung = nguoidung;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (ChucNang privilege : nguoidung.getVaitro().get(0).getChucnang()) {
            authorities.add(new SimpleGrantedAuthority(privilege.getMachucnang()));
        }
        return authorities;
	}
	
	 public void setAuthorities(Set<GrantedAuthority> authorities)
	    {
	        this.authorities=authorities;
	    }
	
	@Override
	public String getPassword() {
		return nguoidung.getPassword();
	}

	@Override
	public String getUsername() {
		return nguoidung.getTennguoidung();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
