package com.example.desktop;

import com.example.desktop.entities.Employee;
import com.example.desktop.entities.Order;
import com.example.desktop.entities.Recipe;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface InterfaceApi {
    @GET("api")
    Call<String> getHelloWorld();

    @GET("img/{file}")
    Call<ResponseBody> getImage(@Path("file") String filePath);

    @GET("api/employees/all")
    Call<List<Employee>> getEmployees();

    @GET("api/orders/status/1")
    Call<List<Order>> getOrdersStatus1();

    @GET("api/orders/Employee/notNull")
    Call<List<Order>> getOrdersEmployeeNotNull();

    @GET("api/recipes/all")
    Call<List<Recipe>> getRecipe();
}