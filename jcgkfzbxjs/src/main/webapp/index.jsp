<%@ page contentType="text/html; charset=utf-8" %>
<%@ page isELIgnored="false" %>
<html>
<body>
<h2>Hello World!</h2>

SpringMVC文件上传
<form name="upload" action="/api/upload.do" method="post" enctype="multipart/form-data">
    <input type="file" name="upload_file"/>
    <input type="submit" value="上传">
</form>
</body>
</html>
