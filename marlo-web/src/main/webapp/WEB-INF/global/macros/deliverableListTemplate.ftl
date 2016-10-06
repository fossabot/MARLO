[#ftl]
[#import "/WEB-INF/global/macros/utils.ftl" as utilities/]

[#macro deliverablesList deliverables={} owned=true canValidate=false canEdit=false isPlanning=false namespace="/" defaultAction=""]
  <table class="deliverableList" id="deliverables">
    <thead>
      <tr class="subHeader">
        <th id="ids">[@s.text name="projectsList.projectids" /]</th>
        <th id="deliverableTitles" >[@s.text name="project.deliverableList.deliverableName" /]</th>
        <th id="deliverableType">[@s.text name="project.deliverableList.type" /]</th>
        <th id="deliverableEDY">[@s.text name="project.deliverableList.expectedYear" /]</th>
        <th id="deliverableFC">[@s.text name="project.deliverableList.fairCompliance" /]</th>
        <th id="deliverableStatus">[@s.text name="project.deliverableList.status" /]</th>
        <th id="deliverableRF">[@s.text name="project.deliverableList.requiredFields" /]</th>
        <th id="deliverableDelete">[@s.text name="projectsList.delete" /]</th>
      </tr>
    </thead>
    <tbody>
    [#if deliverables?has_content]
      [#list deliverables as deliverable]
        <tr>
        [#-- ID --]
        <td class="deliverableId">
          <a href="[@s.url namespace=namespace action=defaultAction][@s.param name='deliverableID']${deliverable.id?c}[/@s.param][@s.param name='edit']true[/@s.param][/@s.url]">D${deliverable.id}</a>
        </td>
          [#-- Deliverable Title --]
          <td class="left"> 
            [#if deliverable.title?has_content]
              <a href="[@s.url namespace=namespace action=defaultAction] [@s.param name='deliverableID']${deliverable.id?c}[/@s.param][@s.param name='edit']true[/@s.param][/@s.url]" title="${deliverable.title}">
              [#if deliverable.title?length < 120] ${deliverable.title}</a> [#else] [@utilities.wordCutter string=deliverable.title maxPos=120 /]...</a> [/#if]
            [#else]
              <a href="[@s.url namespace=namespace action=defaultAction includeParams='get'] [@s.param name='deliverableID']${deliverable.id?c}[/@s.param][@s.param name='edit']true[/@s.param][/@s.url] ">
                [@s.text name="projectsList.title.none" /]
              </a>
            [/#if]
          </td>
          [#-- Deliverable Type --]
          <td >
            ${(deliverable.deliverableType.name?lower_case)!'none'}
          </td>
          [#-- Deliverable Year --]
          <td class="text-center">
            ${(deliverable.year)!'none'}
          </td>
          [#-- Deliverable FAIR compliance --]
          <td class="fair">
            <span class="${(findable?string('findable'))!} ">F</span>
            <span class="${(accesible?string('accesible'))!} ">A</span>
            <span class="${(interoperable?string('interoperable'))!} ">I</span>
            <span class="${(reusable?string('reusable'))!} ">R</span>
          </td>
          [#-- Deliverable Status --]
          <td>
            [#attempt]
              ${(deliverable.statusName)!'none'}
            [#recover]
              none
            [/#attempt]
          </td>
          [#-- Deliverable required fields --]
          <td class="text-center">
            <span class="icon-20 icon-uncheck" title=""></span> 
          </td>
          [#-- Delete Deliverable--]
          <td class="text-center">
            [#--if (action.hasProjectPermission("deleteProject", project.id, "manage") && project.isNew(currentPlanningStartDate)) --]
            [#if true]
              <a id="removeDeliverable-${deliverable.id}" class="removeDeliverable" href="${baseUrl}/projects/${crpSession}/deleteDeliverable.do?deliverableID=${deliverable.id}" title="">
                <img src="${baseUrl}/images/global/trash.png" title="[@s.text name="project.deliverable.removeDeliverable" /]" /> 
              </a>
            [#else]
              <img src="${baseUrl}/images/global/trash_disable.png" title="[@s.text name="project.deliverable.cannotDelete" /]" />
            [/#if]
          </td>
        </tr>  
      [/#list]
      [/#if]
    </tbody>
  </table>
[/#macro]

[#macro deliverablePartner dp={} dp_name="" dp_index="" isResponsable=false template=false editable=true]
  <div id="deliverablePartner-${template?string('template', dp_index)}" class="${isResponsable?string('responsiblePartner','deliverablePartner')} ${isResponsable?string('simpleBox','borderBox')} row" style="display:${template?string('none','')}">
    [#if editable && !isResponsable]
      <div class="removeElement removeLink" title="[@s.text name="project.deliverable.removePartnerContribution" /]"></div> 
    [/#if]
    [#if !isResponsable]
    <div class="leftHead">
      <span class="index">${dp_index+1}</span>
    </div> 
    [/#if]
    [#assign customName]${dp_name}[#if !isResponsable][${dp_index}].projectPartnerPerson[/#if][/#assign]
    <input class="id" type="hidden" name="${customName}.id" value="${(dp.projectPartnerPerson.id)!'-1'}">
    <input class="type" type="hidden" name="${customName}.type" value="${isResponsable?string('Resp','Other')}">
    [#if !isResponsable]
    <input class="element" type="hidden" name="${dp_name}[${dp_index}].id" value="${(dp.id)!-1}">
    [/#if]
    [#if template]
      
      [#-- Partner Name --]
      <div class="fullPartBlock partnerName chosen col-md-12"> 
        [@customForm.select name="" value="-1"  i18nkey="${isResponsable?string('project.deliverable.indicateResponsablePartner','Partner')}" listName="partnerPersons" keyFieldName="id"  displayFieldName="composedName"   className="${isResponsable?string('responsible','partner')} form-control input-sm " editable=editable required=isResponsable /]
      </div>
    [#else]
      [#-- Partner Name --]
      <div class="fullPartBlock partnerName chosen col-md-12"> 
      [#if editable]
        [@customForm.select name="" value="${(dp.projectPartnerPerson.id)!-1}"  label="" i18nkey="${isResponsable?string('project.deliverable.indicateResponsablePartner','project.deliverable.partner')}" listName="partnerPersons" keyFieldName="id"  displayFieldName="composedName"     className="${isResponsable?string('responsible','partner')} form-control input-sm " editable=editable required=isResponsable/]
      [#else]
      <label class="form-group" for="">[@customForm.text name="${isResponsable?string('project.deliverable.indicateResponsablePartner','project.deliverable.partner')}" readText=!editable/] :</label>
      <div class="personRead-content"><span class="glyphicon glyphicon-user" ></span><span>${(dp.projectPartnerPerson.composedName)?html}</span></div>
      [/#if]
      </div>
    [/#if] 
  </div> 
  <div class="clearfix"></div>
[/#macro] 