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
import org.cgiar.ccafs.marlo.ocs.model.AgreementOCS;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AgreementMapper {

  AgreementMapper INSTANCE = Mappers.getMapper(AgreementMapper.class);


  @Mappings({@Mapping(source = "countries", target = "countriesAgreements"),
    @Mapping(source = "plas", target = "plasAgreements"), @Mapping(source = "crps", target = "crpsAgreements"),
    @Mapping(source = "originalDonor.id", target = "originalDonorId"),
    @Mapping(source = "originalDonor.name", target = "originalDonor"),
    @Mapping(source = "directDonor.id", target = "donorId"), @Mapping(source = "directDonor.name", target = "donor"),
    @Mapping(source = "researcher.id", target = "researchId"),
    @Mapping(source = "researcher.name", target = "reasearchName")})
  Agreement agreementDTOToAgreement(AgreementOCS agreement);

  @InheritInverseConfiguration
  AgreementOCS agreementToAgreementDTO(Agreement agreement);

}
