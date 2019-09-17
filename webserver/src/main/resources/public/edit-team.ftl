<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta content="IE=edge" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>maljae home</title>
  </head>

  <body>
    <form action="/team/update/${token}" method="post" class="form">
      <div class="form">
	<p>Information about ${teamName}</p>
	<label for="email">Enter your secret: </label>
	<input type="string" name="secret" id="secret" value="${secret}" required>
      </div>
      <div class="form">
	<label for="students">Enter the members of your team</label>
	<textarea name="students">${students}</textarea>
      </div>
      <div class="form">
	<label for="preferences">Enter the preferences of your team</label>
	<textarea name="preferences">${preferences}</textarea>
      </div>
      <div class="form">
	<input type="submit" value="Update">
      </div>
    </form>
  </body>
</html>
