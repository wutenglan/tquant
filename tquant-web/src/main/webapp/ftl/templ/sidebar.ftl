<!-- BEGIN SIDEBAR -->
            <div class="page-sidebar-wrapper">
                <!-- BEGIN SIDEBAR -->
                <!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
                <!-- DOC: Change data-auto-speed="200" to adjust the sub menu slide up/down speed -->
                <div class="page-sidebar navbar-collapse collapse">
                    <!-- BEGIN SIDEBAR MENU -->
                    <!-- DOC: Apply "page-sidebar-menu-light" class right after "page-sidebar-menu" to enable light sidebar menu style(without borders) -->
                    <!-- DOC: Apply "page-sidebar-menu-hover-submenu" class right after "page-sidebar-menu" to enable hoverable(hover vs accordion) sub menu mode -->
                    <!-- DOC: Apply "page-sidebar-menu-closed" class right after "page-sidebar-menu" to collapse("page-sidebar-closed" class must be applied to the body element) the sidebar sub menu mode -->
                    <!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
                    <!-- DOC: Set data-keep-expand="true" to keep the submenues expanded -->
                    <!-- DOC: Set data-auto-speed="200" to adjust the sub menu slide up/down speed -->
                    <ul class="page-sidebar-menu  page-header-fixed " data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200" style="padding-top: 20px">
                        <!-- DOC: To remove the sidebar toggler from the sidebar you just need to completely remove the below "sidebar-toggler-wrapper" LI element -->
                        <li class="sidebar-toggler-wrapper hide">
                            <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
                            <div class="sidebar-toggler"> </div>
                            <!-- END SIDEBAR TOGGLER BUTTON -->
                        </li>
                        <#if sidebarSearch==true>
                        <!-- DOC: To remove the search box from the sidebar you just need to completely remove the below "sidebar-search-wrapper" LI element -->
                        <li class="sidebar-search-wrapper">
                            <!-- BEGIN RESPONSIVE QUICK SEARCH FORM -->
                            <!-- DOC: Apply "sidebar-search-bordered" class the below search form to have bordered search box -->
                            <!-- DOC: Apply "sidebar-search-bordered sidebar-search-solid" class the below search form to have bordered & solid search box -->
                            <form class="sidebar-search  " action="page_general_search_3.html" method="POST">
                                <a href="javascript:;" class="remove">
                                    <i class="icon-close"></i>
                                </a>
                                <div class="input-group">
                                    <input type="text" class="form-control" placeholder="Search...">
                                    <span class="input-group-btn">
                                        <a href="javascript:;" class="btn submit">
                                            <i class="icon-magnifier"></i>
                                        </a>
                                    </span>
                                </div>
                            </form>
                            <!-- END RESPONSIVE QUICK SEARCH FORM -->
                        </li>
                        </#if>
						<!--搜索框结束了-->
                        <li class="nav-item start  open">
                            <a href="/scorecloud/dashboard/welcome.htm" class="nav-link nav-toggle" m2="welcome">
                                <i class="icon-home"></i>
                                <span class="title">欢迎中心</span>
                                <span class="selected"></span>
                                <span class="arrow open"></span>
                            </a>                            
                        </li>
						<li class="nav-item open">
                            <a href="javascript:;" class="nav-link nav-toggle">
                                <i class="icon-docs"></i>
                                <span class="title">XXXX</span>
                                <span class="arrow open"></span>
                            </a>
                            <ul class="sub-menu" style="display: one;">
                                <li class="nav-item  ">
                                    <a href="/scorecloud/myscore/toList.htm" class="nav-link ">
                                        <i class="icon-clock"></i>
                                        <span class="title" m2="listMyScore">XXXX</span>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <!--MYEND-->
                        <li class="nav-item open">
                            <a href="javascript:;" class="nav-link nav-toggle">
                                <i class="icon-docs"></i>
                                <span class="title">XXXX</span>
                                <span class="arrow open"></span>
                            </a>
                            <ul class="sub-menu" style="display: one;">
                                <li class="nav-item  ">
                                    <a href="/scorecloud/analyze/targetScore.htm" class="nav-link ">
                                        <i class="icon-clock"></i>
                                        <span class="title" m2="targetScore">XXXX</span>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        
                        
                        <li class="nav-item open">
                            <a href="javascript:;" class="nav-link nav-toggle">
                                <i class="icon-docs"></i>
                                <span class="title">系统管理</span>
                                <span class="arrow open"></span>
                            </a>
                            <ul class="sub-menu" style="display: one;">
                                <li class="nav-item  ">
                                    <a href="/scorecloud/option/toList.htm" class="nav-link ">
                                        <i class="icon-clock"></i>
                                        <span class="title" m2="listOption">系统选项</span>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <!--MYEND-->
                    </ul>
                    <!-- END SIDEBAR MENU -->
                </div>
                <!-- END SIDEBAR -->
            </div>
            <!-- END SIDEBAR -->