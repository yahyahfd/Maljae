<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta content="IE=edge" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <#include "ftl.css">
    <title>maljae home</title>
  </head>

  <body>
  	<p style=text-size=20;>This is the assignement trace :
      <#list trace as t>
      <div>${t}</div>
      </#list>
    </p>
  </body>
</html>
