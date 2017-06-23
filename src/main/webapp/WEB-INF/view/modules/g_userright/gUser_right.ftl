<div style="display: table;width: 100%;border-bottom: 0px solid #34B8DF;">
    <div class="container">  
        <ul id="menu" class="tabs">  
            <li class="active"><a id="fc01" href="#tab1">权限列表</a></li> 
            <div class="edit" id="saveQX" >保存</div> 
            <!--<div class="edit" id="saveQX2">保存</div>-->  
        </ul>  
     </div>    
        <div class="tab_container">  
            <div id="tab1" class="tab_content" style="display: block; ">  
             	<ul class="ztree" id="rightTree1"></ul>
            </div>  
              
            <div id="tab4" class="tab_content" style="display: none; ">  
             	<ul class="ztree" id="rightTree2"></ul>
            </div>  
    	</div>  
</div>

<style>
	#menu li a {
    color: #666;
    cursor: pointer;
    display: block;
    height: 24px;
    line-height: 16px;
    margin-top: 6px;
    padding-top: 4px;
    text-align: center;
    width: 92px;
	}
	#menu {
    clear: both;
    margin-left: 0px;
    width: 920px;
	}
	.sel1{
	background-color: #FFFFFF!important;
	font-size:12px;
	}
	#menu li a:hover,#menu li a.sel{
		background: no-repeat -5px -47px;
		color:#333;
	}
	.BtnHideClass{
		display:none;
	}
	.BtnShowClass{
		display:block;
	}
</style> 
<script  type="text/javascript">
	$(function(){
			
			
	//Default Action  
	
    $(".tab_content").hide(); //Hide all content  
    $("ul.tabs li:first").addClass("active").show(); //Activate first tab  
    $("ul.tabs li:first").addClass("sel1").show(); //Activate first tab  
    $(".tab_content:first").show(); //Show first tab content 
    $("#saveQX2").addClass("BtnHideClass"); 
      	
    //On Click Event  
    $("ul.tabs li").click(function() {  
        $("ul.tabs li").removeClass("sel1"); //Remove any "active" class  
        $("ul.tabs li").removeClass("active"); //Remove any "active" class  
        
        $(this).addClass("sel1"); //Add "active" class to selected tab  
        $(this).addClass("active"); //Add "active" class to selected tab  
        $(".tab_content").hide(); //Hide all tab content  
        var activeTab = $(this).find("a").attr("href"); //Find the rel attribute value to identify the active tab + content
        if(activeTab == '#tab1'){
        	$("#saveQX2").removeClass("BtnShowClass");
        	$("#saveQX").removeClass("BtnHideClass");
        	$("#saveQX2").addClass("BtnHideClass");
        	$("#saveQX").addClass("BtnShowClass");
        }else{
        	$("#saveQX").removeClass("BtnShowClass")
        	$("#saveQX2").removeClass("BtnHideClass")
        	$("#saveQX").addClass("BtnHideClass");
        	$("#saveQX2").addClass("BtnShowClass");
        } 
        $(activeTab).fadeIn(); //Fade in the active content  
        return false;  
    });  
	
	
		
		//需要用到选中用户的id，回传过来
		var userId = "${userId}";
		var allRightList1 = eval("("+'${allRightList1!}'+")");
		var allRightList2 = eval("("+'${allRightList2!}'+")");
		var setting = {
				check: {
					enable: true,
					chkboxType: { "Y": "ps", "N": "ps" },
					chkStyle: "checkbox"
				},
				data: {
					simpleData: {
						enable: true
					}
				}
			};
		$.fn.zTree.init($("#rightTree1"), setting, allRightList1);
		$.fn.zTree.init($("#rightTree2"), setting, allRightList2);
		//保存用户权限
		$("#saveQX").on("click",function(){
			var treeObj = $.fn.zTree.getZTreeObj("departmentTree");
			var currNode = treeObj.getSelectedNodes();
			var userId = 0;
			if(currNode == null || currNode =='' || currNode.length ==0 || currNode[0].level!=1){
				$.jBox.info('请选择用户再保存权限！', '提示');
				return false;
			}else{
				userId = currNode[0].id;
				var checkedIds = '';
				var treeObj = $.fn.zTree.getZTreeObj("rightTree1");
				var checkedNodes = treeObj.getCheckedNodes(true);
				for(var i=0; i<checkedNodes.length; i++)  
			    {  
				 	checkedIds += checkedNodes[i].id;
				 	if(i != checkedNodes.length-1){
				 		checkedIds += ",";
				 	}
			    }  
			    $.ajax({
		    		type : "post",
		    		url : encodeURI(pageContext + "/g_userright/gUserright/saveUserRight"),
		    		dataType : "json",
		    		data : {userId:userId,projectId:1,checkedIds:checkedIds},
		    		success : function(data) {
		    			if(data.flag=='true'){
		    				jBox.tip("保存成功","success");
		    			}else{
		    				jBox.info(data.message,"提示");
		    			}
		    		},
		    		error : function(){
		    			jBox.info("保存失败","提示");
		    		}
		    	});  
			}
		});
		//保存用户权限
		$("#saveQX2").on("click",function(){
			var treeObj = $.fn.zTree.getZTreeObj("departmentTree");
			var currNode = treeObj.getSelectedNodes();
			var userId = 0;
			if(currNode == null || currNode =='' || currNode.length ==0 || currNode[0].level!=1){
				$.jBox.info('请选择用户再保存权限！', '提示');
				return false;
			}else{
				userId = currNode[0].id;
				var checkedIds = '';
				var treeObj = $.fn.zTree.getZTreeObj("rightTree2");
				var checkedNodes = treeObj.getCheckedNodes(true);
				for(var i=0; i<checkedNodes.length; i++)  
			    {  
				 	checkedIds += checkedNodes[i].id;
				 	if(i != checkedNodes.length-1){
				 		checkedIds += ",";
				 	}
			    }  
			    $.ajax({
		    		type : "post",
		    		url : encodeURI(pageContext + "/g_userright/gUserright/saveUserRight"),
		    		dataType : "json",
		    		data : {userId:userId,projectId:2,checkedIds:checkedIds},
		    		success : function(data) {
		    			if(data.flag=='true'){
		    				jBox.tip("保存成功","success");
		    			}else{
		    				jBox.info(data.message,"提示");
		    			}
		    		},
		    		error : function(){
		    			jBox.info("保存失败","提示");
		    		}
		    	});  
			}
		});
			//复制其他用户的权限
		$("#copyQX").on("click",function(){
			var treeObj = $.fn.zTree.getZTreeObj("departmentTree");
			var currNode = treeObj.getSelectedNodes();
			var userId = 0;
			if(currNode == null || currNode =='' || currNode.length ==0 || currNode[0].level!=1){
				$.jBox.info('请选择用户再保存权限！', '提示');
				return false;
			}else{
				jBox("iframe:${request.getContextPath()}/g_userright/gUserright/copyQXFromGUser" , {
				    title: "复制其他用户的权限",
				    width: 800,
					id:"editDiv",
				    height: 330,
					buttons: {}
				});
			}
		});
		
	});
	
</script>
       