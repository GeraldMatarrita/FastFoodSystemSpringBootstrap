package com.proyecto.core.model;


import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "users")
@NoArgsConstructor
public class User implements Serializable {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Integer id;
   private String name;
   private String password;
   private String passwordConfirmed;
   private Boolean state = false;
   private String mail;
   private String askSecurity;
   private String answerSecurity;
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn
   private Role role = null;

   public User(String name, String password, String passwordConfirmed, String mail, String askSecurity, String answerSecurity) {
      this.name = name;
      this.password = password;
      this.passwordConfirmed = passwordConfirmed;
      this.mail = mail;
      this.askSecurity = askSecurity;
      this.answerSecurity = answerSecurity;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public Boolean isState() {
      return state;
   }

   public void setState(Boolean state) {
      this.state = state;
   }

   public String getPasswordConfirmed() {
      return passwordConfirmed;
   }

   public void setPasswordConfirmed(String passwordConfirmed) {
      this.passwordConfirmed = passwordConfirmed;
   }

   public String getMail() {
      return mail;
   }

   public void setMail(String mail) {
      this.mail = mail;
   }

   public String getAskSecurity() {
      return askSecurity;
   }

   public void setAskSecurity(String askSecurity) {
      this.askSecurity = askSecurity;
   }

   public String getAnswerSecurity() {
      return answerSecurity;
   }

   public void setAnswerSecurity(String answerSecurity) {
      this.answerSecurity = answerSecurity;
   }

   public Role getRole() {
      return role;
   }

   public void setRole(Role role) {
      this.role = role;
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   @Override
   public String toString() {
      return "User{" +
              "id=" + id +
              ", name='" + name + '\'' +
              ", password='" + password + '\'' +
              ", passwordConfirmed='" + passwordConfirmed + '\'' +
              ", state='" + state + '\'' +
              ", mail='" + mail + '\'' +
              ", askSecurity='" + askSecurity + '\'' +
              ", answerSecurity='" + answerSecurity + '\'' +
              ", role=" + role +
              '}';
   }
}
