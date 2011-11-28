<html>
<head>
<title>File Uploading Form</title>
</head>
<body>
<h3>File Upload:</h3>
Select a file to upload: <br />
You can choose an Image(Jpg,Png,Jpeg),Kml file(.kml) or a text file(.txt)  <br />
    <form enctype="multipart/form-data" action="newjsp.jsp" method="post" >
      <input NAME="fileToUpload1" id="fileToUpload1" TYPE="file" onchange="check_file()" /> <br/>  
<input type="submit" value="Upload File"  />
</form>
</body>
</html>


<script>
	function check_file(){
        str=document.getElementById('fileToUpload1').value.toUpperCase();
        suffix=".JPG";
	suffix1=".JPEG";
        suffix2=".PNG";
        suffix3=".TXT";
        suffix4=".KML";
        if(!(str.indexOf(suffix, str.length - suffix.length) !== -1 || str.indexOf(suffix1, str.length - suffix1.length) !== -1 ||
                       str.indexOf(suffix2, str.length - suffix2.length) !== -1 || str.indexOf(suffix3, str.length - suffix2.length) !== -1 ||
                   str.indexOf(suffix4, str.length - suffix2.length) !== -1)){
        alert('File type not allowed,\nAllowed file types are: *.jpg,*.jpeg,*.png,*.txt,*.kml');
            document.getElementById('fileToUpload1').value='';
        }
    }
	

	
</script>