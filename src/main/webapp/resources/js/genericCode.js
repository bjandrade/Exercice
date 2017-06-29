//===============
// Ajax
//===============

function ajaxSubmission(url, params, preProcess, postProcess, silent){
	preProcess();
	if(!silent) blockPageWithMessage();
	$.ajax({
		type: "POST",
		url: url,
		data: params
	}).done(function(data){
		postProcess(data);

		if(!silent) unblockPage();
	});
}


function blockPageWithMessage(){
	blockPage('<h1 class="wait_message">Please wait...</h1>');
}

function blockPage(msg){
	$.blockUI({
	baseZ: 10000,
	message: msg,
	overlayCSS: {
		backgroundColor: '#e6b900',
		opacity: .3
	},
	css: {
		border: 'none',
		padding: '15px',
		backgroundColor: '#00000',
		'-webkit-border-radius': '10px',
		'-moz-border-radius': '10px',
		opacity: .3,
		color: '#fff',
	}
	});
}

function unblockPage(){
	$.unblockUI();
}

function processMessages(element, data){
	var alertBox = $(element);

	$.each(data.errorList, function(key, value){
		var message = createMessage(value, "alert alert-danger alert-dismissible fade in");
		alertBox.append(message);
	});

	$.each(data.successList, function(key, value){
		var message = createMessage(value, "alert alert-success alert-dismissible fade in");
		alertBox.append(message);
	});

	$.each(data.warningList, function(key, value){
		var message = createMessage(value, "alert alert-warning alert-dismissible fade in");
		alertBox.append(message);
	});
}

function createMessage(message, type, noButton){
	var div = $("<div></div>").attr("class", type).attr("role", "alert").html(message);

	if(noButton == true)
		return div;

	var button = $("<button></button>").attr("class", "close").attr("data-dismiss", "alert").attr("aria-label", "Close");
	var span = $("<span></span>").attr("aria-hidden", "true").html("x");

	button.append(span);
	div.append(button);

	return div;
}

function initializeAutocomplete(element, url){
//	$(element).autocomplete({
//		minLength: 0
//	});
	var postProcess = function(data){
		$(element).autocomplete("option",{lookup: jQuery.parseJSON(data)});};
	ajaxSubmission(url, undefined, function(){}, postProcess, true);
}

function initializeTabs(element){
	blockPageWithMessage();

	$(element).tabs({
		activate: function(event, ui){
			blockPageWithMessage();
		},
		load: function(event, ui){
			unblockPage();
		}
	});
}

function createDialog(title){
	if($("#" + getDialogId()).length)
		return $("#" + getDialogId());

	var titleToSet = (title != undefined || title != "") ? "Caixa de confirmação" : title;
	var div = $("<div id='" + getDialogId() + "'></div>").attr("title", titleToSet);
	return div;
}

function getDialogId(){
	return "dialog";
}

function cleanMessages(box){
	$(box).empty();
}

function createAutocomplete(url, comp, compAppend){
	// initialize autocomplete with custom appendTo
	var postProcessor = function(data){
		$(compAppend).autocomplete({
			lookup: data,
			appendTo: comp,
			lookupFilter: function (suggestion, originalQuery, queryLowerCase) {
				return suggestion.value.toLowerCase().indexOf(queryLowerCase) === 0;
			}
		});
	}
	ajaxSubmission(url, undefined, function(){}, postProcessor, true);
}