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


package org.cgiar.ccafs.marlo.mappers;

import org.cgiar.ccafs.marlo.data.model.CrpAgreement;
import org.cgiar.ccafs.marlo.ocs.model.CrpOCS;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CrpAgreementMapper {

  CrpAgreementMapper INSTANCE = Mappers.getMapper(CrpAgreementMapper.class);

  @Mappings({@Mapping(target = "crpId", source = "id"), @Mapping(target = "id", source = "consecutive"),
    @Mapping(target = "description", source = "description"), @Mapping(target = "percentage", source = "percentage")})
  CrpAgreement crpAgreementDTOToCrpAgreement(CrpOCS crpAgreement);

  @InheritInverseConfiguration
  CrpOCS crpAgreementToCrpAgreementDTO(CrpAgreement crpAgreement);


}
