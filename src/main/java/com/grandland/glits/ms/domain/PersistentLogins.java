package com.grandland.glits.ms.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author yestin
 *
 */
@Entity
public class PersistentLogins {

	private String username;
	@Id
	private String series;
	
	private String token;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUsed;

	public PersistentLogins(String username, String series, String token,
			Date lastUsed) {
		super();
		this.username = username;
		this.series = series;
		this.token = token;
		this.lastUsed = lastUsed;
	}

	public PersistentLogins() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getLastUsed() {
		return lastUsed;
	}

	public void setLastUsed(Date lastUsed) {
		this.lastUsed = lastUsed;
	}

	@Override
	public String toString() {
		return "PersistentLogins [username=" + username + ", series=" + series
				+ ", token=" + token + ", lastUsed=" + lastUsed + "]";
	}
	
}
