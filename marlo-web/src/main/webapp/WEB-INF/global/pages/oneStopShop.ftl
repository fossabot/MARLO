[#ftl]
[#assign title = "One Stop Shop" /]
[#assign globalLibs = ["jquery", "noty"] /]
[#assign customJS = ["${baseUrl}/global/js/oneStopShop.js"] /]
[#assign customCSS = ["${baseUrl}/global/css/oneStopShop.css"] /]
[#assign currentSection = "home" /]
[#assign currentCycleSection = "oneStopShop" /]
[#assign currentStage = "oneStopShop" /]

[#include "/WEB-INF/${(headerPath)!'crp'}/pages/header.ftl" /]
[#include "/WEB-INF/${(headerPath)!'crp'}/pages/main-menu.ftl" /]

<section class="container contentForm">
  <div class="fullContent">
    <div class="col-md-offset-1 col-md-10">
     

      <h1 class="contentTitle">One Stop Shop</h1>
      
      <div class="form-group">
        
      </div>
      
      
    </div>
  </div>
</section>

[#include "/WEB-INF/${(headerPath)!'crp'}/pages/footer.ftl"]