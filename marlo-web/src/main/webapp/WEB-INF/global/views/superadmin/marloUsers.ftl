[#ftl]
[#assign title = "MARLO Users" /]
[#assign currentSectionString = "${actionName?replace('/','-')}" /]
[#assign pageLibs = ["select2", "datatables.net", "datatables.net-bs"] /]
[#assign customJS = [ "${baseUrl}/global/js/superadmin/marloUsers.js" ] /]
[#assign customCSS = [ "${baseUrl}/global/css/superadmin/superadmin.css" ] /]
[#assign currentSection = "superadmin" /]
[#assign currentStage = "users" /]

[#assign breadCrumb = [
  {"label":"superadmin", "nameSpace":"", "action":"marloBoard"},
  {"label":"marloUsers", "nameSpace":"", "action":""}
]/]

 
[#include "/WEB-INF/crp/pages/header.ftl" /]
<hr />

<div class="container">
  [#include "/WEB-INF/global/pages/breadcrumb.ftl" /]
</div>
[#include "/WEB-INF/global/pages/generalMessages.ftl" /]

<section class="marlo-content">
  <div class="container"> 
    <div class="row">
      <div class="col-md-3">
        [#include "/WEB-INF/global/views/superadmin/menu-superadmin.ftl" /]
      </div>
      <div class="col-md-9">
        
        [#-- Success info message --]
        <p class="success-info" style=""></p>
        
        [#-- Add New User Button --]
        <br /><a href="#" id="addNewUser" class="btn btn-danger pull-right"> <span class="glyphicon glyphicon-plus"></span>  Add a new user</a>
        
        [#--Users tables --]
        <h4 class="sectionTitle">MARLO Users</h4>
        <div class="borderBox">
          <table id="marloUsersTable" class="display table table-striped table-hover" width="100%">
            <thead>
              <tr>
                  <th>ID</th>
                  <th>Name</th>
                  <th>Username</th>
                  <th>Email</th>
                  <th>Active</th>
                  <th>AutoSave</th>
                  <th>Last Login</th>
              </tr>
            </thead> 
          </table>
        </div>
        
        <!-- Modal -->
        <div class="modal fade" id="updateCreateUser" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
          <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
              [@s.form action=actionName enctype="multipart/form-data" ]
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Edit Users</h4>
              </div>
              <div class="modal-body">
                [#-- Loading --]
                <div class="loading" style="display:none"></div>
                
                [#-- Hidden inputs --]
                <input type="hidden" class="isNewUser" name="isNewUser" value=""/>
                <input type="hidden" class="userId" name="user.id" />
                
                [#-- Messages Display --]
                <p class="warning-info" style="display:none"></p>
                <p class="error-info" style="display:none"></p>
                
                <div class="form-group row">
                  [#-- User Information --]
                  <div class="col-md-9">
                    <h4 class="sectionTitle">User Information</h4>
                    <div class="form-group">
                      [@customForm.input name="user.email" i18nkey="Email" value="" className="userEmail" type="text"  required=true readOnly=true editable=true /]
                      <hr />
                    </div>
                    <div class="form-group row">
                      <div class="col-md-6 ">
                        [@customForm.input name="user.username" i18nkey="Username" value="" className="userUsername" type="text"   editable=true /]
                      </div>
                      <div class="col-md-6">
                        [@customForm.input name="user.password" i18nkey="Password" value="" className="userPassword" placeholder="Change password" type="password"  required=false  editable=true /]
                      </div>
                      <div class="col-md-6 ">
                        [@customForm.input name="user.firstName" i18nkey="First name" value="" className="userFirstName" type="text"  required=true  editable=true /]
                      </div>
                      <div class="col-md-6">
                        [@customForm.input name="user.lastName" i18nkey="Last name" value="" className="userLastName" type="text"  required=true  editable=true /]
                      </div>
                    </div>
                  </div>
                  
                  [#-- User Settings --]
                  <div class="col-md-3">
                    <h4 class="sectionTitle">User Settings</h4>
                    <div class="form-group">[@radioComponent id="is_CGIAR_user" label="CGIAR user" name="user.cgiarUser" /]<hr /></div>
                    <div class="form-group">[@radioComponent id="is_active_User" label="Is active" name="user.active" /]<hr /></div>
                    <div class="form-group">[@radioComponent id="is_autosave_active" label="Autosave" name="user.autoSave" /]</div>
                  </div>
                  
                </div>
                
                [#-- CRP List --]
                <h4 class="sectionTitle">CRPs, Centers and Platforms</h4>
                <table class="display table table-striped table-bordered  table-hover">
                  <thead>
                    <tr>
                      <th class="col-md-1">CRP</th>
                      <th class="col-md-1">Admin</th>
                      <th>Roles & responsibilities</th>
                      <th class="col-md-1">Remove</th>
                    </tr>
                  </thead>
                  <tbody class="crpList">
                  </tbody>
                </table>
                [#-- CRP Select --]
                <div class="form-group">
                  [@customForm.select name="" label="" i18nkey="Select from the list below and grant access as Guest" listName="crps" keyFieldName="id" displayFieldName="composedName" className="crpSelect" /]
                </div>
                
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                [@s.submit type="button" name="save" cssClass="btn btn-primary saveUser"]<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span> [@s.text name="form.buttons.save" /][/@s.submit]
              </div>
              [/@s.form]
            </div>
          </div>
        </div>
        
      </div>
    </div>
  </div>
</section>


[#-- CRP Item Template --]
<table style="display:none">
  [@crpItem element={} index=-1 name="user.crpUser"  isTemplate=true /]
</table>

[#include "/WEB-INF/crp/pages/footer.ftl" /]

[#macro crpItem element index name  isTemplate=false]
  [#local customName = "${name}[${index}]" /]
  <tr id="crp-${isTemplate?string('template',(element.id)!)}" class="crpItem" >
    [#-- CRP Title --]
    <td> 
      <input type="hidden" class="crpUserId" name="${customName}.id" value=""/>
      <input type="hidden" class="crpUserCrpId" name="${customName}.crp.id" value="" />
      <span title="" class="crpTitle"></span>
      
    </td>
    [#-- Is Admin --]
    <td class="text-center ">
      <input type="checkbox" class="crpUserAdmin" name="${customName}.admin" value="true"/>
    </td>
    [#-- Roles --]
    <td>
      <div id="roles" class="roles"> 
        <ul class="rolesList">
          <li><i>Guest</i></li>
        </ul>
      </div>
    </td>
    [#-- Remove --]
    <td class="text-center ">
      <span class="glyphicon glyphicon-remove removeCrp" aria-hidden="true" ></span>
    </td>
  </tr>
[/#macro]

[#macro radioComponent id label="" name="" value=false]
<div id="${id}">
  <label for="">${label}:</label><br />
  <div class="radioFlat radio-inline">
    <input id="yes-${(name)!}" type="radio" name="${name}" value="true" [#if value]checked[/#if] />
    <label for="yes-${(name)!}" class="radio-label radio-label-yes"> Yes</label>
  </div>
  <div class="radioFlat radio-inline">
    <input id="no-${(name)!}" type="radio" name="${name}" value="false"  [#if !value]checked[/#if]/>
    <label for="no-${(name)!}" class="radio-label radio-label-no"> No</label>
  </div>
</div>
[/#macro]
