<%@include file="include.html"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.entity.Author"%>
<%@page import="com.gcit.lms.entity.LibraryBranch"%>
<%
	AdminService service = new AdminService();
Integer totalCount = service.getBranchesCount();
	int numOfPages = 0;
	if(totalCount%10 > 0){
		numOfPages = totalCount/10 +1;
	}else{
		numOfPages = totalCount/10;
	}
	List<LibraryBranch> branches = new ArrayList<>();
	if(request.getAttribute("branches")!=null){
		branches = (List<LibraryBranch>)request.getAttribute("branches");
	}else{
		branches = service.readBranches(null, 1);
	}
%>
<%
	if (request.getAttribute("statusMessage") != null) {
		out.println(request.getAttribute("statusMessage"));
	}
%>
<div class="container">
	<h1>List of Branches in LMS&nbsp;&nbsp;&nbsp;&nbsp; Total Branches in LMS: <%=totalCount%> Branch</h1>
	<nav aria-label="Page navigation example">
		<ul class="pagination">
			<li class="page-item"><a class="page-link" href="#"
				aria-label="Previous"> <span aria-hidden="true">&laquo;</span> <span
					class="sr-only">Previous</span>
			</a></li>
			<%for(int i=1; i<=numOfPages; i++){ %>
			<li class="page-item"><a class="page-link" href="pageBranches?pageNo=<%=i%>"><%=i%></a></li>
			<%} %>
			<li class="page-item"><a class="page-link" href="#"
				aria-label="Next"> <span aria-hidden="true">&raquo;</span> <span
					class="sr-only">Next</span>
			</a></li>
		</ul>
	</nav>
	<table class="table table-striped">
		<tr>
			<th>#</th>
			<th>BranchName Name</th>
			<th>Books In the Branch</th>
			<th>Edit Borrower</th>
			<th>Delete Borrower</th>
		</tr>
		<%
		for (LibraryBranch b : branches) {
		%>
		<tr>
			<td><%=branches.indexOf(b) + 1%></td>
			<td><%=b.getBranchName()%></td>
			<td>
				 
			</td>
			<td><button type="button"
					onclick="javascript:location.href='editbranch.jsp?branchId=<%=b.getBranchId()%>'"
					class="btn btn-primary btn-sm">Edit</button></td>
			<td><button type="button"
					onclick="javascript:location.href='deleteBranch?branchId=<%=b.getBranchId()%>'"
					class="btn btn-danger btn-sm">Delete</button></td>
		</tr>
		<%
			}
		%>
	</table>
</div>