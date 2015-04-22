<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>The Time Is...</title>
<link href="bootstrap.css" type="text/css" rel="stylesheet">
</head>
<body>
	<c:choose>
		<c:when test="${user != null}">
			<p>
				Welcome, ${user.email}! You can <a href="${logoutUrl}">sign out</a>.
			</p>
		</c:when>
		<c:otherwise>
			<p>
				Welcome! <a href="${loginUrl}">Sign in or register</a> to customize.
			</p>
		</c:otherwise>
	</c:choose>
	<h3>Please create your Character</h3>

	<c:if test="${user != null}">

		<br>
		<br>

		<table>
			<tbody>
				<form action="/character">
					<tr>
						<td width="200">Name of your Character:</td>
						<td><input type="text" name="name"></td>
					</tr>
					<tr>
						<td>Charclass:</td>
						<td><select name="charclass">
								<option value="mage">Mage</option>
								<option value="hobbit">Hobbit</option>
								<option value="dwarf">Dwarf</option>
						</select></td>
					</tr>
					<tr height="100">
						<td>
							<button type="submit">Save your Character</button>
						</td>
					</tr>
				</form>
			</tbody>
		</table>

	</c:if>
</body>
</html>