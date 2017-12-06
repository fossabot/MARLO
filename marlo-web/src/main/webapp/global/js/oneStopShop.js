$(document).ready(init);

function init() {
  console.log('One stop Shop');

  $.ajax({
      url: 'http://ec2-52-211-37-10.eu-west-1.compute.amazonaws.com:8080/solr/ccafs-oss/select',
      data: {
          q: "*:*",
          wt: "json"
      },
      beforeSend: function() {
      },
      success: function(data) {
        console.log(data);
      },
      complete: function() {
      },
      error: function() {
        console.log("error");
      }
  });
}
