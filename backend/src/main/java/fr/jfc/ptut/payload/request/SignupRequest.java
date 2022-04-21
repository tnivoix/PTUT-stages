package fr.jfc.ptut.payload.request;

import java.util.Set;

import javax.validation.constraints.*;

public class SignupRequest {
  @NotBlank
  private String username;

  @NotBlank
  @Email
  private String email;

  @NotBlank
  private Set<String> roles;

  @NotBlank
  private String name;

  @NotBlank
  private String firstName;

  @NotBlank
  private String numTel;

  @NotBlank
  private String password;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getNumTel() {
    return this.numTel;
  }

  public void setNumTel(String numTel) {
    this.numTel = numTel;
  }

  public Set<String> getRoles() {
    return this.roles;
  }

  public void setRoles(Set<String> roles) {
    this.roles = roles;
  }
}