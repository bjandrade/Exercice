$(document).ready(function() {

	$("#search").on('click', function(){
		var data = $("#infoForm").serialize();
		var preProcessor = function(){
			$(".content").empty();
		}
		var postProcessor = function(data){
			$("infoForm").val("");
			$(".content").append(data);
		};

		ajaxSubmission("getInfo", data, preProcessor, postProcessor, false);
	});
});