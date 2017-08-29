var crpList = [];
$(document).ready(init);

function init() {
  
  /* Declaring Events */
  attachEvents();
  /*
   * $('form select').select2({ width: "100%" });
   */
  

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
  var $marloUsersTable = $('#marloUsersTable');

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
      $('#myModal').on('shown.bs.modal', function () {
        // Model open
      })
      $('#myModal').modal();
    })
  });
  
  /**
   * Add New User
   */
  $('#addNewUser').on('click', function(){
    enableFields(false);
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
    
    $('#myModal').modal();
  });

}

function attachEvents() {

  $(".button-save").on("click", checkAllFields);

  $(".crpSelect").on("change", function() {
    var option = $(this).find("option:selected");
    addCrp(option);
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
        // Existing user
        enableFields(true);
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
  $(".userId").val(user.id);
  $(".userFirstName").val(user.name);
  $(".userLastName").val(user.lastName);
  $(".userEmail").val(user.email);
  $(".userUsername").val(user.username);
  $(".userPassword").val("");
  // Configuration
  $('#is_CGIAR_user input[type="radio"][value="'+user.cgiar+'"]').prop('checked',true);
  $('#is_active_User input[type="radio"][value="'+user.active+'"]').prop('checked',true);
  $('#is_autosave_active input[type="radio"][value="'+user.autosave+'"]').prop('checked',true);

}

function enableFields(state) {
  // User data
  $(".userEmail").attr("readonly", state);
  // Configuration
  $(".cgiarUser").attr("disabled", state);
  $(".crpSelect").attr("disabled", state);

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
    rolesList.empty();
    // Roles
    if(jQuery.isEmptyObject(e.role)){
      rolesList.append("<span><i>Guest</i></span>");
    }else{
      $.each(e.role, function(iRole,eRole) {
        var infoList = "<br><ul>";
        $.each(eRole.roleInfo, function(index,element) {
          infoList = infoList + "<li>" + element + "</li>";
        });
        infoList = infoList + "</ul>";
        // Roles info
        rolesList.append("<span> <strong>" + eRole.role +"</strong>  "+ infoList + "</span>");
      });
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
  var span = "<span><i>Guest</i></span>";
  rolesList.append(span);
  list.append(item);
  item.show("slow");
  // Selected option disabled
  $crpsSelect.val("-1");
  $crpsSelect.find("option[value='" + $(option).val() + "']").prop('disabled', true); // .remove();
  $crpsSelect.trigger('select2:change');
  updateCrpIndex();
}

function validateEmail(email) {
  var re =
      /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
  return re.test(email);
}

function checkAllFields(e) {
  var count = 0;
  // If has at least one CRP selected
  if($(".crpList").find(".crpItem").length > 0) {
    count++;
  }
  if($(".isNewUser").val() == "true") {
    if($(".userFirstName").val().length != 0) {
      count++;
    }
    if($(".userLastName").val().trim() != "") {
      count++;
    }
    if(count < 3) {
      e.preventDefault();
      var notyOptions = jQuery.extend({}, notyDefaultOptions);
      notyOptions.text = 'Please complete the fields to create the user guest';
      noty(notyOptions);
    } else {
      $(".button-save").trigger('submit');
    }
  } else {
    if(count < 1) {
      e.preventDefault();
      var notyOptions = jQuery.extend({}, notyDefaultOptions);
      notyOptions.text = 'Please complete the fields to create the user guest';
      noty(notyOptions);
    } else {
      $(".button-save").trigger('submit');
    }
  }

}

function updateCrpIndex() {
  $(".crpList").find('.crpItem').each(function(i,e) {
    // Set crp indexes
    $(e).setNameIndexes(1, i);
  });
}
