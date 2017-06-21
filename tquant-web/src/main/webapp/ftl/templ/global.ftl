<#assign metronicPath="http://xcxx.szgm.edu.cn/metronic/theme">
<#--<#assign metronicPath="/app">-->
<#if title??> 
<#else>
	<#assign title="Tquant">
</#if>
<#assign titleDesc="沪深两市实时数据分析平台，专业分析，精准选股">

<#assign company="2017-2018 © Coricher Company.">


<#assign sidebarSearch=false>  <#--搜索框开关-->

<#--header开关-->
<#assign notificationSwitch=false>  <#--通知开关-->
<#assign inboxSwitch=false>  <#--站内信开关-->
<#assign todoSwitch=false>  <#--待办项开关-->
<#assign quickSiderbarSwitch=false>  <#--弹出滑动框开关-->
<#assign userLoginSwitch=true>  <#--用户下拉列表开关-->


<#--Macro宏定义-->
<#macro framework m2="">
<#include "begin_of_page.ftl">
<#include "page_head.ftl">	
	<div style="display:none" id="m2" value="${m2}"></div>
    <body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white">
        <#include "header.ftl">
        
        
        <!-- BEGIN CONTAINER -->
        <div class="page-container">
            <#include "sidebar.ftl">
            <#--<#include "content.ftl">-->
            <div class="page-content-wrapper">
                <!-- BEGIN CONTENT BODY -->
                <div class="page-content" style="min-height:1318px">
            		<#nested>
            	</div>
                <!-- END CONTENT BODY -->
            </div>
            <#include "quick_sidebar.ftl">
        </div>
        <!-- END CONTAINER -->
        <#include "footer.ftl">
        <#include "end_of_page.ftl">
</#macro>