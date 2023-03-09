package org.acme;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



@Path("/mobile")
public class MobileResource {

    // @GET
    // @Produces(MediaType.TEXT_PLAIN)
    // public String hello() {
    //     return "Hello RESTEasy";
    // }
    List<String> mobileList=new ArrayList<>();
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getMobileList(){
        return Response.ok(mobileList).build();
    }
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addNewMobile(String mobileName){
        mobileList.add(mobileName);
        return Response.ok(mobileName).build();
    }
    @PUT
    @Path("/{oldmobilename}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateMobileName(@PathParam("oldMobileName") String oldMobileName,
                                     @QueryParam("newMobileName") String newMobileName){

           mobileList=mobileList.stream().map(mobile -> {
                if(mobile.equals(oldMobileName)){
                    return newMobileName;
                }
                else{
                    return mobile;
                }
           }).collect(Collectors.toList());
           return Response.ok(mobileList).build();                          
        }

    @DELETE
    @Path("{mobiletoDelete}")
    public Response deleteMobile(@PathParam("mobiletoDelete")String mobileName){
        boolean isRemoved=mobileList.remove(mobileName);
        if(isRemoved)
            return Response.ok(mobileList).build();
        else
            return Response.status(Response.Status.BAD_REQUEST).build();
    }


}