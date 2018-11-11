package com.csye6225.fall2018.project.jw.cloudProject.resources;

import java.util.List;

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

import com.csye6225.fall2018.project.jw.cloudProject.datamodel.Announcement;
import com.csye6225.fall2018.project.jw.cloudProject.service.AnnouncementService;

@Path("announcements")
public class AnnouncementResource {

    AnnouncementService annoService = new AnnouncementService();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Announcement> showAll(){
        return annoService.getAllAnnouncements(); 
    }
    
    
    
    @GET
    @Path("/allannouncements")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Announcement> getAllAnnouncements(){
        return annoService.getAllAnnouncements(); 
    }
   
    
    @GET
    @Path("/{AnnouncementId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Announcement getAnnouncement(@PathParam("AnnouncementId") String annoId) {
        return annoService.getAnnouncement(annoId); 
    }
    
    @DELETE
    @Path("/{AnnouncementId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Announcement deleteAnnouncement(@PathParam("AnnouncementId") String annoId) {
        return annoService.deleteAnnouncement(annoId);
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Announcement addAnnouncement(Announcement anno) {
            return annoService.addAnnouncement(anno);
    }
    
    @PUT
    @Path("/{AnnouncementId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Announcement updateAnnouncement(@PathParam("AnnouncementId") String annoId,
            Announcement anno) {
        return annoService.updateAnnouncementInformation(annoId, anno);
    }
    
}
