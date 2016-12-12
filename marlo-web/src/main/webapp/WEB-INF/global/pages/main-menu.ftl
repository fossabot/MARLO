[#ftl]
[#assign mainMenu= [
  { 'slug': 'home',           'name': 'menu.home',          'namespace': '/',               'action': 'login',                                              'visible': !logged, 'active': true },
  { 'slug': 'home',           'name': 'menu.home',          'namespace': '/',               'action': 'dashboard',                      'icon': 'home',     'visible': logged, 'active': true },
  { 'slug': 'admin',          'name': 'menu.admin',         'namespace': '/admin',          'action': '${(crpSession)!}/management',    'icon': 'cog',      'visible': action.canAcessCrpAdmin(), 'active': true }
  { 'slug': 'impactPathway',  'name': 'menu.impactPathway', 'namespace': '/impactPathway',  'action': '${(crpSession)!}/outcomes',                          'visible': action.canAcessImpactPathway(), 'active': true },
  { 'slug': 'projects',       'name': 'menu.projects',      'namespace': '/projects',       'action': '${(crpSession)!}/projectsList',                      'visible': logged, 'active': true },
  { 'slug': 'fundingSources', 'name': 'menu.fundingSources',      'namespace': '/fundingSources',       'action': '${(crpSession)!}/fundingSourcesList',    'visible': action.canAcessFunding(), 'active': action.canAcessFunding() },
  { 'slug': 'summaries', 'name': 'menu.summaries',      'namespace': '/summaries',       'action': '${(crpSession)!}/summaries',    'visible': logged, 'active': action.canAcessCrpAdmin() }
]/]

[#macro mainMenuList]
  [#list mainMenu as item]
   [#if item.visible]
    <li id="${item.slug}" class="[#if currentSection?? && currentSection == item.slug ]currentSection[/#if] ${(item.active)?string('enabled','disabled')}">
      <a href="[@s.url namespace=item.namespace action='${item.action}'][#if logged][@s.param name="edit" value="true"/][/#if][/@s.url]" onclick="return ${item.active?string}" class="action-${item.action}">
        [#if item.icon?has_content]<span class="glyphicon glyphicon-${item.icon}"></span> [/#if]
        [@s.text name=item.name ][@s.param]${(crpSession?upper_case)!'CRP'}[/@s.param] [/@s.text]
      </a>
    </li>
    [/#if]
  [/#list]
[/#macro]

<nav id="mainMenu"> 
<div class="menuContent">
	<div class="container">
	  <ul class="hidden-md hidden-lg">
	   <li> <span class="glyphicon glyphicon-menu-hamburger"></span> Menu
	     <ul class="subMenu">
	       [@mainMenuList /]
	     </ul>
	   </li>
	  </ul>
	  <ul class="visible-md-block visible-lg-block">
	    [@mainMenuList /]
	  </ul>
	  
	  [#if logged?? && logged]
      <div id="userInfo">
        <a id="userLogOut" href="[@s.url action="logout" namespace="/" /]">[@s.text name="header.logout" /]</a>
        <p class="userId" style="display:none">${(currentUser.id)!}</p> 
        <p class="name">${(currentUser.firstName)!} ${(currentUser.lastName)!}</p>  
        <p class="institution">${(currentUser.email)!}</p>
        <p class="roles"> [${(roles)!}${(roles?has_content && liasons?has_content)?string(',','')}${(liasons)!}]</p>
      </div>
  	[/#if]
  </div>
</div> 
</nav> 

<div class="subMainMenu">
<div class="container">
  [#include "/WEB-INF/global/pages/breadcrumb.ftl" /]
</div>

[#include "/WEB-INF/global/pages/generalMessages.ftl" /]

</div>


