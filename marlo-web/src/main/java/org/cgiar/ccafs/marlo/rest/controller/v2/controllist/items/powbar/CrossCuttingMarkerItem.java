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

package org.cgiar.ccafs.marlo.rest.controller.v2.controllist.items.powbar;

import org.cgiar.ccafs.marlo.data.manager.RepIndGenderYouthFocusLevelManager;
import org.cgiar.ccafs.marlo.data.model.RepIndGenderYouthFocusLevel;
import org.cgiar.ccafs.marlo.rest.dto.CrossCuttingMarkerDTO;
import org.cgiar.ccafs.marlo.rest.mappers.CrossCuttingMarkerMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Named
public class CrossCuttingMarkerItem<T> {


  private RepIndGenderYouthFocusLevelManager repIndGenderYouthFocusLevelManager;
  private CrossCuttingMarkerMapper crossCuttingMarkersMapper;

  @Inject
  public CrossCuttingMarkerItem(RepIndGenderYouthFocusLevelManager repIndGenderYouthFocusLevelManager,
    CrossCuttingMarkerMapper crossCuttingMarkersMapper) {
    this.repIndGenderYouthFocusLevelManager = repIndGenderYouthFocusLevelManager;
    this.crossCuttingMarkersMapper = crossCuttingMarkersMapper;
  }

  /**
   * Find a Cross Cutting Marker requesting a MARLO id
   * 
   * @param id
   * @return a CrossCuttingMarkersDTO with the Cross Cutting Marker data.
   */
  public ResponseEntity<CrossCuttingMarkerDTO> findCrossCuttingMarkerById(Long id) {
    RepIndGenderYouthFocusLevel repIndGenderYouthFocusLevel =
      repIndGenderYouthFocusLevelManager.getRepIndGenderYouthFocusLevelById(id);
    return Optional.ofNullable(repIndGenderYouthFocusLevel)
      .map(crossCuttingMarkersMapper::repIndGenderYouthFocusLevelToCrossCuttingMarkersDTO)
      .map(result -> new ResponseEntity<>(result, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }


  /**
   * Get All the Cross Cutting Markers Items *
   * 
   * @return a List of CrossCuttingMarkersDTO with all RepIndGenderYouthFocusLevel Items.
   */
  public List<CrossCuttingMarkerDTO> getAllCrossCuttingMarkers() {
    if (repIndGenderYouthFocusLevelManager.findAll() != null) {
      List<RepIndGenderYouthFocusLevel> repIndGenderYouthFocusLevels =
        new ArrayList<>(repIndGenderYouthFocusLevelManager.findAll());
      List<CrossCuttingMarkerDTO> crossCuttingMarkersDTOs = repIndGenderYouthFocusLevels.stream()
        .map(repIndGenderYouthFocusLevelsEntity -> crossCuttingMarkersMapper
          .repIndGenderYouthFocusLevelToCrossCuttingMarkersDTO(repIndGenderYouthFocusLevelsEntity))
        .collect(Collectors.toList());
      return crossCuttingMarkersDTOs;
    } else {
      return null;
    }
  }


}
