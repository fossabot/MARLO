[#ftl]
[#assign title = "MARLO Publications" /]
[#assign currentSectionString = "${actionName?replace('/','-')}" /]
[#assign pageLibs = ["datatables.net", "datatables.net-bs"] /]
[#assign customJS = ["${baseUrl}/js/publications/publicationsList.js" ] /]
[#assign customCSS = ["${baseUrl}/css/global/customDataTable.css"] /]
[#assign currentSection = "publications" /]
[#assign currentStage = (filterBy)!"all" /]


[#assign breadCrumb = [
  {"label":"publicationsListList", "nameSpace":"/publications", "action":""}
]/]

[#include "/WEB-INF/global/pages/header.ftl" /]
[#include "/WEB-INF/global/pages/main-menu.ftl" /]
[#import "/WEB-INF/global/macros/forms.ftl" as customForm /]
[#import "/WEB-INF/global/macros/publicationsListTemplate.ftl" as publicationsList /]


<section class="container">
  <article class="row" id="mainInformation">
    <div class="col-md-12">
    
      [#-- Publications not directly linked to a Project (My publications) --]
      <h3 class="headTitle text-center">[@s.text name="publicationsList.yourPublications"/]</h3>
      <div class="loadingBlock"></div>
      <div style="display:none">[@publicationsList.publicationsList publications=myPublications canValidate=true canEdit=true namespace="/publications" defaultAction="${(crpSession)!}/description" /]</div>
  
      [#-- Section Buttons --]
      <div class="buttons">
        <div class="buttons-content">
          <a class="addButton" href="[@s.url action='${crpSession}/addNewPublication'/]">[@s.text name="publicationsList.addPublication" /]</a>
          <div class="clearfix"></div>
        </div>
      </div>
      
      <div class="clearfix"></div>
      <hr/>
      
      [#-- Publications List (Other publications) --]
      <h3 class="headTitle text-center">[@s.text name="publicationsList.otherPublications" /] <br /> <small>[@s.text name="publicationsList.otherPublications.help" /]</small></h3>
      <div class="loadingBlock"></div>
      <div style="display:none">[@publicationsList.publicationsList publications=allPublications canValidate=true namespace="/publications" defaultAction="${(crpSession)!}/description"/]</div>
    </div>
    
  </article>
</section>
[@customForm.confirmJustification action="deletePublication.do" namespace="/${currentSection}" title="Remove Publications" /]


[#include "/WEB-INF/global/pages/footer.ftl"]
