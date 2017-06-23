<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>企业招聘</title>
    <link href="${request.getContextPath()}/css/lanrenzhijia.css" type="text/css" rel="stylesheet" />
    <link href="${request.getContextPath()}/css/button.css" type="text/css" rel="stylesheet" />
    <link href="${request.getContextPath()}/css/daohang.css" type="text/css" rel="stylesheet" />
    <script language="javascript">
        function getBg(num){
            for(var id = 0;id<=8;id++)
            {
                if(id==num)
                {
                    document.getElementById("mynav"+id).className="current";
                }
                else
                {
                    document.getElementById("mynav"+id).className="";
                }
            }
        }
    </script>
</head>
<body>



<div id="cxslide_fade" class="slide_fade">

    <div id="nav_out">
        <div id="nav_in">
            <div id="nav">
                <ul>
                    <li><a href="#" onclick="javascript:getBg(0)" id="mynav0" class="current"><span>创赢网</span></a></li>
                    <div class="clear"></div>
                </ul>
            </div>
        </div>
    </div>

    <div class="box">
        <ul class="list">
            <li style="position: absolute; top: 0px; left: 0px; display: ;">
                <img src="${request.getContextPath()}/picture/59252e4366a47.jpg" style="width: 100%; height: 100%;">
                <div class="txt">
                    <h3 style="color:#fff;">如果您正在找工作，点击“我要应聘”编辑好个人信息之后，我们的工作人员稍后会主动电话联系您，与您预约面试时间！请您保持手机畅通！平台是免费为大家提供的，不会收取任何费用！</h3>
                    <p style="margin-top: 20px;"><a href="/index.php?g=Job&amp;m=Index&amp;a=article&amp;id=7" class="button orange bigrounded">我要应聘</a></p>
                </div>
            </li>
            <li style="position: absolute; top: 0px; left: 0px; display: none;">
                <img src="${request.getContextPath()}/picture/5936324b58921.jpg" style="width: 100%; height: 100%;">
                <div class="txt">
                    <h3 style="color:#fff;">如果您正在找工作，点击“我要应聘”编辑好个人信息之后，我们的工作人员稍后会主动电话联系您，与您预约面试时间！请您保持手机畅通！平台是免费为大家提供的，不会收取任何费用！</h3>
                    <p style="margin-top: 20px;"><a href="http://www.创赢.cn/index.php?g=Job&amp;m=Index&amp;a=article&amp;id=12" class="button orange bigrounded">我要应聘</a></p>
                </div>
            </li>
            <li style="position: absolute; top: 0px; left: 0px; display: none;">
                <img src="${request.getContextPath()}/picture/5923ab5b7e4a5.jpg" style="width: 100%; height: 100%;">
                <div class="txt">
                    <h3 style="color:#fff;">如果您正在找工作，点击“我要应聘”编辑好个人信息之后，我们的工作人员稍后会主动电话联系您，与您预约面试时间！请您保持手机畅通！平台是免费为大家提供的，不会收取任何费用！</h3>
                    <p style="margin-top: 20px;"><a href="http://www.创赢.cn/index.php?g=Job&amp;m=Index&amp;a=article&amp;id=11" class="button orange bigrounded">我要应聘</a></p>
                </div>
            </li>
            <li style="position: absolute; top: 0px; left: 0px; display: none;">
                <img src="${request.getContextPath()}/picture/5926417868498.jpg" style="width: 100%; height: 100%;">
                <div class="txt">
                    <h3 style="color:#fff;">如果您正在找工作，点击“我要应聘”编辑好个人信息之后，我们的工作人员稍后会主动电话联系您，与您预约面试时间！请您保持手机畅通！平台是免费为大家提供的，不会收取任何费用！</h3>
                    <p style="margin-top: 20px;"><a href="http://www.创赢.cn/index.php?g=Job&amp;m=Index&amp;a=article&amp;id=13" class="button orange bigrounded">我要应聘</a></p>
                </div>
        	</li>
         </ul>
        </div>
        <ul class="btn clearfix">
            <li class="">
                <a href="/index.php?g=Job&amp;m=Index&amp;a=article&amp;id=7">
                    <img src="${request.getContextPath()}/picture/59252e4366a47.jpg" width="150" height="42">
                    <h3>ofo小黄车</h3>
                </a>
            </li>
            <li class="">
                <a href="http://www.创赢.cn/index.php?g=Job&amp;m=Index&amp;a=article&amp;id=12">
                    <img src="${request.getContextPath()}/picture/5936324b58921.jpg" width="150" height="42">
                    <h3>诺兰特</h3>
                </a>
            </li>
            <li class="">
                <a href="http://www.创赢.cn/index.php?g=Job&amp;m=Index&amp;a=article&amp;id=11">
                    <img src="${request.getContextPath()}/picture/5923ab5b7e4a5.jpg" width="150" height="42">
                    <h3>京东商城</h3>
                </a>
            </li>
            <li class="">
                <a href="http://www.创赢.cn/index.php?g=Job&amp;m=Index&amp;a=article&amp;id=13">
                    <img src="${request.getContextPath()}/picture/5926417868498.jpg" width="150" height="42">
                    <h3>京东方</h3>
                </a>
            </li>        
         </ul>
    </div>
    <script src="${request.getContextPath()}/js/jquery.min.js"></script>
    <script src="${request.getContextPath()}/js/jquery.cxslide.js"></script>
    <script>
    $('#cxslide_fade').cxSlide({
      events: 'mouseover',
      type: 'fade',
      speed: 300
    });
        //$("#slide_fade").cxSlide({events:"mouseover",type:"fade",speed:300});
    </script>
    </body>
    </html>