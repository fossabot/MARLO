$(document).ready(init);

function init() {
// Setting Currency Inputs
  $('.currencyInput').currencyInput();
  date("form #fundingSource\\.startDate", "form #fundingSource\\.endDate");
  $('form select').select2({
    width: "100%"
  });
// When select center as Funding Window----------
  var lastDonor = -1;
  $(".type").on("change", function() {
    var option = $(this).find("option:selected");
    var institutionSelect = $(".donor");
    var institutionSelected = $(".institution").find("option:selected").val();
    console.log(institutionSelected);
    // If the option selected is center
    if(option.val() == 4) {
      if(institutionSelect.val() != "-1") {
        lastDonor = institutionSelect.val();
      }
      institutionSelect.attr("disabled", "disabled");
      institutionSelect.val(institutionSelected);
      institutionSelect.trigger('change.select2');
      $(".note").hide("slow");
    } else {
      $(".note").show("slow");

      if(institutionSelect.attr("disabled") == "disabled") {
        institutionSelect.removeAttr("disabled");
        institutionSelect.val(lastDonor);
        institutionSelect.trigger('change.select2');
      }
    }
  });
}

function date(start,end) {
  var dateFormat = "yy-mm-dd";
  var from = $(start).datepicker({
      dateFormat: dateFormat,
      minDate: '2015-01-01',
      maxDate: '2030-12-31',
      showButtonPanel: true,
      changeMonth: true,
      numberOfMonths: 1,
      changeYear: true,
      onClose: function(dateText,inst) {
        var selectedDate = new Date(inst.selectedYear, inst.selectedMonth, 1)
        $(this).datepicker('setDate', selectedDate);
        if(selectedDate != "") {
          $(end).datepicker("option", "minDate", selectedDate);
        }
        getYears();
      }
  }).on("change", function() {
    getYears();
  });

  var to = $(end).datepicker({
      dateFormat: dateFormat,
      minDate: '2015-01-01',
      maxDate: '2030-12-31',
      showButtonPanel: true,
      changeMonth: true,
      numberOfMonths: 1,
      changeYear: true,
      onClose: function(dateText,inst) {
        var selectedDate = new Date(inst.selectedYear, inst.selectedMonth + 1, 0)
        $(this).datepicker('setDate', selectedDate);
        if(selectedDate != "") {
          $(start).datepicker("option", "maxDate", selectedDate);
        }
        getYears();
      }
  }).on("change", function() {
    getYears();
  });

  function getYears() {
    var endYear = (new Date(to.val())).getFullYear();
    var years = [];
    var startYear = (new Date(from.val())).getFullYear() || 2015;

    $('.budgetByYears .nav-tabs').empty();
    $('.budgetByYears .tab-content').empty();

    var index = 0;
    while(startYear <= endYear) {
      var state = '';
      if(currentCycleYear == startYear) {
        state = 'active';
      }

      var tab = '<li class="' + state + '">';
      tab += '<a href="#fundingYear-' + startYear + '" data-toggle="tab">' + startYear + '</a>';
      tab += '</li>';
      $('.budgetByYears .nav-tabs').append(tab);

      var content = '<div class="tab-pane col-md-4 ' + state + '" id="fundingYear-' + startYear + '">';
      content += '<label for="">Budget for ' + startYear + ':</label>';
      content += '<input type="hidden" name="fundingSource.budgets[' + index + '].year" value="' + startYear + '">';
      content +=
          '<input type="text" name="fundingSource.budgets[' + index
              + '].budget" class="currencyInput form-control input-sm col-md-4" />';
      content += '</div>';
      $('.budgetByYears .tab-content').append(content);

      index++;
      years.push(startYear++);
    }

    if(years.indexOf(parseInt(currentCycleYear)) == -1) {
      $('.budgetByYears .nav-tabs li').last().addClass('active');
      $('.budgetByYears .tab-content .tab-pane').last().addClass('active');
    }

    // Set currency format
    $('.currencyInput').currencyInput();

  }

  function getDate(element) {
    var date;
    try {
      date = $.datepicker.parseDate(dateFormat, element.value);
    } catch(error) {
      date = null;
    }

    return date;
  }
}