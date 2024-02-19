package com.elderus.elderusproject.global.oauth.provider;

public interface OAuth2UserInfo {

    String getProviderId();
    String getProvider();  // google, facebook
    String getEmail();
    String getName();
    String getGivenName();
    String getFamilyName();
}
