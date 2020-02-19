package com.liuchongyang.cms.domain;

import java.io.Serializable;

public class ShouChang implements Serializable {
	private Integer id;
	private String texts;
	private String url;
	private String user_id;
	private String created;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTexts() {
		return texts;
	}
	public void setTexts(String texts) {
		this.texts = texts;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public ShouChang() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ShouChang(Integer id, String texts, String url, String user_id, String created) {
		super();
		this.id = id;
		this.texts = texts;
		this.url = url;
		this.user_id = user_id;
		this.created = created;
	}
	@Override
	public String toString() {
		return "ShouChang [id=" + id + ", texts=" + texts + ", url=" + url + ", user_id=" + user_id + ", created="
				+ created + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((texts == null) ? 0 : texts.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		result = prime * result + ((user_id == null) ? 0 : user_id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShouChang other = (ShouChang) obj;
		if (created == null) {
			if (other.created != null)
				return false;
		} else if (!created.equals(other.created))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (texts == null) {
			if (other.texts != null)
				return false;
		} else if (!texts.equals(other.texts))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		if (user_id == null) {
			if (other.user_id != null)
				return false;
		} else if (!user_id.equals(other.user_id))
			return false;
		return true;
	}
}
