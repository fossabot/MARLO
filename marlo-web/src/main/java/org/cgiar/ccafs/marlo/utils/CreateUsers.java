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


package org.cgiar.ccafs.marlo.utils;

import org.cgiar.ciat.auth.LDAPService;
import org.cgiar.ciat.auth.LDAPUser;

public class CreateUsers {

  public static void main(String[] args) {


    LDAPUser user = null;
    try {
      user = new LDAPService(true).searchUserByEmail("r.islas@cgiar.org");
    } catch (Exception e) {
      user = null;
    }

    if (user != null) {
      System.out.println("First name : " + user.getFirstName());
      System.out.println("Last name : " + user.getLastName());
      System.out.println("User Login : " + user.getLogin().toLowerCase());
      System.out.println("User Email : " + user.getEmail().toLowerCase());
    } else {
      System.out.println("User is NULL");
    }

  }


}
