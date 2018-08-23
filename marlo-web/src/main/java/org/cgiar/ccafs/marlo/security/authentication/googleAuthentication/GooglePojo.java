package org.cgiar.ccafs.marlo.security.authentication.googleAuthentication;

public class GooglePojo {

  String id;
  String email;
  boolean verified_email;
  String name;
  String given_name;
  String family_name;

  public String getEmail() {
    return this.email;
  }

  public String getFamily_name() {
    return this.family_name;
  }

  public String getGiven_name() {
    return this.given_name;
  }

  public String getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public boolean isVerified_email() {
    return this.verified_email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setFamily_name(String family_name) {
    this.family_name = family_name;
  }

  public void setGiven_name(String given_name) {
    this.given_name = given_name;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setVerified_email(boolean verified_email) {
    this.verified_email = verified_email;
  }

  @Override
  public String toString() {
    return

    "GooglePojo [id=" + this.id + ", email=" + this.email + ", verified_email=" + this.verified_email + ", name="
      + this.name + ", given_name=" + this.given_name + ", family_name=" + this.family_name + "]";
  }
}
