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
        
          [#-- Please describe any successes and points to improve in future, providing numbers where possible --]
          <div class="form-group margin-panel">
            [@customForm.textArea name="${customName}.successes" i18nkey="${customLabel}.successes" help="${customLabel}.successes.help" className="" helpIcon=false required=true editable=editable && PMU /]
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