/*****************************************************************
 * This file is part of Managing Agricultural Research for Learning &
 * Outcomes Platform (MARLO).
 * MARLO is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * at your option) any later version.
 * MARLO is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with MARLO. If not, see <http://www.gnu.org/licenses/>.
 *****************************************************************/

package org.cgiar.ccafs.marlo.data.model;
// Generated May 17, 2016 9:53:46 AM by Hibernate Tools 4.3.1.Final

import com.google.gson.annotations.Expose;

/**
 * UserRoles generated by hbm2java
 */
public class UserRole extends MarloBaseEntity implements java.io.Serializable {


  private static final long serialVersionUID = -4464617364263332726L;

  private Role role;

  @Expose
  private User user;

  public UserRole() {
  }

  public UserRole(Role roles, User users) {
    this.role = roles;
    this.user = users;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }

    UserRole other = (UserRole) obj;
    if (role == null) {
      if (other.role != null) {
        return false;
      }
    } else if (!role.getId().equals(other.getRole().getId())) {
      return false;
    }
    if (user == null) {
      if (other.user != null) {
        return false;
      }
    } else if (!user.getId().equals(other.getUser().getId())) {
      return false;
    }
    return true;
  }

  public Role getRole() {
    return this.role;
  }

  public User getUser() {
    return this.user;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((role == null) ? 0 : role.hashCode());
    result = prime * result + ((user == null) ? 0 : user.hashCode());
    return result;
  }

  public void setRole(Role roles) {
    this.role = roles;
  }

  public void setUser(User users) {
    this.user = users;
  }

  @Override
  public String toString() {
    return "UserRole [id=" + this.getId() + ", role=" + role + ", user=" + user + "]";
  }
}

