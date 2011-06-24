package com.sree.webservice.rest;

import javax.jws.WebService;
import javax.jws.WebParam;
import javax.jws.WebResult;
import org.codehaus.jra.HttpResource;
import org.codehaus.jra.Post;
import org.codehaus.jra.Get;


@WebService(targetNamespace = "http://demo.order")
public interface OrderProcess {

	// Get all the orders
	@Get
    @HttpResource(location = "/orders")
    @WebResult(name = "Orders")
    public Orders getOrders();

	// Get order data based on the specified order ID
	@Get
    @HttpResource(location = "/orders/{id}")
    public Order getOrder(@WebParam(name = "GetOrder") GetOrder getOrder);

	// Add an order
	@Post
    @HttpResource(location = "/orders")
    public void addOrder(@WebParam(name = "Order") Order order);
}
