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
    <form action="/team/update/${token}" method="post" class="form">
      <p>Information about ${teamName}</p>
      <div class="form">
        <label for="email">Enter your secret: </label>
        <input type="string" name="secret" id="secret" value="${secret}" required>
      </div>
      <div class="form">
        <label for="students">Enter the members of your team: </label>
        <textarea name="students">${students}</textarea>

      </div>
      <ol>
        <#list prefs as t>
        <li>${t}</li>
        </#list>
      </ol>
      <div class="form">
        <label for="preferences">Enter the preferences of your team: </label>
        <textarea name="preferences">${preferences}</textarea>
      </div>
      <div class="form button">
        <div id="slide"></div>
        <input type="submit" value="Update">
      </div>
    </form>
  </body>
</html>
