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


package org.cgiar.ccafs.marlo.data.model.dto;

/**
 * a simple DTO class for PlaAgreement
 * 
 * @author Julián Rodríguez CCAFS/CIAT
 * @date 20171012
 */

public class PlaAgreementDTO {

  private Long id;
  private AgreementDTO agreement;
  private String plaId;
  private String description;
  private Double ammount;

  public AgreementDTO getAgreement() {
    return agreement;
  }

  public Double getAmmount() {
    return ammount;
  }

  public String getDescription() {
    return description;
  }

  public Long getId() {
    return id;
  }

  public String getPlaId() {
    return plaId;
  }

  public void setAgreement(AgreementDTO agreement) {
    this.agreement = agreement;
  }

  public void setAmmount(Double ammount) {
    this.ammount = ammount;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setPlaId(String plaId) {
    this.plaId = plaId;
  }


}
