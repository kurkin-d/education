<html>
<head>
	<title>Tag page</title>
</head>
<body onload="onPageLoad('${tag.getTagName()}')">
	<h1>Tag Page</h1>
	<script>
		console.log("getSvgDocument id="+"svg"+'${tag.getTagName()}');
		
		function onPageLoad(tagName)
		{
			alert(tagName);
		}
		
	</script>
	
	<p>${tag!=null ? tag.getTagName() : "empty_val"}</p>
	<p>${tag!=null ? tag.getTagValue() : "empty_val"}</p>
</body>
</html>