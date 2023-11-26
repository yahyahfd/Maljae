<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta content="IE=edge" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <#include "ftl.css">
    <title>Edit Team</title>
  </head>

  <body>
    <form action="/team/update/${token}" method="post" class="form flex">
      <div class="left">
          <h3>Current Members of the team</h3>
          <hr>
          <label class="teammail">${mail}</label>
          <ul>
            <#list emails as t>
            <li>${t}</li>
            </#list>
          </ul>
          <hr>
      </div>
      <div class="center">
        <h3>Information about ${teamName}</h3>
        <hr>
        <div class="form">
          <label for="secret">Enter your secret: </label>
          <input type="string" name="secret" id="secret" value="${secret}" spellcheck="false" required>
        </div>
        <div class="form">
          <label for="students">Enter the members of your team: </label>
          <textarea name="students" class="input" spellcheck="false">${students}</textarea>
        </div>
        <div class="form">
          <label for="preferences">Enter the preferences of your team: </label>
          <textarea name="preferences" class="input" spellcheck="false">${preferences}</textarea>
        </div>
        <hr>
        <div class="form button">
          <div id="slide"></div>
          <input type="submit" value="Update">
        </div>
      </div>
      <div class="right">
          <h3>Current preferences of the team</h3>
          <hr>
          <ol>
            <#list prefs as t>
            <li>${t}</li>
            </#list>
          </ol>
        <hr>
      </div>

    </form>
  </body>
</html>
