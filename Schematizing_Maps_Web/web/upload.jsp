<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    </head>
    <body>
<form enctype="multipart/form-data" action="upload.jsp" method="post" onsubmit="return validate();">
  Source Map
  <input NAME="fileToUpload1" id="fileToUpload1" TYPE="file" onchange="check_file()" /> <br/>
   Kml file   
  <input NAME="fileToUpload2" id="fileToUpload2" TYPE="file" onchange="check_fileKML()" /> <br/> 
  Text File 
  <input NAME="fileToUpload3" id="fileToUpload3" TYPE="file" onchange="check_fileTxt()" /> <br/>
 <input TYPE="submit" VALUE="Next" />
</form>
    </body>
</html>
<script>

	function validate()
	{
		var val1 = document.getElementById('fileToUpload1').value;
		var val2 = document.getElementById('fileToUpload2').value;
		var val3 = document.getElementById('fileToUpload3').value;
		
		if(val1=="" && val2 == "" && val3=="")
		{
			alert("select at least one of them!");
			return false;
		}
		else
			return true;
	}

	function check_file(){

		document.getElementById('fileToUpload2').value='';
		document.getElementById('fileToUpload3').value='';

        str=document.getElementById('fileToUpload1').value.toUpperCase();
        suffix=".JPG";
		suffix1=".JPEG";
        suffix2=".PNG";
        if(!(str.indexOf(suffix, str.length - suffix.length) !== -1 || str.indexOf(suffix1, str.length - suffix1.length) !== -1 ||
                       str.indexOf(suffix2, str.length - suffix2.length) !== -1)){
        alert('File type not allowed,\nAllowed file: *.jpg,*.jpeg,*.png');
            document.getElementById('fileToUpload1').value='';
        }
    }
	
	function  check_fileKML(){
		document.getElementById('fileToUpload1').value='';
		document.getElementById('fileToUpload3').value='';
		
		str=document.getElementById('fileToUpload2').value.toUpperCase();
        suffix=".KML";
        if(!(str.indexOf(suffix, str.length - suffix.length) !== -1 )){
        alert('File type not allowed,\nAllowed file: *.kml');
            document.getElementById('fileToUpload2').value='';
        }
    }
	
	
	function  check_fileTxt(){

		document.getElementById('fileToUpload1').value='';
		document.getElementById('fileToUpload2').value='';
		
		str=document.getElementById('fileToUpload3').value.toUpperCase();
        suffix=".TXT";
        if(!(str.indexOf(suffix, str.length - suffix.length) !== -1 )){
        alert('File type not allowed,\nAllowed file: *.txt');
            document.getElementById('fileToUpload3').value='';
        }
    }
	
</script>