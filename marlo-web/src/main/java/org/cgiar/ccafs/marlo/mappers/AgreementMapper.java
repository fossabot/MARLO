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

import org.cgiar.ccafs.marlo.data.model.Agreement;
import org.cgiar.ccafs.marlo.data.model.dto.AgreementDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AgreementMapper {

  AgreementMapper INSTANCE = Mappers.getMapper(AgreementMapper.class);

  Agreement agreementDTOToAgreement(AgreementDTO agreement);

  // the idea is that map all the fields
  @Mappings({@Mapping(source = "id", target = "id"), @Mapping(source = "description", target = "description")})
  AgreementDTO agreementToAgreementDTO(Agreement agreement);

}
