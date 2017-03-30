<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Products</title>
</head>
<body>
	<h2>Please order some products</h2>
	<table border="1">
		<thead>
			<td>Product name</td>
			<td>Quantity</td>
			<td>Order</td>
		</thead>
		<tr>
			<td>Banana</td>
			<td>3</td>
			<td>
				<form action="products" method="post">
					<input type="hidden" value="Banana" name="product">
					<input type="hidden" value="3" name="quantity">
					<input type="submit" value="Buy">
				</form>
			</td>
		</tr>
		<tr>
			<td>Orange</td>
			<td>33</td>
			<td>
				<form action="products" method="post">
					<input type="hidden" value="Orange" name="product">
					<input type="hidden" value="33" name="quantity">
					<input type="submit" value="Buy">
				</form>
			</td>
		</tr>
		<tr>
			<td>Apple</td>
			<td>24</td>
			<td>
				<form action="products" method="post">
					<input type="hidden" value="Apple" name="product">
					<input type="hidden" value="24" name="quantity">
					<input type="submit" value="Buy">
				</form>
			</td>
		</tr>
	</table>
	<br>
	<form action="cart.jsp" method="get">
		<input type="submit" value="View yout cart">
	</form>
</body>
</html>