package com.jin.auth;



import org.apache.commons.lang3.StringUtils;

import com.github.scribejava.apis.GoogleApi20;
import com.github.scribejava.core.builder.api.DefaultApi20;

import lombok.Data;

@Data
public class SnsValue implements SnsUrls{
	
		private String service;
		private String clientId;
		private String clientSecret;
		private String rediretUrl;
		private DefaultApi20 api20Instance;
		private String profileUrl;
		
		public SnsValue(String service, String clientId, String clientSecret, String rediretUrl) {
			this.service = service;
			this.clientId = clientId;
			this.clientSecret = clientSecret;
			this.rediretUrl = rediretUrl;
			if(StringUtils.equalsIgnoreCase(service, "naver")) {
				this.api20Instance = NaverAPI20.Instance();
				this.profileUrl = NAVER_PROFILE_URL;
			}else if(StringUtils.equalsIgnoreCase(service, "google")){
				this.api20Instance = GoogleApi20.instance();
				this.profileUrl = GOOGLE_PROFILE_URL;
			}
		}

		public String getService() {
			return service;
		}

		public void setService(String service) {
			this.service = service;
		}

		public String getClientId() {
			return clientId;
		}

		public void setClientId(String clientId) {
			this.clientId = clientId;
		}

		public String getClientSecret() {
			return clientSecret;
		}

		public void setClientSecret(String clientSecret) {
			this.clientSecret = clientSecret;
		}

		public String getRediretUrl() {
			return rediretUrl;
		}

		public void setRediretUrl(String rediretUrl) {
			this.rediretUrl = rediretUrl;
		}

		public DefaultApi20 getApi20Instance() {
			return api20Instance;
		}

		public void setApi20Instance(DefaultApi20 api20Instance) {
			this.api20Instance = api20Instance;
		}

		public String getProfileUrl() {
			return profileUrl;
		}

		public void setProfileUrl(String profileUrl) {
			this.profileUrl = profileUrl;
		}

		@Override
		public String toString() {
			return "SnsValue [service=" + service + ", clientId=" + clientId + ", clientSecret=" + clientSecret
					+ ", rediretUrl=" + rediretUrl + ", api20Instance=" + api20Instance + ", profileUrl=" + profileUrl
					+ "]";
		}
		
		
		
}
