[#ftl]

[#--Identifier --]
<input name="publicationID" type="hidden" value="${(publication.id)!}" />
<input type="hidden"  name="className" value="${(publication.class.name)!}"/>
<input type="hidden"  name="id" value="${(publication.id)!}"/>
<input type="hidden"  name="modifiedBy.id" value="${(currentUser.id)!}"/>
<input type="hidden"  name="actionName" value="${(actionName)!}"/>

<input id="redirectionUrl" type="hidden" name="url" value="" />

<div class="buttons">
  <div class="buttons-content">
    [#-- History Log --]
    [#if action.getListLog(publication)?has_content]
      [#import "/WEB-INF/global/macros/logHistory.ftl" as logHistory /]
      [@logHistory.logList list=action.getListLog(publication) itemName="publicationID" itemId=(publication.id)! /]
      <a href="" onclick="return false" class="form-button button-history"><span class="glyphicon glyphicon-glyphicon glyphicon-list-alt" aria-hidden="true"></span> [@s.text name="form.buttons.history" /]</a>
    [/#if]
    [#if editable]
      [#-- Back Button 
      <a href="[@s.url][@s.param name="fundingSourceID" value=fundingSourceID /][/@s.url]" class="form-button button-edit"><span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span> [@s.text name="form.buttons.back" /]</a>
      --]
      [#-- Discard Button --]
      [@s.submit type="button" cssStyle="display:none"  name="cancel" cssClass="button-cancel"]<span class="glyphicon glyphicon-trash" aria-hidden="true"></span> [@s.text name="form.buttons.discard" /] [/@s.submit]
      
      [#-- Save Button --]
      [@s.submit type="button" name="save" cssClass="button-save"]<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span> [@s.text name="form.buttons.save" /] [/@s.submit]
    [#elseif canEdit]
      [#-- Edit Button --]
      <a href="[@s.url][@s.param name="publicationID" value=(publicationID)! /][@s.param name="edit" value="true"/][/@s.url]" class="form-button button-edit"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> [@s.text name="form.buttons.edit" /]</a>
    [/#if]
  </div>
</div>