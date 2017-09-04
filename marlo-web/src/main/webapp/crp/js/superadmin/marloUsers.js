var crpList = [];
var $modal;
var $marloUsersTable;
$(document).ready(init);

function init() {
  
  $modal = $('#updateCreateUser');
  
  /* Declaring Events */
  attachEvents();

 
  $(".crpSelect").find("option").each(function(i,e) {
    var option = {
        "id": $(e).val(),
        "name": $(e).html()
    };
    crpList.push(option);
  });

  /**
   * Users Datatable
   */
  $marloUsersTable = $('#marloUsersTable');

  $marloUsersTable.DataTable({
      iDisplayLength: 20, // Number of rows to show on the table
      ajax: {
          "url": baseURL + '/searchUsers.do?q=',
          "dataSrc": "users"
      },
      columns: [
          {
            data: "id"
          }, {
            data: "name"
          }, {
              data: "username",
              render: function(data,type,full,meta) {
                return data || '<i>No Username<i>';
              }
          }, {
              data: "email",
              render: function(data,type,full,meta) {
                return '<span class="email">' + data + '</span>';
              }
          }, {
              data: "isActive",
              render: function(data,type,full,meta) {
                return '<div class="text-center"><img src="' + baseUrlMedia + '/images/global/checked-' + data + '.png" /></div>';
              }
          }, {
              data: "autoSaveActive",
              render: function(data,type,full,meta) {
                return '<div class="text-center"><img src="' + baseUrlMedia + '/images/global/checked-' + data + '.png" /></div>';
              }
          }, {
              data: "lastLogin",
              render: function(data,type,full,meta) {
                return data || '<i>No Date<i>';
              }
          }
      ]
  });
  $marloUsersTable.on('draw.dt', function() {
    $marloUsersTable.find('tbody tr').on("click", function() {
      var userSelectedEmail = $(this).find('span.email').text();

      // Find user details
      searchUserByEmail(userSelectedEmail);
      
      // Modal
      $modal.on('shown.bs.modal', function () {
        // Model open
      })
      $modal.modal();
    })
  });
  
  /**
   * Add New User
   */
  $('#addNewUser').on('click', function(){
    
    updateData({
      "lastName":"",
      "autosave":true,
      "cgiar":false,
      "newUser":true,
      "name":"",
      "active":true,
      "id":"",
      "email":"",
      "username":""
    });
    updateCrps({
      
    });
    
    $modal.modal();
  });

}

function attachEvents() {

  // Validate form fields are correct
  $("button.saveUser").on("click", function(e){
    saveUser(e);
  });

  // Add a new CRP to the current user
  $(".crpSelect").on("change", function() {
    var option = $(this).find("option:selected");
    addCrp(option);
  });
  
  // Remove a CRP from the current user
  $(".removeCrp").on("click", removeCRP);
  
  // Change CGIAR state
  $('#is_CGIAR_user input[type="radio"]').on('change', function(){
    enableCGIARFields(($(this).val() === "true"));
  });

}

function searchUserByEmail(email) {
  if(!validateEmail(email)) {
    return
  } 
  $.ajax({
      url: baseURL + "/searchUserByEmail.do",
      type: 'GET',
      data: {
        userEmail: email
      },
      success: function(m) {
        console.log(m);
        // Update User Info
        updateData(m.userFound);
        // Update CRPs
        updateCrps(m.crpUserFound);
        $(".crpSelect").attr("disabled", false);
         
        
      }
  });
}

function updateData(user) {
  // User data
  $(".isNewUser").val(user.newUser);
  $(".userEmail").val(user.email);
  $(".userId").val(user.id);
  $(".userFirstName").val(user.name);
  $(".userLastName").val(user.lastName);
  $(".userUsername").val(user.username);
  $(".userPassword").val("");
  // Configuration
  $('#is_CGIAR_user input[type="radio"][value="'+user.cgiar+'"]').prop('checked',true);
  $('#is_active_User input[type="radio"][value="'+user.active+'"]').prop('checked',true);
  $('#is_autosave_active input[type="radio"][value="'+user.autosave+'"]').prop('checked',true);
  // Hide/Enable fields
  enableCGIARFields(user.cgiar);
  $(".userEmail").attr("readonly", !user.newUser);

}

function enableCGIARFields(state) {
  // User data
  $(".userUsername").attr("readonly", state);
  $(".userFirstName").attr("readonly", state);
  $(".userLastName").attr("readonly", state);
  $(".userPassword").attr("readonly", state);
}

function updateCrpSelect() {
  var $select = $(".crpSelect");
  $select.empty();
  $.each(crpList, function(i,e) {
    select.addOption(e.id, e.name);
  });
}

function updateCrps(crps) {
  var $list = $(".crpList");
  var $crpsSelect = $("select.crpSelect");
  $crpsSelect.find("option").prop('disabled', false);
  $list.empty();
  $.each(crps, function(i,e) {
    var $item = $("#crp-template").clone(true).removeAttr("id");
    $item.find(".crpTitle").html((e.crpAcronym))
    $item.find(".crpUserId").val(e.crpUserId)
    $item.find(".crpUserCrpId").val(e.crpId);
    // Remove CRPs from the select component
    $crpsSelect.find("option[value='" + e.crpId + "']").prop('disabled', true);
    // Role list
    var rolesList = $item.find(".rolesList");
    var isGuest = false;
    rolesList.empty();
    // Roles
    if(jQuery.isEmptyObject(e.role)){
      rolesList.append("<li><i>Guest</i></li>");
      isGuest = true;
    }else{
      $.each(e.role, function(iRole,eRole) {
        var infoList = "<br><ul>";
        $.each(eRole.roleInfo, function(index,element) {
          infoList = infoList + "<li> <small>" + element + "</small></li>";
        });
        infoList = infoList + "</ul>";
        // Roles info
        rolesList.append("<li> <strong>" + eRole.role +"</strong>  "+ infoList + "</li>");
        // Validate Guest
        isGuest = (eRole.role == "Guest");
      });
    }
    // Remove Button disabled
    if(isGuest){
      $item.find(".removeCrp").addClass('active').removeClass('disabled');
    }else{
      $item.find(".removeCrp").addClass('disabled').removeClass('active');
    }
    $list.append($item);
    $item.show("slow");
  });
  $crpsSelect.trigger('select2:change');
  updateCrpIndex();
}

function addCrp(option) {
  var list = $(".crpList");
  var item = $("#crp-template").clone(true).removeAttr("id");
  var $crpsSelect = $(".crpSelect");
  item.find(".crpTitle").html(($(option).html()).split(":")[0]);
  item.find(".crpUserId").val("-1");
  item.find(".crpUserCrpId").val($(option).val());
  var rolesList = item.find(".rolesList");
  rolesList.empty();
  var span = "<li><i>Guest</i></li>";
  rolesList.append(span);
  list.append(item);
  item.show("slow");
  // Selected option disabled
  $crpsSelect.val("-1");
  $crpsSelect.find("option[value='" + $(option).val() + "']").prop('disabled', true); // .remove();
  $crpsSelect.trigger('select2:change');
  updateCrpIndex();
}

function removeCRP(){
  if($(this).hasClass('disabled')){
    return
  }
  $(this).parents('tr').remove();    
  updateCrpIndex();
}

function validateEmail(email) {
  var re =
      /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
  return re.test(email);
}

function saveUser(e) {
  e.preventDefault();
  var user = $('form').serializeObject();
  $modal.find('.warning-info, .error-info').empty().hide();
  
  // Validate Email
  if( (!((user["user.email"]).length > 0)) || (!(validateEmail(user["user.email"]))) ) {
    showMessage("Invalid Email");
    return
  }
  
  // Execute save
  $.ajax({
    url: baseURL + '/manageUserWithPrivileges.do',
    data: user,
    beforeSend: function() {
      $modal.find('.loading').fadeIn();
    },
    success: function(data) {
      if(data.message) {
        showMessage(data.message);
      } else {
        $modal.modal('hide');
        $marloUsersTable.DataTable().ajax.reload();
        
        // Noty Message
        var notyOptions = jQuery.extend({}, notyDefaultOptions);
        notyOptions.type = "success";
        notyOptions.text = data.successMessage;
        $('.success-info').noty(notyOptions);
        
      }
    },
    complete: function() {
      $modal.find('.loading').fadeOut();
    },
    error: function(e) {
      console.log(e);
      $modal.find('.error-info').text("Error "+e.status+": "+e.statusText).fadeIn('slow');
    }
});
  
 
   
  
}

function showMessage(message){
  $modal.find('.warning-info').text(message).fadeIn('slow');
}

function updateCrpIndex() {
  $(".crpList").find('.crpItem').each(function(i,e) {
    // Set crp indexes
    $(e).setNameIndexes(1, i);
  });
}
