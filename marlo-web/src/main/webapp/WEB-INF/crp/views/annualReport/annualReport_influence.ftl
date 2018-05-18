[#ftl]
[#assign title = "Annual Report" /]
[#assign currentSectionString = "annualReport-${actionName?replace('/','-')}-${powbSynthesisID}" /]
[#assign currentSection = "synthesis" /]
[#assign currentStage = actionName?split('/')[1]/]
[#assign pageLibs = [ ] /]
[#assign customJS = [ ] /]
[#assign customCSS = ["${baseUrlMedia}/css/annualReport/annualReportGlobal.css"] /]

[#assign breadCrumb = [
  {"label":"${currentSection}", "nameSpace":"", "action":""},
  {"label":"annualReport", "nameSpace":"annualReport", "action":"${crpSession}/crpProgress"},
  {"label":"${currentStage}", "nameSpace":"annualReport", "action":"${crpSession}/{currentStage}"}
]/]

[#import "/WEB-INF/global/macros/utils.ftl" as utilities /]
[#include "/WEB-INF/global/pages/header.ftl" /]
[#include "/WEB-INF/global/pages/main-menu.ftl" /]

[#-- Helptext --]
[@utilities.helpBox name="annualReport.${currentStage}.help" /]
    
<section class="container">
  [#-- Program (Flagships and PMU) --]
  [#include "/WEB-INF/crp/views/annualReport/submenu-annualReport.ftl" /]
  
  <div class="row">
    [#-- POWB Menu --]
    <div class="col-md-3">
      [#include "/WEB-INF/crp/views/annualReport/menu-annualReport.ftl" /]
    </div> 
    <div class="col-md-9">
      [#-- Section Messages --]
      [#include "/WEB-INF/crp/views/annualReport/messages-annualReport.ftl" /]
      
      [@s.form action=actionName method="POST" enctype="multipart/form-data" cssClass=""]
      
        [#assign customName= "annualReport.${currentStage}" /]
        [#assign customLabel= "annualReport.${currentStage}" /]
        
        [#-- Title --]
        <h3 class="headTitle">[@s.text name="${customLabel}.title" /]</h3>
        <div class="borderBox">
        
          [#-- Data --]
          <div class="form-group margin-panel">
            [@customForm.textArea name="${customName}.data" i18nkey="${customLabel}.data" help="${customLabel}.data.help" paramText="${(actualPhase.year)!}" className="" helpIcon=false required=true editable=editable /]
          </div>
          
          [#-- Comments/Analysis --]
          <div class="form-group margin-panel">
            [@customForm.textArea name="${customName}.comments" i18nkey="${customLabel}.comments" help="${customLabel}.comments.help" paramText="${(actualPhase.year)-1!}" className="" helpIcon=false required=true editable=editable && PMU /]
          </div>
          
          [#-- Outcomes/Impacts involved in policy/investments --]
          <div class="form-group margin-panel">
            <h4 class="subTitle headTitle">[@s.text name="${customLabel}.table.title" /]</h4>
            
            <hr />
            [@tableOutcomesMacro list=[{},{},{},{}] /]
          </div>
          
          [#-- Data --]
          <div class="form-group margin-panel">
            [@customForm.textArea name="${customName}.data.2" i18nkey="${customLabel}.data.2" help="${customLabel}.data.2.help" paramText="${(actualPhase.year)!}" className="" helpIcon=false required=true editable=editable /]
          </div>
          
          [#-- Comments/Analysis --]
          <div class="form-group margin-panel">
            [@customForm.textArea name="${customName}.comments.2" i18nkey="${customLabel}.comments.2" help="${customLabel}.comments.2.help" className="" helpIcon=false required=true editable=editable && PMU /]
          </div>
        
        </div>
        [#-- Section Buttons & hidden inputs--]
        [#if PMU]
          [#include "/WEB-INF/crp/views/annualReport/buttons-annualReport.ftl" /]
        [/#if]
      [/@s.form] 
    </div> 
  </div> 
</section>
[#include "/WEB-INF/global/pages/footer.ftl"]

[#macro tableOutcomesMacro list ]
  <table class="annual-report-table table-border">
    <thead>
      <tr class="subHeader">
        <th id="tb-id" width="11%">[@s.text name="${customLabel}.table.id" /]</th>
        <th id="tb-title" width="15%">[@s.text name="${customLabel}.table.studiesTitle" /]</th>
        <th id="tb-type" width="20%">[@s.text name="${customLabel}.table.policy" /]</th>
        <th id="tb-organization-type" width="20%">[@s.text name="${customLabel}.table.implementingType" /]</th>
        <th id="tb-stage" width="15%">[@s.text name="${customLabel}.table.stage" /]</th>
        <th id="tb-geoScope" width="19%">[@s.text name="${customLabel}.table.geoScope" /]</th>
      </tr>
    </thead>
    <tbody>
    [#-- Loading --]
    [#if list?has_content]
      [#list list as item]
        <tr>
          [#-- OICS ID --]
          <td class="tb-id text-center">
            ${item.id!}
          </td>
          [#-- Title --]
          <td class="">
          [#if item.crp?has_content]
            ${item.title}
          [#else]
            <i style="opacity:0.5">[@s.text name="global.prefilledWhenAvailable"/]</i>
          [/#if]
          </td>
          [#-- Policy/Investment Type --]
          <td class="">
          [#if item.type?has_content]
            ${item.type}
          [#else]
            <i style="opacity:0.5">[@s.text name="global.prefilledWhenAvailable"/]</i>
          [/#if]
          </td>
          [#-- Implementing Organization Type --]
          <td class="text-center">
          [#if item.type?has_content]
            ${item.type}
          [#else]
            <i style="opacity:0.5">[@s.text name="global.prefilledWhenAvailable"/]</i>
          [/#if]
          </td>
          [#-- Stage --]
          <td class="text-center">
          [#if item.stage?has_content]
            ${item.stage}
          [#else]
            <i style="opacity:0.5">[@s.text name="global.prefilledWhenAvailable"/]</i>
          [/#if]
          </td>
          [#-- Geographic Scope --]
          <td class="text-center">
          [#if item.scope?has_content]
            ${item.scope}
          [#else]
            <i style="opacity:0.5">[@s.text name="global.prefilledWhenAvailable"/]</i>
          [/#if]
          </td>
        </tr>
      [/#list]
    [#else]
      <tr>
        <td class="text-center" colspan="6">
          <i style="opacity:0.5">[@s.text name="global.prefilledWhenAvailable"/]</i>
        </td>
      </tr>
    [/#if]
    </tbody>
  </table>
[/#macro]