<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta content="IE=edge" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <#include "ftl.css">
    <title>Trace</title>
  </head>

  <body>
    <div class="assign">
      <h3>This is the assignement trace :</h3>
        <#list trace as t>
        <div>${t}</div>
        </#list>
    </div>
  </body>
</html>
