package com.example.springJwt.model;

public class AuthResponseLogin {
	 private String jwt;
	    private String message;
		private String accessLevel;
		 public AuthResponseLogin(String jwt, String message, String accessLevel) {
				super();
				this.jwt = jwt;
				this.message = message;
				this.accessLevel = accessLevel;
			}
		public String getJwt() {
			return jwt;
		}
		public void setJwt(String jwt) {
			this.jwt = jwt;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public String getAccessLevel() {
			return accessLevel;
		}
		public void setAccessLevel(String accessLevel) {
			this.accessLevel = accessLevel;
		}
		@Override
		public String toString() {
			return "AuthResponseLogin [jwt=" + jwt + ", message=" + message + ", accessLevel=" + accessLevel + "]";
		}
		
}
