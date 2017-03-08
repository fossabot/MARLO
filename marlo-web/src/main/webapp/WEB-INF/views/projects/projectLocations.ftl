[#ftl]
[#assign title = "Project Locations" /]
[#assign currentSectionString = "project-${actionName?replace('/','-')}-${projectID}" /]
[#assign pageLibs = ["select2"] /]
[#assign customJS = ["${baseUrl}/js/projects/projectLocations.js", "${baseUrl}/js/global/autoSave.js","${baseUrl}/js/global/fieldsValidation.js"] /] [#-- "${baseUrl}/js/global/autoSave.js" --]
[#assign customCSS = ["${baseUrl}/css/projects/projectLocations.css" ] /]
[#assign currentSection = "projects" /]
[#assign currentStage = "locations" /]
[#assign hideJustification = false /]

[#assign breadCrumb = [
  {"label":"projectsList", "nameSpace":"/projects", "action":"${(crpSession)!}/projectsList"},
  {"label":"projectLocations", "nameSpace":"/projects", "action":""}
] /]

[#assign locationLevelName = "project.locationsData"/]
[#assign locationName = "locElements"/]
[#assign countID = 0/]

[#include "/WEB-INF/global/pages/header.ftl" /]
[#include "/WEB-INF/global/pages/main-menu.ftl" /]

<div class="container helpText viewMore-block">
  <div  class="helpMessage infoText">
    <img class="col-md-2" src="${baseUrl}/images/global/icon-help.jpg" />
    <p class="col-md-10">[#if reportingActive] [@s.text name="projectLocations.help2" /] [#else] [@s.text name="projectLocations.help1" /] [/#if] </p>
  </div> 
  <div style="display:none" class="viewMore closed"></div>
</div>
    
<section class="container">
    <div class="row">
      [#-- Project Menu --]
      <div class="col-md-3">
        [#include "/WEB-INF/views/projects/menu-projects.ftl" /]
      </div>
      [#-- Project Section Content --]
      <div class="col-md-9">
        [#-- Section Messages --]
        [#include "/WEB-INF/views/projects/messages-projects.ftl" /]
      
        [@s.form action=actionName method="POST" enctype="multipart/form-data" cssClass=""]
           
          
          <div class="row">
          <h3 class="headTitle col-md-7">[@s.text name="projectLocations.title" /]</h3>  
              <div class="col-md-5 isGlobal">
              <br />
              [#if editable]
              <label class="col-md-10" for=""><span id="globalText">[@s.text name="projectLocations.isGlobal" /]</span><span id="globalHelp">(If the project is global, check the next button)</span></label>
              <div class=" col-md-2">
              <input id="" class="" type="checkbox" name="project.locationGlobal" value=[#if project.locationGlobal]"true" checked[#else]"false"[/#if]/>
              <label for=""></label>
              </div>
              [#else]
              <h4 style="text-align:center; display: inline-block">
                [#if (project.locationGlobal?has_content)?string('true','false')=="true"]
                  <label for="">[@s.text name="projectLocations.isGlobalYes" /]</label>
                [#else]
                  <label>[@s.text name="projectLocations.isGlobalNo" /]</label>
                [/#if]
              </h4>
              [/#if]
            </div>
            [#--  
            <div id="view2" title="view 2" class="btn-primary  view" ><img src="${baseUrl}/images/global/layout-icon2.png" alt="Layout2" /></div>
            <div id="view1" title="view 1" class="btn-primary  view" ><img src="${baseUrl}/images/global/layout-icon.png" alt="Layout1" /></div>
            --]
          </div>
          <div id="" class="borderBox projectLocationsWrapper">
            [#-- Content--]
              
              [#-- [#if editable]
              <div class="text-center col-md-12  alert alert-info"><span> [@s.text name="projectLocations.selectLocations" /] </span></div>
              [/#if]
               --]
              <div class="row">
                <div  class="col-md-12 map">
                  <div id="map" class="col-md-12"></div>
                </div>
                
                <div class="col-md-12">
                <label for="">Locations list</label>
                <div id="selectsContent" class="col-md-12 simpleBox " listname="project.locationsData">
                  [#-- Content collapsible--]
                  <div class="selectWrapper row">
                  [#if project.locationsData?has_content]
                    [#list project.locationsData as locationLevels]
                      [@locationLevel element=locationLevels name="${locationLevelName}" index=locationLevels_index list=locationLevels.list?? && locationLevels.list /]
                    [/#list]
                  [/#if]
                  
                  </div>
                </div>
                </div>
              </div>
              
            [#--[#if editable]
             locations level Select 
            <label for="locLevelSelect">Select to add a location level:</label>
            <select name="" id="locLevelSelect" class="selectLocationLevel select " >
            <option value="-1" >Select an option...</option>
              [#list locationsLevels as locLevels]
                [#list locLevels.locations as locations]
                  <option value="${locations.id}-${locations.list?string}-${locations.name}" >${locations.name}</option>
                [/#list]
              [/#list]
            </select>
            [/#if]
            --]
          </div> 
          
          [#include "/WEB-INF/views/projects/buttons-projects.ftl" /]
             
         
          [/@s.form] 
      </div>
    </div>  
</section>

[#--<script src="https://maps.googleapis.com/maps/api/js?key=${config.googleApiKey}&callback=initMap"></script>--]

[#-- Section hidden inputs--]
[@locationLevel element={} name="" index=0 template=true /]

[@locationMacro element={} name="" index=0 template=true /]

<input type="hidden" id="locationLevelName" value="${locationLevelName}" />
<input type="hidden" id="locationName" value="${locationName}" />

[#-- INFO WINDOW FORM - TEMPLATE --]
<div id="infoWrapper" class="infoWrapper" style="display:none;">
<h4 class="sectionSubTitle" style="text-align:center; width:450px;">Adding a new location</h4>
<br />
<div class="form-group">
<label for="locLevelSelect" style="display:block;">Select a location level:</label>
  <select name="" id="locLevelSelect" class="selectLocationLevel select " >
    <option value="-1" >Select an option...</option>
    [#list locationsLevels as locLevels]
      [#list locLevels.locations as locations]
        <option value="${locations.id}-${locations.list?string}-${locations.name}" >${locations.name}</option>
      [/#list]
    [/#list]
  </select>
  </div>
  <div class=" form-group yesnoQuestion" style="display:none">
    [@customForm.yesNoInput name="isBranch" label="Is this project working in all locations?"  inverse=false value="" cssClass="text-left " value="true" /]
  </div>
  <div class="selectLocations panel tertiary col-md-12"  style="display:none">
    <div class="panel-body">
      [@customForm.select name="" label="" required=true  i18nkey="Select location(s)" multiple=true listName="" keyFieldName="id"  displayFieldName="composedName" className="" value="" /]
    </div>
  </div>
  [#-- Form 2 --]
  <div id="inputFormWrapper" style="display:none; width: 450px;">
    <div class="nameWrapper"><label for="">Location name:</label><input placeholder="name (Required)" class="name form-control" type="text" /></div>
    <div class="latitudeWrapper"><label for="">Latitude:</label><input placeholder="Latitude" class="latitude form-control" type="text" readOnly=true /></div>
    <div class="longitudeWrapper"><label for="">Longitude:</label><input placeholder="Longitude" class="longitude form-control " type="text" readOnly=true /></div>
  </div>
  [#-- Button --]
  <div>
    <span id="addLocationButton" class=" addButton pull-right" style="display:none; margin-top:10px; border-radius:8px;">[@s.text name="Map out" /]</span>
  </div>
</div>
  
[#include "/WEB-INF/global/pages/footer.ftl"]

[#macro locationLevel element  name index template=false list=false]
  [#local customName = "${name}[${index}]" /]
  [#-- Content collapsible--]
  <div id="locationLevel-${template?string('template',index)}" class="locationLevel col-md-12" style="display:${template?string('none','block')}">
    [#-- header element --]
    <h5 class="sectionSubTitle">${(element.name)!}:
    [#-- Remove --]
   [#if editable]<span class="listButton remove removeLocationLevel pull-right" style="padding: 0 5px 3px 5px;">[@s.text name="form.buttons.remove" /] location level</span>[/#if]
    </h5>
    <div class=" locationLevel-optionContent " listname="${customName}.locElements">
      <span class="allCountriesQuestion" style="display:${list?string('inline-block','none')}">
        <span class="">[@s.text name="Is this project working on all " /][#if element?has_content && element.name?contains("Country")]countries[#else]climate smart village sites[/#if] ?</span>
        [@customForm.yesNoInput name="${customName}.allCountries"  editable=editable inverse=false  cssClass="allCountries text-center" /]
        <hr />
      </span>
      [#-- Content of locations--]
      <div class="optionSelect-content row">
        [#if element.locElements?has_content]
          [#list element.locElements as location]
            [@locationMacro element=location name="${customName}.${locationName}" index=location_index isList=list template=element.allCountries /]
          [/#list]
        [/#if]
      </div>
      [#if !editable]
      <select style="display:${(element.list?? && element.list)?string('block','none')}" [#if (element.allCountries?has_content)?string == "true"]disabled[/#if]  class="form-control selectLocation col-md-12" placeholder="select an option...">
        <option selected="selected" value="-1" >Select a location</option>
        [#if element.allElements?has_content ]
        [#list element.allElements as locElements]
            <option value="${locElements.id}-${(locElements.isoAlpha2)!}" >${locElements.name}</option>
        [/#list]
        [/#if]
      </select>
        <div class=" coordinates-inputs" style="display:${(element.list?? && !element.list)?string('block','none')}">
          <div class="nameWrapper"><input placeholder="name (Required)" class="name form-control" type="text" /></div>
          <div class="latitudeWrapper"><input placeholder="Latitude" class="latitude form-control" type="text" /></div>
          <div class="longitudeWrapper"><input placeholder="Longitude" class="longitude form-control " type="text" /></div>
          <div class="buttonBlock addLocation text-right"><div class="button-blue"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span> [@s.text name="form.buttons.addLocation"/]</div></div>
        </div>
        [/#if]
    </div>
    <input class="locationLevelId" type="hidden" name="${locationLevelName}[${index}].id" value="${(element.id)!}"/>
    <input class="locationLevelName" type="hidden" name="${locationLevelName}[${index}].name" value="${(element.name)!}"/>
    <input type="hidden" class="isList" name="${customName}.isList"  value="${(list)?string}"/>
  </div>
[/#macro]

[#macro locationMacro element  name index template=false isList=false ]
  [#local customName = "${name}[${index}]" /]
  [#assign countID = countID+1/]
  [#-- Content collapsible--]
  <div id="location-${template?string('template',countID)}" class="col-md-4 locElement" style="display:${template?string('none','block')}">
    <div class="locations col-md-12">
      <div class="locationName"><span class="lName">${(element.name)!}</span> [#if element.locGeoposition?? && element.locGeoposition.latitude?? && element.locGeoposition.longitude?? && element.locGeoposition.latitude!=0 && element.locGeoposition.longitude!=0] <span class="lPos">[#if isList!=true](${(element.locGeoposition.latitude)!}, ${(element.locGeoposition.longitude)!})[/#if]</span> [/#if] </div>
      [#if editable]
      <div class="removeLocation removeIcon" title="Remove Location"></div>
      [/#if]
    </div>
    [#-- Hidden inputs --]
    <input type="hidden" class="locElementId" name="${customName}.id" value="${(element.id)!}"/>
    <input type="hidden" class="locElementName" name="${customName}.name" value="${(element.name)!}" />
    <input type="hidden" class="locElementCountry" name="${customName}.locElement.isoAlpha2" value="${(element.isoAlpha2)!}" />
    <input type="hidden" class="geoId" name="${customName}.locGeoposition.id"  value="${(element.locGeoposition.id)!}" />
    
    <input type="hidden" class="geoLatitude" name="${customName}.locGeoposition.latitude"  value="${(element.locGeoposition?? && element.locGeoposition.latitude?? && element.locGeoposition.latitude!=0)?string((element.locGeoposition.latitude?c)!,'')}" /> 
    <input type="hidden" class="geoLongitude" name="${customName}.locGeoposition.longitude"  value="${(element.locGeoposition?? && element.locGeoposition.longitude?? && element.locGeoposition.longitude!=0)?string((element.locGeoposition.longitude?c)!,'')}" />
  </div>
[/#macro]
