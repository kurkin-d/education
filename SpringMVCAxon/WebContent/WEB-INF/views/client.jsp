<html>

<head>
	<title>Client</title>
</head>
<body>
<h1>Client</h1>
<script>
	alert("������");
	if("WebSocket" in window) {
		alert("url ="+"ws://localhost:8080/SpringWebSocketProto/tags?tag="+encodeURIComponent("tag#1 0")+"&tag=fufufutag");	
		var ws = new WebSocket("ws://localhost:8080/SpringMVCAxon/ws/tags?tag="+encodeURIComponent("tag#1 0"));
		ws.onopen = function() {
			alert("�������");
		};
		ws.onmessage = function(evt) {
			try {
				alert("������ ��� " + evt.data);
				console.log("������ ��� " + evt.data);
			} catch (err) {
				console.log("name: " + err.name + "\nmessage: "
						+ err.message + "\nstack: " + err.stack);
			}
		};
		ws.onclose = function() {
					console.log("��");
		};
	} else
		alert("������� �� �����");
</script>			
</html>