# Shops

Create two microservices Orders and Users that will communicate with each other using kafka or a feign client.
The User service  will be the main and contain OrdersServiceClient.

The user server will have controllers where you can create a user and get a list of users.

After creating a user, you can log in with this username and password and create Orders. 
The orders must will create inside Order service.In the orders service I can get list of orders for user
and update order status for user for example (in progress -> done).

