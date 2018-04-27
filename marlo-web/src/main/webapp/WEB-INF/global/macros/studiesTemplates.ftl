[#ftl]
[#macro studyMacro element name index=-1 template=false isOutcomeCaseStudy=true fromProject=true ]
[#local customName = "${name}"/]
  [#local customId = "caseStudy-${template?string('template',index)}" /]
  
  <div id="${customId}" class="caseStudy" style="display:${template?string('none','block')}">
    <div class="borderBox">
      [#-- Hidden ID --]
      <input type="hidden" name="${customName}.id" class="caseStudyID" value="${(element.id)!}"/>
      
      <div class="form-group row">
        <div class="col-md-6">
          [@customForm.select name="" className="setSelect2" i18nkey="study.type" listName="" keyFieldName="id"  displayFieldName="name" editable=editable /]
        </div>
        <div class="col-md-6">
          [@customForm.select name="" className="setSelect2" i18nkey="study.status" listName="" keyFieldName="id"  displayFieldName="name" editable=editable /]
        </div>
      </div>
    </div>
    <div class="borderBox">
      [#-- 1. Title (up to 20 words) --]
      <div class="form-group">
        [@customForm.input name="${customName}.title" i18nkey="study.title" help="study.title.help" className="limitWords-20" helpIcon=false required=true editable=editable /]
      </div>
      
      [#-- Flagships & Regions --]
      [#if fromProject]
      <div class="form-group row">
        [#-- Flagships --]
        [#if flagshipsList??]
          <div class="col-md-6">
            <h5>[@s.text name="study.flagships" /]:[@customForm.req required=editable/] </h5>
            <div id="" class="dottedBox">
              [#if editable]
                [#list flagshipsList as flagship]
                  [@customForm.checkBoxFlat id="flagship-${element.id}" name="${customName}.flagshipValue" label="${flagship.composedName}" value="${flagship.id}" editable=editable checked=((flagshipIds?seq_contains(element.id))!false) cssClass="checkboxInput fpInput" /]
                [/#list]
              [#else]
                <input type="hidden" name="${customName}.flagshipValue" value="${(element.flagshipValue)!}"/>
                [#if element.programs?has_content]
                  [#list element.programs as flagship]<p class="checked">${(flagship.crpProgram.composedName)!'null'}</p>[/#list]
                [/#if]
              [/#if]
            </div>
          </div>
        [/#if]
        
        [#-- Regions --] 
        [#if regionsList?has_content] 
          <div class="col-md-6"> 
            <h5>[@s.text name="study.regions" /]:[@customForm.req required=editable /]</h5>
            <div id="" class="dottedBox">
              [#if editable]
                [#list regionsList as region]
                  [@customForm.checkBoxFlat id="region-${element.id}" name="${customName}.regionsValue" label="${region.composedName}" value="${region.id}" editable=editable checked=((regionsIds?seq_contains(region.id))!false) cssClass="checkboxInput rpInput" /]
                [/#list]
              [#else] 
                <input type="hidden" name="${customName}.regionsValue" value="${(element.regionsValue)!}"/>
                [#if  element.regions?has_content]
                  [#list element.regions as region]<p class="checked">${(region.crpProgram.composedName)!'null'}</p>[/#list]
                [/#if]
              [/#if]
            </div>
          </div>
        [/#if]
      </div>
      [/#if]
      
      [#-- 2. Short outcome/impact statement (up to 80 words) --]
      [#if isOutcomeCaseStudy]
      <div class="form-group">
        [@customForm.textArea name="${customName}.outcomeStatement" i18nkey="study.outcomeStatement" help="study.outcomeStatement.help" className="limitWords-80" helpIcon=false required=true editable=editable /]
      </div>
      [/#if]
      
      [#-- 3. Link to Common Results Reporting Indicator #I3 --]
      [#if isOutcomeCaseStudy]
      <div class="form-group">
        [#-- Does this outcome reflect a contribution of the CGIAR in influencing or modifying policies/ strategies / laws/ regulations/ budgets/ investments or  curricula?  --]
        <div class="form-group">
          <label for="">[@s.text name="study.reportingIndicatorThree" /]:[@customForm.req required=editable /]
            [@customForm.helpLabel name="study.reportingIndicatorThree.help" showIcon=false editable=editable/]
          </label>
          [#assign studyIndicatorThree = "studyIndicatorThree"]
          [@customForm.radioFlat id="${studyIndicatorThree}-yes" name="${name}.${studyIndicatorThree}.value" label="Yes" value="true" checked=((element.reportingIndicatorThree.value)!false) cssClass="radioType-${studyIndicatorThree}" cssClassLabel="radio-label-yes" editable=editable /]
          [@customForm.radioFlat id="${studyIndicatorThree}-no" name="${name}.${studyIndicatorThree}.value" label="No" value="false" checked=!((element.reportingIndicatorThree.value)!true) cssClass="radioType-${studyIndicatorThree}" cssClassLabel="radio-label-no" editable=editable /]
        </div>
        
        [#-- Disaggregates for CGIAR Indicator I3  --]
        <div class="form-group simpleBox block-${studyIndicatorThree}" style="display:${((element.reportingIndicatorThree.value)!false)?string('block','none')}">
          <div class="form-group row">
            <div class="col-md-6">
              [#-- Policy/Investment Type --]
              [@customForm.select name="${customName}.reportingIndicatorThree.policyType.id" className="setSelect2" i18nkey="study.reportingIndicatorThree.policyType" listName="" keyFieldName="id"  displayFieldName="name" required=true /]
            </div>
            <div class="col-md-6">
              [#-- Amount (Only for Budget or Investment) --]
              [@customForm.input name="${customName}.reportingIndicatorThree.amount" i18nkey="study.reportingIndicatorThree.amount" help="study.reportingIndicatorThree.amount.help" className="" required=true editable=editable /]
            </div>
          </div>
          <div class="form-group row">
            <div class="col-md-6">
              [#-- Implementing Organization Type --]
              [@customForm.select name="${customName}.reportingIndicatorThree.organizationType.id" className="setSelect2" i18nkey="study.reportingIndicatorThree.organizationType" listName="" keyFieldName="id"  displayFieldName="name" required=true /]
            </div>
            <div class="col-md-6">
              [#-- Stage in Process --]
              [@customForm.select name="${customName}.reportingIndicatorThree.stage.id" className="setSelect2" i18nkey="study.reportingIndicatorThree.stage" listName="" keyFieldName="id"  displayFieldName="name"required=true  /]
            </div>
          </div>
        </div>
      </div>
      [/#if]
      
      [#-- 4.  Maturity of change reported (tick-box)  --]
      [#if isOutcomeCaseStudy]
      <div class="form-group">
        <label for="">[@s.text name="study.maturityChange" /]:[@customForm.req required=editable /]
          [@customForm.helpLabel name="study.maturityChange.help" showIcon=false editable=editable/][@customForm.helpLabel name="study.maturityChange.help2" showIcon=true editable=editable/]
        </label>
        <div class="form-group">
          <p>[@customForm.radioFlat id="maturityChange-1" name="${customName}.maturityChange" label="Stage 1" value="1" checked=false cssClass="" cssClassLabel="" editable=editable/]</p> 
          <p>[@customForm.radioFlat id="maturityChange-2" name="${customName}.maturityChange" label="Stage 2" value="2" checked=false cssClass="" cssClassLabel="" editable=editable/]</p>
          <p>[@customForm.radioFlat id="maturityChange-3" name="${customName}.maturityChange" label="Stage 3" value="3" checked=false cssClass="" cssClassLabel="" editable=editable/]</p>
        </div>
      </div>
      [/#if]
      
      [#-- 5. Links to the Strategic Results Framework  --]
      <div class="form-group">
        [#if isOutcomeCaseStudy]
          <label for="">[@s.text name="study.stratgicResultsLink" /]:[@customForm.req required=editable /]
            [@customForm.helpLabel name="study.stratgicResultsLink.help" showIcon=false editable=editable/]
          </label>
        [#else]
          <label for="">[@s.text name="study.relevantTo" /]:[@customForm.req required=editable /]
          </label> 
        [/#if]
        [#-- Sub IDOs  --]
        <div class="form-group simpleBox">
          [#-- List --]
          [#assign keyContributions = [
            { "name": "Increased capacity for innovation in partner development organizations and in poor and vulnerable communities" }
          ] /]
          [@customForm.elementsListComponent name="${customName}.subIDOs" elementType="subIDO" elementList=keyContributions label="study.stratgicResultsLink.subIDOs"  listName="" keyFieldName="id" displayFieldName="name"/]
        </div>
        
        [#-- SRF Targets  --]
        <div class="form-group simpleBox">
          [#-- List --]
          [#assign keyContributions = [
            { "name": "1.2. 30 million people, of which 50% are women, assisted to exit poverty" },
            { "name": "3.1. 5% increase in water and nutrient efficiency in agroecosystems" }
          ] /]
          [@customForm.elementsListComponent name="${customName}.srfTargets" elementType="srfTarget" elementList=keyContributions label="study.stratgicResultsLink.srfTargets"  listName="" keyFieldName="id" displayFieldName="name"/]
        </div>
        
        [#-- Comments  --]
        [#if isOutcomeCaseStudy]
        <div class="form-group simpleBox">
          [@customForm.textArea name="${customName}.stratgicResultsLink.comments" i18nkey="study.stratgicResultsLink.comments" help="study.stratgicResultsLink.comments.help" className="" editable=editable /]
        </div>
        [/#if]
      </div>
      
      [#-- 6.  Geographic scope - Countries  --]
      [#if isOutcomeCaseStudy]
      <div class="form-group">
        [#assign isRegional = true ]
        [#assign isMultiNational = true ]
        [#assign isNational = true ]
        [#assign isSubNational = true ]
        
        <label for="">[@s.text name="study.geographicScopeTopic" /]:[@customForm.req required=editable /]</label>
        <div class="form-group simpleBox">
          <div class="form-group row">
            <div class="col-md-6">
              [#-- Geographic Scope --]
              [@customForm.select name="${customName}.geographicScope.id" className="setSelect2 geographicScopeSelect" i18nkey="study.geographicScope" listName="allRepIndGeographicScope" keyFieldName="id"  displayFieldName="name" editable=editable required=true /]
            </div>
          </div>
          <div class="form-group regionalBlock" style="display:${(isRegional)?string('block','none')}">
            [#-- Regional scope --]
            [@customForm.select name="${customName}.region.id" className="setSelect2" i18nkey="study.region" listName="allRepIndRegions" keyFieldName="id"  displayFieldName="name" required=true editable=editable /]
          </div>
          <div class="form-group nationalBlock" style="display:${(isMultiNational || isNational || isSubNational)?string('block','none')}">
            [#-- Multinational, National and Subnational scope --]
            [@customForm.select name="${customName}.countriesIds" label="" i18nkey="study.countries" listName="countries" keyFieldName="isoAlpha2"  displayFieldName="name" value="${name}.countriesIds" multiple=true required=true className="countriesSelect" disabled=!editable/]
          </div>
          <div class="form-group">
            [#-- Comment box --]
            [@customForm.textArea name="${customName}.geographicScopeComments" className="limitWords-30" i18nkey="study.geographicScopeComments" help="study.geographicScopeComments.help" editable=editable required=true/]
          </div>
        </div>
      </div>
      [/#if]

      [#-- 7. Key Contributors  --]
      [#if isOutcomeCaseStudy]
      <div class="form-group">
        <label for="">[@s.text name="study.keyContributors" /]:</label>
        <div class="form-group simpleBox">
          [#-- List --]
          [#assign keyContributions = [
            { "name": "A4NH - Agriculture for Nutrition and Health" }
          ] /]
          [@customForm.elementsListComponent name="${customName}.crps" elementType="globalUnit" elementList=keyContributions label="study.keyContributors.crps"  listName="" keyFieldName="id" displayFieldName="name"/]
          
        </div>
        <div class="form-group simpleBox">
          [#-- List --]
          [#assign keyContributions = [
            { "name": "F1 - Priorities and Policies for CSA" },
            { "name": "F3 - Low emissions development" }
          ] /]
          [@customForm.elementsListComponent name="${customName}.flagships" elementType="crpProgram" elementList=keyContributions label="study.keyContributors.flagships"  listName="" keyFieldName="id" displayFieldName="name"/]
          
        </div>
        <div class="form-group simpleBox">
          [#-- List --]
          [#assign keyContributions = [
            { "name": "Ministries of Agriculture for Bangladesh" }
          ] /]
          [@customForm.elementsListComponent name="${customName}.externalPartners" elementType="institution" elementList=keyContributions label="study.keyContributors.externalPartners"  listName="" keyFieldName="id" displayFieldName="name"/]
        </div>
      </div>
      [/#if]

      [#-- 8. Elaboration of Outcome/Impact Statement  --]
      [#if isOutcomeCaseStudy]
      <div class="form-group">
        [@customForm.textArea name="${customName}.elaborationStatement" i18nkey="study.elaborationStatement" help="study.elaborationStatement.help" helpIcon=false className="limitWords-400" required=true editable=editable /]
      </div>
      [/#if]
      
      [#-- 9. References cited  --]
      [#if isOutcomeCaseStudy]
      <div class="form-group">
        <div class="form-group">
          [@customForm.textArea name="${customName}.referencesCited" i18nkey="study.referencesCited" help="study.referencesCited.help" helpIcon=false className="" required=true editable=editable /]
        </div>
        <div class="form-group" style="position:relative" listname="">
          [@customForm.fileUploadAjax 
            fileDB=(element.referencesCitedAttach.file)!{} 
            name="${customName}.referencesCitedAttach.file.id" 
            label="study.referencesCitedAttach" 
            dataUrl="${baseUrl}/UPLOAD_SERVICE_HERE.do" 
            path="${(action.getPath(caseStudyID))!}"
            isEditable=editable
            labelClass="label-min-width"
          /]
        </div>
      </div>
      [/#if]
      
      [#-- 10. Quantification (where data is available)  --]
      [#if isOutcomeCaseStudy]
      <div class="form-group">
        [@customForm.textArea name="${customName}.quantification" i18nkey="study.quantification" help="study.quantification.help" helpIcon=false className=" " required=true editable=editable /]
      </div>
      [/#if]
      
      [#-- 11. Gender, Youth, and Capacity Development  --]
      [#if isOutcomeCaseStudy]
      <div class="form-group">
        [#local ccDimensions = [
          { "name": "0 - Not Targeted" },
          { "name": "1 - Significant" },
          { "name": "2 - Principal" }
        ] /]
        <label for="">[@s.text name="study.crossCuttingRelevance" /]:
          [@customForm.helpLabel name="study.crossCuttingRelevance.help" showIcon=false editable=editable/]
        </label>
        [#-- Gender --]
        <div class="simpleBox">
          [@customForm.textArea name="${customName}.achievementsGenderRelevance" i18nkey="study.achievementsGenderRelevance" className="limitWords-100" required=true editable=editable /]
          [#list ccDimensions as cc]
            [@customForm.radioFlat id="genderRelevance-${cc_index}" name="${name}.genderRelevance" label="${cc.name}" value="1" checked=false cssClass="" cssClassLabel="" editable=editable /]
          [/#list]
        </div>
        [#-- Youth  --]
        <div class="simpleBox">
          [@customForm.textArea name="${customName}.achievementsYouthRelevance" i18nkey="study.achievementsYouthRelevance"  className="limitWords-100" required=true editable=editable /]
          [#list ccDimensions as cc]
            [@customForm.radioFlat id="youthRelevance-${cc_index}" name="${name}.youthRelevance" label="${cc.name}" value="1" checked=false cssClass="" cssClassLabel="" editable=editable /]
          [/#list]
        </div>
        [#-- CapDev   --]
        <div class="simpleBox">
          [@customForm.textArea name="${customName}.achievementsCapDevRelevance" i18nkey="study.achievementsCapDevRelevance"  className="limitWords-100" required=true editable=editable /]
          [#list ccDimensions as cc]
            [@customForm.radioFlat id="capDevRelevance-${cc_index}" name="${name}.capDevRelevance" label="${cc.name}" value="1" checked=false cssClass="" cssClassLabel="" editable=editable /]
          [/#list]
        </div> 
      </div>
      [/#if]
      
      [#-- 12. Other cross-cutting dimensions   --]
      [#if isOutcomeCaseStudy]
      <div class="form-group">
        [@customForm.textArea name="${customName}.otherCrossCutting" i18nkey="study.otherCrossCutting" help="study.otherCrossCutting.help" helpIcon=false className="limitWords-100" required=true editable=editable /]
      </div>
      [/#if]
      
      [#-- 13. Communications materials    --]
      [#if isOutcomeCaseStudy]
      <div class="form-group">
        <div class="form-group">
          [@customForm.textArea name="${customName}.communicationMaterials" i18nkey="study.communicationMaterials" help="study.communicationMaterials.help" helpIcon=false className=" " required=true editable=editable /]
        </div>
        
        <div class="form-group" style="position:relative" listname="">
          [@customForm.fileUploadAjax 
            fileDB=(element.communicationMaterialsAttach.file)!{} 
            name="${customName}.communicationMaterialsAttach.file.id" 
            label="study.communicationMaterialsAttach" 
            dataUrl="${baseUrl}/UPLOAD_SERVICE_HERE.do" 
            path="${(action.getPath(caseStudyID))!}"
            isEditable=editable
            labelClass="label-min-width"
          /]
        </div>
      </div>
      [/#if]

      [#-- 14. Contact person    --]
      [#if isOutcomeCaseStudy]
      <div class="form-group">
        [@customForm.textArea name="${customName}.contacts" i18nkey="study.contacts" help="study.contacts.help" className="" helpIcon=false required=true editable=editable /]
      </div>
      [/#if]
      
      [#-- Comments for other studies--]
      [#if !isOutcomeCaseStudy]
      <div class="form-group"> 
        [@customForm.textArea name="${customName}.comments" i18nkey="study.comments"  placeholder="" className="limitWords-100" required=true editable=isEditable /]
      </div>
      [/#if]
      
    </div>
    
    [#if fromProject]
    <h3 class="headTitle"> Share Study </h3>
    <div class="borderBox">
      [#-- Projects shared --]
      <div id="gender-levels" class="panel tertiary col-md-12">
       <div class="panel-head"><label for=""> This outcome study is done jointly with the following project(s), please select below: </label></div>
        <div id="myProjectsList" class="panel-body" listname="deliverable.genderLevels"> 
          <ul class="list">
          [#if element.projects?has_content ]
            [#list element.projects as projectLink]
              [@shareStudyMacro element=projectLink name="caseStudy.projects" index=projectLink_index template=false /]
            [/#list]
          [#else]
            <p class="emptyText"> [@s.text name="caseStudy.projects.empty" /]</p> 
          [/#if]  
          </ul>
          [#if editable ]
            [@customForm.select name="" label="" keyFieldName="id"  displayFieldName="composedName" showTitle=false i18nkey="" listName="myProjects"   required=true  className="projects" editable=editable/]
          [/#if] 
        </div>
      </div>
    </div>
    [/#if]
  </div>
[/#macro]

[#macro shareStudyMacro element name index=-1 template=false]
  [#local own = (!template) && (element.project.id == projectID) /]
  <li id="sharedProject-${template?string('template', index)}" class="sharedProject ${own?string('hide','')} clearfix" style="display:${template?string('none','block')}">
    [#-- Remove button --]
    [#if editable]<div class="removeProject removeIcon" title="Remove Project"></div>[/#if] 
    [#-- Hidden inputs --]
    <input class="id" type="hidden" name="${name}[${index}].id" value="${(element.id)!}" />
    <input class="projectId" type="hidden" name="${name}[${index}].project.id" value="${(element.project.id)!}" />
    [#-- title --]
    <span title="${(element.project.projectInfo.title)!'undefined'}" class="name">${(element.project.projectInfo.composedName)!'undefined'}</span>
    <div class="clearfix"></div>
  </li>
[/#macro]