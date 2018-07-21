package com.s3a.poc.web.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUser implements UserDetails{


	private String username;
	private String password;
	
	private String utmSource;
	private String utmCampaign;
	private String utmMedium;
	private String lang;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	
	public void setUsername(String username) {
		this.username = username;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	
	public String getPassword() {
		return password;
	}

	
	
	public String getUtmSource() {
		return utmSource;
	}


	public void setUtmSource(String utmSource) {
		this.utmSource = utmSource;
	}


	public String getUtmCampaign() {
		return utmCampaign;
	}


	public void setUtmCampaign(String utmCampaign) {
		this.utmCampaign = utmCampaign;
	}


	public String getUtmMedium() {
		return utmMedium;
	}


	public void setUtmMedium(String utmMedium) {
		this.utmMedium = utmMedium;
	}

	

	public String getLang() {
		return lang;
	}


	public void setLang(String lang) {
		this.lang = lang;
	}


	@Override
	public String getUsername() {
		return username;
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
