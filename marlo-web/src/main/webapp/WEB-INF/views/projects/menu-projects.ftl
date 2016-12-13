[#ftl]
[#if !((project.projectEditLeader)!false)]
  [#assign menus= [
    { 'title': 'General Information',
      'items': [
      { 'slug': 'description',  'name': 'projects.menu.description',  'action': 'description',  'active': true  },
      { 'slug': 'partners',  'name': 'projects.menu.partners',  'action': 'partners',  'active': true  },
      { 'slug': 'budgetByPartners',  'name': 'Budget',  'action': 'budgetByPartners',  'active': true  }
      ]
    }
    
  ]/]
[#else]
  [#assign menus= [
    { 'title': 'General Information',
      'items': [
      { 'slug': 'description',  'name': 'projects.menu.description',  'action': 'description',  'active': true  },
      { 'slug': 'partners',  'name': 'projects.menu.partners',  'action': 'partners',  'active': true  },
      { 'slug': 'locations',  'name': 'projects.menu.locations',  'action': 'locations',  'active': true  }
      ]
    },
    { 'title': 'Outcomes',
      'items': [
      { 'slug': 'contributionsCrpList',  'name': 'projects.menu.contributionsCrpList',  'action': 'contributionsCrpList',  'active': true  },
      { 'slug': 'otherContributions',  'name': 'projects.menu.otherContributions',  'action': 'otherContributions',  'active': true, 'show': reportingActive  },
      { 'slug': 'caseStudies',  'name': 'Outcome Case Studies',  'action': 'caseStudies',  'active': true, 'show': reportingActive }
      ]
    },
    { 'title': 'Outputs',
      'items': [
      { 'slug': 'deliverableList',  'name': 'projects.menu.deliverables',  'action': 'deliverableList',  'active': true  },
      { 'slug': 'highlights',  'name': 'Project Highlights',  'action': 'highlights',  'active': true ,'show': reportingActive }
      ]
    },
    { 'title': 'Activities',
      'items': [
      { 'slug': 'activities',  'name': 'projects.menu.activities',  'action': 'activities',  'active': true  }
      ]
    },
    { 'title': 'Budget',
      'items': [
      { 'slug': 'budgetByPartners',  'name': 'projects.menu.budgetByPartners',  'action': 'budgetByPartners',  'active': true  },
      { 'slug': 'budgetByCoAs',  'name': 'projects.menu.budgetByCoAs',  'action': 'budgetByCoAs', 'show': action.canEditBudgetByCoAs(project.id), 'active': true  },
      { 'slug': 'leverages',  'name': 'Leverages',  'action': 'leverages',  'active': true, 'show': reportingActive }
      ]
    }
    
  ]/]
[/#if]


[#attempt]
  [#assign submission = (action.isProjectSubmitted(projectID))! /]
  [#assign canSubmit = (action.hasPersmissionSubmit(projectID))!false /]
  [#assign completed = (action.isCompleteProject(projectID))!false /]
[#recover]
  [#assign submission = false /]
  [#assign canSubmit = false /]
  [#assign completed = false /]
[/#attempt]


[#assign sectionsForChecking = [] /]

[#-- Menu--]
<nav id="secondaryMenu" class="">
  <p>Project Menu [#-- <br /><small>([@s.text name="project.type.${(project.type?lower_case)!'none'}" /] ${project.cofinancing?string('Co-Funded','')})</small>--]</p> 
  <ul>
    [#list menus as menu]
    <li>
      <ul><p class="menuTitle">${menu.title}</p>
        [#list menu.items as item]
          [#attempt]
            [#assign submitStatus = (action.getProjectSectionStatus(item.action, projectID))!false /]
          [#recover]
            [#assign submitStatus = false /]
          [/#attempt]
          [#if (item.show)!true ]
            <li id="menu-${item.action}" class="[#if item.slug == currentStage]currentSection[/#if] [#if canEdit]${submitStatus?string('submitted','toSubmit')}[/#if] ${(item.active)?string('enabled','disabled')}">
              <a href="[@s.url action="${crpSession}/${item.action}"][@s.param name="projectID" value=projectID /][@s.param name="edit" value="true"/][/@s.url]" onclick="return ${item.active?string}" class="action-${crpSession}/${item.action}">
                [@s.text name=item.name/]
              </a>
            </li>
            [#if item.active]
              [#assign sectionsForChecking = sectionsForChecking + ["${item.action}"] /]
            [/#if]
          [/#if]
        [/#list] 
      </ul>
    </li>
    [/#list]
  </ul> 
</nav>

<div class="clearfix"></div>

[#-- Sections for checking (Using by JS) --]
<span id="sectionsForChecking" style="display:none">[#list sectionsForChecking as item]${item}[#if item_has_next],[/#if][/#list]</span>

[#-- Open for Project Leaders --]
[#if canSwitchProject && (action.isCompletePreProject(project.id) || project.projectEditLeader) ]
  [#if !submission?has_content]
  <div class="grayBox text-center">
    [@customForm.yesNoInput name="project.projectEditLeader" label="project.isOpen" editable=true inverse=false cssClass="projectEditLeader text-center" /]  
  </div>
  <br />
  [/#if]
[#else]
  [#if !((project.projectEditLeader)!false)]
    <p class="text-justify note"><small>All sections need to be completed (green check mark) for the Project Leader to be able to enter the project details.</small></p>
  [/#if]
[/#if]


[#if ((project.projectEditLeader)!false)]

  [#-- Submition message --]
  [#if !submission?has_content && completed && !canSubmit]
    <p class="text-center" style="display:block">The Project can be submitted now by the project leader.</p>
  [/#if]
  
  [#-- Check button --]
  [#if canEdit && !completed && !submission?has_content]
    <p class="projectValidateButton-message text-center">Check for missing fields.<br /></p>
    <div id="validateProject-${projectID}" class="projectValidateButton ${(project.type)!''}">[@s.text name="form.buttons.check" /]</div>
    <div id="progressbar-${projectID}" class="progressbar" style="display:none"></div>
  [/#if]
  
  [#-- Submit button --]
  [#if canEdit]
    [#assign showSubmit=(canSubmit && !submission?has_content && completed)]
    <a id="submitProject-${projectID}" class="projectSubmitButton" style="display:${showSubmit?string('block','none')}" href="[@s.url action="${crpSession}/submit"][@s.param name='projectID']${projectID}[/@s.param][/@s.url]" >
      [@s.text name="form.buttons.submit" /]
    </a>
  [/#if]
  
[#else]
 

[/#if]

[#-- Discard Changes Popup --]
[#include "/WEB-INF/global/macros/discardChangesPopup.ftl"]

[#-- Project Submit JS --]
[#assign customJS = [ "${baseUrl}/js/projects/projectSubmit.js" ] + customJS  /]
