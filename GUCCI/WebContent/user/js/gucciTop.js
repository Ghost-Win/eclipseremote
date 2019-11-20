
//input框获得焦点边框变为黑色
$("input").focus(function(){
	$(this).css("border","1px solid #666");
});

$(function(){
	var cxt = $("#cxt").val();
	var url = cxt + "/InitPhone";
	var param = {};
	$.ajax({
		url:url,
		type:"post",
		dataType:"json",
		data:param,
		success:function(result){
			if(null == result.error){
				if(result.result == 'Y' ){
					//验证通过
					$("#optionPhone").val(result.phone);
				}else{
					//验证失败
				}
			}else{
				alert(result.error);
			}
		},
		error:function(){
			alert("error:请求超时!");
		}
	});
	$("#update_sumbit").click(function(){
		var cxt = $("#cxt").val();
		var url = cxt + "/Update";
		var sex = $("input[name='sex']:checked").val();
		var name = $("#name").val();
		var country = $("#country").val();
		var birthday = $("#birthday").val();
		var params = {sex:sex,name:name,country:country,birthday:birthday};
		$.ajax({
			url:url,
			type:"post",
			dataType:"json",
			data:params,
			success:function(result){
				if(null == result.error){
					if(result.result == 'Y' ){
						//验证通过
						window.location =cxt + "/user/gucci.jsp";
					}
				}else{
					alert(result.error);
				}
			},
			error:function(){
				alert("error:请求超时!");
			}
		});
	});
});





