<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory"%>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService"%>

<%
	BlobstoreService blobstoreService = BlobstoreServiceFactory
			.getBlobstoreService();
%>

<html>

<head>
<meta charset="UTF-8">
<title>GeekQuest Creat Character</title>
<meta name="description" content="">
<meta name="author" content="">
<meta name="keywords" content="">

<link href="bootstrap.css" type="text/css" rel="stylesheet">
</head>

<body>
	<h3>Please create your Character</h3>
	<br>
	<br>

	<table>
		<tbody>
			<form action="<%=blobstoreService.createUploadUrl("/character")%>" method="post" enctype="multipart/form-data">
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
				<tr>
					<td>Name:</td>
					<td><input type="text" name="foo"></td>
				</tr>
				<tr>
					<td>File:</td>
					<td><input type="file" name="myFile"></td>
				</tr>
				<tr height="100">
					<td><input type="submit" value="Submit"></td>
				</tr>
			</form>

			<tr height="100">
				<td>CreateUploadURL: <%=blobstoreService.createUploadUrl("/upload")%></td>
				<td>CreateUploadURL: <%=blobstoreService.createUploadUrl("/character")%></td>
			</tr>

		</tbody>
	</table>

</body>
</html>