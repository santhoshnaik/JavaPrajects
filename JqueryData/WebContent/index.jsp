<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.justhire.model.*"%>
     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>J query data table</title>
 <link href="media/dataTables/demo_page.css" rel="stylesheet" type="text/css" />
        <link href="media/dataTables/demo_table.css" rel="stylesheet" type="text/css" />
        <link href="media/dataTables/demo_table_jui.css" rel="stylesheet" type="text/css" />
        <link href="media/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" media="all" />
        <link href="media/themes/smoothness/jquery-ui-1.7.2.custom.css" rel="stylesheet" type="text/css" media="all" />
        
        <script src="http://code.jquery.com/jquery-latest.min.js"></script>
        
        <script src="scripts/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="script/jquery.dataTable.columnFilter.js"></script>
        <script src="script/ColumnFilterWidgets.js"></script>
        <script src="script/jquery.dataTable.rowGrouping.js"></script>
        
        
        <script type="text/javascript">
        $(document).ready(function(){
        	$("#companies").dataTable({
        		"bServerSide": true,
                "sAjaxSource": "./CompanyGsonServlet",
                "bProcessing": false,
                "sPaginationType": "full_numbers",
                "bJQueryUI": true
            });
        });
        
        
        </script>
</head>
<body>
<div id="dt_example">
    <div id="container">
        <div id="links">
        
        </div>
        <div id="demo_jui">
           <table id="companies" class="display">
             <thead>
             <tr>
		             <th>Company Name</th>
		             <th>Address</th>
		             <th>Town</th>
             </tr>            
             </thead>
           <tbody>
		          		<% for(Company c: DataRepository.GetCompanies()){ %>
		          		
						  <tr>
						    <td><%=c.getName()%></td>
						    <td><%=c.getAddress()%></td>
						    <td><%=c.getTown()%></td>
						  </tr>
						<% } %>
		            </tbody>
           </table>
        
        </div>
    
    </div>

</div>
</body>
</html>