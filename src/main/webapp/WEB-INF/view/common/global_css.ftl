<link rel="shortcut icon" type="image/x-icon" href="${request.getContextPath()}/favicon.ico" />
<link href="${request.getContextPath()}/css/skin/global.css" rel="stylesheet" type="text/css" />
<link href="${request.getContextPath()}/css/skin/blue.css" rel="stylesheet" type="text/css" id="cssSrc" />
<script src="${request.getContextPath()}/js/jquery.min.js"></script>
<script type="text/javascript">
$(function() {
	if('${disabledButtonIdList!}' && '${disabledButtonIdList!}'!=''){
		
		//var disabledButtonIdList = eval('(' + '${disabledButtonIdList!}' + ')');
		var disabledButtonIdList = '${disabledButtonIdList!}'.substring(1,'${disabledButtonIdList!}'.length-1);
		var buttonIdArray = disabledButtonIdList.split(',');
		$.each(buttonIdArray,function(index,value){
		    $('#'+$.trim(value)).hide();
		});
	}
});
</script>
