package db;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class APIEntity {

	@Id
	private String api;
	@Lob
	private byte[] impl;

	public byte[] getImpl() {
		return impl;
	}

	public void setImpl(byte[] impl) {
		this.impl = impl;
	}

	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
	}

	@Override
	public String toString() {
		// return "function " + api + System.lineSeparator() + "{" +
		// System.lineSeparator() + " " + impl + System.lineSeparator() + "}";

		return "function " + api + System.lineSeparator() + new String(impl);
	}
}
